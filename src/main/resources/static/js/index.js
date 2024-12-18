document.addEventListener('DOMContentLoaded', async () => {
    if (localStorage.getItem('account') === null) {
        hideLoginInfoArea()
    } else {
        showLoginInfoArea()
    }

    await getMovies()
    await searchRunningTimeByMovietitle()
})

/**
 * 영화 목록을 가져온다.
 * @returns {Promise<void>}
 */
const getMovies = async () => {
    await fetch('/movie')
        .then((response) => response.json())
        .then((json) => {
            updateMovieTable(json)
        }).catch((error) => {
            console.error(error)
        })
}

/**
 * 영화 제목 입력을 통해 영화를 조회한다.
 * @param title
 * @returns {Promise<void>}
 */
const searchRunningTimeByMovietitle = async () => {
    const title = document.getElementById('search-movie-title').value
    const queryString = new URLSearchParams({'title': title}).toString()

    await fetch(`/running-time?${queryString}`)
        .then((response) => response.json())
        .then((json) => {
            uploadRunningTimetable(json)
        }).catch((error) => {
            console.error(error)
        })
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
        searchSpan.setAttribute('onclick', `passByMovieTitle(${movie.title})`);
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
    const tableBody = document.getElementById('running-time');

    // 기존 상영시간 데이터 제거
    const existingRows = tableBody.querySelectorAll('tr.running-time');
    existingRows.forEach(row => row.remove());

    // 새로운 상영시간 데이터 추가
    runningTimes.forEach(runningTime => {
        const row = document.createElement('tr');
        row.className = 'running-time';

        // 제목
        const titleCell = document.createElement('td');
        titleCell.textContent = runningTime.movie.title;

        // 극장
        const CinemaCell = document.createElement('td');
        CinemaCell.textContent = runningTime.screenRoom.cinemaId;

        // 상영관
        const screenRoomCell = document.createElement('td');
        screenRoomCell.textContent = `${runningTime.screenRoom.screenNumber}관`;

        // 상영시간
        const startTimeCell = document.createElement('td');
        startTimeCell.textContent = runningTime.startTime;

        // 비고
        const actionCell = document.createElement('td');
        const searchSpan = document.createElement('span');
        searchSpan.textContent = '예매하기';
        searchSpan.setAttribute('onclick', 'passByMovieTitle(this)');
        actionCell.appendChild(searchSpan);

        // 행 구성
        row.appendChild(titleCell);
        row.appendChild(CinemaCell);
        row.appendChild(screenRoomCell);
        row.appendChild(startTimeCell);
        row.appendChild(actionCell);

        // 테이블에 행 추가
        tableBody.appendChild(row);
    });
}

/**
 * '로그인' 버튼 클릭시 로그인을 흉내낸다.
 */
const loginEvent = async () => {
    const accountElement = document.getElementById('account-field')
    const passwordElement = document.getElementById('password-field')
    const accountValue = accountElement.value
    const passwordValue = passwordElement.value

    // 입력 값 검사
    // Email validation regex pattern   (도움 : chatGPT)
    const emailPattern = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

    if (accountValue !== 'admin' && !emailPattern.test(accountValue)) {
        alert('계정을 올바르게 입력해주세요')
        accountElement.focus()
        return;
    }
    if (passwordValue.trim() === '') {
        alert('비밀번호를 올바르게 입력해주세요')
        passwordElement.focus()
        return;
    }

    // 전제 조건에 따라, 로그인은 구현된 것으로 가정

    localStorage.setItem('account', accountValue)

    switch (accountValue) {
        case 'admin':
            localStorage.setItem('nickname', '운영자')
            break;
        case 'user':
            localStorage.setItem('nickname', '사용자')
            break;
        case 'Pcloud63514@gmail.com':
            localStorage.setItem('nickname', '권태헌')
            break;
        case 'leesj8115@gmail.com':
            localStorage.setItem('nickname', '이승주')
            break;
        default:
            localStorage.setItem('nickname', '몰?루는 유저')
    }
    showLoginInfoArea()

    // 로그인 처리 후 계정, 비밀번호 필드는 초기화
    accountElement.value = '';
    passwordElement.value = '';
}

/**
 * '로그아웃' 버튼 클릭시 로그아웃을 흉내낸다.
 */
const logout = () => {
    localStorage.clear()
    hideLoginInfoArea()
    // 계정 입력 란에 포커스
    document.getElementById('account-field').focus()
}

/**
 * 로그인 입력 폼 대신 사용자 정보 화면을 보여준다.
 */
const showLoginInfoArea = () => {
    document.getElementById('user-name').innerText = localStorage.getItem('nickname')
    document.getElementById('login-area').style.display = 'none'
    document.getElementById('login-info-area').style.display = 'block'
}

/**
 * 로그인 입력 폼을 보여주고, 사용자 정보 화면을 숨긴다.
 */
const hideLoginInfoArea = () => {
    document.getElementById('login-area').style.display = 'block'
    document.getElementById('login-info-area').style.display = 'none'
}

/**
 * '검색' 버튼을 클릭시, 해당 줄의 영화 제목을 검색창으로 전달한다.
 */
const passByMovieTitle = (movieName) => {
    document.getElementById('search-movie-title').value = movieName
    document.getElementById('search-movie-title-btn').focus()
}