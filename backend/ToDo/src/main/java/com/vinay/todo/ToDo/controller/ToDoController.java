package com.vinay.todo.ToDo.controller;

import com.vinay.todo.ToDo.repository.ToDoJpaRepository;
import com.vinay.todo.ToDo.todo.ToDo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3002")
public class ToDoController {

    @Autowired
    private ToDoJpaRepository toDoJpaRepository;

    @GetMapping("/jpa/users/{username}/todos")
    public List<ToDo> getAllToDos(@PathVariable String username){
        return toDoJpaRepository.findByUsername(username);
    }

    @GetMapping("/jpa/users/{username}/todos/{id}")
    public ToDo getToDo(@PathVariable String username,@PathVariable long id){
        return toDoJpaRepository.findById(id).get();
    }

    @DeleteMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<?> deleteToDo(@PathVariable String username,@PathVariable Long id){
        toDoJpaRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping("/jpa/users/{username}/todos/{id}")
    public ResponseEntity<ToDo> updateToDo(@PathVariable String username,@PathVariable long id,@RequestBody ToDo todo){
        todo.setUsername(username);
        ToDo updatedtoDo = toDoJpaRepository.save(todo);
        return new ResponseEntity<ToDo>(todo, HttpStatus.OK);
    }

    @PostMapping("/jpa/users/{username}/todos")
    public ResponseEntity<?> addToDo(@PathVariable String username,@RequestBody ToDo todo){

        todo.setUsername(username);
        ToDo createdtoDo = toDoJpaRepository.save(todo);

        //Location
        //Get current resource URL
        ///{id}
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdtoDo.getId()).toUri();
        return new ResponseEntity<URI>(uri,HttpStatus.CREATED);
    }
}
