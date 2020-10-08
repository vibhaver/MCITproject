package com.vibha.todo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vibha.todo.model.Todo;
import com.vibha.todo.repository.TodoRepository;

import javax.print.attribute.standard.DateTimeAtCreation;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TodoServiceImpl implements TodoService {

    @Autowired private TodoRepository todoRepository;


    public  Todo create(Todo todo) {

        todo.setCreatedAt(new Date());

        return todoRepository.save(todo);
    }

   
    public List<Todo> getAllTodos() {
        return (List<Todo>) todoRepository.findAll();
    }

    
    public Todo findById(Long id) {
        Optional<Todo> todo = todoRepository.findById(id);
        if (todo.isPresent()){
            return todo.get();
        }else{
            return null;
        }


    }

    
    public Todo update(Todo todo) {
        todo.setCreatedAt(todo.getCreatedAt());
        todo.setUpdatedAt(new Date());
        return todoRepository.save(todo);
    }

    
    public void delete(Long id) {
        todoRepository.deleteById(id);

    }

    
    public Todo findATask(Long id){
       Todo foundTodo = todoRepository.findById(id).orElse(null);
       return foundTodo;
    }
}
