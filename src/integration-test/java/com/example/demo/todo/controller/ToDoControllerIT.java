package com.example.demo.todo.controller;

import com.example.demo.todo.model.ToDo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ToDoControllerIT {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testCreateAndGet() {
        ResponseEntity<ToDo> toDoPostResponseEntity = testRestTemplate.postForEntity("/todos?title=MyTask", null, ToDo.class);
        assertEquals(HttpStatus.OK, toDoPostResponseEntity.getStatusCode());
        ToDo toDo = toDoPostResponseEntity.getBody();
        assertNotNull(toDo);
        assertEquals("MyTask", toDo.getTitle());

        // get
        ResponseEntity<ToDo> toDoGetResponseEntity = testRestTemplate.getForEntity("/todos/" + toDo.getId(), ToDo.class);
        assertEquals(HttpStatus.OK, toDoGetResponseEntity.getStatusCode());
        ToDo existingToDo = toDoGetResponseEntity.getBody();
        assertNotNull(existingToDo);
        assertEquals(toDo.getId(), existingToDo.getId());
    }
}
