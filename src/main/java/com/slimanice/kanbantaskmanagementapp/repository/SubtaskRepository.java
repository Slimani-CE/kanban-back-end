package com.slimanice.kanbantaskmanagementapp.repository;

import com.slimanice.kanbantaskmanagementapp.entity.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {
}
