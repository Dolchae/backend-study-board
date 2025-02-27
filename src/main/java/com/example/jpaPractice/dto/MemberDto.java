package com.example.jpaPractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberDto {
    private Long id;
    private String username;
    private String email;
    private String nickname;
}
