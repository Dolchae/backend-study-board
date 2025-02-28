package com.example.jpaPractice.dto;


import com.example.jpaPractice.entity.Board;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BoardDto {
    private Long id;
    private String title;
    private String content;
    private String memberUsername; // 작성자 이름만 전달

    public BoardDto(Board board) {
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.memberUsername = board.getMember().getUsername();
    }

}
