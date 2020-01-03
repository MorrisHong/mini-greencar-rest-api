package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.exception.CarNotFoundException;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class CarService {

    private final CarRepository carRepository;

    @Transactional(readOnly = true)
    public CarResponseDto getCar(Long id) {
        return carRepository.findById(id).map(CarResponseDto::new).orElseThrow( () -> new CarNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public Page<CarResponseDto> getCars(Pageable pageable) {
        return carRepository.findAll(pageable).map(CarResponseDto::new);
    }

    public Long resisterCar(CarRequestDto dto) {
        return carRepository.save(dto.toEntity()).getId();
    }

    public Long updateCarInfo(Long id, CarRequestDto requestDto) {
        Car car = carRepository.findById(id).orElseThrow(() -> new CarNotFoundException(id) );

        if(requestDto.getStatus() != null) {
            car.changeStatus(requestDto.getStatus());
        }

        if(requestDto.getName() != null) {
            car.changeName(requestDto.getName());
        }

        return car.getId();

    }
}
