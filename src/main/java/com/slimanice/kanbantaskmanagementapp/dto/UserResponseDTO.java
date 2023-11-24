package com.slimanice.kanbantaskmanagementapp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data @AllArgsConstructor
@NoArgsConstructor @Builder
public class UserResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<BoardResponseDTO> boards = new ArrayList<>();
}
