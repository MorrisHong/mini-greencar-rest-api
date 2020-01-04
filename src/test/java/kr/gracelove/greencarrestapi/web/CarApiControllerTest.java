package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CarApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CarRepository carRepository;

    @Test
    void 차_저장_불러오기() {
        //given
        Car car = carRepository.save(Car.builder()
                .name("붕붕이")
                .type(CarType.GENESIS)
                .status(CarStatus.AVAILABLE)
                .build());
        //when
        Long id = car.getId();
        String name = car.getName();
        CarType type = car.getType();
        CarStatus status = car.getStatus();

        String url = "http://localhost:"+port+"/api/v1/cars/"+id;
        CarRequestDto requestDto = new CarRequestDto(car.getName(), car.getType(), car.getStatus(), car.getPricePerHours());
        HttpEntity<CarRequestDto> requestEntity = new HttpEntity<>(requestDto);
        ResponseEntity<CarResponseDto> exchange = restTemplate.exchange(url, HttpMethod.GET, requestEntity, CarResponseDto.class);

        CarResponseDto responseDto = exchange.getBody();

        //then
        assertNotNull(id);
        assertEquals(name, responseDto.getName());
        assertEquals(type, responseDto.getType());
        assertEquals(status, responseDto.getStatus());
    }

    @Test
    void 차_수정된다() {
        Car entity = Car.builder()
                .name("테스트")
                .status(CarStatus.AVAILABLE)
                .type(CarType.SONATA)
                .pricePerHours(20000)
                .build();

        Car savedCar = carRepository.save(entity);
        String url = "http://localhost:"+port+"/api/v1/cars/"+savedCar.getId();
        HttpEntity<CarRequestDto> requestEntity = new HttpEntity<>(new CarRequestDto("업데이트", CarType.SONATA, CarStatus.BROKEN, 20000));
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        assertEquals(entity.getId(), exchange.getBody());
    }

}