package com.app.askme.dto.mapper;

import com.app.askme.domain.User;
import com.app.askme.dto.UserDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    List<UserDTO> usersToUserDTOs(List<User> user);

    UserDTO userToUserDTO(User user);

}
