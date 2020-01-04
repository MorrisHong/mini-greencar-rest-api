package kr.gracelove.greencarrestapi.domain.member.exception;

public class MemberIncorrectPassword extends RuntimeException {

    public MemberIncorrectPassword() {
        super("비밀번호를 다시 확인해주세요.");
    }
}
