package com.example.demo.todo.controller;

import com.example.demo.todo.model.ToDo;
import com.example.demo.todo.service.ToDoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/todos")
public class ToDoController {

    private final ToDoService toDoService;

    public ToDoController(ToDoService toDoService) {
        this.toDoService = toDoService;
    }

    @PatchMapping("/{toDoId}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable(value = "toDoId") Long toDoId, @RequestParam(value = "title") String title) {
        ToDo updatedToDo = toDoService.updateTitle(toDoId, title);

        return ResponseEntity.ok(updatedToDo);
    }

    @PostMapping
    public ResponseEntity<ToDo> createToDo(@RequestParam(value = "title") String title) {
        ToDo savedToDo = toDoService.createToDo(title);

        return ResponseEntity.ok(savedToDo);
    }

    @GetMapping("/{toDoId}")
    public ResponseEntity<ToDo> getToDoById(@PathVariable(value = "toDoId") Long toDoId) {
        ToDo existingToDo = toDoService.findById(toDoId);

        return ResponseEntity.ok(existingToDo);
    }
}
