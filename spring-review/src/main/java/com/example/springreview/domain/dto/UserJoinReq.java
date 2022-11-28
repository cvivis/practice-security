package com.example.springreview.domain.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinReq {
    private String userName;
    private String password;
    private String email;
}
