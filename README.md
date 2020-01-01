## 엔드포인트

### 자동차
- 등록
  - POST http://localhost:8080/api/v1
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
  