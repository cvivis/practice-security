package com.example.springreview.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Getter
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
    private String password;
    private String email;

}
