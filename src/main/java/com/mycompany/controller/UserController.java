package com.mycompany.controller;

import com.mycompany.service.UserService;
import com.mycompany.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    @GetMapping("/users")
    public String showUserList(Model model){
        List<User> listUsers=service.listALL();
        model.addAttribute("listUsers",listUsers);
        return "users";

    }
    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user" , new User());
        return "user_form";
    }
    @GetMapping("/users/save")
    public String saveUser(User user){
        service.save(user);
        return "redirect:/users";
    }


}
