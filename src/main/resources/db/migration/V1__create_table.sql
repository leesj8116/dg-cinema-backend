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
