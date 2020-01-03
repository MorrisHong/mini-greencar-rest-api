package kr.gracelove.greencarrestapi.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.gracelove.greencarrestapi.service.ReservationService;
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
import kr.gracelove.greencarrestapi.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"3, Reservation"})
@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @ApiOperation(value = "예약 등록", notes = "예약을 등록한다")
    @PostMapping("/api/v1/reservations")
    public Long registerReservation(@RequestBody ReservationRequestDto requestDto) {
        return reservationService.reservation(requestDto);
    }

    @ApiOperation(value = "예약 단건 조회", notes = "해당 id의 예약을 조회한다.")
    @GetMapping("/api/v1/reservations/{id}")
    public ReservationResponseDto getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @ApiOperation(value = "예약 목록 조회", notes = "등록된 모든 예약을 조회한다.")
    @GetMapping("/api/v1/reservations")
    public Page<ReservationResponseDto> getReservations(Pageable pageable) {
        return reservationService.getReservations(pageable);
    }

    @ApiOperation(value = "예약 취소", notes = "해당 id의 예약을 취소한다.")
    @PutMapping("/api/v1/reservations/{id}")
    public Long cancelReservation(@PathVariable Long id) {
        return reservationService.cancel(id);
    }

}
