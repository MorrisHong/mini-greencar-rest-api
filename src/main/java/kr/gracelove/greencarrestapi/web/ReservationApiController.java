package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.service.ReservationService;
import kr.gracelove.greencarrestapi.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class ReservationApiController {

    private final ReservationService reservationService;

    @PostMapping("/api/v1/reservations")
    public Long registerReservation(Long memberId, Long carId) {
        return reservationService.reservation(memberId, carId);
    }

    @GetMapping("/api/v1/reservations/{id}")
    public ReservationResponseDto getReservation(@PathVariable Long id) {
        return reservationService.getReservation(id);
    }

    @GetMapping("/api/v1/reservations")
    public List<ReservationResponseDto> getReservations() {
        return reservationService.getReservations();
    }

}
