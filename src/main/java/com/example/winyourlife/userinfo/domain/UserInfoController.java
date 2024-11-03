package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.UserInfoResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateDataRequest;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateDataResponse;
import com.example.winyourlife.userinfo.dto.UserInfoUpdateSettings;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public record UserInfoController(UserInfoService userInfoService) {

    @GetMapping
    UserInfoResponse getUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        return userInfoService.getUserInfo(user.getUsername());
    }

    @PatchMapping("/data")
    UserInfoUpdateDataResponse updateUserInfoData(@RequestBody UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        return userInfoService.updateUserInfoData(user.getUsername(), userInfoUpdateDataRequest);
    }

    @PatchMapping("/settings")
    void updateUserInfoSettings(@RequestBody UserInfoUpdateSettings userInfoUpdateSettings) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        userInfoService.updateUserInfoSettings(user.getUsername(), userInfoUpdateSettings);
    }
}
