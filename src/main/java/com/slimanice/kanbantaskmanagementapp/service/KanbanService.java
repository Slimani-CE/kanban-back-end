package com.slimanice.kanbantaskmanagementapp.service;

import com.slimanice.kanbantaskmanagementapp.dto.BoardRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.BoardResponseDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Board;
import com.slimanice.kanbantaskmanagementapp.entity.Subtask;
import com.slimanice.kanbantaskmanagementapp.entity.Task;
import com.slimanice.kanbantaskmanagementapp.exception.*;

import java.util.List;

public interface KanbanService {
    // Save new user
    UserResponseDTO saveUser(UserRequestDTO user) throws UsernameAlreadyExistException;

    // Get User By Id
    UserResponseDTO getUser(Long id) throws UserNotExistException;

    // Get User By Username
    UserResponseDTO getUser(String username) throws UserNotExistException;

    // Get All Users
    List<UserResponseDTO> getAllUsers();

    // Save new board
    BoardResponseDTO saveBoard(BoardRequestDTO board, Long userId) throws UserNotExistException;

    // Get Board By Id
    BoardResponseDTO getBoard(Long id) throws BoardNotExistException;

    // Get All Boards By User Id
    List<BoardResponseDTO> getAllBoardsByUserId(Long userId) throws UserNotExistException;

    // Save new Task
    Task saveTask(Task task, Long boardId) throws BoardNotExistException;

    // Get Task By Id
    Task getTask(Long id) throws TaskNotExistException;

    // Save new subtask
    Subtask saveSubtask(Subtask subtask, Long taskId) throws TaskNotExistException;

    // Get subtask by id
    Subtask getSubtask(Long id) throws SubtaskNotExistException;
}
