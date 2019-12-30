package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.member.Member;
import lombok.Builder;

public class MemberSaveRequestDto {


    private String name;

    private String email;

    private Address address;

    private String password;

    @Builder
    public MemberSaveRequestDto(String name, String email, Address address, String password) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
    }

    public Member toEntity() {
        return Member.builder()
                .name(this.name)
                .password(this.password)
                .email(this.email)
                .address(this.address)
                .build();
    }
}
