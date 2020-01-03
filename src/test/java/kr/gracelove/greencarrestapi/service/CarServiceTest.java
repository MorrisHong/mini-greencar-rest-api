package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import kr.gracelove.greencarrestapi.domain.car.exception.CarNotFoundException;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.List;
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

    @Test
    void 자동차_목록_조회() {
        List<Car> list = new ArrayList<>();
        list.add(car);

        Pageable pageable = PageRequest.of(0, 1);
        Page<Car> pageList = new PageImpl<>(list);

        given(carRepository.findAll(pageable)).willReturn(pageList);

        Page<CarResponseDto> cars = carService.getCars(pageable);

        assertEquals(1, cars.getTotalElements());

    }

    @Test
    void 없는_자동차_등록()  {
        assertThrows(CarNotFoundException.class, () -> carService.getCar(Long.MAX_VALUE));
    }

}