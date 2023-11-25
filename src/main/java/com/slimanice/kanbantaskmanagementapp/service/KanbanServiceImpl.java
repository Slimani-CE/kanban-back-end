package com.slimanice.kanbantaskmanagementapp.service;

import com.slimanice.kanbantaskmanagementapp.dto.BoardRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.BoardResponseDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Board;
import com.slimanice.kanbantaskmanagementapp.entity.Subtask;
import com.slimanice.kanbantaskmanagementapp.entity.Task;
import com.slimanice.kanbantaskmanagementapp.entity.User;
import com.slimanice.kanbantaskmanagementapp.exception.*;
import com.slimanice.kanbantaskmanagementapp.mapper.BoardMapper;
import com.slimanice.kanbantaskmanagementapp.mapper.SubtaskMapper;
import com.slimanice.kanbantaskmanagementapp.mapper.TaskMapper;
import com.slimanice.kanbantaskmanagementapp.mapper.UserMapper;
import com.slimanice.kanbantaskmanagementapp.repository.BoardRepository;
import com.slimanice.kanbantaskmanagementapp.repository.SubtaskRepository;
import com.slimanice.kanbantaskmanagementapp.repository.TaskRepository;
import com.slimanice.kanbantaskmanagementapp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class KanbanServiceImpl implements KanbanService {
    UserRepository userRepository;
    BoardRepository boardRepository;
    TaskRepository taskRepository;
    SubtaskRepository subtaskRepository;
    UserMapper userMapper;
    BoardMapper boardMapper;
    TaskMapper taskMapper;
    SubtaskMapper subtaskMapper;

    // Save new user
    @Override
    public UserResponseDTO saveUser(UserRequestDTO user) throws UsernameAlreadyExistException {
        if (userRepository.findByUsername(user.getUsername()) != null) {
            throw new UsernameAlreadyExistException("Username " + user.getUsername() + " already exist");
        }
        return userMapper.toUserResponseDTO(userRepository.save(userMapper.toUser(user)));
    }

    // Get User By Id
    @Override
    public UserResponseDTO getUser(Long id) throws UserNotExistException {
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotExistException("User with id " + id + " not exist");
        }
        return userMapper.toUserResponseDTO(user);
    }

    // Get User By Username
    @Override
    public UserResponseDTO getUser(String username) throws UserNotExistException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UserNotExistException("User with username " + username + " not exist");
        }
        return userMapper.toUserResponseDTO(user);
    }

    @Override
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll().stream().map(userMapper::toUserResponseDTO).toList();
    }

    @Override
    public void deleteUser(Long id) throws UserNotExistException{
        User user = userRepository.findById(id).orElse(null);
        if (user == null) {
            throw new UserNotExistException("User with id " + id + " not exist");
        }
        userRepository.deleteById(id);
    }

    // Save new board
    @Override
    public BoardResponseDTO saveBoard(BoardRequestDTO request, Long userId) throws UserNotExistException {
        UserResponseDTO user = getUser(userId);
        Board board = boardRepository.save(boardMapper.toBoard(request));
        board.setUser(userMapper.toUser(user));
        return boardMapper.toBoardResponseDTO(board);
    }

    // Get Board By Id
    @Override
    public BoardResponseDTO getBoard(Long id) throws BoardNotExistException {
        Board board = boardRepository.findById(id).orElseThrow(() -> new BoardNotExistException("Board with id " + id + " not exist"));
        return boardMapper.toBoardResponseDTO(board);
    }

    @Override
    public List<BoardResponseDTO> getAllBoardsByUserId(Long userId) throws UserNotExistException {
        UserResponseDTO user = getUser(userId);
        if (user == null) {
            throw new UserNotExistException("User with id " + userId + " not exist");
        }
        return boardRepository.findAllByUserId(userId).stream().map(boardMapper::toBoardResponseDTO).toList();
    }

    // Save new Task
    @Override
    public Task saveTask(Task task, Long boardId) throws BoardNotExistException {
        Board board = boardMapper.toBoard(getBoard(boardId));
        task.setBoard(board);
        return taskRepository.save(task);
    }

    // Get Task By Id
    @Override
    public Task getTask(Long id) throws TaskNotExistException {
        return taskRepository.findById(id).orElseThrow(() -> new TaskNotExistException("Task with id " + id + " not exist"));
    }

    // Save new subtask
    @Override
    public Subtask saveSubtask(Subtask subtask, Long taskId) throws TaskNotExistException {
        Task task = getTask(taskId);
        subtask.setTask(task);
        return subtaskRepository.save(subtask);
    }

    // Get subtask by id
    @Override
    public Subtask getSubtask(Long id) throws SubtaskNotExistException {
        return subtaskRepository.findById(id).orElseThrow(() -> new SubtaskNotExistException("Subtask with id " + id + " not exist"));
    }

    @Override
    public void deleteBoard(Long boardId) throws BoardNotExistException {
        Board board = boardRepository.findById(boardId).orElse(null);
        if (board == null) {
            throw new BoardNotExistException("Board with id " + boardId + " not exist");
        }
        boardRepository.deleteById(boardId);
    }

    @Override
    public void deleteTask(Long taskId) throws TaskNotExistException {
        Task task = taskRepository.findById(taskId).orElse(null);
        if (task == null) {
            throw new TaskNotExistException("Task with id " + taskId + " not exist");
        }
        taskRepository.deleteById(taskId);
    }

    @Override
    public void deleteSubtask(Long subtaskId) throws SubtaskNotExistException {
        Subtask subtask = subtaskRepository.findById(subtaskId).orElse(null);
        if (subtask == null) {
            throw new SubtaskNotExistException("Subtask with id " + subtaskId + " not exist");
        }
        subtaskRepository.deleteById(subtaskId);
    }
}
