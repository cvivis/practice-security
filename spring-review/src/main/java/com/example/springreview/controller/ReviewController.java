package com.example.springreview.controller;


import com.example.springreview.domain.Response;
import com.example.springreview.domain.dto.review.ReviewCreateReq;
import com.example.springreview.domain.dto.review.ReviewCreateRes;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reviews")
public class ReviewController {


    @PostMapping
    public Response<ReviewCreateRes> write(@RequestBody ReviewCreateReq dto){
        ReviewCreateRes reviewCreateRes = new ReviewCreateRes(dto.getContext(),dto.getFind() );
        return Response.success(reviewCreateRes);
    }
}
