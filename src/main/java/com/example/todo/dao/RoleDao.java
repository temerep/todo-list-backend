package com.example.todo.dao;

import java.util.Optional;

import com.example.todo.models.ERole;
import com.example.todo.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleDao extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
