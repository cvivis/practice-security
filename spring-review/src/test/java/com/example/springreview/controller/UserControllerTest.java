package com.example.springreview.controller;

import com.example.springreview.domain.dto.UserDto;
import com.example.springreview.domain.dto.UserJoinReq;
import com.example.springreview.domain.dto.UserLoginReq;
import com.example.springreview.exception.ErrorCode;
import com.example.springreview.exception.HospitalReviewAppException;
import com.example.springreview.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    UserService userService;
    @Autowired
    ObjectMapper objectMapper;




    @Test
    @DisplayName("join 테스트")
    /**{
     "resultCode": "SUCCESS",
     "result": {
     "userName": "cvivis",
     "email": "cvivis@gmail.com"
     }
     }**/
    @WithMockUser
    void join() throws Exception {
        String url = String.format("/users/join");
        UserJoinReq userJoinReq = new UserJoinReq("qqqq", "qqqq", "email");

        when(userService.join(any())).thenReturn(mock(UserDto.class));
        String str = objectMapper.writeValueAsString(userJoinReq);
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str)
                        .with(csrf())
                )
                .andExpect(status().isOk())
                .andDo(print());//http request, reponse 내역 출력
    }

    @Test
    @DisplayName("join fail 테스트")
    @WithMockUser
    void joinFail() throws Exception {
        String url = String.format("/users/join");
        UserJoinReq userJoinReq = new UserJoinReq("asdf", "asdf", "email");

        when(userService.join(any())).thenReturn(mock(UserDto.class));
        when(userService.join(any())).thenThrow(new HospitalReviewAppException(ErrorCode.USERNAME_DUPLICATED, ""));
        String str = objectMapper.writeValueAsString(userJoinReq);
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str)
                        .with(csrf())
                )
                .andExpect(status().isConflict())
                .andDo(print());//http request, reponse 내역 출력
    }

    @Test
    @DisplayName("login 성공 테스트")
    @WithMockUser
    void loginSuccess() throws Exception {
        String url = String.format("/users/login");
        UserLoginReq userLoginReq = new UserLoginReq("cvivis","cvivispwd");
//        when(userService.login(any())).thenReturn(mock(UserDto.class));
        when(userService.login(any())).thenThrow(new HospitalReviewAppException(ErrorCode.NOT_FOUND, ""));
        String str = objectMapper.writeValueAsString(userLoginReq);
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str)
                        .with(csrf())
                )
                .andExpect(status().isNotFound())
                .andDo(print());//http request, reponse 내역 출력
    }

    @Test
    @DisplayName("login 실패, 아이디 X 테스트")
    @WithMockUser
    void loginFail1() throws Exception {
        UserLoginReq userLoginReq = new UserLoginReq("cvivisasdf","cvivispwd");
    }

    @Test
    @DisplayName("login 실패, 비번 틀림 테스트")
    @WithMockUser
    void loginFail2() throws Exception {
        UserLoginReq userLoginReq = new UserLoginReq("cvivis","qwer");
    }
}