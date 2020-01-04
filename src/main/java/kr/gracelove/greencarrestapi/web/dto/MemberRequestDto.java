package kr.gracelove.greencarrestapi.web.dto;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.exception.MemberIncorrectPassword;
import lombok.Builder;
import lombok.Getter;

@Getter
public class MemberRequestDto {


    private String name;
    private String email;
    private Address address;
    private String password;
    private String password2;

    @Builder
    public MemberRequestDto(String name, String email, Address address, String password, String password2) {
        this.name = name;
        this.email = email;
        this.address = address;
        this.password = password;
        this.password2 = password2;
    }

    public void isCorrectPassword() {
        if(!password.equals(password2)) throw new MemberIncorrectPassword();
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
