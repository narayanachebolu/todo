package com.example.demo.todo.repository;

import com.example.demo.todo.model.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToDoRepository extends JpaRepository<ToDo, Long> {
}
