package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import lombok.Getter;

@Getter
public class CarResponseDto {

    private String name;
    private CarType type;
    private CarStatus status;

    public CarResponseDto(Car car) {
        this.name = car.getName();
        this.type = car.getType();
        this.status = car.getStatus();
    }
}
