package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.userinfo.UserInfoService;
import com.example.winyourlife.userinfo.dto.*;
import java.util.List;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public record UserInfoController(UserInfoService userInfoService, UserInfoResponseService userInfoResponseService) {

    @GetMapping
    UserInfoResponse getUserInfo() {
        return userInfoResponseService.getUserInfoWithTasks();
    }

    @PatchMapping("/data")
    UserInfoUpdateDataResponse updateUserInfoData(@RequestBody UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
        return userInfoService.updateUserInfoData(userInfoUpdateDataRequest);
    }

    @GetMapping("/friends")
    List<FriendResponse> getFriends() {
        return userInfoService.getFriends();
    }

    @DeleteMapping("/friends/{email}")
    void deleteFriend(@PathVariable String email) {
        userInfoService.deleteFriend(new DeleteFriendRequest(email));
    }
}
