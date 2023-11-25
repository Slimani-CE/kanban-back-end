package com.slimanice.kanbantaskmanagementapp.web;

import com.slimanice.kanbantaskmanagementapp.dto.BoardRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.BoardResponseDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Subtask;
import com.slimanice.kanbantaskmanagementapp.entity.Task;
import com.slimanice.kanbantaskmanagementapp.exception.*;
import com.slimanice.kanbantaskmanagementapp.service.KanbanService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kanban")
@AllArgsConstructor
public class KanbanApi {
    private KanbanService kanbanService;

    @GetMapping("users")
    public ResponseEntity<List<UserResponseDTO>> users() {
        return ResponseEntity.ok(kanbanService.getAllUsers());
    }

    @GetMapping("users/user/{id}")
    public ResponseEntity<UserResponseDTO> user(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(kanbanService.getUser(id));
        } catch (UserNotExistException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @GetMapping("users/userByUsername/{username}")
    public ResponseEntity<UserResponseDTO> userByUsername(@PathVariable String username) {
        try {
            return ResponseEntity.ok(kanbanService.getUser(username));
        } catch (UserNotExistException e) {
            return ResponseEntity.noContent().build();
        }
    }

    @PostMapping("users/user")
    public ResponseEntity<UserResponseDTO> saveUser(@RequestBody UserRequestDTO user) {
        try {
            return ResponseEntity.ok(kanbanService.saveUser(user));
        } catch (UsernameAlreadyExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // User Authentication
    // TODO: Migration to Spring Security
    @PostMapping("users/user/authenticate")
    public ResponseEntity<UserResponseDTO> authenticateUser(@RequestBody UserRequestDTO request) {
        try {
            UserResponseDTO userFromDb = kanbanService.getUser(request.getUsername());
            if (userFromDb.getPassword().equals(request.getPassword())) {
                return ResponseEntity.ok(userFromDb);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (UserNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Drop user
    @DeleteMapping("users/user/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            kanbanService.deleteUser(id);
            return ResponseEntity.ok().build();
        } catch (UserNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Save new board for user
    @PostMapping("users/user/{userId}/boards/board")
    public ResponseEntity<BoardResponseDTO> saveBoard(@PathVariable Long userId, @RequestBody BoardRequestDTO request) {
        try {
            return ResponseEntity.ok(kanbanService.saveBoard(request, userId));
        }
        catch (UserNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Get all boards for user
    @GetMapping("users/user/{userId}/boards")
    public ResponseEntity<List<BoardResponseDTO>> boards(@PathVariable Long userId) {
        try {
            return ResponseEntity.ok(kanbanService.getAllBoardsByUserId(userId));
        } catch (UserNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Drop board
    @DeleteMapping("boards/board/{boardId}")
    public ResponseEntity<Void> deleteBoard(@PathVariable Long boardId) {
        try {
            kanbanService.deleteBoard(boardId);
            return ResponseEntity.ok().build();
        } catch (BoardNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Save new task for board
    @PostMapping("boards/board/{boardId}/tasks/task")
    public ResponseEntity<Task> saveTask(@PathVariable Long boardId, @RequestBody Task task) {
        try {
            return ResponseEntity.ok(kanbanService.saveTask(task, boardId));
        } catch (BoardNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Drop task
    @DeleteMapping("tasks/task/{taskId}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long taskId) {
        try {
            kanbanService.deleteTask(taskId);
            return ResponseEntity.ok().build();
        } catch (TaskNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Save new subtask for task
    @PostMapping("tasks/task/{taskId}/subtasks/subtask")
    public ResponseEntity<Subtask> saveSubtask(@PathVariable Long taskId, @RequestBody Subtask subtask) {
        try {
            return ResponseEntity.ok(kanbanService.saveSubtask(subtask, taskId));
        } catch (TaskNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    // Drop subtask
    @DeleteMapping("subtasks/subtask/{subtaskId}")
    public ResponseEntity<Void> deleteSubtask(@PathVariable Long subtaskId) {
        try {
            kanbanService.deleteSubtask(subtaskId);
            return ResponseEntity.ok().build();
        } catch (SubtaskNotExistException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
