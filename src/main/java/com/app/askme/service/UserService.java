package com.app.askme.service;

import com.app.askme.domain.User;
import com.app.askme.dto.UserDTO;
import com.app.askme.dto.mapper.UserMapper;
import com.app.askme.dto.request.RegisterRequest;
import com.app.askme.dto.request.UpdatePasswordRequest;
import com.app.askme.dto.request.UpdateUserRequest;
import com.app.askme.exceptions.BadRequestException;
import com.app.askme.exceptions.ConflictException;
import com.app.askme.exceptions.messages.ErrorMessage;
import com.app.askme.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private UserRepository userRepository;
    private UserMapper userMapper;


    public List<UserDTO> getAllUsers() {

        List<User> users= userRepository.findAll();
        return userMapper.usersToUserDtos(users);
    }

    public void register(RegisterRequest registerRequest) {

        if (userRepository.existsByEmail(registerRequest.getEmail())){
            throw new ConflictException(String.format(ErrorMessage.EMAIL_ALREADY_EXIST,registerRequest.getEmail()));
        }

        User user = new User();

        user.setUsername(registerRequest.getUsername());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        userRepository.save(user);
    }

    public UserDTO getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() ->
         new ConflictException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,userId)));

        return userMapper.userToUserDto(user);

    }
    
    public UserDTO updateOneUser(Long userId, UpdateUserRequest newUser) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setUsername(newUser.getUsername());
            foundUser.setEmail(newUser.getEmail());

            userRepository.save(foundUser);
            return userMapper.userToUserDto(foundUser);
        } else {
            throw new BadRequestException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,userId));
        }

    }

    public UserDTO updateUserPassword(Long userId, UpdatePasswordRequest newPassword) {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            User foundUser = user.get();
            foundUser.setPassword(newPassword.getPassword());
            userRepository.save(foundUser);
            return userMapper.userToUserDto(foundUser);
        } else {
            throw new BadRequestException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,userId));
        }

    }

    public void deleteOneUserbyId(Long userId){
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            userRepository.deleteById(user.get().getId());
        }else {
            throw new BadRequestException(String.format(ErrorMessage.USER_NOT_FOUND_MESSAGE,userId));
        }
    }

}
