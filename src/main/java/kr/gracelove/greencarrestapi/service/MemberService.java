package kr.gracelove.greencarrestapi.service;

import kr.gracelove.greencarrestapi.domain.member.Member;
import kr.gracelove.greencarrestapi.domain.member.MemberRepository;
import kr.gracelove.greencarrestapi.domain.member.exception.MemberNotFoundException;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
@Transactional
public class MemberService implements UserDetailsService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;


    @Transactional(readOnly = true)
    public MemberResponseDto getMember(Long id) {
        Member member = memberRepository.findById(id).orElseThrow( () -> new MemberNotFoundException(id) );
        return new MemberResponseDto(member);
    }

    @Transactional(readOnly = true)
    public Page<MemberResponseDto> getMembers(Pageable pageable) {
        return memberRepository.findAll(pageable).map(MemberResponseDto::new);
    }

    public Long join(MemberRequestDto member) {
        member.isCorrectPassword();
        member.encodePassword(passwordEncoder.encode(member.getPassword()));
        return memberRepository.save(member.toEntity()).getId();
    }

    public Long updateMember(Long id, MemberRequestDto dto) {
        Member savedMember = memberRepository.findById(id).orElseThrow(() -> new MemberNotFoundException(id));
        savedMember.updateMember(dto.getName(), dto.getEmail(), dto.getAddress(), dto.getPassword());
        return id;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Member> byName = memberRepository.findByName(username);
        Member member = byName.orElseThrow(() -> new UsernameNotFoundException("사용자 이름 : " + username + " 에 해당하는 사용자를 찾을 수 없습니다."));
        return new User(member.getName(), member.getPassword(), authorities() );
    }

    private Collection<? extends GrantedAuthority> authorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
