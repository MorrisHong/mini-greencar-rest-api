package kr.gracelove.greencarrestapi.domain.car;

import kr.gracelove.greencarrestapi.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@ToString(of = {"id", "name", "type", "status"})
public class Car extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "car_id")
    private Long id;
    private String name;

    @Enumerated(EnumType.STRING)
    private CarType type;
    @Enumerated(EnumType.STRING)
    private CarStatus status;

    private int pricePerHours;

    @Builder
    public Car(String name, CarType type, CarStatus status, int pricePerHours) {
        this.name = name;
        this.type = type;
        this.status = status;
        this.pricePerHours = pricePerHours;
    }

    public void changeStatus(CarStatus status) {
        this.status = status;
    }

    public void changeName(String name) {
        this.name = name;
    }
}
