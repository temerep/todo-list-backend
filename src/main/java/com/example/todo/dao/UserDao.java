package com.example.todo.dao;

import java.util.Optional;

import com.example.todo.models.Role;
import com.example.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    @Query("select t from User t where t.id = :id")
    User findUserById(@Param("id") Long id);

    @Query("select t from User t join Role r on t.role.id = r.id where r.name='ROLE_USER'")
    Iterable<User> findUsers();
    Boolean existsByUsername(String username);
    Boolean existsByEmail(String email);
}