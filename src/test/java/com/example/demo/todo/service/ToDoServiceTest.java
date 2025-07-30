package com.example.demo.todo.service;

import com.example.demo.todo.model.ToDo;
import com.example.demo.todo.repository.ToDoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.OptimisticLockingFailureException;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class ToDoServiceTest {
    @Autowired
    private ToDoRepository toDoRepository;

    @Autowired
    private ToDoService toDoService;

    @Test
    void shouldThrowOptimisticLockingFailureException() {
        // create a ToDo
        ToDo savedToDo = toDoService.createToDo("Interview preparation..");

        // load it in two separate sessions
        ToDo toDo1 = toDoService.findById(savedToDo.getId());
        ToDo toDo2 = toDoService.findById(savedToDo.getId());

        // change both
        toDo1.setTitle("First update..");
        toDo2.setTitle("Second update..");

        // Save toDo1
        toDoRepository.save(toDo1);

        // save toDo2 -> should fail!
        assertThrows(OptimisticLockingFailureException.class, () -> toDoRepository.save(toDo2));
    }
}
