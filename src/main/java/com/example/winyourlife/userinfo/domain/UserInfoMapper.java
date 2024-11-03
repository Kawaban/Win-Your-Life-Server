package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.dto.UserInfoRequest;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import java.util.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
interface UserInfoMapper {

    UserInfo userInfoRequestToUserInfo(UserInfoRequest userInfo);

    @Mapping(source = "avatar", target = "avatar", qualifiedByName = "encodeImage")
    UserInfoResponse userInfoToUserInfoResponse(UserInfo userInfo);

    @Named("encodeImage")
    default String encodeImage(byte[] avatar) {
        return avatar != null ? Base64.getEncoder().encodeToString(avatar) : null;
    }
}
