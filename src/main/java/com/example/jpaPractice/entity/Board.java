package com.example.jpaPractice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
public class Board {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID",nullable = false)
    private Member member;

    @Column(nullable = false,length = 100)
    private String title;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
    private int viewcount = 0;

    public Board(String title, String content, Member member) {
        this.title = title;
        this.content = content;
        this.member = member;
        this.createdAt = LocalDateTime.now(); // 생성 시간 설정
        this.updatedAt = LocalDateTime.now();
    }

    public void increaseViewCount() {
        this.viewcount++;
    }

    //게시글 수정 메서드
    public void update(String title, String content) {
        this.title = title;
        this.content = content;
        this.updatedAt = LocalDateTime.now();
    }

}
