package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.web.dto.MemberSaveRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    public Long join(MemberSaveRequestDto member) {
        return memberRepository.save(member.toEntity()).getId();
    }

    public Long updateMember(Long id, MemberUpdateRequestDto dto) {
        Member savedMember = memberRepository.findById(id).orElseThrow();
        savedMember.updateMember(dto);
        return id;
    }


}
