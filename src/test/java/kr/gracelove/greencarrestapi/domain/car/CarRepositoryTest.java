package kr.gracelove.greencarrestapi.domain.car;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.EntityManager;

@DataJpaTest
class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private EntityManager em;

    @BeforeEach
    public void setUp() {
        carRepository.deleteAll();
    }

    @Test
    void 차_등록된다() {
        String name = "TestCarName";
        Car car = Car.builder()
                .name(name)
                .status(CarStatus.AVAILABLE)
                .type(CarType.GENESIS)
                .build();

        carRepository.save(car);
        Assertions.assertThat(car.getId()).isGreaterThan(0L);
    }

    @Test
    void 차_이름과_정보_수정() throws InterruptedException {
        String name = "TestCarName";
        Car car = Car.builder()
                .name(name)
                .status(CarStatus.AVAILABLE)
                .type(CarType.GENESIS)
                .build();

        carRepository.save(car);
        Assertions.assertThat(car.getId()).isGreaterThan(0L);

        Car savedCar = carRepository.findById(car.getId()).get();
        String updateName = "updateName";
        savedCar.changeName(updateName);
        savedCar.changeStatus(CarStatus.RESERVED);

        Assertions.assertThat(car.getStatus()).isEqualTo(CarStatus.RESERVED);
        Assertions.assertThat(car.getName()).isEqualTo(updateName);

    }

}