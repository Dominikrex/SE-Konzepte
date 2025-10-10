package com.example.demo;


import com.example.demo.api.DefaultApi;
import com.example.demo.model.DeleteTodo200Response;
import com.example.demo.model.Todo;
import com.example.demo.model.TodoInput;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TodosApiImpl implements DefaultApi {

    private final TodosStorageService todosStorageService;

    public TodosApiImpl(TodosStorageService todosStorageService) {
        this.todosStorageService = todosStorageService;
    }

    @Override
    public ResponseEntity<List<Todo>> listTodos() {
        List<Todo> todos = new ArrayList<>(todosStorageService.todosGetAll());
        return ResponseEntity.ok(todos);
    }

    @Override
    public ResponseEntity<Todo> getTodoById(Integer id) {
        Todo todo = todosStorageService.todosIdGet(id);
        if (todo == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(todo);
    }

    @Override
    public ResponseEntity<Todo> createTodo(TodoInput todoInput) {
        Todo todo = new Todo();
        todo.setTask(todoInput.getTask());
        todo.setCompleted(todoInput.getCompleted());
        todosStorageService.todosCreate(todo);
        return ResponseEntity.status(201).body(todo);
    }

    @Override
    public ResponseEntity<Todo> updateTodo(Integer id, TodoInput todoInput) {
        Todo existingTodo = todosStorageService.todosIdGet(id);
        if (existingTodo == null) {
            return ResponseEntity.notFound().build();
        }
        existingTodo.setTask(todoInput.getTask());
        existingTodo.setCompleted(todoInput.getCompleted());
        todosStorageService.todosUpdate(existingTodo);
        return ResponseEntity.ok(existingTodo);
    }

    @Override
    public ResponseEntity<DeleteTodo200Response> deleteTodo(Integer id) {
        Todo removedTodo = todosStorageService.todosDelete(id);
        if (removedTodo == null) {
            return ResponseEntity.notFound().build();
        }
        DeleteTodo200Response resp = new DeleteTodo200Response();
        resp.setMessage("Todo deleted");
        return ResponseEntity.ok(resp);
    }
}
