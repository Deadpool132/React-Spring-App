package com.vinay.todo.ToDo.repository;

import com.vinay.todo.ToDo.todo.ToDo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToDoJpaRepository extends JpaRepository<ToDo, Long> {
    List<ToDo> findByUsername(String username);
}
