package com.example.jpaPractice.service;

import com.example.jpaPractice.dto.LoginRequestDto;
import com.example.jpaPractice.dto.MemberRequestDto;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public void register(MemberRequestDto requestDto) {
        //중복 확인(username, email, nickname이 unique해야 함)
        if (memberRepository.findByUsername((requestDto.getUsername())).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 사용자 이름입니다.");
        }

        if (memberRepository.findByEmail((requestDto.getEmail())).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 이메일입니다.");
        }

        if(memberRepository.findByNickname((requestDto.getNickname())).isPresent()) {
            throw new IllegalStateException("이미 사용 중인 닉네임입니다.");
        }

        //비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(requestDto.getPassword());

        //Member 엔티티 생성 후 저장.
        Member member = new Member(requestDto.getUsername(), encodedPassword, requestDto.getEmail(), requestDto.getNickname());
        memberRepository.save(member);
    }

    public String login(LoginRequestDto requestDto) {
        Member member = memberRepository.findByEmail(requestDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("이메일을 찾을 수 없습니다."));

        if (!passwordEncoder.matches(requestDto.getPassword(), member.getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        return "로그인 성공!";

    }




}
