# Entity 설계

## Stage 1: 기본 기능

```mermaid
---
title: dg-cinema Stage 1 설계
---
erDiagram
    "User(사용자)" {
        Long user_id PK "사용자_엔티티"
        string account "계정"
        string password "비밀번호"
        string nickname "사용자_이름"
    }
    "Movie(영화)" {
        Long movie_id PK "영화_아이디"
        String title "영화명"
        String director "영화 감독"
        LocalDate release_date "개봉일 (국내기준)"
    }
    "Cinema(극장)" {
        Long cinema_id PK "극장_아이디"
        string name "극장명"
        string location "극장 위치"
    }

    "ScreenRoom(상영관)" {
        Long cinema_id PK, FK "극장_아이디"
        Long screen_number PK "상영관_번호"
    }

    "Reservation(예약)" {
        Long reservation_id PK "예약_아이디"
        Long user_id FK "사용자_엔티티"
        Long running_time_id FK "상영시간_아이디"
        String seet_no "좌석번호"
    }

    "RunningTime(상영시간)" {
        Long running_time_id "상영시간_아이디"
        Long movie_id FK "영화_아이디"
        UUID cinema_id FK "극장_아이디"
        Long screen_no FK "상영관_번호"
        LocalDateTime start_time "상영시작시간"
    }

    "Movie(영화)" ||--o{ "RunningTime(상영시간)": "영화는 0 ~ n 회 상영한다"
    "ScreenRoom(상영관)" ||--o{ "RunningTime(상영시간)": "상영관은 여러 상영시간을 갖는다"
    "Cinema(극장)" ||--|{ "ScreenRoom(상영관)": "극장은 여러개의 상영관을 갖는다"
    "User(사용자)" ||--o{ "Reservation(예약)": "사용자는 여러 예약을 갖는다"
    "RunningTime(상영시간)" ||--o{ "Reservation(예약)": "상영시간마다 0 ~ n개의 예약을 갖는다"

```

### 제약 사항

- 상영관 명은 번호로만 관리합니다 (1번 상영관, 2번 상영관 ...)
- 상영관 좌석은 상영관별 특성을 반영하지 않습니다. (일괄 A행 ~ H행, 1열 ~ 12열까지 있습니다. 8 * 12개의 좌석)
- 상영관 좌석은 차등가격제도를 이용하지 않습니다. (일괄 10,000원으로 설정합니다.)
- 예약 1건당 하나의 좌석에 대해 처리합니다. (한 사람이 여러장을 예약할 경우, n 건의 예약 row가 발생합니다.)
- **제일중요** : 해당 설계는 초안이며, 의견, 코드리뷰에 따라 수정할 수 있습니다.

## 기타

### H2 DB에서 schema 확인시

```sql
select TABLE_NAME, COLUMN_NAME, DATA_TYPE, CHARACTER_MAXIMUM_LENGTH as LENGTH, COLUMN_DEFAULT, REMARKS
from INFORMATION_SCHEMA.COLUMNS
where TABLE_SCHEMA = 'PUBLIC';
```
