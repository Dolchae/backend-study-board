package com.example.jpaPractice.controller;

import com.example.jpaPractice.entity.MemberDetails;
import com.example.jpaPractice.dto.LoginRequestDto;
import com.example.jpaPractice.dto.MemberRequestDto;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.service.MemberService;
import com.example.jpaPractice.service.AuthService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
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

//    @PostMapping("/login") // Security가 /login 요청 처리.
//    public ResponseEntity<String> login(@RequestBody LoginRequestDto loginRequestDto, HttpServletRequest request) {
//        authService.login(loginRequestDto,request);
//        return ResponseEntity.ok("로그인 성공!");
//    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest request) {
        request.getSession().invalidate(); // 세션 무효화
        SecurityContextHolder.clearContext();

        return ResponseEntity.ok("로그아웃 성공!");
    }


    @GetMapping("/me")
    public ResponseEntity<String> getMember() {

        MemberDetails memberDetails = (MemberDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok("현재 로그인한 사용자 이메일: " + memberDetails.getUsername());

    }
}
