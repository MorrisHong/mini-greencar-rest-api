## 엔드포인트

### 자동차
#### 등록
  - POST http://localhost:8080/api/v1/cars
  - body
    - name : 자동차의 이름입니다.(String)
    - type : 자동차의 타입입니다 `.../domain/car/CarType.class` 에 정의되어있습니다.
    - status : 자동차의 상태입니다. `.../domain/car/CarStatus.class` 에 정의되어있습니다.
        - example
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

#### 업데이트
  - PUT http://localhost:8080/api/v1/cars/{id}
  - body : 위 등록을 참조하세요

