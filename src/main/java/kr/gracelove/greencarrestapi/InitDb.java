package kr.gracelove.greencarrestapi;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationRepository;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDb implements ApplicationRunner {

    private final CarRepository carRepository;
    private final MemberRepository memberRepository;
    private final ReservationRepository reservationRepository;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Address address1 = new Address("서울시 관악구", "봉천동", "111-2222");
        Address address2 = new Address("경기도 용인시", "백옥대로", "111-1111");

        IntStream.rangeClosed(1, 10).forEach( index -> {
            Car car = Car.builder().name("붕붕이" + index).status(CarStatus.AVAILABLE).type(CarType.K5).build();
            carRepository.save(car);
        });

        Member grace = Member.builder()
                .name("grace")
                .address(address1)
                .email("gracelove91@naver.com")
                .password("1111").build();

        Member hong = Member.builder()
                .name("eunmo")
                .address(address2)
                .email("govlmo91@gmail.com")
                .password("2222").build();

        memberRepository.save(grace);
        memberRepository.save(hong);

        Reservation reservation1 = Reservation.builder()
                .member(grace)
                .car(carRepository.findById(1L).get())
                .status(ReservationStatus.RESERVATION)
                .build();

        Reservation reservation2 = Reservation.builder()
                .member(hong)
                .car(carRepository.findById(2L).get())
                .status(ReservationStatus.RESERVATION)
                .build();

        reservationRepository.save(reservation1);
        reservationRepository.save(reservation2);

    }
}
