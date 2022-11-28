package com.example.springreview.controller;

import com.example.springreview.domain.dto.UserJoinReq;
import com.example.springreview.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
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
    void join() throws Exception {
        String url = String.format("/users/join");
        UserJoinReq userJoinReq = new UserJoinReq("asdf","asdf","email");
        String str= objectMapper.writeValueAsString(userJoinReq);
        mockMvc.perform(post(url)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(str))
//                .andExpect(jsonPath("$.status").value("500"))
                .andExpect(status().isInternalServerError())
                .andDo(print());//http request, reponse 내역 출력
    }
}