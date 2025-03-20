package com.example.jpaPractice.controller;

import com.example.jpaPractice.dto.MemberRequestDto;
import com.example.jpaPractice.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.register(memberRequestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }
}
