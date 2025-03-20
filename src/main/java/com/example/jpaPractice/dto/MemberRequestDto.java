package com.example.jpaPractice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MemberRequestDto {
    private String username;
    private String password;
    private String email;
    private String nickname;
}
