package com.slimanice.kanbantaskmanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BoardRequestDTO {
    private String name;
    private List<String> statusList;
}
