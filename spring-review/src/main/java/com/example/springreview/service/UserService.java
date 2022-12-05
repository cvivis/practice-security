package com.example.springreview.service;

import com.example.springreview.Repository.UserRepository;
import com.example.springreview.domain.User;
import com.example.springreview.domain.dto.UserDto;
import com.example.springreview.domain.dto.UserJoinReq;
import com.example.springreview.domain.dto.UserLoginReq;
import com.example.springreview.domain.dto.UserLoginRes;
import com.example.springreview.exception.ErrorCode;
import com.example.springreview.exception.HospitalReviewAppException;
import com.example.springreview.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder encoder;

    @Value("${jwt.token.secret}")//spring 제공 라이브러리
    private String secretKey;

    private long expiredTimeMs = 1000 * 60 * 60;

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

    public UserLoginRes login(UserLoginReq userLoginReq) {

        //없는 아이디
        User user = userRepository.findByUserName(userLoginReq.getUserName())
                .orElseThrow(() -> {
                    throw new HospitalReviewAppException(ErrorCode.NOT_FOUND,String.format("UserName: %s",userLoginReq.getUserName()));
                });
        if(!encoder.matches(userLoginReq.getPassword(),user.getPassword())){ // 왼쪽은 평문, 오른쪽은 암호문 둘을 비교한다.
            throw new HospitalReviewAppException(ErrorCode.INVALID_PASSWORD,String.format("password: %s",userLoginReq.getPassword()));
        }
        String token = JwtTokenUtils.generateToken(userLoginReq.getUserName(), secretKey, expiredTimeMs);


        return UserLoginRes.builder()
                .token(token)
                .build();
    }
}
