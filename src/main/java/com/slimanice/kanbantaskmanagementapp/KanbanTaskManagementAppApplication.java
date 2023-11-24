package com.slimanice.kanbantaskmanagementapp;

import com.slimanice.kanbantaskmanagementapp.dto.UserRequestDTO;
import com.slimanice.kanbantaskmanagementapp.service.KanbanService;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
@AllArgsConstructor
public class KanbanTaskManagementAppApplication {
	KanbanService kanbanService;

	public static void main(String[] args) {
		SpringApplication.run(KanbanTaskManagementAppApplication.class, args);
	}

	@Bean
	CommandLineRunner start() {
		return args -> {
			// Create new user and save it to database
			UserRequestDTO user = UserRequestDTO.builder()
					.firstName("Mustapha")
					.lastName("Slimani")
					.username("Slimani")
					.password("123456")
					.build();
			kanbanService.saveUser(user);
		};
	}
}
