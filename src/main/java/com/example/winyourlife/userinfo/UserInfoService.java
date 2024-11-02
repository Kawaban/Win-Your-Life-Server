package com.example.winyourlife.userinfo;

import com.example.winyourlife.userinfo.dto.UserInfoRequest;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateData;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateSettings;

public interface UserInfoService {
    void createUserInfo(UserInfoRequest userInfoRequest);
    UserInfoResponse getUserInfo(String email);
    void updateUserInfoData(String email, UserInfoUpdateData userInfoUpdateData);
    void updateUserInfoSettings(String email, UserInfoUpdateSettings userInfoUpdateSettings);
}
