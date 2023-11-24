package com.slimanice.kanbantaskmanagementapp.mapper;

import com.slimanice.kanbantaskmanagementapp.dto.TaskRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.TaskResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Task;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class TaskMapper {
    private SubtaskMapper subtaskMapper;

    // Map from Task to TaskResponseDTO
    public TaskResponseDTO toTaskResponseDTO(Task task) {
        TaskResponseDTO response = TaskResponseDTO.builder()
                .id(task.getId())
                .title(task.getTitle())
                .description(task.getDescription())
                .status(task.getStatus())
                .build();
        if (task.getSubtaskList() != null) response.setSubtaskList(task.getSubtaskList().stream().map(subtaskMapper::toSubtaskResponseDTO).toList());
        return response;
    }

    // Map from TaskRequestDTO to Task
    public Task toTask(TaskRequestDTO request) {
        return Task.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .status(request.getStatus())
                .build();
    }

    // Map from TaskResponseDTO to Task
    public Task toTask(TaskResponseDTO response) {
        Task task = Task.builder()
                .id(response.getId())
                .title(response.getTitle())
                .description(response.getDescription())
                .status(response.getStatus())
                .build();
        if (response.getSubtaskList() != null) task.setSubtaskList(response.getSubtaskList().stream().map(subtaskMapper::toSubtask).toList());
        return task;
    }
}
