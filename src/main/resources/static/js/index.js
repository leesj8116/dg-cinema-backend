document.addEventListener('DOMContentLoaded', async () => {
    if (localStorage.getItem('account') === null) {
        hideLoginInfoArea()
    } else {
        showLoginInfoArea()
    }

    await getMovies()
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
 * 화면에 표시된 영화 목록을 갱신한다.
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
        searchSpan.setAttribute('onclick', 'passByMovieName(this)');
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
const passByMovieName = (target) => {
    const movieName = target.parentElement.parentElement.firstElementChild.innerText
    document.getElementById('search-movie-name').value = movieName
    document.getElementById('search-movie-name-btn').focus()
}