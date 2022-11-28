package com.example.springreview.service;

import com.example.springreview.domain.dto.UserDto;
import com.example.springreview.domain.dto.UserJoinReq;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    public UserDto join(UserJoinReq userJoinReq){
        return new UserDto(1L,"","","");
    }
}
