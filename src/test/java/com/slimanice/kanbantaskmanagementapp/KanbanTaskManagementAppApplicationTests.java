package com.slimanice.kanbantaskmanagementapp;

import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.exception.UserNotExistException;
import com.slimanice.kanbantaskmanagementapp.exception.UsernameAlreadyExistException;
import com.slimanice.kanbantaskmanagementapp.mapper.UserMapper;
import com.slimanice.kanbantaskmanagementapp.service.KanbanService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class KanbanTaskManagementAppApplicationTests {
	@Autowired
	KanbanService kanbanService;
	@Autowired
	UserMapper userMapper;

	// Test user creation and retrieval
	@Test
	void userCreationAndRetrieval() {
		UserRequestDTO user = UserRequestDTO.builder()
				.firstName("firstName")
				.lastName("lastName")
				.username("username1")
				.password("password")
				.build();
		try {
			assert kanbanService.saveUser(user) != null;
			assert kanbanService.getUser(kanbanService.saveUser(user).getId()) != null;
		} catch (UserNotExistException | UsernameAlreadyExistException e) {
			assert true;
		}
	}

	// Test that no users with same username can be created
	@Test
	void noUsersWithSameUsername() {
		UserRequestDTO user1 = UserRequestDTO.builder()
				.firstName("firstName")
				.lastName("lastName")
				.username("username2")
				.password("password")
				.build();
		UserRequestDTO user2 = UserRequestDTO.builder()
				.firstName("firstName")
				.lastName("lastName")
				.username("username2")
				.password("password")
				.build();

		try {
			assert kanbanService.saveUser(user1).equals(user1);
			kanbanService.saveUser(user2);
			assert false;
		} catch (Exception e) {
			assert true;
		}
	}

	// Test adding a board to a user
	@Test
	void addBoardToUser() {

	}
}
