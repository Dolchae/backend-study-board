package com.example.jpaPractice.dto;


import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;

@Getter
@Setter
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String memberUsername; // 작성자 이름만 전달
}
