package com.slimanice.kanbantaskmanagementapp.dto;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardResponseDTO {
    private Long id;
    private String name;
    private List<TaskResponseDTO> taskList = new ArrayList<>();
    private List<String> statusList = new ArrayList<>();
}
