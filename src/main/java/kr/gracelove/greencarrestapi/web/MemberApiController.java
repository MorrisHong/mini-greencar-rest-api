package kr.gracelove.greencarrestapi.web;

import kr.gracelove.greencarrestapi.service.MemberService;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @GetMapping("/api/v1/members/{id}")
    public MemberResponseDto member(@PathVariable Long id) {
        return memberService.getMember(id);
    }

    @GetMapping("/api/v1/members")
    public List<MemberResponseDto> members() {
        return memberService.getMembers();
    }

    @PostMapping("/api/v1/members")
    public Long newMember(@RequestBody MemberRequestDto dto) {
        return memberService.join(dto);
    }

    @PutMapping("/api/v1/members/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberRequestDto dto) {
        return memberService.updateMember(id, dto);
    }

}
