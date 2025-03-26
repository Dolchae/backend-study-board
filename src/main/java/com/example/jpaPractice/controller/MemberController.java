package com.example.jpaPractice.controller;

import com.example.jpaPractice.dto.LoginRequestDto;
import com.example.jpaPractice.dto.MemberRequestDto;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.service.MemberService;
import com.example.jpaPractice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/member")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<String> register(@RequestBody MemberRequestDto memberRequestDto) {
        memberService.register(memberRequestDto);
        return ResponseEntity.ok("회원가입 성공!");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
        authService.login(loginRequestDto, request);
        return ResponseEntity.ok("로그인 성공!");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        Object loginMember = session.getAttribute("loginMember");
        System.out.println("현재 로그인된 사용자 ID: " + loginMember);

        if (loginMember == null) {
            return ResponseEntity.status(401).body("로그인된 사용자만 로그아웃할 수 있습니다.");
        }

        session.invalidate();
        return ResponseEntity.ok("로그아웃 성공!");
    }


    @GetMapping("/me")
    public ResponseEntity<String> getMember(HttpSession session) {
        Long memberId = (Long) session.getAttribute("loginMember");
        if (memberId == null) {
            return ResponseEntity.status(401).body("로그인 안 됨 (세션 없음)");
        }
        return ResponseEntity.ok("현재 로그인한 사용자 ID: " + memberId);

    }
}
