package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import lombok.Getter;

@Getter
public class CarRequestDto {
    private String name;
    private CarType type;
    private CarStatus status;
    private int pricePerHours;

    public CarRequestDto(String name, CarType type, CarStatus status, int pricePerHours) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.pricePerHours = pricePerHours;
    }

    public Car toEntity() {
        return Car.builder()
                .name(name)
                .type(type)
                .status(status)
                .pricePerHours(pricePerHours)
                .build();
    }
}
