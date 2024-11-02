package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateData;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateSettings;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public record UserInfoController(UserInfoService userInfoService) {

    @GetMapping("/{email}")
    UserInfoResponse getUserInfo(@PathVariable String email) {
        return userInfoService.getUserInfo(email);
    }

    @PatchMapping("/data/{email}")
    void updateUserInfoData(@PathVariable String email, @RequestBody UserInfoUpdateData userInfoUpdateData) {
        userInfoService.updateUserInfoData(email, userInfoUpdateData);
    }

    @PatchMapping("/settings/{email}")
    void updateUserInfoSettings(@PathVariable String email, @RequestBody UserInfoUpdateSettings userInfoUpdateSettings) {
        userInfoService.updateUserInfoSettings(email, userInfoUpdateSettings);
    }
}
