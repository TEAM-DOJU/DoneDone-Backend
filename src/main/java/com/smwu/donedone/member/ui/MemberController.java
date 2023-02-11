package com.smwu.donedone.member.ui;

import com.smwu.donedone.member.application.MemberService;
import com.smwu.donedone.member.ui.dto.MemberResponse;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @Operation(summary = "멤버 단건조회 API", description = "id를 기반으로 멤버를 조회합니다.")
    @RequestMapping("/{id}")
    public MemberResponse findMember(@PathVariable final Long id) {
        return memberService.findMember(id);
    }


}
