package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.address.Address;
import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.member.exception.MemberNotFoundException;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberServiceTest {

    @InjectMocks
    MemberService memberService;

    @Mock
    MemberRepository memberRepository;

    private Member member;

    @BeforeEach
    void setUp() {
        Address address = new Address("경기도 용인시 처인구", "백옥대로", "111-111");
        this.member = Member.builder()
                .name("테스트이름")
                .address(address)
                .email("govlmo91@gmail.com")
                .password("1234")
                .build();
    }

    @Test
    void 회원가입_and_회원단건조회() {
        given(memberRepository.save(any())).willReturn(member);
        given(memberRepository.findById(any())).willReturn(Optional.of(member));

        Long join = memberService.join(MemberRequestDto.builder()
                .name(member.getName())
                .email(member.getEmail())
                .address(member.getAddress())
                .password(member.getPassword())
                .build());

        MemberResponseDto member = memberService.getMember(1L);

        assertEquals(join, this.member.getId());
        assertEquals(member.getName(), this.member.getName());
        assertEquals(member.getAddress(), this.member.getAddress());
        assertEquals(member.getEmail(), this.member.getEmail());
    }

    @Test
    void 회원조회_리스트() {

        List<Member> list = new ArrayList<>();
        list.add(member);
        Page<Member> pageList = new PageImpl<>(list);

        Pageable pageable = PageRequest.of(0, 10);
        given(memberRepository.findAll(pageable)).willReturn(pageList);

        Page<MemberResponseDto> members = memberService.getMembers(pageable);
        assertEquals(1, members.getTotalElements());

    }

    @Test
    void 없는_회원_조회() {
        assertThrows(MemberNotFoundException.class, () -> memberService.getMember(Long.MAX_VALUE));
    }

}