package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.member.Member;
import lombok.Getter;

@Getter
public class MemberResponseDto {

    private String name;

    private String email;

    private Address address;


    public MemberResponseDto(Member member) {
        this.name = member.getName();
        this.email = member.getEmail();
        this.address = member.getAddress();
    }
}
