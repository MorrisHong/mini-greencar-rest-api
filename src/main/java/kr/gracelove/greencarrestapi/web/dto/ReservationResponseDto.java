package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class ReservationResponseDto {
    private Long id;
    private String carName;
    private String memberName;
    private LocalDateTime reservationDate;

    public ReservationResponseDto(Long id, String carName, String memberName, LocalDateTime reservationDate) {
        this.id = id;
        this.carName = carName;
        this.memberName = memberName;
        this.reservationDate = reservationDate;
    }

    public ReservationResponseDto(Reservation reservation) {
        this.id = reservation.getId();
        this.carName = reservation.getCar().getName();
        this.memberName = reservation.getMember().getName();
        this.reservationDate = reservation.getModifiedDate();
    }
}
