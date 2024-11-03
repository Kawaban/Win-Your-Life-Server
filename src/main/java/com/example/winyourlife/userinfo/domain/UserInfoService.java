package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.authentication.user.UserService;
import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.infrastructure.exception.UserAlreadyExistsException;
import com.example.winyourlife.jwt.JwtService;
import com.example.winyourlife.jwt.dto.JwtUser;
import com.example.winyourlife.userinfo.dto.*;
import java.util.Base64;
import lombok.val;
import org.springframework.stereotype.Service;

@Service
public record UserInfoService(
        UserInfoRepository userInfoRepository,
        UserInfoMapper userInfoMapper,
        JwtService jwtService,
        UserService userService)
        implements com.example.winyourlife.userinfo.UserInfoService {
    @Override
    public void createUserInfo(UserInfoRequest userInfoRequest) {
        val userInfo = userInfoMapper.userInfoRequestToUserInfo(userInfoRequest);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfoResponse getUserInfo(String email) {
        return userInfoRepository
                .findByEmail(email)
                .map(userInfoMapper::userInfoToUserInfoResponse)
                .orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public UserInfoUpdateDataResponse updateUserInfoData(
            String email, UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
        if (!email.equals(userInfoUpdateDataRequest.email())
                && userInfoRepository.existsByEmail(userInfoUpdateDataRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        val userInfo = userInfoRepository.findByEmail(email).orElseThrow(ApplicationEntityNotFoundException::new);
        userInfo.setAvatar(Base64.getDecoder().decode(userInfoUpdateDataRequest.avatar()));
        userInfo.setName(userInfoUpdateDataRequest.name());
        userInfo.setEmail(userInfoUpdateDataRequest.email());
        userInfoRepository.save(userInfo);
        if (!email.equals(userInfoUpdateDataRequest.email())) {
            userService.changeEmail(email, userInfoUpdateDataRequest.email());
            return new UserInfoUpdateDataResponse(jwtService.generateToken(new JwtUser(userInfo.getEmail())));
        } else {
            return new UserInfoUpdateDataResponse(null);
        }
    }

    @Override
    public void updateUserInfoSettings(String email, UserInfoUpdateSettings userInfoUpdateSettings) {
        val userInfo = userInfoRepository.findByEmail(email).orElseThrow(ApplicationEntityNotFoundException::new);
        userInfo.setDailyReminderActive(userInfoUpdateSettings.isDailyReminderActive());
        userInfo.setFriendNotificationActive(userInfoUpdateSettings.isFriendNotificationActive());
        userInfoRepository.save(userInfo);
    }
}
