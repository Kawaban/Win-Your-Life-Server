package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.dto.UserInfoRequest;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
interface UserInfoMapper {

    UserInfo userInfoRequestToUserInfo(UserInfoRequest userInfo);

    @Mapping(source = "avatar", target = "avatar", qualifiedByName = "encodeImage")
    @Mapping(source = "userInfo", target = "daysIn", qualifiedByName = "calculateDaysIn")
    UserInfoResponse userInfoToUserInfoResponse(UserInfo userInfo);

    @Named("encodeImage")
    default String encodeImage(byte[] avatar) {
        return avatar != null ? Base64.getEncoder().encodeToString(avatar) : null;
    }

    @Named("calculateDaysIn")
    default int calculateDaysIn(UserInfo userInfo) {
        return (int) userInfo.getCreatedDate().until(Instant.now(), ChronoUnit.DAYS);
    }
}
