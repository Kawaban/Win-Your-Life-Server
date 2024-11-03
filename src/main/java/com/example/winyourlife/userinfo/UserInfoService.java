package com.example.winyourlife.userinfo;

import com.example.winyourlife.userinfo.dto.*;

public interface UserInfoService {
    void createUserInfo(UserInfoRequest userInfoRequest);
    UserInfoResponse getUserInfo(String email);
    UserInfoUpdateDataResponse updateUserInfoData(String email, UserInfoUpdateDataRequest userInfoUpdateDataRequest);
    void updateUserInfoSettings(String email, UserInfoUpdateSettings userInfoUpdateSettings);
}
