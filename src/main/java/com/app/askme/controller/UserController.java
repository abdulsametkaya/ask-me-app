package com.app.askme.controller;

import com.app.askme.domain.User;
import com.app.askme.dto.UserDTO;
import com.app.askme.dto.request.RegisterRequest;
import com.app.askme.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping
    public void createUser(@RequestBody RegisterRequest registerRequest){
        userService.register(registerRequest);
    }

    @GetMapping("/{userId}")
    public UserDTO getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User newUser) {
        User user = userService.updateOneUser(userId, newUser);

        return user;
    }

}


