package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.authentication.user.UserService;
import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.infrastructure.exception.UserAlreadyExistsException;
import com.example.winyourlife.jwt.JwtService;
import com.example.winyourlife.jwt.dto.JwtUser;
import com.example.winyourlife.userinfo.dto.*;
import java.util.Base64;
import java.util.List;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public record UserInfoService(
        UserInfoRepository userInfoRepository,
        UserInfoMapper userInfoMapper,
        JwtService jwtService,
        UserService userService,
        FriendMapper friendMapper)
        implements com.example.winyourlife.userinfo.UserInfoService {
    @Override
    public void createUserInfo(UserInfoRequest userInfoRequest) {
        val userInfo = userInfoMapper.userInfoRequestToUserInfo(userInfoRequest);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfoResponse getUserInfoWithOutTasks() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        return userInfoRepository
                .findByEmail(user.getUsername())
                .map(userInfoMapper::userInfoToUserInfoResponse)
                .orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public UserInfoUpdateDataResponse updateUserInfoData(UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        if (!user.getUsername().equals(userInfoUpdateDataRequest.email())
                && userInfoRepository.existsByEmail(userInfoUpdateDataRequest.email())) {
            throw new UserAlreadyExistsException();
        }

        val userInfo =
                userInfoRepository.findByEmail(user.getUsername()).orElseThrow(ApplicationEntityNotFoundException::new);
        userInfo.setAvatar(Base64.getDecoder().decode(userInfoUpdateDataRequest.avatar()));
        userInfo.setName(userInfoUpdateDataRequest.name());
        userInfo.setEmail(userInfoUpdateDataRequest.email());
        userInfoRepository.save(userInfo);
        if (!user.getUsername().equals(userInfoUpdateDataRequest.email())) {
            userService.changeEmail(user.getUsername(), userInfoUpdateDataRequest.email());
            return new UserInfoUpdateDataResponse(jwtService.generateToken(new JwtUser(userInfo.getEmail())));
        } else {
            return new UserInfoUpdateDataResponse(null);
        }
    }

    @Override
    public void addFriend(UserInfo sender, UserInfo recipient) {
        sender.getFriends().add(recipient);
        recipient.getFriends().add(sender);
        userInfoRepository.save(sender);
        userInfoRepository.save(recipient);
    }

    @Override
    public UserInfo getUserInfoByEmail(String email) {
        return userInfoRepository.findByEmail(email).orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public List<FriendResponse> getFriends() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();
        val userInfo =
                userInfoRepository.findByEmail(user.getUsername()).orElseThrow(ApplicationEntityNotFoundException::new);
        return userInfo.getFriends().stream()
                .map(friendMapper::userInfoToFriendResponse)
                .toList();
    }

    @Override
    public void deleteFriend(DeleteFriendRequest deleteFriendRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        val user = (UserDetails) authentication.getPrincipal();

        val userInfo =
                userInfoRepository.findByEmail(user.getUsername()).orElseThrow(ApplicationEntityNotFoundException::new);

        val friendInfo = userInfoRepository
                .findByEmail(deleteFriendRequest.email())
                .orElseThrow(ApplicationEntityNotFoundException::new);

        userInfo.getFriends().remove(friendInfo);
        friendInfo.getFriends().remove(userInfo);

        userInfoRepository.save(userInfo);
        userInfoRepository.save(friendInfo);
    }

    @Override
    public void wonDay(UserInfo userInfo) {
        userInfo.setStreak(userInfo.getStreak() + 1);
        userInfo.setLongestStreak(Math.max(userInfo.getStreak(), userInfo.getLongestStreak()));
        userInfo.setWonDays(userInfo.getWonDays() + 1);

        userInfo.setCompletedTasks(userInfo.getCompletedTasks()
                + (int) userInfo.getTasks().stream()
                        .filter(t -> t.isActive() && t.isCompleted())
                        .count());
        userInfoRepository.save(userInfo);
    }

    @Override
    public void lostDay(UserInfo userInfo) {
        userInfo.setStreak(0);
        userInfo.setCompletedTasks(userInfo.getCompletedTasks()
                + (int) userInfo.getTasks().stream()
                        .filter(t -> t.isActive() && t.isCompleted())
                        .count());
        userInfoRepository.save(userInfo);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }
}
