package kr.gracelove.greencarrestapi.domain.payment;

import kr.gracelove.greencarrestapi.domain.reservation.Reservation;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int totalPrice;

    public void setTotalPrice(int pricePerMinute, int minute) {
        this.totalPrice = pricePerMinute * minute;
    }
}
