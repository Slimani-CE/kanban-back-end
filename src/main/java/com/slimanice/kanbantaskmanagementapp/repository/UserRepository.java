package com.slimanice.kanbantaskmanagementapp.repository;

import com.slimanice.kanbantaskmanagementapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
