package com.example.jpaPractice.controller;

import com.example.jpaPractice.dto.CommentDto;
import com.example.jpaPractice.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
@CrossOrigin(origins = "http://localhost:5500")
public class CommentController {

    private final CommentService commentService;


    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    //댓글 작성
    @PostMapping
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto) {
        CommentDto createdComment = commentService.createComment(
                commentDto.getBoardId(),
                commentDto.getMemberUsername(),
                commentDto.getContent()
        );
        return ResponseEntity.ok(createdComment);
    }

    //게시글의 모든 댓글 조회
    @GetMapping("/{boardId}")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable Long boardId) {
        return ResponseEntity.ok(commentService.getCommentByBoardId(boardId));
        }


    //특정 댓글 수정
    @PutMapping("/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@PathVariable Long commentId, @RequestBody CommentDto commentDto) {
        CommentDto updatedComment = commentService.updateComment(commentId, commentDto.getContent());
        return ResponseEntity.ok(updatedComment);
    }


    //특정 댓글 삭제
    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long commentId) {
        commentService.deleteComment(commentId);
        return ResponseEntity.noContent().build();
    }


}
