package com.slimanice.kanbantaskmanagementapp.mapper;

import com.slimanice.kanbantaskmanagementapp.dto.SubtaskRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.SubtaskResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.Subtask;
import org.springframework.stereotype.Component;

@Component
public class SubtaskMapper {
    // Map from SubtaskRequestDTO to Subtask
    public Subtask toSubtask(SubtaskRequestDTO request) {
        return Subtask.builder()
                .title(request.getTitle())
                .isCompleted(request.getIsCompleted())
                .build();
    }

    // Map from SubtaskResponseDTO to Subtask
    public Subtask toSubtask(SubtaskResponseDTO response) {
        return Subtask.builder()
                .id(response.getId())
                .title(response.getTitle())
                .isCompleted(response.getIsCompleted())
                .build();
    }

    // Map from Subtask to SubtaskResponseDTO
    public SubtaskResponseDTO toSubtaskResponseDTO(Subtask subtask) {
        return SubtaskResponseDTO.builder()
                .id(subtask.getId())
                .title(subtask.getTitle())
                .isCompleted(subtask.getIsCompleted())
                .build();
    }
}
