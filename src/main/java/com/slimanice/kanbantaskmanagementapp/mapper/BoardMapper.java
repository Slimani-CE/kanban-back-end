package com.slimanice.kanbantaskmanagementapp.mapper;

import com.slimanice.kanbantaskmanagementapp.dto.BoardRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.BoardResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Board;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BoardMapper {
    TaskMapper taskMapper;

    // Map from Board to BoardResponseDTO
    public BoardResponseDTO toBoardResponseDTO(Board board) {
        BoardResponseDTO response = BoardResponseDTO.builder()
                .id(board.getId())
                .name(board.getName())
                .statusList(board.getStatusList())
                .build();
        if (board.getTaskList() != null) response.setTaskList(board.getTaskList().stream().map(taskMapper::toTaskResponseDTO).toList());
        return response;
    }

    // Map from BoardRequestDTO to Board
    public Board toBoard(BoardRequestDTO request) {
        return Board.builder()
                .name(request.getName())
                .statusList(request.getStatusList())
                .build();
    }

    // Map from BoardResponseDTO to Board
    public Board toBoard(BoardResponseDTO response) {
        Board board = Board.builder()
                .id(response.getId())
                .name(response.getName())
                .statusList(response.getStatusList())
                .build();
        if (response.getTaskList() != null) board.setTaskList(response.getTaskList().stream().map(taskMapper::toTask).toList());
        return board;
    }
}
