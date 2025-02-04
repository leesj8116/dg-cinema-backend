// @TODO: 좌석 체계 점검
// 더 좋은 방법이 있을텐데......
const seatsRow = ['A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'];
const seatsCol = ['1', '2', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12'];

document.addEventListener('DOMContentLoaded', async () => {
    if (localStorage.getItem('userId') === null) {
        hideLoginInfoArea();
    } else {
        showLoginInfoArea();
    }

    await getCinemasApi().then((json) => {
        console.log('극장 정보', json);

        // 극장 정보는 재조합하여 localStorage에 저장, 사용
        const cinemas = {};
        for (let i = 0; i < json.length; i++) {
            cinemas[json[i].cinemaId] = {'name': json[i].name, 'location': json[i].location};
        }
        localStorage.setItem('cinemas', JSON.stringify(cinemas));

    }).catch((error) => {
        console.error(error);
        alert('극장 목록을 조회할 수 없습니다. 잠시 후 다시 시도해주세요.');
    });

    await changePage(0);
});

/**
 * 메인 화면에서 '예약 하기' 화면과 '예약 확인' 화면을 이동한다.
 * @param pageNo
 */
const changePage = async (pageNo = 0) => {
    const mainPage = document.getElementById('tab-main');
    const reservationPage = document.getElementById('tab-my-reservation');

    const selected = document.querySelector('.nav-list .selected');
    if (selected !== null) {
        selected.classList.remove('selected');
        selected.onclick = '';
    }

    switch (pageNo) {
        case 0:
            // 메인 화면
            mainPage.style.display = 'block';
            reservationPage.style.display = 'none';

            cleanReservationForm();

            await getMoviesApi()
                .then((json) => {
                    updateMovieTable(json);
                }).catch((error) => {
                    console.error(error);
                    alert('영화 목록을 조회할 수 없습니다. 잠시 후 다시 시도해주세요.');
                });

            await searchRunningTimeWithTitle();

            document.getElementById('nav-main').classList.add('selected');
            document.getElementById('nav-my-reservation').onclick = () => changePage(1);

            break;
        case 1:
            if (loginCheck()) {
                return;
            }

            // 예약 확인 화면
            mainPage.style.display = 'none';
            reservationPage.style.display = 'block';

            document.getElementById('nav-main').onclick = () => changePage(0);
            document.getElementById('nav-my-reservation').classList.add('selected');

            await checkMyReservation();

            break;
    }
};

/**
 * '검색' 버튼 클릭시 영화 제목으로된 상영 시간표를 검색하고 표시한다.
 */
const searchRunningTimeWithTitle = async () => {
    const title = document.getElementById('search-movie-title').value;

    await searchRunningTimeByMovieTitleApi(title)
        .then((json) => {
            console.log('상영시간 정보', json);
            uploadRunningTimetable(json);
        }).catch((error) => {
            console.error(error);
            alert('상영시간 정보를 조회할 수 없습니다. 잠시 후 다시 시도해주세요.');
        });
}


/**
 * 화면에 표시된 영화 목록을 갱신한다. (도움 - chatGPT4o)
 * @param movies
 */
const updateMovieTable = (movies) => {
    const tableBody = document.getElementById('movie-list');

    // 기존 영화 데이터 제거
    const existingRows = tableBody.querySelectorAll('tr.movie');
    existingRows.forEach(row => row.remove());

    // 새로운 영화 데이터 추가
    movies.forEach(movie => {
        const row = document.createElement('tr');
        row.className = 'movie';

        // 제목
        const titleCell = document.createElement('td');
        titleCell.textContent = movie.title;

        // 감독
        const directorCell = document.createElement('td');
        directorCell.textContent = movie.director;

        // 개봉일자
        const releaseDateCell = document.createElement('td');
        releaseDateCell.textContent = movie.releaseDate;

        // 비고
        const actionCell = document.createElement('td');
        const searchSpan = document.createElement('span');
        searchSpan.textContent = '검색';
        searchSpan.onclick = () => passByMovieTitle(movie.title);
        actionCell.appendChild(searchSpan);

        // 행 구성
        row.appendChild(titleCell);
        row.appendChild(directorCell);
        row.appendChild(releaseDateCell);
        row.appendChild(actionCell);

        // 테이블에 행 추가
        tableBody.appendChild(row);
    });
};

/**
 * 상영시간표를 갱신한다.
 * @param runningTimes
 */
const uploadRunningTimetable = (runningTimes) => {
    const tableBody = document.getElementById('running-time-body');

    // 기존 상영시간 데이터 제거
    const existingRows = tableBody.querySelectorAll('tr.running-time');
    existingRows.forEach(row => row.remove());

    // 극장 정보 조회
    const cinemas = JSON.parse(localStorage.getItem('cinemas'));

    // 새로운 상영시간 데이터 추가
    runningTimes.forEach(runningTime => {
        const date = new Date(runningTime.startTime);
        const {formattedDate, formattedTime} = dateStringSplit(runningTime.startTime);

        const row = document.createElement('tr');
        row.className = 'running-time';

        // 제목
        const titleCell = document.createElement('td');
        titleCell.textContent = runningTime.movieTitle;

        // 극장
        const cinemaCell = document.createElement('td');
        // 극장 id => 이름으로 변경하여 표시
        cinemaCell.textContent = cinemas[runningTime.cinemaId].name;


        // 상영관
        const screenRoomCell = document.createElement('td');
        screenRoomCell.textContent = `${runningTime.screenNumber}관`;

        // 상영일
        const startDateCell = document.createElement('td');
        startDateCell.textContent = formattedDate;

        // 상영시간
        const startTimeCell = document.createElement('td');
        startTimeCell.textContent = formattedTime;

        // 비고
        const actionCell = document.createElement('td');
        const searchSpan = document.createElement('span');

        searchSpan.textContent = '예매하기';

        if (date < new Date()) {
            // 이미 상영을 시작했다면 예약 이벤트 제거
            searchSpan.classList.add('past');
        } else {
            // 아직 상영 전이라면 좌석 확인으로 안내
            // @TODO: 이게 맞나 싶은 데이터 전달
            searchSpan.onclick = () => reservationCheckSeat(runningTime.runningTimeId, runningTime.movieTitle,
                formattedDate, formattedTime, runningTime.cinemaId, runningTime.screenNumber);
        }
        actionCell.appendChild(searchSpan);

        // 행 구성
        row.appendChild(titleCell);
        row.appendChild(cinemaCell);
        row.appendChild(screenRoomCell);
        row.appendChild(startDateCell);
        row.appendChild(startTimeCell);
        row.appendChild(actionCell);

        // 테이블에 행 추가
        tableBody.appendChild(row);
    });
}

/**
 * '로그인' 버튼 클릭시 로그인을 흉내낸다.
 */
const loginEvent = () => {
    const accountElement = document.getElementById('account-field');
    const passwordElement = document.getElementById('password-field');
    const accountValue = accountElement.value;
    const passwordValue = passwordElement.value;

    // 입력 값 검사
    // Email validation regex pattern   (도움 : chatGPT)
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (accountValue !== 'admin' && !emailPattern.test(accountValue)) {
        alert('계정을 올바르게 입력해주세요');
        accountElement.focus();
        return;
    }
    if (passwordValue.trim() === '') {
        alert('비밀번호를 올바르게 입력해주세요');
        passwordElement.focus();
        return;
    }

    // 전제 조건에 따라, 로그인은 구현된 것으로 가정
    localStorage.setItem('account', accountValue);

    switch (accountValue) {
        case 'admin':
            localStorage.setItem('userId', '1');
            localStorage.setItem('nickname', '운영자');
            break;
        case 'Pcloud63514@gmail.com':
            localStorage.setItem('userId', '2');
            localStorage.setItem('nickname', '권태헌');
            break;
        case 'leesj8115@gmail.com':
            localStorage.setItem('userId', '3');
            localStorage.setItem('nickname', '이승주');
            break;
        case 'user':
            localStorage.setItem('userId', '4');
            localStorage.setItem('nickname', '사용자');
            break;
        default:
            localStorage.setItem('userId', '5');
            localStorage.setItem('nickname', '몰?루는 유저');
    }
    showLoginInfoArea();

    // 로그인 처리 후 계정, 비밀번호 필드는 초기화
    accountElement.value = '';
    passwordElement.value = '';
};

/**
 * '로그아웃' 버튼 클릭시 로그아웃을 흉내낸다.
 */
const logout = () => {
    localStorage.clear();
    hideLoginInfoArea();
    // 계정 입력 란에 포커스
    document.getElementById('account-field').focus();
};

/**
 * 로그인 입력 폼 대신 사용자 정보 화면을 보여준다.
 */
const showLoginInfoArea = () => {
    document.getElementById('user-name').innerText = localStorage.getItem('nickname');
    document.getElementById('login-area').style.display = 'none';
    document.getElementById('login-info-area').style.display = 'block';
};

/**
 * 로그인 입력 폼을 보여주고, 사용자 정보 화면을 숨긴다.
 */
const hideLoginInfoArea = () => {
    document.getElementById('login-area').style.display = 'block';
    document.getElementById('login-info-area').style.display = 'none';
};

/**
 * '검색' 버튼을 클릭시, 해당 줄의 영화 제목을 검색창으로 전달한다.
 */
const passByMovieTitle = (movieName) => {
    document.getElementById('search-movie-title').value = movieName;
    document.getElementById('search-movie-title-btn').focus();
};

const reservationCheckSeat = async (runningTimeId, title, stdate, sttime, cinemaStr, screenNumber) => {
    // 극장 정보 조회
    const cinemas = JSON.parse(localStorage.getItem('cinemas'));
    const cinemaIdx = Number(cinemaStr);


    document.getElementById('reservation-task-what').innerText = title;
    document.getElementById('reservation-task-when').innerText = `${stdate} ${sttime}`;
    document.getElementById('reservation-task-where-cinema').innerText = `${cinemas[cinemaIdx].name} (${cinemas[cinemaIdx].location})`;
    document.getElementById('reservation-task-where-screen').innerText = `${screenNumber}관`;
    document.getElementById('reservation-running-time-id').value = runningTimeId;

    await reservationCheckSeatApi(runningTimeId)
        .then((json) => {
            updateReservationSeat(json);
        }).catch((error) => {
            console.error(error);
            alert('잔여 좌석 조회에 실패했습니다. 잠시 후에 다시 시도해주세요.');
        })
}

/**
 * 예약 좌석 현황을 그려준다
 * @param seats
 */
const updateReservationSeat = (seats) => {
    console.log('좌석 정보', seats)
    const tableBody = document.getElementById('reservation-seat-info-body');
    // 기존 데이터 삭제
    const existingRows = tableBody.querySelectorAll('tr');
    existingRows.forEach(row => row.remove());

    // 표 생성

    seatsRow.forEach(r => {
        const row = document.createElement('tr');

        seatsCol.forEach(c => {
            const seatNo = `${r}${c}`;

            const td = document.createElement('td');
            td.id = seatNo;
            td.innerText = seatNo;

            if (seats.indexOf(seatNo) !== -1) {
                td.classList.add('already'); // 예약된 자리는 다르게 표시
            } else {
                td.onclick = () => selectMySeat(seatNo);
            }

            row.appendChild(td);
        })

        tableBody.appendChild(row);
    });
};

/**
 * 선택한 좌석을 '선택 내용' 폼에 전달한다.
 * @param mySeat
 */
const selectMySeat = (mySeat) => {
    const selectedItem = document.querySelector('#reservation-seat-info-body .selected');

    if (selectedItem != null) {
        selectedItem.classList.remove('selected');
    }

    document.getElementById('seat-no').value = mySeat;
    document.getElementById(mySeat).classList.add('selected');
    document.getElementById('regist-reservation-btn').focus();  // 예약 버튼에 포커스
};


/**
 * 하단의 예약 관련 화면을 초기화한다.
 */
const cleanReservationForm = () => {
    // 좌석 현황 화면 초기화
    const tableBody = document.getElementById('reservation-seat-info-body');
    const existingRows = tableBody.querySelectorAll('tr');
    existingRows.forEach(row => row.remove());

    // 선택 내용 화면 초기화
    document.getElementById('reservation-task-what').innerText = '';
    document.getElementById('reservation-task-when').innerText = '';
    document.getElementById('reservation-task-where-cinema').innerText = '';
    document.getElementById('reservation-task-where-screen').innerText = '';
    document.getElementById('reservation-running-time-id').value = '';
    document.getElementById('seat-no').value = '';
};

/**
 * 예약 버튼을 클릭시 예약 API를 호출한다.
 */
const makeReservation = async () => {
    if (loginCheck())
        return;

    const userId = Number(localStorage.getItem('userId'));
    const runningTimeId = Number(document.getElementById('reservation-running-time-id').value);
    const seatNo = document.getElementById('seat-no').value;

    if (runningTimeId == 0) {
        alert('예약할 상영 시간을 선택해주세요.');
        return;
    }
    if (seatNo === '') {
        alert('예약할 좌석을 선택해주세요.');
        return;
    }

    await registerReservationApi(userId, runningTimeId, seatNo)
        .then((json) => {
            console.log('예약 API 결과', json);
            alert("예약을 생성했습니다. 5분 이내 결제를 완료해주세요.");
            changePage(1);
        }).catch((error) => {
            console.error(error);
            alert('예약 등록에 실패했습니다. 잠시 후 다시 시도해주세요.');
        });
};

const checkMyReservation = async () => {
    const userId = Number(localStorage.getItem('userId'));
    const cinemas = JSON.parse(localStorage.getItem('cinemas'));
    const reservationTable = document.getElementById('my-reservation-table');

    await getMyReservationApi(userId)
        .then((list) => {
            console.log('나의 예약 확인', list);

            // 기존 데이터 삭제
            const existingRows = reservationTable.querySelectorAll('#my-reservation-table tr:not(:first-child)');
            existingRows.forEach(row => row.remove());

            list.forEach(reservation => {
                const row = document.createElement('tr');

                const cinemaName = document.createElement('td');
                cinemaName.textContent = cinemas[Number(reservation.cinemaId)].name

                const screenRoom = document.createElement('td');
                screenRoom.textContent = `${reservation.screenNumber}관`;

                const startAt = dateStringSplit(reservation.startTime)

                const startDate = document.createElement('td');
                startDate.textContent = startAt.formattedDate;

                const startTime = document.createElement('td');
                startTime.textContent = startAt.formattedTime;

                const movieTitle = document.createElement('td');
                movieTitle.textContent = reservation.movieTitle;

                const seatNo = document.createElement('td');
                seatNo.textContent = reservation.seatNo;

                const created = document.createElement('td');

                const createdDate = dateStringSplit(reservation.createdDate);
                created.textContent = `${createdDate.formattedDate} ${createdDate.formattedTime}`;

                const status = document.createElement('td');

                switch (reservation.status) {
                    case 'PENDING':
                        status.textContent = '결제 대기';
                        break;
                    case 'SUCCESS':
                        status.textContent = '결제 완료';
                        break;
                    case 'CANCEL':
                        status.textContent = '예약 취소';
                        break;
                    default:
                        status.textContent = '운영자 문의';
                }
                ;

                const action = document.createElement('td');

                const dummy = document.createElement('span');
                dummy.classList.add('dummy')
                dummy.textContent = ' | ';

                const actionCancel = document.createElement('span');
                actionCancel.textContent = '취소';
                actionCancel.onclick = () => {
                    alert(`취소 ${reservation.reservationId}`);
                };

                const actionMain = document.createElement('span');  // 예약 상황에 따라 기능이 달라짐

                switch (reservation.status) {
                    case 'PENDING':
                        actionMain.classList.add('payment');
                        actionMain.textContent = '결제';
                        actionMain.onclick = () => runPurchase(reservation.reservationId, 10000);

                        action.appendChild(actionMain);
                        action.appendChild(dummy);
                        action.appendChild(actionCancel);

                        break;
                    case 'SUCCESS':
                        action.appendChild(actionCancel);

                        break;
                    case 'CANCEL':
                        // 필요시 추가
                        break;
                    case 'EXPIRED':
                        // 필요시 추가
                        break;
                }

                row.appendChild(cinemaName);
                row.appendChild(screenRoom);
                row.appendChild(startDate);
                row.appendChild(startTime);
                row.appendChild(movieTitle);
                row.appendChild(seatNo);
                row.appendChild(created);
                row.appendChild(status);
                row.appendChild(action);


                reservationTable.appendChild(row);
            });


        }).catch((error) => {
            console.error(error);
            alert('예약 목록 조회를 실패했습니다. 잠시 후 다시 시도해주세요.');
        });
    ;
}

const loginCheck = () => {
    if (localStorage.getItem('userId') === null) {
        alert('로그인이 필요합니다, 로그인 후 다시 시도해주세요');
        document.getElementById('account-field').focus();
        return true;
    }

    return false;
}

/**
 * 스트링 형태의 시간 값을 날짜와 시간으로 분리하여 반환한다.
 * @param strDate "YYYY-MM-DDThh:mm:ss" 형식의 스트링
 * @returns {{formattedDate: string, formattedTime: string}} "YYYY년 MM월 DD일", "mm시 ss분"
 */
const dateStringSplit = (input = '') => {
    const strDate = (input.indexOf('.') !== -1) ? input.split('.')[0] : input;

    //// 상영 시간 계산
    const date = new Date(strDate);

    const dateString = date.toLocaleString('ko-KR', {
        year: 'numeric',
        month: '2-digit',
        day: '2-digit',
        hour: '2-digit',
        minute: '2-digit',
        second: '2-digit',
        hour12: false // 24시간 형식으로
    });

    // 날짜와 시간을 분리
    const datePart = dateString.substring(0, 14);
    const timePart = dateString.substring(14);

    // 날짜 부분을 '년 월 일' 형식으로 변환
    const [year, month, day] = datePart.split('.').map(part => part.trim());
    const formattedDate = `${year}년 ${month}월 ${day}일`;

    // 시간 부분을 '시 분' 형식으로 변환
    const [hour, minute] = timePart.split(':');
    const formattedTime = `${hour}시 ${minute}분`;

    return {formattedDate, formattedTime};
}

const runPurchase = async (reservationId, amount) => {
    await registerPaymentApi(reservationId, amount)
        .then((json) => {
            console.log('결제 API 결과', json);

            if (json.code) {
                throw json.message;
            }

            alert("결제를 완료했습니다. 즐거운 시간 보내세요.");
        }).catch((error) => {
            console.error(error);
            alert('결제를 실패했습니다. ' + error.message);
        }).finally(() => {
            checkMyReservation();
        })
}