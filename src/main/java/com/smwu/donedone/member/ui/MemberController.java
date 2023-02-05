package com.smwu.donedone.member.ui;

import com.smwu.donedone.member.application.MemberService;
import com.smwu.donedone.member.ui.dto.MemberResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping("/{id}")
    public MemberResponse findMember(@PathVariable final Long id){
        return memberService.findMember(id);
    }


}
