package com.payhere.app.service;

import com.payhere.app.dao.AccountBookDao;
import com.payhere.app.domain.AccountBook;
import com.payhere.app.dto.AccountBookDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountBookService {

    private final AccountBookDao accountBookDao;

    public AccountBookService(AccountBookDao accountBookDao){
        this.accountBookDao = accountBookDao;
    }

    public void saveAccountBook(AccountBookDto accountBookDto){
        accountBookDao.insert(accountBookDto);
    }

    public void updateAccountBook(AccountBookDto accountBookDto, int id){
        accountBookDao.update(accountBookDto,id);
    }

    public void deleteAccountBook(int id){
        AccountBook accountBook = accountBookDao.selectById(id);
        accountBookDao.insertToDeleteAccountBook(accountBook);
        accountBookDao.delete(id);
    }



}
