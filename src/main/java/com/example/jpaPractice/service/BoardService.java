package com.example.jpaPractice.service;

import com.example.jpaPractice.BoardNotFoundException;
import com.example.jpaPractice.dto.BoardDto;
import com.example.jpaPractice.entity.Board;
import com.example.jpaPractice.entity.Member;
import com.example.jpaPractice.repository.BoardRepository;
import com.example.jpaPractice.repository.MemberRepository;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BoardService {

    private final BoardRepository boardRepository;
    private final MemberRepository memberRepository;

    public BoardService(BoardRepository boardRepository, MemberRepository memberRepository) {
        this.boardRepository = boardRepository;
        this.memberRepository = memberRepository;
    }

    //게시글 생성
    public BoardDto createBoard(BoardDto boardDto) {
        Member member = memberRepository.findByUsername(boardDto.getMemberUsername())
                .orElseThrow(() -> new RuntimeException("사용자를 찾을 수 없습니다."));

        Board board = new Board(boardDto.getTitle(), boardDto.getContent(), member);
        boardRepository.save(board);

        return convertToDto(board);
    }

    //모든 게시글 조회
    public List<BoardDto> getAllBoards() {
        return boardRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    //단일 게시글 조회
    public BoardDto getBoard(Long id) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));
        return convertToDto(board);
    }

    //게시글 수정
    public BoardDto updateBoard(Long id, BoardDto boardDto) {
        Board board = boardRepository.findById(id)
                .orElseThrow(() -> new BoardNotFoundException("게시글을 찾을 수 없습니다."));

        board.update(boardDto.getTitle(),boardDto.getContent());
        boardRepository.save(board);

        return convertToDto(board);
    }

    //게시글 삭제
    public void deleteBoard(Long id) {
        if(!boardRepository.existsById(id)) {
            throw new BoardNotFoundException("게시글을 찾을 수 없습니다.");
        }
        boardRepository.deleteById(id);
    }

    //엔티티 -> DTO
    private BoardDto convertToDto(Board board) {
        BoardDto dto = new BoardDto();
        dto.setId(board.getId());
        dto.setTitle(board.getTitle());
        dto.setContent(board.getContent());
        dto.setMemberUsername(board.getMember().getUsername());
        return dto;
    }
}
