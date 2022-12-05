package com.example.springreview.domain.dto.review;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class ReviewCreateRes {

    private String context;
    private String find;
}
