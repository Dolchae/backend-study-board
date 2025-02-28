package com.example.jpaPractice.dto;

import com.example.jpaPractice.entity.Comment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class CommentDto {
    private Long id;
    private String content;
    private String memberUsername; //댓글 작성자
    private Long boardId; //어떤 게시글에 작성됐는지

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.content = comment.getContent();
        this.boardId = comment.getBoard().getId();
        this.memberUsername = comment.getMember().getUsername();
    }
}
