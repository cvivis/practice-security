package com.example.springreview.domain.dto.review;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
@JsonAutoDetect
public class ReviewCreateReq {
    private String context;
    private String find;
}
