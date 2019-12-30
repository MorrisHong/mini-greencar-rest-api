package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;


    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow();
        return new MemberResponseDto(member);
    }

    public List<MemberResponseDto> getMembers() {
        return memberRepository.findAll().stream().map(MemberResponseDto::new).collect(Collectors.toList());
    }

    public Long join(MemberRequestDto member) {
        return memberRepository.save(member.toEntity()).getId();
    }

    public Long updateMember(Long id, MemberRequestDto dto) {
        Member savedMember = memberRepository.findById(id).orElseThrow();
        savedMember.updateMember(dto);
        return id;
    }


}
