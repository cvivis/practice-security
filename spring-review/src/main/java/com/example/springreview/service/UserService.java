package com.example.springreview.service;

import com.example.springreview.Repository.UserRepository;
import com.example.springreview.domain.User;
import com.example.springreview.domain.dto.UserDto;
import com.example.springreview.domain.dto.UserJoinReq;
import com.example.springreview.exception.ErrorCode;
import com.example.springreview.exception.HospitalReviewAppException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    public UserDto join(UserJoinReq userJoinReq) {

        userRepository.findByUserName(userJoinReq.getUserName())
                .ifPresent(user -> {
                    throw new HospitalReviewAppException(ErrorCode.USERNAME_DUPLICATED,String.format("UserName: %s",userJoinReq.getUserName()));
                }); // 에러 발생 지정해서 리턴
        User savedUser = userRepository.save(userJoinReq.toEntity(encoder.encode(userJoinReq.getPassword())));
        return UserDto.builder()
                .id(savedUser.getId())
                .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .build();
    }
}
