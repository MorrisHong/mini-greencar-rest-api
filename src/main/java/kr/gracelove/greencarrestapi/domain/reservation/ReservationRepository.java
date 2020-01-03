package kr.gracelove.greencarrestapi.domain.reservation;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    @Override
    Page<Reservation> findAll(Pageable pageable);
}
