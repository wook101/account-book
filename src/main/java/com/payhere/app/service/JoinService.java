package com.payhere.app.service;

import com.payhere.app.dao.JoinDao;
import com.payhere.app.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final JoinDao joinDao;
    public JoinService(JoinDao joinDao){
        this.joinDao = joinDao;
    }

    //회원가입
    public Boolean join(UserDto userDto){
        if (validateDuplicateUser(userDto)==1)
            return false;
        joinDao.save(userDto);
        return true;
    }

    //회원 중복 검사
    public int validateDuplicateUser(UserDto userDto){
        return joinDao.findByEmailCount(userDto.getEmail());
    }






}
