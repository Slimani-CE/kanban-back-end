package com.slimanice.kanbantaskmanagementapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskResponseDTO {
    private Long id;
    private String title;
    private String description;
    private String status;
    private List<SubtaskResponseDTO> subtaskList = new ArrayList<>();
}
