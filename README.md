## 엔드포인트

### 자동차
#### 등록
  - POST http://localhost:8080/api/v1/cars
  - body
    - name : 자동차의 이름입니다.(String)
    - type : 자동차의 타입입니다 `.../domain/car/CarType.class` 에 정의되어있습니다.
    - status : 자동차의 상태입니다. `.../domain/car/CarStatus.class` 에 정의되어있습니다.
    ##### example
      ````json
      {
            "name" : "붕붕",
            "type" : "SONATA",
            "status" : "BROKEN"
      }
      ````
#### 조회
  - GET http://localhost:8080/api/v1/cars
  - GET http://localhost:8080/api/v1/cars/{id}

#### 수정
  - PUT http://localhost:8080/api/v1/cars/{id}
  - body : 위 등록을 참조하세요

### 사용자
#### 등록
  - POST http://localhost:8080/api/v1/members
  - body
    - name : 사용자의 이름입니다. (String)
    - email : 사용자의 이메일입니다. (String)
    - address : 사용자의 주소입니다. (Address)
      - city : 도시이름입니다 (String)
      - street : 거리 이름입니다 (String)
      - zipcode : 우편번호입니다 (String)
    - password : 사용자의 비밀번호입니다. (String)
    - password2 : 사용자의 비밀번호 확인입니다. (String)
    ##### example
    ````json
    {
      "name": "member1",
      "email": "govlmo91@gmail.com",
      "address" : {
                    "city":"경기도용인시처인구",
                    "street": "백옥대로",
                    "zipcode": "111-111"
                  },
      "password" : "1234",
      "password2": "1234"
    }
    ````  
    
#### 조회
 - 단건조회
    - GET http://localhost:8080/v1/api/members/{id}
 - 모든 사용자 조회
    - GET http://localhost:8080/v1/api/members

#### 수정
- PUT http://localhost:8080/v1/api/members/{id}
- body : 위 등록을 참조하세요.


### 예약
#### 등록
  - POST http://localhost:8080/v1/api/reservations
  - body
    - carId : 등록된 자동차의 id입니다. (Long)
    - memberId : 등록된 사용자의 id입니다. (Long)
    ##### example
    ```json   
    {
      "carId" : 1,
      "memberId" : 1
    }
    ```
#### 조회
- GET http://localhost:8080/v1/api/reservations
- GET http://localhost:8080/v1/api/reservations/{id}

#### 예약취소
- PUT http://localhost:8080/v1/api/reservations/{id}