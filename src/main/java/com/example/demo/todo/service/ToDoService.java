package com.example.demo.todo.service;

import com.example.demo.todo.model.ToDo;
import com.example.demo.todo.repository.ToDoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class ToDoService {

    private final ToDoRepository toDoRepository;

    public ToDoService(ToDoRepository toDoRepository) {
        this.toDoRepository = toDoRepository;
    }

    @Transactional
    public ToDo updateTitle(Long toDoId, String newTitle) {
        ToDo existingToDo = toDoRepository.findById(toDoId).orElseThrow();
        existingToDo.setTitle(newTitle);

        return toDoRepository.save(existingToDo);
    }

    public ToDo createToDo(String title) {
        ToDo toDo = new ToDo();
        toDo.setTitle(title);

        return toDoRepository.save(toDo);
    }

    public ToDo findById(Long toDoId) {
        return toDoRepository.findById(toDoId).orElseThrow();
    }
}
