package com.payhere.app.controller;

import com.payhere.app.domain.AccountBook;
import com.payhere.app.dto.AccountBookDto;
import com.payhere.app.jwt.TokenProvider;
import com.payhere.app.service.AccountBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/auth")
public class AccountBookController {

    private final AccountBookService accountBookService;

    public AccountBookController(AccountBookService accountBookService){
        this.accountBookService = accountBookService;
    }

    //가계부 등록
    @PostMapping("/account-book")
    public ResponseEntity saveAccountBook(@RequestBody AccountBookDto accountBookDto){
        accountBookService.saveAccountBook(accountBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(Collections.singletonMap("message","등록 성공"));
    }

    //가계부 수정
    @PutMapping("/account-book/{id}")
    public ResponseEntity updateAccountBook(@RequestBody AccountBookDto accountBookDto,
                                            @PathVariable int id){
        accountBookService.updateAccountBook(accountBookDto,id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message","수정 성공"));
    }


    //가계부 삭제
    @DeleteMapping("/account-book/{id}")
    public ResponseEntity deleteAccountBook(@PathVariable int id){
        accountBookService.deleteAccountBook(id);
        return ResponseEntity.status(HttpStatus.OK).body(Collections.singletonMap("message","삭제 성공"));
    }


    /*
    //가계부 복구
    @PostMapping("/account-book/{id}")
    public ResponseEntity recoveryAccountBook(@RequestBody AccountBookDto accountBookDto){
        accountBookService.saveAccountBook(accountBookDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("등록 성공");
    }

    //가계부 리스트
    @PostMapping("/accountBooks")
    public ResponseEntity accountBooks(){
        List<AccountBook> accountBooks = accountBookService.accountBooks();
        return ResponseEntity.status(HttpStatus.OK).body(accountBooks);
    }
    */
}
