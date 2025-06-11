package com.fast_pos.fast_pos.adapter.in.controllers;

import com.fast_pos.fast_pos.adapter.in.mapper.UserWebMapper;
import com.fast_pos.fast_pos.domain.model.User;
import com.fast_pos.fast_pos.application.service.UserService;
import com.fast_pos.fast_pos.application.exceptions.EntityNotFoundException;
import com.fast_pos.fast_pos.adapter.in.dto.request.UserRequest;
import com.fast_pos.fast_pos.adapter.in.dto.response.UserResponse;
import com.fast_pos.fast_pos.application.exceptions.UserNotFoundException;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
@CrossOrigin(origins = "http://localhost:3000")
@RestController @RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserWebMapper userWebMapper;

    public UserController(UserService userService,UserWebMapper userWebMapper){
        this.userService=userService;
        this.userWebMapper=userWebMapper;
    }

    //crete user
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody UserRequest userRequest){
        User user = userWebMapper.toDomain(userRequest);
        User userSaved = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(userWebMapper.toResponse(userSaved));
    }
    //get user by id
    @GetMapping("/{userId}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable UUID userId){
        User user =  userService.getUserById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));
        return  ResponseEntity.ok(userWebMapper.toResponse(user));
    }
    // Obtener usuario por correo electrónico
    @GetMapping("/email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable String email) {
        User user = userService.getUserByEmail(email)
                .orElseThrow(()-> new EntityNotFoundException("User not found with email:"+email));
        return ResponseEntity.ok(userWebMapper.toResponse(user));
    }
    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<User> users = userService.getAllUsers();//1. first get all users
        List<UserResponse> responses = users.stream()//2.convert to response (DTO) every user
                .map(userWebMapper::toResponse)
                .toList();
        return ResponseEntity.ok(responses);//return the list
    }
}
