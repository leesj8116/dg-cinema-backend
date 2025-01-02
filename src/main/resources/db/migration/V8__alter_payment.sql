------------------------------------------------------------
-- V8 : 결제 엔티티 설계 변경 (result -> success, type 추가)
------------------------------------------------------------

ALTER TABLE payment
ALTER COLUMN result RENAME TO success;

ALTER TABLE payment
ADD COLUMN type ENUM('PURCHASE', 'REFUND') NOT NULL COMMENT '구분';