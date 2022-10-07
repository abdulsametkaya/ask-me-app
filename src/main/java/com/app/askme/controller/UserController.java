package com.app.askme.controller;

import com.app.askme.dto.UserDTO;
import com.app.askme.dto.request.RegisterRequest;
import com.app.askme.dto.request.UpdatePasswordRequest;
import com.app.askme.dto.request.UpdateUserRequest;
import com.app.askme.dto.response.AskMeResponse;
import com.app.askme.dto.response.ResponseMessage;
import com.app.askme.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    // http://localhost:8080/user/all
    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // http://localhost:8080/user/register
    @PostMapping("/register")
    public ResponseEntity<AskMeResponse> createUser(@RequestBody RegisterRequest registerRequest){

        userService.register(registerRequest);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8080/user/all/{userId}
    @GetMapping("/all/{userId}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long userId){
       UserDTO user =  userService.getOneUserById(userId);
        return ResponseEntity.ok(user);
    }

    // http://localhost:8080/user/update/{userId}
    @PatchMapping("/update/{userId}")
    public ResponseEntity<AskMeResponse> updateOneUser(@PathVariable Long userId, @RequestBody UpdateUserRequest user){
        userService.updateOneUser(userId,user);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8080/user/update
    @PatchMapping("/password/{userId}")
    public ResponseEntity<AskMeResponse> updatePassword(@PathVariable Long userId, @RequestBody UpdatePasswordRequest password){

        userService.updateUserPassword(userId,password);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.PASSWORD_CHANGED_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    // http://localhost:8080/user/{id}/auth
    @DeleteMapping("/{id}/auth")
    public ResponseEntity<AskMeResponse> deleteUser(@PathVariable Long id){
        userService.deleteOneUserbyId(id);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.DELETE_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }


}


