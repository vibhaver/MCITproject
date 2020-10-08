package com.vibha.todo.service;

import java.util.List;

import com.vibha.todo.model.Todo;

public interface TodoService {


    Todo create(Todo todo);


    List<Todo> getAllTodos();

    Todo findById(Long id);

    Todo update(Todo todo);

    void delete(Long id);

    Todo findATask(Long id);
}
