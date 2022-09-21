package com.app.askme.service;

import com.app.askme.domain.User;
import com.app.askme.dto.UserDTO;
import com.app.askme.dto.mapper.UserMapper;
import com.app.askme.dto.request.RegisterRequest;
import com.app.askme.exceptions.ConflictException;
import com.app.askme.exceptions.messages.ErrorMessage;
import com.app.askme.repository.UserRepository;
import com.sun.xml.bind.v2.TODO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    UserRepository userRepository;
    UserMapper userMapper;


    public List<UserDTO> getAllUsers() {

        List<User> users= userRepository.findAll();
        return userMapper.usersToUserDTOs(users);
    }

    public void register(RegisterRequest registerRequest) {

        if (userRepository.existsByEMail(registerRequest.getEMail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST,registerRequest.getEMail()));
        }

        User user = new User();

        user.setUserName(registerRequest.getUserName());
        user.setEMail(registerRequest.getEMail());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
    }

    public UserDTO getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
         new ConflictException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,userId)));

        return userMapper.userToUserDTO(user);

    }

    // TODO: 21.09.2022  burada kaldım.

    
    public User updateOneUser(Long userId, User newUser) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUserName(newUser.getUserName());
            foundUser.setPassword(newUser.getPassword());
            userRepository.save(foundUser);
            return foundUser;
        } else {
            return null;
        }

    }
}
