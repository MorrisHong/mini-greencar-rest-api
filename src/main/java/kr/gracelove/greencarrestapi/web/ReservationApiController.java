package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.service.ReservationService;
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
import kr.gracelove.greencarrestapi.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping("/api/v1/reservations")
    public Long registerReservation(@RequestBody ReservationRequestDto requestDto) {
        return reservationService.reservation(requestDto);
    }

    @GetMapping("/api/v1/reservations/{id}")
    public ReservationResponseDto getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/api/v1/reservations")
    public List<ReservationResponseDto> getReservations() {
        return reservationService.getReservations();
    }

    @PutMapping("/api/v1/reservations/{id}")
    public void cancelReservation(@PathVariable Long id) {
        reservationService.cancel(id);
    }

}
