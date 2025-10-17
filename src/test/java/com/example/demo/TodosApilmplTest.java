package com.example.demo;


import com.example.demo.model.Todo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TodosApiImplTest {

    @Mock
    private TodosStorageService todosStorageService;

    @InjectMocks
    private TodosApiImpl underTest;

    @Test
    void listTodos() {
        List<Todo> expectedResultList = List.of();
        when(todosStorageService.todosGetAll()).thenReturn(expectedResultList);

        ResponseEntity<List<Todo>> result = underTest.listTodos();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(expectedResultList, result.getBody());

    }
    void getTodoById_returns200AndTodo() {
        Integer id = 1;
        Todo todo = mock(Todo.class);
        when(todo.getId()).thenReturn(id);
        when(todosStorageService.todosIdGet(id)).thenReturn(todo);

        ResponseEntity<Todo> resp = underTest.getTodoById(id);

        assertEquals(HttpStatus.OK, resp.getStatusCode());
        assertSame(todo, resp.getBody());
    }
}

