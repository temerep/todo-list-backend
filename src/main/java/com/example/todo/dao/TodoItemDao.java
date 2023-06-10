package com.example.todo.dao;

import com.example.todo.models.TodoItem;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemDao extends CrudRepository<TodoItem, Long> {

    @Query("select t from TodoItem t where t.user.id = :id")
    Iterable<TodoItem> findTodosByUserID(@Param("id") Long id);

    @Query("select t from TodoItem t where t.user.id = :id and t.day = :day")
    Iterable<TodoItem> findTodosByUserIDForToday(@Param("id") Long id, @Param("day") String day);

    @Query("select t from TodoItem t where t.id = :id")
    TodoItem findTodoById(@Param("id") Long id);

    @Query("select t from TodoItem t join Category c on t.category.id = c.id where t.user.id = :id and c.open = true")
    Iterable<TodoItem> findTodosByUserIDForAdmin(@Param("id") Long id);
}
