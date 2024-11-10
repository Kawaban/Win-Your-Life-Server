package com.example.winyourlife.userinfo.domain;

import com.example.winyourlife.authentication.user.UserService;
import com.example.winyourlife.infrastructure.exception.ApplicationEntityNotFoundException;
import com.example.winyourlife.infrastructure.exception.UserAlreadyExistsException;
import com.example.winyourlife.jwt.JwtService;
import com.example.winyourlife.jwt.dto.JwtUser;
import com.example.winyourlife.userinfo.dto.*;
import java.util.Base64;
import lombok.val;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public record UserInfoService(
        UserInfoRepository userInfoRepository,
        UserInfoMapper userInfoMapper,
        JwtService jwtService,
        UserService userService,
        Authentication authentication)
        implements com.example.winyourlife.userinfo.UserInfoService {
    @Override
    public void createUserInfo(UserInfoRequest userInfoRequest) {
        val userInfo = userInfoMapper.userInfoRequestToUserInfo(userInfoRequest);
        userInfoRepository.save(userInfo);
    }

    @Override
    public UserInfoResponse getUserInfo() {
        val user = (UserDetails) authentication.getPrincipal();
        return userInfoRepository
                .findByEmail(user.getUsername())
                .map(userInfoMapper::userInfoToUserInfoResponse)
                .orElseThrow(ApplicationEntityNotFoundException::new);
    }

    @Override
    public UserInfoUpdateDataResponse updateUserInfoData(UserInfoUpdateDataRequest userInfoUpdateDataRequest) {
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
}
