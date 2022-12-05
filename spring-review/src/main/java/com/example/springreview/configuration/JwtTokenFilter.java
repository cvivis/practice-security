package com.example.springreview.configuration;

import com.example.springreview.domain.Response;
import com.example.springreview.exception.ErrorCode;
import com.example.springreview.exception.HospitalReviewAppException;
import com.example.springreview.service.UserService;
import com.example.springreview.utils.JwtTokenUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor // 초기화 할때 di 해주기
@Slf4j
public class JwtTokenFilter extends OncePerRequestFilter { // 들어갈 때마다 확인하기

    private final String secretKey;
    private final UserService userService;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // 권한 주거나 안주기
        // 개찰구 역할
        // 현재는 모두 닫혀 있다.

//        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
        final String authorizationHeader;
        String token = "";
        try{
            authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION); //org.springframework.http.HttpHeaders
        }catch (Exception e){
            log.error("헤더를 가져오는 과정에서 에러가 났습니다. 헤더가 null이거나 잘못되었습니다.");
            filterChain.doFilter(request, response);
//            throw new HospitalReviewAppException(ErrorCode.TOKEN_ERROR,String.format("token 오류"));
            return;
        }
        try {
            token = authorizationHeader.split(" ")[1];
            log.info("authorizationHeader:{}", authorizationHeader);
            log.info(token);
        } catch (Exception e) {
            filterChain.doFilter(request, response);
            return;
//            throw new HospitalReviewAppException(ErrorCode.TOKEN_ERROR,String.format("token 오류"));
        }

        if(JwtTokenUtils.isExpired(token, secretKey)){
            filterChain.doFilter(request, response);
            return;
        };
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken("", null, List.of(new SimpleGrantedAuthority("USEr")));
        authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        filterChain.doFilter(request, response);

    }

}
