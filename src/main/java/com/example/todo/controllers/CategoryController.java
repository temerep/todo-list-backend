package com.example.todo.controllers;

import com.example.todo.dao.CategoryDao;
import com.example.todo.dao.TodoItemDao;
import com.example.todo.dao.UserDao;
import com.example.todo.models.Category;
import com.example.todo.models.TodoItem;
import com.example.todo.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/category")
public class CategoryController {
    private CategoryDao categoryDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    public CategoryController(CategoryDao categoryDao){
        this.categoryDao = categoryDao;
    }

    @GetMapping("/user_id/{id}")
    public Iterable<Category> findCategoryByUserId(@PathVariable long id){
        return categoryDao.findTodosByCategoryID(id);
    }

    @PostMapping("/{id}")
    public Category create(@RequestBody Category category, @PathVariable long id) {
        User user = userDao.findUserById(id);
        category.setUser(user);
        return categoryDao.save(category);
    }

    @GetMapping("/delete/{id}")
    public void delete(@PathVariable long id){
        Category category = categoryDao.findCategoryById(id);
        categoryDao.delete(category);
    }

    @GetMapping("/toggle/{id}")
    public Category toggleTodoItem(@PathVariable long id){
        Category category = categoryDao.findCategoryById(id);
        category.setOpen(!category.isOpen());
        categoryDao.save(category);
        return category;
    }

    @GetMapping("/user_id/for_admin/{id}")
    public Iterable<Category> findCategoryByUserIdForAdmin(@PathVariable long id){
        return categoryDao.findTodosByCategoryIDForAdmin(id);
    }
}
