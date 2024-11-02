package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.dto.UserInfoRequest;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
interface UserInfoMapper {

        UserInfo userInfoRequestToUserInfo(UserInfoRequest userInfo);

        UserInfoResponse userInfoToUserInfoResponse(UserInfo userInfo);
}
