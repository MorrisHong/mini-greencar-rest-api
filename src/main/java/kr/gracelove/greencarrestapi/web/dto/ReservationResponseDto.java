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
    private String carName;
    private String memberName;
    private LocalDateTime reservationDate;

    public ReservationResponseDto(String carName, String memberName, LocalDateTime reservationDate) {
        this.carName = carName;
        this.memberName = memberName;
        this.reservationDate = reservationDate;
    }

    public ReservationResponseDto(Reservation reservation) {
        this.carName = reservation.getCar().getName();
        this.memberName = reservation.getMember().getName();
        this.reservationDate = reservation.getModifiedDate();
    }
}
