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
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;


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

    @Autowired
    private EntityManager em;

    Long carId = -1L;
    Long memberId = -1L;

    @BeforeEach
    @Transactional
    @Rollback(value = false)
    void setUp() {
        Car car = Car.builder()
                .name("예약차")
                .status(CarStatus.AVAILABLE)
                .type(CarType.GENESIS).build();
        Member member = Member.builder()
                .name("예약자")
                .password("1234")
                .address(new Address("경기도 용인시 처인구", "백옥대로", "111-111"))
                .email("govlmo91@gmail.com")
                .build();

        carId = carRepository.save(car).getId();
        memberId = memberRepository.save(member).getId();
    }


    @Test
    @Transactional
    //TODO : FIX
    void 예약_등록() {

        ReservationRequestDto requestDto = new ReservationRequestDto(1L, 1L);
        HttpEntity<ReservationRequestDto> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:"+port+"/api/v1/reservations";
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        assertTrue(exchange.getStatusCode() == HttpStatus.OK);
        assertTrue(exchange.getBody() > 0L);


    }



}