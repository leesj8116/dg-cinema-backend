/**
 * 극장 목록을 조회하여 localStorage에 저장한다
 */
const getCinemasApi = async () => {
    return await fetch('/cinema')
        .then((response) => response.json());
};

/**
 * 영화 목록을 가져온다.
 */
const getMoviesApi = async () => {
    return await fetch('/movie')
        .then((response) => response.json());
};

/**
 * 영화 제목 입력을 통해 영화를 조회한다.
 * @param title
 */
const searchRunningTimeByMovieTitleApi = async (title) => {
    const queryString = new URLSearchParams({'title': title}).toString();

    return await fetch(`/running-time?${queryString}`)
        .then((response) => response.json());
};

/**
 * '예매하기' 클릭시, 선택한 상영 시간의 잔여 좌석을 확인한다.
 */
const reservationCheckSeatApi = async (runningTimeId) => {
    const queryString = new URLSearchParams({'runningTime': runningTimeId}).toString();

    return await fetch(`/reservation/seat?${queryString}`)
        .then((response) => response.json());
}

/**
 * 예약 버튼을 클릭시 예약 등록을 요청한다.
 */
const registerReservationApi = async (userId, runningTimeId, seatNo) => {
    return await fetch(`/reservation`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json;charset=UTF-8'
        },
        body: JSON.stringify({
            userId, runningTimeId, seatNo
        })
    }).then((response) => response.json());
};

/**
 * 나의 예약 목록을 조회한다.
 */
const getMyReservation = async () => {

}
