package com.vs45tech.com.restfulwebservice.todo;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResourceJpa {

    private TodoRepository repository;
    
    public TodoResourceJpa(TodoRepository repository) {
        this.repository = repository;
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
    @PathVariable int id){
        System.out.println("Checking");
        repository.deleteById(id);
 System.out.println("Checking two");
 return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
return repository.findByUsername(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
    @PathVariable int id){
return repository.findById(id).get();
    }
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
    @PathVariable int id,
    @RequestBody Todo todo){
   todo.setId(id);
  todo.setUsername(username);
       return repository.save(todo);
    }
    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
    @RequestBody Todo todo){
        todo.setId(null);
        todo.setUsername(username);
        todo.setDone(false);
  Todo createdTodo=repository.save(todo);
       return createdTodo; 
    }

}
