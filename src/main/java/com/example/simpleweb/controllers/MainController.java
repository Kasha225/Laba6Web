package com.example.simpleweb.controllers;

import com.example.simpleweb.entity.UserEntity;
import com.example.simpleweb.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class MainController {

    @Autowired
    private UserService userRep;
    @GetMapping("/welcome")
    public String showWelcomePage(Model model){
        List<UserEntity> users = userRep.getAllUsers();
        model.addAttribute("users", users);
        return "welcome";
    }
    @GetMapping("/Login")
    public String loginUser(){
        return "login";
    }
    @GetMapping("/welcomeAdmin")
    public String showWelcomeAdminPage(Model model){
        List<UserEntity> users = userRep.getAllUsers();
        model.addAttribute("users", users);
        return "welcomeAdmin";
    }

    @PostMapping("/Login")
    public String loginUser(@RequestParam String name, @RequestParam String pass) {
        if (userRep.existsByNameAndPassword(name, pass)) {
            UserEntity user = userRep.findByName(name);
            if ("user".equals(user.getRole())) {
                return "redirect:/welcome";
            } else if ("admin".equals(user.getRole())) {
                return "redirect:/welcomeAdmin";
            } else {
                return "redirect:/Login";
            }
        } else {
            return "redirect:/Login";
        }
    }

    @GetMapping("/Register")
    public String registerUser() {
        return "register";
    }

    @PostMapping("/Register")
    public String registerUser(@RequestParam String name, @RequestParam String pass, @RequestParam String mail) {
        UserEntity user = new UserEntity(name, pass, mail);
        userRep.saveUser(user);
        return "redirect:/welcome";
    }

}
