package com.vs45tech.com.restfulwebservice.user.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vs45tech.com.restfulwebservice.user.User;

public interface UserRepository extends JpaRepository<User,Integer>{
    
}
