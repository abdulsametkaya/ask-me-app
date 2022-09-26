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

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping("/all")
    public ResponseEntity<List<UserDTO>> getAllUsers(){
        List<UserDTO> users=userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<AskMeResponse> createUser(@RequestBody RegisterRequest registerRequest){

        userService.register(registerRequest);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.REGISTER_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/all/{userId}")
    public ResponseEntity<UserDTO> getOneUser(@PathVariable Long userId){
       UserDTO user =  userService.getOneUser(userId);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/update/{userId}")
    public ResponseEntity<AskMeResponse> updateOneUser(@PathVariable Long userId, @RequestBody UpdateUserRequest user){
        userService.updateOneUser(userId,user);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.UPDATE_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("/update")
    public ResponseEntity<AskMeResponse> updatePassword(HttpServletRequest httpServletRequest, @RequestBody UpdatePasswordRequest password){

        Long id = (Long) httpServletRequest.getAttribute("id");

        userService.updateUserPassword(id,password);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.PASSWORD_CHANGED_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}/auth")
    public ResponseEntity<AskMeResponse> deleteUser(@PathVariable Long id){
        userService.deleteOneUserbyId(id);
        AskMeResponse response = new AskMeResponse();
        response.setMessage(ResponseMessage.DELETE_RESPONSE_MESSAGE);
        response.setSuccess(true);
        return ResponseEntity.ok(response);
    }


}


