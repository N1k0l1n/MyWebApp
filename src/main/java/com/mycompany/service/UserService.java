package com.mycompany.service;


import com.mycompany.repository.UserRepository;
import com.mycompany.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired private UserRepository repo;

    public List<User> listALL(){
        return (List<User>) repo.findAll();
    }

    public void save(User user) {
        repo.save(user);
    }
}
