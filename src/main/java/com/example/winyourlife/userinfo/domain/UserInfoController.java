package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.*;
import java.util.List;
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

    @GetMapping("/friends")
    List<FriendResponse> getFriends() {
        return userInfoService.getFriends();
    }

    @DeleteMapping("/friends")
    void deleteFriend(@RequestBody DeleteFriendRequest deleteFriendRequest) {
        userInfoService.deleteFriend(deleteFriendRequest);
    }
}
