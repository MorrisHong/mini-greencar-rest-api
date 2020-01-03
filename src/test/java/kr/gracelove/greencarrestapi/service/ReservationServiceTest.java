package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.car.CarType;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationRepository;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationStatus;
import kr.gracelove.greencarrestapi.web.dto.ReservationResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @InjectMocks
    private ReservationService reservationService;
    @Mock
    private ReservationRepository reservationRepository;

    @BeforeEach
    void setUp() {
        Car car = Car.builder().name("테스트차").type(CarType.GENESIS).status(CarStatus.AVAILABLE).build();

        Member member = Member.builder()
                .name("테스트멤버")
                .password("1234")
                .address(new Address("경기도 용인시", "백옥대로", "1234-1234"))
                .build();

        given(reservationRepository.findById(any())).willReturn(Optional.of(Reservation.builder()
                .car(car)
                .member(member)
                .status(ReservationStatus.RESERVATION)
                .build()));
    }

    @Test
    void 예약() {
        ReservationResponseDto reservation = reservationService.getReservation(1L);
    }

    @Test
    void 예약취소() {

    }
}