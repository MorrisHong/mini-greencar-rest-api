package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationRepository;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationStatus;
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ReservationApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ReservationRepository reservationRepository;


    @Test
    @Transactional
    //TODO : FIX
    void 예약_등록_조회() {

        ReservationRequestDto requestDto = new ReservationRequestDto(1L, 1L);
        HttpEntity<ReservationRequestDto> requestEntity = new HttpEntity<>(requestDto);

        String url = "http://localhost:"+port+"/api/v1/reservations";
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Long.class);

        assertTrue(exchange.getStatusCode() == HttpStatus.OK);
        assertTrue(exchange.getBody() > 0L);

        Reservation reservation = reservationRepository.findById(exchange.getBody()).get();
        Car car = reservation.getCar();
        assertTrue(reservation.getStatus() == ReservationStatus.RESERVATION);
        assertTrue(car.getStatus() == CarStatus.RESERVED);

    }

    @Test
    @Transactional
    void 예약_취소() throws Exception {

        String url = "http://localhost:"+port+"/api/v1/reservations/"+1;
        ResponseEntity<Long> exchange = restTemplate.exchange(url, HttpMethod.PUT, null, Long.class);

        Reservation reservation = reservationRepository.findById(exchange.getBody()).get();
        Car car = reservation.getCar();

        assertTrue(reservation.getStatus() == ReservationStatus.CANCEL);
        assertTrue(car.getStatus() == CarStatus.AVAILABLE);

    }



}