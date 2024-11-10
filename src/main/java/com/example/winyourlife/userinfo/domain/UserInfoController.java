package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateDataRequest;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateDataResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public record UserInfoController(UserInfoService userInfoService) {

    @GetMapping
    UserInfoResponse getUserInfo() {
        return userInfoService.getUserInfo();
    }

    @PatchMapping("/data")
    UserInfoUpdateDataResponse updateUserInfoData(@RequestBody UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
        return userInfoService.updateUserInfoData(userInfoUpdateDataRequest);
    }
}
