package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class CarResponseDto {

    private Long id;
    private String name;
    private CarType type;
    private CarStatus status;

    public CarResponseDto(Car car) {
        this.id = car.getId();
        this.name = car.getName();
        this.type = car.getType();
        this.status = car.getStatus();
    }
}
