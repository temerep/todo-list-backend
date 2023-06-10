package com.example.todo.controllers;

import com.example.todo.dao.CategoryDao;
import com.example.todo.dao.TodoItemDao;
import com.example.todo.dao.UserDao;
import com.example.todo.dto.CreateTodoItem;
import com.example.todo.models.Category;
import com.example.todo.models.TodoItem;
import com.example.todo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class TodoItemController {

    @Autowired
    private TodoItemDao todoItemDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    public TodoItemController(TodoItemDao todoItemDao){
        this.todoItemDao = todoItemDao;
    }

    @GetMapping()
    public Iterable<TodoItem> findAll() {
        return todoItemDao.findAll();
    }

    @PostMapping()
    public TodoItem create(@RequestBody CreateTodoItem createTodoItem) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(createTodoItem.getTitle());
        todoItem.setCompleted(createTodoItem.isCompleted());
        todoItem.setDay(createTodoItem.getDay());
        User user = userDao.findUserById(createTodoItem.getUser_id());
        todoItem.setUser(user);
        if(createTodoItem.getCategory_id() == 0){
            todoItem.setCategory(null);
        } else {
            Category category = categoryDao.findCategoryById(createTodoItem.getCategory_id());
            todoItem.setCategory(category);
        }
        return todoItemDao.save(todoItem);
    }

    @GetMapping("/user_id/{id}")
    public Iterable<TodoItem> findTodosByUserId(@PathVariable long id){
        return todoItemDao.findTodosByUserID(id);
    }

    @GetMapping("/{id}")
    public TodoItem findTodoById(@PathVariable long id){
        return todoItemDao.findTodoById(id);
    }

    @GetMapping("/user_id/today/{id}")
    public Iterable<TodoItem> findTodosByUserIdForToday(@PathVariable long id){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        return todoItemDao.findTodosByUserIDForToday(id, dateFormat.format(date));
    }

    @GetMapping("/toggle/{toggleId}")
    public TodoItem toggleTodoItem(@PathVariable long toggleId){
        TodoItem todoItem = todoItemDao.findTodoById(toggleId);
        todoItem.setCompleted(!todoItem.isCompleted());
        todoItemDao.save(todoItem);
        return todoItem;
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        TodoItem todoItem = todoItemDao.findTodoById(id);
        todoItemDao.delete(todoItem);
    }

    @PostMapping("/edit")
    public TodoItem edit(@RequestBody CreateTodoItem createTodoItem) {
        TodoItem todoItem = todoItemDao.findTodoById(createTodoItem.getId());
        todoItem.setTitle(createTodoItem.getTitle());
        todoItem.setCompleted(createTodoItem.isCompleted());
        todoItem.setDay(createTodoItem.getDay());
        User user = userDao.findUserById(createTodoItem.getUser_id());
        todoItem.setUser(user);
        if(createTodoItem.getCategory_id() == 0){
            todoItem.setCategory(null);
        } else {
            Category category = categoryDao.findCategoryById(createTodoItem.getCategory_id());
            todoItem.setCategory(category);
        }
        return todoItemDao.save(todoItem);
    }

    @GetMapping("/all-users")
    public Iterable<User> findAllUsers() {
        return userDao.findUsers();
    }

    @GetMapping("/user_id/for_admin/{id}")
    public Iterable<TodoItem> findTodosByUserIdForAdmin(@PathVariable long id){
        return todoItemDao.findTodosByUserIDForAdmin(id);
    }
}
