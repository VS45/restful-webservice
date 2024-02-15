package com.vs45tech.com.restfulwebservice.user;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;



@Component
public class UserDaoService {
    
    private static List<User> users=new ArrayList<>();
    private static Integer userCount=0;
    static{
        users.add(new User(1,"Jacob",LocalDate.now().minusYears(20)));
        users.add(new User(2,"Steve",LocalDate.now().minusYears(25)));
        users.add(new User(3,"James",LocalDate.now().minusYears(22)));
    }

    public List<User> findAll(){
        return users;
    }
    public User findOne(int id){
        return users.stream().filter(user->user.getId().equals(id)).findFirst().orElse(null);
    }

    public User save(User user){
        userCount=users.size()+1;
        user.setId(userCount);
        users.add(user);
        return user;
    }
    public void deleteById(int id){
  users.removeIf(user->user.getId().equals(id));
    }
}
