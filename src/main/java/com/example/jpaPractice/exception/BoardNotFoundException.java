package com.example.jpaPractice.exception;

public class BoardNotFoundException extends RuntimeException {
    public BoardNotFoundException(String message) {
        super(message); // 부모 클래스(RuntimeException)의 생성자 호출
    }
}
