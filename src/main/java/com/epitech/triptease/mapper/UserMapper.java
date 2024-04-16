package com.epitech.triptease.mapper;

import com.epitech.triptease.entity.User;
import com.epitech.triptease.mapper.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

@Mapper(componentModel = "spring")
public abstract class UserMapper {

    @Autowired
    protected PasswordEncoder encoder;

    @Named("encryptPwd")
    public String encryptPwd(String password) {
        return encoder.encode(password);
    }

    public abstract UserDTO userEntityToUserDto(User user);

    @Mapping(target = "password", source = "password", qualifiedByName = "encryptPwd")
    public abstract User userDtoToUserEntity(UserDTO userDTO);

    public abstract List<UserDTO> userEntityListToUserListDto(List<User> userList);
}
