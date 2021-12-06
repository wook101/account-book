package com.payhere.app.controller;

import com.payhere.app.dto.UserDto;
import com.payhere.app.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
public class JoinController {

    private final JoinService joinService;

    public JoinController(JoinService joinService){
        this.joinService = joinService;
    }

    //회원가입
    @PostMapping("/join")
    public ResponseEntity join(@RequestBody UserDto userDto){
        boolean joinValid = joinService.join(userDto);
        if (!joinValid)
            return ResponseEntity.status(HttpStatus.CONFLICT).body(Collections.singletonMap("message","이메일 중복"));
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message","회원가입 성공"));
    }



}
