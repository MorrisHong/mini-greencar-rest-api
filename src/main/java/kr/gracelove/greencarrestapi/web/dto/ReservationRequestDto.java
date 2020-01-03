package kr.gracelove.greencarrestapi.web.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
public class ReservationRequestDto {
    private Long carId;
    private Long memberId;

    public ReservationRequestDto(Long carId, Long memberId) {
        this.carId = carId;
        this.memberId = memberId;
    }
}
