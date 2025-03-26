package com.example.jpaPractice.service;

import com.example.jpaPractice.exception.BoardNotFoundException;
import com.example.jpaPractice.dto.BoardDto;
import com.example.jpaPractice.entity.Board;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.exception.UnauthorizedAccessException;
import com.example.jpaPractice.repository.BoardRepository;
import com.example.jpaPractice.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;


    public BoardDto createBoard(BoardDto boardDto) {
        Member principal = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();


        Member member = memberRepository.findByEmail(principal.getEmail()).orElseThrow(() -> new RuntimeException("Member Not Found"));

        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), member);
        boardRepository.save(board); //영속 상태로 만들어주기

        return new BoardDto(board);
    }



    //모든 게시글 조회
    @Transactional(readOnly = true)
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(BoardDto::new)
                .collect(Collectors.toList());
    }

    //단일 게시글 조회
    @Transactional
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
        board.increaseViewCount(); //조회수 증가

        return new BoardDto(board);
    }

    //게시글 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));

        Member currentUser = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!board.getMember().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("작성자만 수정할 수 있습니다.");
        }

        board.update(boardDto.getTitle(),boardDto.getContent());

        return new BoardDto(board);
    }

    //게시글 삭제
    public void deleteBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));

        Member currentUser = (Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(!board.getMember().getId().equals(currentUser.getId())) {
            throw new UnauthorizedAccessException("작성자만 삭제할 수 있습니다.");
        }

        boardRepository.deleteById(id);
    }
}
