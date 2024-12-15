-- 사용자
CREATE TABLE dg_user (
    user_id IDENTITY NOT NULL PRIMARY KEY,
    account VARCHAR(50) NOT NULL UNIQUE COMMENT '사용자 계정',
    password VARCHAR(100) NOT NULL COMMENT '비밀번호',
    nickname VARCHAR(30) NOT NULL COMMENT '사용자 이름',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 영화
CREATE TABLE movie (
    movie_id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(200) NOT NULL COMMENT '영화 제목',
    director VARCHAR(50) COMMENT '감독',
    release_date DATE COMMENT '개봉일 (국내 기준)',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 극장
CREATE TABLE cinema (
    cinema_id IDENTITY NOT NULL PRIMARY KEY COMMENT '극장 아이디',
    name VARCHAR(50) NOT NULL COMMENT '극장 이름',
    location VARCHAR(200) NOT NULL COMMENT '극장 위치',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

-- 상영관
CREATE TABLE screen_room (
    cinema_id BIGINT NOT NULL COMMENT '극장 아이디',
    screen_number BIGINT NOT NULL COMMENT '상영관 번호',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cinema_id, screen_number),
    FOREIGN KEY (cinema_id) REFERENCES cinema(cinema_id)
);

-- 상영시간
CREATE TABLE running_time (
    running_time_id IDENTITY NOT NULL COMMENT '상영시간 아이디',
    movie_id BIGINT NOT NULL COMMENT '영화 아이디',
    cinema_id BIGINT NOT NULL COMMENT '극장 아이디',
    screen_number BIGINT NOT NULL COMMENT '상영관 번호',
    start_time TIMESTAMP(6) NOT NULL COMMENT '상영 시작 시간',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (running_time_id),
    FOREIGN KEY (movie_id) REFERENCES movie(movie_id),
    FOREIGN KEY (cinema_id, screen_number) REFERENCES screen_room(cinema_id, screen_number)
);

-- 예약
CREATE TABLE reservation (
    reservation_id IDENTITY NOT NULL COMMENT '예약 아이디',
    user_id BIGINT NOT NULL COMMENT '유저 아이디',
    running_time_id BIGINT NOT NULL COMMENT '상영시간 아이디',
    seet_no VARCHAR(10) NOT NULL COMMENT '좌석번호',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES dg_user(user_id),
    FOREIGN KEY (running_time_id) REFERENCES running_time(running_time_id)
);