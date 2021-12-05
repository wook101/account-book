package com.payhere.app.service;

import com.payhere.app.jwt.TokenProvider;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private TokenProvider tokenProvider;

    public LoginService(TokenProvider tokenProvider){
        this.tokenProvider = tokenProvider;
    }




}
