package com.slimanice.kanbantaskmanagementapp.mapper;

import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.dto.UserResponseDTO;
import com.slimanice.kanbantaskmanagementapp.entity.User;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserMapper {
    BoardMapper boardMapper;

    // Map from User to UserResponseDTO
    public UserResponseDTO toUserResponseDTO(User user) {
        UserResponseDTO response = UserResponseDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .password(user.getPassword())
                .build();
        if (user.getBoards() != null) response.setBoards(user.getBoards().stream().map(boardMapper::toBoardResponseDTO).toList());
        return response;
    }

    // Map from UserRequestDTO to User
    public User toUser(UserRequestDTO request) {
        return User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .password(request.getPassword())
                .build();
    }

    // Map from UserResponseDTO to User
    public User toUser(UserResponseDTO response) {
        User user = User.builder()
                .id(response.getId())
                .firstName(response.getFirstName())
                .lastName(response.getLastName())
                .username(response.getUsername())
                .password(response.getPassword())
                .build();
        if (response.getBoards() != null) user.setBoards(response.getBoards().stream().map(boardMapper::toBoard).toList());
        return user;
    }
}
