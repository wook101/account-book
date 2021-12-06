package com.payhere.app.controller;


import com.payhere.app.dto.UserDto;
import com.payhere.app.jwt.TokenProvider;
import com.payhere.app.service.LoginService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;


@RestController
public class LoginController {

    private final LoginService loginService;
    private TokenProvider tokenProvider;

    public LoginController(LoginService loginService, TokenProvider tokenProvider){
        this.loginService = loginService;
        this.tokenProvider = tokenProvider;
    }

    //로그인
    @PostMapping("/login")
    public ResponseEntity login(@RequestBody UserDto userDto){
        boolean loginVaild = loginService.login(userDto);
        //로그인 실패
        if (!loginVaild)
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Collections.singletonMap("message","이메일 또는 비밀번호가 일치하지 않습니다."));

        //로그인 성공
        String token = tokenProvider.createToken(userDto); //토큰 생성
        return ResponseEntity.status(HttpStatus.OK)
                .body(Collections.singletonMap("accessToken",token));
    }





}
