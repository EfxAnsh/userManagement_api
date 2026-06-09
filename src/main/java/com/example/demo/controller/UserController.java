package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.service.UserService;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/test")
    public String test() {
        return "Controller Working";
    }

    // Create User
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    // Get All Users
    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    // Get User By ID
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    // Update User
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id,
                           @RequestBody User user) {

        return userService.updateUser(id, user);
    }

    // Soft Delete User
    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {

        userService.softDeleteUser(id);

        return "User soft deleted successfully";
    }

    // Single Dynamic API for Active/Inactive Users
    @GetMapping("/status")
    public List<User> getUsersByStatus(@RequestParam boolean active) {
        return userService.getUsersByStatus(active);
    }
}