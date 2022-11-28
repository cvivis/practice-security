package com.example.springreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserJoinRes {
    private String userName;
    private String password;
}
