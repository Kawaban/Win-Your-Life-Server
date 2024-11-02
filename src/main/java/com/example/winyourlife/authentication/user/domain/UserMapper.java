package com.example.winyourlife.authentication.user.domain;

import com.example.winyourlife.authentication.user.dto.UserRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserMapper {

        User userRequestToUser(UserRequest user);
}
