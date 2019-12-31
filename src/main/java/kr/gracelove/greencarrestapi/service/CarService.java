package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import kr.gracelove.greencarrestapi.web.dto.CarSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CarService {

    private final CarRepository carRepository;

    public Long resisterCar(CarSaveRequestDto dto) {
        return carRepository.save(dto.toEntity()).getId();
    }

    public CarResponseDto getCar(Long id) {
        return carRepository.findById(id).map(CarResponseDto::new).orElseThrow();
    }

    public List<CarResponseDto> getCars() {
        return carRepository.findAll().stream().map(CarResponseDto::new).collect(Collectors.toList());
    }

    //todo: car변경
}
