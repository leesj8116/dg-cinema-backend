------------------------------------------------------------
-- V2 : 기초 데이터 생성
------------------------------------------------------------

-- 사용자
INSERT INTO dg_user(account, password, nickname)
VALUES ('admin@dgcinema.com', 'admin_password', '운영자'),
       ('Pcloud63514@gmail.com', 'user_password1', '권태헌'),
       ('leesj8115@gmail.com', 'user_password2', '이승주');

-- 영화
INSERT INTO movie(title, director, release_date)
VALUES ('하얼빈', '우민호', DATE '2024-12-24'),
       ('무파사: 라이온 킹', '배리 젠킨스', DATE '2024-12-18'),
       ('소방관', '곽경택', DATE '2024-12-04'),
       ('블루 자이언트', '타치카와 유즈루', DATE '2023-10-18');

-- 극장
INSERT INTO cinema(name, location)
VALUES ('강남', '서울특별시 서초구 서초대로 77길 3 (서초동) 아라타워 8층'),
       ('성수', '서울특별시 성동구 왕십리로 50, (성수동 1가) 메가박스스퀘어 3층'),
       ('별내', '경기도 남양주시 별내동 두물로 19');

-- 상영관
-- 위에서 입력한 극장에 대해 1 ~ 3 까지의 상영관 추가 (도움 : chatGPT-4o)
INSERT INTO screen_room (cinema_id, screen_number)
SELECT c.cinema_id, s.screen_number
FROM cinema c
         CROSS JOIN (SELECT 1 AS screen_number
                     UNION ALL
                     SELECT 2
                     UNION ALL
                     SELECT 3) s;

-- 상영시간 (노가다로 추가)
INSERT INTO running_time (movie_id, cinema_id, screen_number, start_time)
VALUES
    -- '하얼빈' 영화
    (1, 1, 1, TIMESTAMP '2024-12-15 10:00:00'),
    (1, 1, 2, TIMESTAMP '2024-12-15 13:00:00'),
    (1, 2, 1, TIMESTAMP '2024-12-15 16:00:00'),
    (1, 3, 1, TIMESTAMP '2024-12-15 20:00:00'),

    -- '무파사: 라이온 킹' 영화
    (2, 1, 3, TIMESTAMP '2024-12-15 09:00:00'),
    (2, 2, 2, TIMESTAMP '2024-12-15 14:00:00'),
    (2, 3, 3, TIMESTAMP '2024-12-15 19:00:00'),

    -- '소방관' 영화
    (3, 1, 1, TIMESTAMP '2024-12-15 11:00:00'),
    (3, 2, 1, TIMESTAMP '2024-12-15 15:00:00'),
    (3, 3, 2, TIMESTAMP '2024-12-15 18:00:00'),

    -- '블루 자이언트' 영화
    (4, 1, 2, TIMESTAMP '2023-10-15 12:00:00'),
    (4, 2, 3, TIMESTAMP '2023-10-15 17:00:00'),
    (4, 3, 3, TIMESTAMP '2023-10-15 21:00:00');