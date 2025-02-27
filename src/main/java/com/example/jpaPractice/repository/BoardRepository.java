package com.example.jpaPractice.repository;

import com.example.jpaPractice.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board,Long> {
    List<Board> findByMember_Id(Long memberId);
}
