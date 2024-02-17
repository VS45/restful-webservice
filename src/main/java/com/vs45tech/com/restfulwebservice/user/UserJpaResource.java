package com.vs45tech.com.restfulwebservice.user;

import java.net.URI;
//import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.vs45tech.com.restfulwebservice.post.Post;
import com.vs45tech.com.restfulwebservice.post.PostRepository;
import com.vs45tech.com.restfulwebservice.user.jpa.UserRepository;

import jakarta.validation.Valid;

@RestController
public class UserJpaResource {
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private PostRepository postRepository;
    @GetMapping("/jpa/users")
    public List<User> retrieveAllUsers() {
        return userRepository.findAll();
    }

    @GetMapping("/jpa/users/{id}")
    public EntityModel<User> retrieveUser(@PathVariable int id) {
        User user=userRepository.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("id: "+id);
        }
        EntityModel<User> entityModel=EntityModel.of(user);
        WebMvcLinkBuilder link=linkTo(methodOn(this.getClass()).retrieveAllUsers());
        entityModel.add(link.withRel("all-users"));
        return entityModel;
    }

    @PostMapping("/jpa/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
 User savedUser=userRepository.save(user);
 URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedUser.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
    @DeleteMapping("/jpa/users/{id}")
    public void deleteUser(@PathVariable int id) {
        User user=userRepository.findById(id).get();
        if(user==null){
            throw new UserNotFoundException("Could not delete, User not found for id: "+id);
        }
        userRepository.deleteById(id);
    }
    @GetMapping("/jpa/users/{id}/posts")
    public List<Post> getUserPosts(@PathVariable int id) {
        Optional<User> user=userRepository.findById(id);
        if(user==null){
            throw new UserNotFoundException("Could not delete, User not found for id: "+id);
        }
        return user.get().getPosts();
}   
@PostMapping("/jpa/users/{id}/posts")
public ResponseEntity<Post> createUserPost(@PathVariable int id,   @Valid @RequestBody Post post) {
    Optional<User> user=userRepository.findById(id);
    if(user==null){
        throw new UserNotFoundException("Could not delete, User not found for id: "+id);
    }
    post.setUser(user.get());
 Post savedPost=   postRepository.save(post);
 URI location=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedPost.getId()).toUri();
        return ResponseEntity.created(location).build();
}
}  