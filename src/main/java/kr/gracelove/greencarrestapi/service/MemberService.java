package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.member.exception.MemberNotFoundException;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        Member member = memberRepository.findById(id).orElseThrow( () -> new MemberNotFoundException(id) );
        return new MemberResponseDto(member);
    }

    public Page<MemberResponseDto> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberResponseDto::new);
    }

    public Long join(MemberRequestDto member) {
        member.isCorrectPassword();
        return memberRepository.save(member.toEntity()).getId();
    }

    public Long updateMember(Long id, MemberRequestDto dto) {
        Member savedMember = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        savedMember.updateMember(dto.getName(), dto.getEmail(), dto.getAddress(), dto.getPassword());
        return id;
    }


}
