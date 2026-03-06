package com.fitness.userservice.controller;


import com.fitness.userservice.dto.UserRequest;
import com.fitness.userservice.dto.UserResponse;
import com.fitness.userservice.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
@CrossOrigin(origins = "http://127.0.0.1:5500")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse>getUser(@PathVariable String userId){
      return ResponseEntity.ok(userService.getUserProfile(userId));
    }

    @PostMapping("/register")
    public ResponseEntity<UserResponse>  register(@RequestBody UserRequest data){
        return ResponseEntity.ok(userService.register(data));
    }

    @GetMapping("/{userId}/validate")
    public ResponseEntity<Boolean>validate(@PathVariable String userId){
        return ResponseEntity.ok(userService.existByUserId(userId));
    }
}
