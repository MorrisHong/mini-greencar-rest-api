package kr.gracelove.greencarrestapi.web;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.gracelove.greencarrestapi.service.MemberService;
import kr.gracelove.greencarrestapi.web.dto.MemberRequestDto;
import kr.gracelove.greencarrestapi.web.dto.MemberResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"2. Member"})
@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberService memberService;

    @ApiOperation(value = "회원 단건 조회", notes = "해당 id의 회원을 조회한다.")
    @GetMapping("/api/v1/members/{id}")
    public MemberResponseDto member(@PathVariable Long id) {
        return memberService.getMember(id);
    }

    @ApiOperation(value = "회원 목록 조회", notes = "등록된 모든 회원을 조회한다.")
    @GetMapping("/api/v1/members")
    public Page<MemberResponseDto> members(Pageable pageable) {
        return memberService.getMembers(pageable);
    }

    @ApiOperation(value = "회원 등록", notes = "회원을 등록한다.")
    @PostMapping("/api/v1/members")
    public Long newMember(@RequestBody MemberRequestDto dto) {
        return memberService.join(dto);
    }

    @ApiOperation(value = "회원 정보 수정", notes = "해당 id의 회원 정보를 수정한다.")
    @PutMapping("/api/v1/members/{id}")
    public Long updateMember(@PathVariable Long id, @RequestBody MemberRequestDto dto) {
        return memberService.updateMember(id, dto);
    }

}
