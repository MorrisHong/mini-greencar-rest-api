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
import kr.gracelove.greencarrestapi.service.CarService;
import kr.gracelove.greencarrestapi.service.MemberService;
import kr.gracelove.greencarrestapi.service.ReservationService;
import kr.gracelove.greencarrestapi.web.dto.CarRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.stream.IntStream;

@Component
@RequiredArgsConstructor
public class InitDb implements ApplicationRunner {

    private final CarService carService;
    private final MemberService memberService;
    private final ReservationService reservationService;



    @Override
    public void run(ApplicationArguments args) throws Exception {
        Address address1 = new Address("서울시 관악구", "봉천동", "111-2222");
        Address address2 = new Address("경기도 용인시", "백옥대로", "111-1111");

        IntStream.rangeClosed(1, 10).forEach( index -> {
            carService.resisterCar(new CarRequestDto("붕붕이" + index, CarType.SONATA, CarStatus.AVAILABLE, 20000));
        });


        Long grace = memberService.join(MemberRequestDto.builder()
                .password("1111")
                .password2("1111")
                .address(address1)
                .name("grace")
                .email("gracelove91@naver.com")
                .build());

        Long eunmo = memberService.join(MemberRequestDto.builder()
                .name("eunmo")
                .address(address2)
                .email("govlmo91@gmail.com")
                .password("2222")
                .password2("2222").build());

        Long carId1 = carService.resisterCar(CarRequestDto.builder()
                .name("씽씽이")
                .status(CarStatus.AVAILABLE)
                .pricePerHours(10000)
                .type(CarType.GENESIS)
                .build());

        Long carId2 = carService.resisterCar(CarRequestDto.builder()
                .name("꼬마자동차")
                .status(CarStatus.AVAILABLE)
                .pricePerHours(20000)
                .type(CarType.SORENTO)
                .build());

        ReservationRequestDto reservationRequestDto1 = new ReservationRequestDto(carId1, grace);
        ReservationRequestDto reservationRequestDto2 = new ReservationRequestDto(carId2, eunmo);

        reservationService.reservation(reservationRequestDto1);
        reservationService.reservation(reservationRequestDto2);


    }
}
