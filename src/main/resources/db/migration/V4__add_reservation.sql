------------------------------------------------------------
-- V4 : 좌석 현황 확인을 위한 테스트 데이터 추가
------------------------------------------------------------

-- 50번째 상영 시간 데이터 추가
INSERT INTO running_time (movie_id, cinema_id, screen_number, start_time)
VALUES
    (4, 1, 1, TIMESTAMP '2024-12-31 18:00:00');

-- 50번째에 대해 예약 데이터 사전 등록
INSERT INTO reservation (user_id, running_time_id, seet_no)
VALUES
    (1, 50, 'A1'),
    (1, 50, 'B12'),
    (1, 50, 'C4'),
    (1, 50, 'G9');
