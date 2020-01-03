package kr.gracelove.greencarrestapi.web;

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
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ReservationApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private CarRepository carRepository;
    @Autowired
    private MemberRepository memberRepository;


    @Test
    //TODO : FIX
    void 예약_등록_조회() {

        Car savedCar = carRepository.save(Car.builder()
                .name("붕붕이")
                .type(CarType.GENESIS)
                .status(CarStatus.AVAILABLE)
                .build());

        Member savedMember = memberRepository.save(Member.builder()
                .name("test")
                .password("1234")
                .email("govlmo91@gmail.com")
                .address(new Address("경기도 용인시 처인구", "백옥대로", "111-123"))
                .build());

        ReservationRequestDto requestDto = new ReservationRequestDto(savedCar.getId(), savedMember.getId());
        HttpEntity<ReservationRequestDto> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:"+port+"/api/v1/reservations";
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        assertTrue(exchange.getStatusCode() == HttpStatus.OK);
        assertTrue(exchange.getBody() > 0L);

    }

    @Test
    void 예약_취소() throws Exception {

        String url = "http://localhost:"+port+"/api/v1/reservations/"+1;
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.PUT, null, Long.class);

        Reservation reservation = reservationRepository.findById(exchange.getBody()).get();
        Car car = reservation.getCar();

        assertTrue(reservation.getStatus() == ReservationStatus.CANCEL);

    }
}