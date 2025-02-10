package com.art.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.art.dto.LoginDto;
import com.art.dto.UserDto;
import com.art.exception.ResourceNotFoundException;
import com.art.service.UserService;


@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @PostMapping
    public ResponseEntity<String> addUser (@RequestBody UserDto userDTO) {
        return ResponseEntity.ok(userService.addUser (userDTO));
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUserDetails(@PathVariable Long id) {
        return ResponseEntity.ok(userService.getUserDetails(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser (@PathVariable Long id, @RequestBody UserDto userDTO) {
        return ResponseEntity.ok(userService.updateUser (id, userDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser (@PathVariable Long id) {
        return ResponseEntity.ok(userService.deleteUser (id));
    }
    
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody UserDto userDto) {
        String response = userService.registerUser(userDto);
        return ResponseEntity.ok(response);
    }
    
    
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginDto loginRequest) {
        try {
            UserDto userDto = userService.loginUser(loginRequest.getEmail(), loginRequest.getPassword());
            return ResponseEntity.ok().body(Map.of("token", "dummy-jwt-token", "user", userDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Map.of("message", e.getMessage()));
        }
    }

}
