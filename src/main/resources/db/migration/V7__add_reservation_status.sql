------------------------------------------------------------
-- V7 : 예약(Reservation) 테이블에 상태 컬럼 추가
------------------------------------------------------------

ALTER TABLE reservation
ADD COLUMN status ENUM('PENDING', 'SUCCESS', 'CANCEL', 'EXPIRED') NOT NULL DEFAULT 'PENDING' COMMENT '예약 상태';