package com.example.springreview.controller;

import com.example.springreview.domain.Response;
import com.example.springreview.domain.dto.UserDto;
import com.example.springreview.domain.dto.UserJoinReq;
import com.example.springreview.domain.dto.UserJoinRes;
import com.example.springreview.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public Response<UserJoinRes> join(@RequestBody UserJoinReq userJoinReq){
         UserDto result = userService.join(userJoinReq);
         UserJoinRes userJoinRes = UserJoinRes.builder()
                 .userName(result.getUserName())
                 .email(result.getEmail())
                 .build();
         return Response.success(userJoinRes);
    }
}
