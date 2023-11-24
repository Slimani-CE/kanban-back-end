package com.slimanice.kanbantaskmanagementapp.repository;

import com.slimanice.kanbantaskmanagementapp.entity.Board;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {
    List<Board> findAllByUserId(Long userId);
}
