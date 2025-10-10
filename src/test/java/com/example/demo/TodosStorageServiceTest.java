package com.example.demo;


import com.example.demo.model.Todo;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

class TodosStorageServiceTest {

    @Test
    void todosCreate_setsIdAndStoresTodo() {
        TodosStorageService service = new TodosStorageService();

        Todo todo = new Todo();          // noch ohne ID
        service.todosCreate(todo);       // sollte ID=0 setzen und speichern

        assertEquals(0, todo.getId(), "erste vergebene ID muss 0 sein");
        assertSame(todo, service.todosIdGet(0), "Todo muss unter seiner ID abrufbar sein");
    }
}
