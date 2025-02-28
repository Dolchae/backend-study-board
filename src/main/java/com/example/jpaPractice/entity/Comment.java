package com.example.jpaPractice.entity;

import com.example.jpaPractice.dto.CommentDto;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Comment {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID",nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BOARD_ID",nullable = false)
    private Board board;

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdAt =  LocalDateTime.now();
    private LocalDateTime updatedAt;


    @Builder
    public Comment(Board board, Member member, String content) {
        this.board = board;
        this.member = member;
        this.content = content;
        this.createdAt = LocalDateTime.now();
    }

    public void updateComment(String newContent) {
        this.content = newContent;
        this.updatedAt = LocalDateTime.now();
    }
}
