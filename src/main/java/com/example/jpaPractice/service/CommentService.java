package com.example.jpaPractice.service;

import com.example.jpaPractice.dto.CommentDto;
import com.example.jpaPractice.entity.Board;
import com.example.jpaPractice.entity.Comment;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.repository.BoardRepository;
import com.example.jpaPractice.repository.CommentRepository;
import com.example.jpaPractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class CommentService {
    private final CommentRepository commentRepository;
    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    //댓글 생성
    public CommentDto createComment(Long boardId, String memberUsername, String content) {
        Board board = boardRepository.findById(boardId)
                .orElseThrow(() -> new RuntimeException("Board not found"));
        Member member = memberRepository.findByUsername(memberUsername)
                .orElseThrow(() -> new RuntimeException("Member not found"));
        Comment comment = Comment.builder().board(board).member(member).content(content).build();

        commentRepository.save(comment);
        return new CommentDto(comment);
    }

    //게시글의 모든 댓글 조회
    @Transactional(readOnly = true)
    public List<CommentDto> getCommentByBoardId(Long boardId) {
        return commentRepository.findByBoardId(boardId)
                .stream()
                .map(CommentDto::new)
                .collect(Collectors.toList());
    }

    //댓글 수정
    public CommentDto updateComment(Long commentId, String newContent) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        comment.updateComment(newContent);
        return new CommentDto(comment);
    }

    //댓글 삭제
    public void deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentRepository.delete(comment);
    }



}
