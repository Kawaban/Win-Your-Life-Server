package com.example.winyourlife.userinfo.dto;

import lombok.Builder;

@Builder
public record UserInfoRequest(String email, String name) {}
