package com.example.jpaPractice.service;

import com.example.jpaPractice.dto.LoginRequestDto;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.repository.MemberRepository;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void login(LoginRequestDto loginRequestDto, HttpServletRequest request) {
        Member member = memberRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(() -> new RuntimeException("해당 이메일을 가진 사용자가 없습니다."));

        if(!passwordEncoder.matches(loginRequestDto.getPassword(), member.getPassword())){
            throw new RuntimeException("비밀번호가 일치하지 않습니다.");
        }

        Authentication auth = new UsernamePasswordAuthenticationToken(member, null, new ArrayList<>());
        SecurityContext context = SecurityContextHolder.createEmptyContext();
        context.setAuthentication(auth);
        SecurityContextHolder.setContext(context);

        request.getSession().setAttribute("SPRING_SECURITY_CONTEXT", context);


    }
}
