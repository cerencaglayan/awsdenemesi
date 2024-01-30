package com.example.awsdenemesi.userpackage;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UserController {

    @GetMapping("/home")
    public String home() {
        return "home";
    }

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String registerForm() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestBody User user) {
        userService.registerUser(user.getUsername(), user.getPassword());
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestBody User  user1) {

        User user = userService.findByUsername(user1.getUsername());
        if(user == null){
            return "no user in the system has that name.";
        }
        else{
            if (user.getPassword().equals(user1.getPassword()))
            {
                return "redirect:/user/{username}";
            }
        }

        return "wrong password";
    }

    @GetMapping("/user/{username}")
    public User userProfile(@PathVariable String username) {
        User user = userService.findByUsername(username);
        return user;
    }
}