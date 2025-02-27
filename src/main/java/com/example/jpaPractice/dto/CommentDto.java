package com.example.jpaPractice.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CommentDto {
    private Long id;
    private String content;
    private String memberUsername; //댓글 작성자
    private Long boardId; //어떤 게시글에 작성됐는지
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
