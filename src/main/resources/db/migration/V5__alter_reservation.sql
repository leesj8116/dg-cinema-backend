------------------------------------------------------------
-- V5 : 좌석 번호 컬럼 오타 수정 (seet_no -> seat_no)
------------------------------------------------------------

ALTER TABLE reservation
ALTER COLUMN seet_no RENAME TO seat_no;