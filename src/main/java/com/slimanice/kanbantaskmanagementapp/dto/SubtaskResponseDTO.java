package com.slimanice.kanbantaskmanagementapp.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SubtaskResponseDTO {
    private Long id;
    private String title;
    private Boolean isCompleted;
}
