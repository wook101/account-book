package com.payhere.app.service;

import com.payhere.app.dao.LoginDao;
import com.payhere.app.domain.User;
import com.payhere.app.dto.UserDto;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    private final LoginDao loginDao;

    public LoginService(LoginDao loginDao){
        this.loginDao = loginDao;
    }

    public boolean login(UserDto userDto){
        User user = loginDao.findByEmailAndPassword(userDto);
        if (user==null) return false;
        return true;
    }


}
