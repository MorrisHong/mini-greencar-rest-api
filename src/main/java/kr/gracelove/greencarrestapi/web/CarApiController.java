package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.service.CarService;
import kr.gracelove.greencarrestapi.web.dto.CarSaveRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CarApiController {

    private final CarService carService;

    @PostMapping("/api/v1/cars")
    public Long register(@RequestBody CarSaveRequestDto requestDto) {
        return carService.resisterCar(requestDto);
    }

//    @GetMapping("/api/v1/cars/{id}")
//    public CarResponseDto getCar(@PathVariable Long id) {
//
//    }
}
