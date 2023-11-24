package com.slimanice.kanbantaskmanagementapp.repository;

import com.slimanice.kanbantaskmanagementapp.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {
}
