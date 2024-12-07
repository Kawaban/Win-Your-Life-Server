package com.example.winyourlife.userinfo;

import com.example.winyourlife.userinfo.domain.UserInfo;
import com.example.winyourlife.userinfo.dto.*;
import java.util.List;

public interface UserInfoService {
    void createUserInfo(UserInfoRequest userInfoRequest);

    UserInfoResponse getUserInfoWithOutTasks();

    UserInfoUpdateDataResponse updateUserInfoData(UserInfoUpdateDataRequest userInfoUpdateDataRequest);

    void addFriend(UserInfo sender, UserInfo recipient);

    UserInfo getUserInfoByEmail(String email);

    List<FriendResponse> getFriends();

    void deleteFriend(DeleteFriendRequest deleteFriendRequest);

    void wonDay(UserInfo userInfo);

    void lostDay(UserInfo userInfo);

    List<UserInfo> getAllUsers();
}
