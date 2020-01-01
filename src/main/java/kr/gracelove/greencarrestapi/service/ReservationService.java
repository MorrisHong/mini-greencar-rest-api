package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationRepository;
import kr.gracelove.greencarrestapi.web.dto.ReservationResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final CarRepository carRepository;

    public Long reservation(Long memberId, Long carId) {
        Member member = memberRepository.findById(memberId).orElseThrow();
        Car car = carRepository.findById(carId).orElseThrow();

        car.changeStatus(CarStatus.RESERVED);

        Reservation reservation = Reservation.builder()
                .car(car)
                .member(member).build();

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    public ReservationResponseDto getReservation(Long id) {
        return reservationRepository.findById(id).map(ReservationResponseDto::new).orElseThrow();
    }

    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAll().stream().map(ReservationResponseDto::new).collect(Collectors.toList());
    }


}
