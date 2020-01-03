package kr.gracelove.greencarrestapi.domain.reservation.exception;

public class ReservationNotFoundException extends RuntimeException {

    public ReservationNotFoundException(Long reservationId) {
        super("해당 id의 예약이 없습니다. id : " + reservationId);
    }
}
