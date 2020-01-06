package kr.gracelove.greencarrestapi.domain.payment;

import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Payment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

}
