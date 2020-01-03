package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.car.Car;
import kr.gracelove.greencarrestapi.domain.car.CarRepository;
import kr.gracelove.greencarrestapi.domain.car.CarStatus;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationRepository;
import kr.gracelove.greencarrestapi.domain.reservation.ReservationStatus;
import kr.gracelove.greencarrestapi.web.dto.ReservationRequestDto;
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

    public Long reservation(ReservationRequestDto requestDto) {
        Member member = memberRepository.findById(requestDto.getMemberId()).orElseThrow(() -> new RuntimeException("해당 사용자가 없습니다. id : "+requestDto.getMemberId()));
        Car car = carRepository.findById(requestDto.getCarId()).orElseThrow();

        Reservation reservation = Reservation.builder()
                .status(ReservationStatus.RESERVATION)
                .car(car)
                .member(member).build();

        car.changeStatus(CarStatus.RESERVED);

        reservationRepository.save(reservation);
        return reservation.getId();
    }

    @Transactional(readOnly = true)
    public ReservationResponseDto getReservation(Long id) {
        return reservationRepository.findById(id).map(ReservationResponseDto::new).orElseThrow();
    }

    @Transactional(readOnly = true)
    public List<ReservationResponseDto> getReservations() {
        return reservationRepository.findAll().stream().map(ReservationResponseDto::new).collect(Collectors.toList());
    }

    public Long cancel(Long id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow();
        reservation.cancelReservation();
        Car car = reservation.getCar();
        car.changeStatus(CarStatus.AVAILABLE);

        return reservation.getId();
    }


}
