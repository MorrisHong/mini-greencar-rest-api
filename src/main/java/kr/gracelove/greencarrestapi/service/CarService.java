package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.web.dto.CarSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public Long resisterCar(CarSaveRequestDto dto) {
        return carRepository.save(dto.toEntity()).getId();
    }
}
