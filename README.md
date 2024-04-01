# 특강 신청 서비스 구현
[![Hits](https://hits.seeyoufarm.com/api/count/incr/badge.svg?url=https://github.com/keepbang/hanghae-architecture-java)](https://hits.seeyoufarm.com)

## API List
### 1. [특강 등록 API](#특강-등록-api) (`추가`)
### 2. [특강 목록 조회 API](#특강-목록-조회-api)
### 3. [특강 신청 API](#특강-신청-api) (`핵심`)
### 4. [특강 신청 여부 조회 API](#특강-신청-여부-조회-api)


## ERD
![img.png](images/img.png)

## Create Table SQL
```SQL
create table lecture (
    id bigint auto_increment comment '특강 아이디',
    name varchar(255) comment '특강 이름',
    max_user bigint comment '최대 수강생 수',
    start_apply_millis bigint comment '특강 신청 시작 시간',
    start_lecture_millis bigint comment '특강 시작 시간',
    primary key (id)
);

ALTER TABLE lecture COMMENT '특강 메타정보 테이블';

create table lecture_history (
    lecture_id bigint comment '특강 아이디',
    user_id bigint comment '사용자 아이디',
    apply_millis bigint comment '신청 시간',
    primary key (lecture_id, user_id)
);

ALTER TABLE lecture_history COMMENT '특강 신청 이력 테이블';

create table apply_counter (
    lecture_id bigint not null comment '특강 아이디',
    apply_count bigint comment '특강 신청 수강생 수',
    max_user bigint comment '최대 수강생 수',
    primary key (lecture_id)
);

ALTER TABLE apply_counter COMMENT '특강 신청 카운트 테이블';

```


---

## 요구사항 분석

### 특강 등록 API

- 특강 정보를 저장합니다.
- 특강 저보를 저장할때 특강 신청 카운트 테이블도 같이 저장합니다.

```
POST http://{SERVER_URL}/lectures
```
- **Request body**

| 파라미터             | 타입        | 필수여부 | 설명       | 중복 여부 |
|------------------|-----------|------|----------|-------|
| name             | string    | Y    | 특강 이름    | 중복 불가 |
| maxUser          | integer   | Y    | 최대 수강생 수 |       |
| startApplyMillis | timestamp | Y    | 신청 시작 시간 |       |
| startLectureDate | date      | Y    | 특강 시작 날짜 |       |

- **Response body**

| 파라미터             | 타입        | 필수여부 | 설명       |
|------------------|-----------|------|----------|
| id               | integer   | Y    | 특강 아이디   |
| name             | string    | Y    | 특강 이름    |
| maxUser          | integer   | Y    | 최대 수강생 수 |
| startApplyMillis | timestamp | Y    | 신청 시작 시간 |
| startLectureDate | date      | Y    | 특강 시작 날짜 |


- 실패 케이스
  - `request` 필수값이 `null`이거나 0일 경우
  - `timestamp`나 날짜 형식이 맞지 않거나 과거 시간인 경우
  - 최대 수강생 수가 0이하인 경우

---

### 특강 목록 조회 API

- 사용자들이 각 특강 목록을 조회할 수 있다.

```
GET http://{SERVER_URL}/lectures
```

- **Response body**

| 파라미터                | 타입        | 필수여부 | 설명       |
|---------------------|-----------|------|----------|
| [].id               | integer   | Y    | 특강 아이디   |
| [].name             | string    | Y    | 특강 이름    |
| [].maxUser          | integer   | Y    | 최대 수강생 수 |
| [].startApplyMillis | timestamp | Y    | 신청 시작 시간 |
| [].startLectureDate | date      | Y    | 특강 시작 날짜 |

---

### 특강 신청 API

- 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
- 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
- 특강은 `4월 20일 토요일 1시` 에 열리며, 선착순 30명만 신청 가능합니다.
- 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.

```
POST http://{SERVER_URL}/lectures/{lectureId}/users/{userId}
```
- **Path variable**

| 파라미터                 | 타입      | 필수여부 | 설명      |
|----------------------|---------|------|---------|
| lectureId            | integer | Y    | 특강 아이디  |
| userId               | integer | Y    | 사용자 아이디 |

- **Response body**

| 파라미터        | 타입        | 필수여부 | 설명      |
|-------------|-----------|------|---------|
| userId      | string    | Y    | 사용자 아이디 |
| lectureId   | integer   | Y    | 특강 아이디  |
| applyMillis | timestamp | Y    | 신청 시간   |


- **프로세스**
  - `validate` 특강 인원제한에 걸렸는지 확인 
    - ~~history 및 특강 조회~~
    - 특강 신청 카운트 테이블 조회(lock 걸림)
  - `validate` 이미 신청한 내역이 존재하는지 확인
    - 방안 1) DB 유니크 제약조건 설정
    - 방안 2) history 테이블 확인
      - 두가지 방법 모두 사용
  - 특강 신청 기록에 저장


- **실패 케이스**
  - 신청자가 N명이 초과 될 경우
  - 이미 신청했을 경우
  - 선택한 특강가 없을 경우


- 동시성 처리
  - 동시에 여러명에 사용자가 신청을 할경우에도 설정한 인원만큼만 신청 가능해야 한다.
  - 비관적 락을 사용하여 행에 일기와 쓰기에 락 적용 
    ```java
    public interface ApplyCounterJpaRepository extends JpaRepository<ApplyCounter, Long> {
      // 비관적 락 적용
      @Lock(LockModeType.PESSIMISTIC_WRITE)
      Optional<ApplyCounter> findAndLockByLectureId(Long id);

    }
    ```
    
- 동시성 테스트
  - 3개의 특강에(최대 30명) 120명이 신청할 경우 30명은 실패해야합니다.
  - 사용자 5명이 5번씩 동시에 요청을 보내도 저장되는건 5명이여야 합니다.

---

### 특강 신청 여부 조회 API

- 특정 userId 로 특강 신청 완료 여부를 조회하는 API 를 작성합니다.
- 특강 신청에 성공한 사용자는 성공했음을, 특강 등록자 명단에 없는 사용자는 실패했음을 반환합니다.

```
GET http://{SERVER_URL}/lectures/{lectureId}/users/{userId}
```
- **Path variable**

| 파라미터                 | 타입      | 필수여부 | 설명      |
|----------------------|---------|------|---------|
| lectureId            | integer | Y    | 특강 아이디  |

- **Response body**
```
true or false
```

- 프로세스
  - 신청 이력 테이블에서 조회
  - 신청에 성공한 경우 -> `true`
  - 신청한 내역이 없는 경우 -> `false`


___

### 고민한 내용
- `DB Lock`을 걸때 낙관적 락과 비관적 락의 차이에 따른 적용
  - `PESSIMISTIC_WRITE`와 `PESSIMISTIC_READ` 의 차이점
- `DB Lock`과 `isolation`의 연관성
- `application`단에서 `DB Lock` 설정을 할 경우 다른 인스턴스에 영향을 끼치는지? 
