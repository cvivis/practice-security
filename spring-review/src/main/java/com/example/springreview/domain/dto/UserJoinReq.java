package com.example.springreview.domain.dto;


import com.example.springreview.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserJoinReq {
    private String userName;
    private String password;
    private String email;

    public User toEntity(String password){
        return User.builder()
                .userName(this.userName)
                .password(password) // 암호화된 비밀번호
                .email(this.email)
                .build();
    }
}
