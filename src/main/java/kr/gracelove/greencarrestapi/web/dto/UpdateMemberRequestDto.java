package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.address.Address;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateMemberRequestDto {

    private String name;
    private String email;
    private Address address;
    private String password;

    @Builder
    public UpdateMemberRequestDto(String name, String email, Address address, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
    }
}
