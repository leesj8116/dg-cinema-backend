document.addEventListener('DOMContentLoaded', () => {
    if (localStorage.getItem('account') === null) {
        hideLoginInfoArea()
    } else {
        showLoginInfoArea()
    }
})

const loginEvent = async () => {
    const accountElement = document.getElementById('account-field');
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
}

const logout = () => {
    localStorage.clear()
    hideLoginInfoArea()
}

const showLoginInfoArea = () => {
    document.getElementById('user-name').innerText = localStorage.getItem('nickname')
    document.getElementById('login-area').style.display = 'none'
    document.getElementById('login-info-area').style.display = 'block'
}

const hideLoginInfoArea = () => {
    document.getElementById('login-area').style.display = 'block'
    document.getElementById('login-info-area').style.display = 'none'
}