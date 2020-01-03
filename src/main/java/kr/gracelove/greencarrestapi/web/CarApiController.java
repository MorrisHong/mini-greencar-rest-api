package kr.gracelove.greencarrestapi.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.gracelove.greencarrestapi.service.CarService;
import kr.gracelove.greencarrestapi.web.dto.CarResponseDto;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"1. Car"})
@RequiredArgsConstructor
@RestController
public class CarApiController {

    private final CarService carService;

    @ApiOperation(value = "자동차 등록", notes = "자동차를 등록한다.")
    @PostMapping("/api/v1/cars")
    public Long register(@RequestBody CarRequestDto requestDto) {
        return carService.resisterCar(requestDto);
    }

    @ApiOperation(value = "자동차 단건 조회", notes = "해당 id의 자동차를 조회한다.")
    @GetMapping("/api/v1/cars/{id}")
    public CarResponseDto getCar(@PathVariable Long id) {
        return carService.getCar(id);
    }

    @ApiOperation(value = "자동차 목록 조회", notes = "등록된 모든 자동차를 조회한다.")
    @GetMapping("/api/v1/cars")
    public List<CarResponseDto> getCars() {
        return carService.getCars();
    }

    @ApiOperation(value = "자동차 정보 변경", notes = "해당 id의 자동차 정보를 수정한다.")
    @PutMapping("/api/v1/cars/{id}")
    public Long updateCarInfo(@PathVariable Long id, @RequestBody CarRequestDto requestDto) {
        return carService.updateCarInfo(id, requestDto);
    }
}
