package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class CarServiceTest {

    @InjectMocks
    private CarService carService;

    @Mock
    private CarRepository carRepository;

    private Car car;

    @BeforeEach
    void setUp() {
        String name = "testCar";
        car = Car.builder()
                .name(name)
                .status(CarStatus.AVAILABLE)
                .type(CarType.SONATA).build();
    }

    @Test
    void 자동차_등록_and_단건조회() {
        given(carRepository.save(any())).willReturn(car);
        given(carRepository.findById(any())).willReturn(Optional.of(car));
        CarRequestDto requestDto = new CarRequestDto(car.getName(), car.getType(), car.getStatus());
        Long carId = carService.resisterCar(requestDto);

        CarResponseDto car = carService.getCar(carId);

        assertEquals(this.car.getName(), car.getName());
        assertEquals(this.car.getStatus(), car.getStatus());
        assertEquals(this.car.getType(), car.getType());
    }

}