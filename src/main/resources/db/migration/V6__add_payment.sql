------------------------------------------------------------
-- V6 : 결제(Payment) 테이블 생성
------------------------------------------------------------

CREATE TABLE payment (
    payment_id IDENTITY NOT NULL PRIMARY KEY COMMENT '결제 아이디',
    reservation_id BIGINT NOT NULL COMMENT '예약 아이디',
    amount INTEGER NOT NULL COMMENT '금액',
    result BOOLEAN NOT NULL DEFAULT FALSE COMMENT '결제 여부',
    created_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_date TIMESTAMP(6) NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (reservation_id) REFERENCES reservation(reservation_id)
);