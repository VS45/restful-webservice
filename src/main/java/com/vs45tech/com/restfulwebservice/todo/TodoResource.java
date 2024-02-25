package com.vs45tech.com.restfulwebservice.todo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
public class TodoResource {
    private TodoService todoService;
    
    public TodoResource(TodoService todoService) {
        this.todoService = todoService;
    }
    @DeleteMapping("/users/{username}/todos/{id}")
    public ResponseEntity<Void> deleteTodo(@PathVariable String username,
    @PathVariable int id){
        System.out.println("Checking");
 todoService.deleteById(id);
 System.out.println("Checking two");
 return ResponseEntity.noContent().build();
    }
    @GetMapping("/users/{username}/todos")
    public List<Todo> retrieveTodos(@PathVariable String username){
return todoService.findByUserName(username);
    }
    @GetMapping("/users/{username}/todos/{id}")
    public Todo retrieveTodo(@PathVariable String username,
    @PathVariable int id){
return todoService.findById(id);
    }
    @PutMapping("/users/{username}/todos/{id}")
    public Todo updateTodo(@PathVariable String username,
    @PathVariable int id,
    @RequestBody Todo todo){
        todo.setId(id);
        todo.setUsername(username);
       todoService.updateTodo(todo);
       return todo;
    }
    @PostMapping("/users/{username}/todos")
    public Todo createTodo(@PathVariable String username,
    @RequestBody Todo todo){
        todo.setUsername(username);
        todo.setDone(false);
  Todo createdTodo=todoService.save(todo);
       return createdTodo;
    }

    
}
