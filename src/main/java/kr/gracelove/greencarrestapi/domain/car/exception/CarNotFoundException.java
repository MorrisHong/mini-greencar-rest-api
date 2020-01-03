package kr.gracelove.greencarrestapi.domain.car.exception;

public class CarNotFoundException extends RuntimeException {

    public CarNotFoundException(Long carId) {
        super("해당 id의 자동차가 없습니다. id : " + carId);
    }
}
