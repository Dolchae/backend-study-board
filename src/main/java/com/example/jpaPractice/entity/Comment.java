package com.example.jpaPractice.entity;

import jakarta.persistence.*;
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
}
