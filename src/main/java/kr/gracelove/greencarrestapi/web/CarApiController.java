package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.service.CarService;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CarApiController {

    private final CarService carService;

    @PostMapping("/api/v1/cars")
    public Long register(@RequestBody CarRequestDto requestDto) {
        return carService.resisterCar(requestDto);
    }

    @GetMapping("/api/v1/cars/{id}")
    public CarResponseDto getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @GetMapping("/api/v1/cars")
    public List<CarResponseDto> getCars() {
        return carService.getCars();
    }

    @PutMapping("/api/v1/cars/{id}")
    public Long updateCarInfo(@PathVariable Long id, @RequestBody CarRequestDto requestDto) {
        return carService.updateCarInfo(id, requestDto);
    }
}
