package kr.gracelove.greencarrestapi.domain.member.exception;

import lombok.Getter;

@Getter
public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException(Long memberId) {
        super("해당 id의 사용자가 없습니다. id : " + memberId);
    }


}
