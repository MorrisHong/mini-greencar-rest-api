package kr.gracelove.greencarrestapi.domain.car.exception;

public class CarNotAvailableException extends RuntimeException {

    public CarNotAvailableException(Long carId) {
        super("해당 id의 자동차는 현재 예약이 불가합니다. id : " + carId);
    }
}
