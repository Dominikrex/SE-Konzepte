package com.example.demo;


import com.example.demo.model.Todo;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class TodosStorageService {

    private final AtomicInteger currentId = new AtomicInteger(0);
    private final HashMap<Integer, Todo> todoStorage = new HashMap<>();

    public List<Todo> todosGetAll() {
        return new ArrayList<>(todoStorage.values());
    }

    public Todo todosIdGet(Integer id) {
        return todoStorage.get(id);
    }

    public void todosCreate(Todo todo) {
        int newId = currentId.getAndIncrement();
        todo.setId(newId);
        todoStorage.put(newId, todo);
    }

    public void todosUpdate(Todo todo) {
        todoStorage.put(todo.getId(), todo);
    }

    public Todo todosDelete(Integer id) {
        return todoStorage.remove(id);
    }
}
