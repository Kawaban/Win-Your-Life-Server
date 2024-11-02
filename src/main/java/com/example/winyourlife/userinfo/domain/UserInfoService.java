package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.userinfo.dto.UserInfoRequest;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateData;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateSettings;
import lombok.val;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public record UserInfoService(UserInfoRepository userInfoRepository, UserInfoMapper userInfoMapper) implements com.example.winyourlife.userinfo.UserInfoService {
    @Override
    public void createUserInfo(UserInfoRequest userInfoRequest) {
        val userInfo = userInfoMapper.userInfoRequestToUserInfo(userInfoRequest);
        System.out.println(userInfo.getAvatar());
        System.out.println(userInfo.getEmail());
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfoResponse getUserInfo(String email) {
        return userInfoRepository.findByEmail(email)
                .map(userInfoMapper::userInfoToUserInfoResponse)
                .orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public void updateUserInfoData(String email, UserInfoUpdateData userInfoUpdateData) {
        val userInfo = userInfoRepository.findByEmail(email)
                .orElseThrow(ApplicationEntityNotFoundException::new);
        userInfo.setAvatar(userInfoUpdateData.avatar());
        userInfo.setName(userInfoUpdateData.name());
        userInfo.setEmail(userInfoUpdateData.email());
        userInfoRepository.save(userInfo);
    }

    @Override
    public void updateUserInfoSettings(String email, UserInfoUpdateSettings userInfoUpdateSettings) {
        val userInfo = userInfoRepository.findByEmail(email)
                .orElseThrow(ApplicationEntityNotFoundException::new);
        userInfo.setDailyReminderActive(userInfoUpdateSettings.isDailyReminderActive());
        userInfo.setFriendNotificationActive(userInfoUpdateSettings.isFriendNotificationActive());
        userInfoRepository.save(userInfo);
    }

}
