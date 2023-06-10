package com.example.todo.dao;

import com.example.todo.models.Category;
import com.example.todo.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryDao extends JpaRepository<Category, Long> {

    @Query("select t from Category t where t.user.id = :id")
    Iterable<Category> findTodosByCategoryID(@Param("id") Long id);

    @Query("select t from Category t where t.id = :id")
    Category findCategoryById(@Param("id") Long id);

    @Query("select t from Category t where t.user.id = :id and t.open = true")
    Iterable<Category> findTodosByCategoryIDForAdmin(@Param("id") Long id);
}
