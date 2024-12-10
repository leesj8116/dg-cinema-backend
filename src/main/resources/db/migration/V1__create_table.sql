CREATE TABLE dg_user (
    user_id IDENTITY NOT NULL PRIMARY KEY,
    account VARCHAR(100) NOT NULL UNIQUE COMMENT '사용자 계정',
    password VARCHAR(100) NOT NULL COMMENT '비밀번호',
    nickname VARCHAR(100) NOT NULL COMMENT '사용자 이름',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE movie (
    movie_id IDENTITY NOT NULL PRIMARY KEY,
    title VARCHAR(200) NOT NULL COMMENT '영화 제목',
    director VARCHAR(50) COMMENT '감독',
    release_date DATE COMMENT '개봉일 (국내 기준)',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE cinema (
    cinema_id IDENTITY NOT NULL PRIMARY KEY COMMENT '영화관 아이디',
    name VARCHAR(50) NOT NULL COMMENT '영화관 이름',
    location VARCHAR(200) NOT NULL COMMENT '영화관 위치',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE screen_room (
    cinema_id BIGINT NOT NULL COMMENT '영화관 아이디',
    screen_number BIGINT NOT NULL COMMENT '상영관 번호',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    PRIMARY KEY (cinema_id, screen_number),
    FOREIGN KEY (cinema_id) REFERENCES cinema(cinema_id)
);