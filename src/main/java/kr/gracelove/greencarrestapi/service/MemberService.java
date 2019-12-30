package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private MemberRepository memberRepository;


}
