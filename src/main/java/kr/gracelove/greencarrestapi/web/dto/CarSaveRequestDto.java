package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;

public class CarSaveRequestDto {
    private String name;
    private CarType type;
    private CarStatus status;

    public Car toEntity() {
        return Car.builder()
                .name(name)
                .type(type)
                .status(status)
                .build();
    }
}
