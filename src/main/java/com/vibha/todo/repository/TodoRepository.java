package com.vibha.todo.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.vibha.todo.model.Todo;

@Repository
public interface TodoRepository extends CrudRepository<Todo, Long> {

}
