package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public CarResponseDto getCar(Long id) {
        return carRepository.findById(id).map(CarResponseDto::new).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<CarResponseDto> getCars() {
        return carRepository.findAll().stream().map(CarResponseDto::new).collect(Collectors.toList());
    }

    public Long resisterCar(CarRequestDto dto) {
        return carRepository.save(dto.toEntity()).getId();
    }

    public Long updateCarInfo(Long id, CarRequestDto requestDto) {
        Car car = carRepository.findById(id).orElseThrow();

        if(requestDto.getStatus() != null) {
            car.changeStatus(requestDto.getStatus());
        }

        if(requestDto.getName() != null) {
            car.changeName(requestDto.getName());
        }

        return car.getId();

    }
}
