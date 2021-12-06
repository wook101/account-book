package com.payhere.app.dao;

import com.payhere.app.domain.AccountBook;
import com.payhere.app.dto.AccountBookDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.*;

@Repository
public class AccountBookDao {

    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<AccountBook> accountBookRowMapper = BeanPropertyRowMapper.newInstance(AccountBook.class);
    int user_id = 1; //토큰 인증 후 사용자 정보 얻어와야함

    public AccountBookDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //가계부 조회
    public AccountBook selectById(int id){
        String sql = "select * from account_book where id=:id";
        return jdbcTemplate.queryForObject(sql,Collections.singletonMap("id",id) ,accountBookRowMapper);
    }


    //가계부 저장
    public void insert(AccountBookDto accountBookDto){
        String sql = "insert into account_book(price,memo,category,create_at,user_id) " +
                "values(:price,:memo,:category,:create_at,:user_id)";
        Map<String,Object> param = new HashMap<>();
        param.put("price",accountBookDto.getPrice());
        param.put("memo",accountBookDto.getMemo());
        param.put("category",accountBookDto.getCategory());
        param.put("create_at",new Date());
        param.put("user_id",user_id);
        jdbcTemplate.update(sql, param);
    }

    //삭제된 가계부 저장
    public void insertToDeleteAccountBook(AccountBook accountBook){
        String sql = "insert into delete_account_book(price,memo,category,create_at,account_book_id,user_id) " +
                "values(:price,:memo,:category,:create_at,:account_book_id,:user_id)";
        Map<String,Object> param = new HashMap<>();
        param.put("price",accountBook.getPrice());
        param.put("memo",accountBook.getMemo());
        param.put("category",accountBook.getCategory());
        param.put("create_at",new Date());
        param.put("account_book_id",accountBook.getId());
        param.put("user_id",user_id);
        jdbcTemplate.update(sql, param);
    }


    //가계부 수정
    public void update(AccountBookDto accountBookDto, int accountBookId){
        String sql = "update account_book set price=:price, memo=:memo where user_id=:user_id and id=:account_id";
        Map<String, Object> param = new HashMap<>();
        param.put("price",accountBookDto.getPrice());
        param.put("memo",accountBookDto.getMemo());
        param.put("user_id",user_id);
        param.put("account_id",accountBookId);
        jdbcTemplate.update(sql, param);
    }

    //가계부 삭제
    public void delete(int accountBookId){
        String sql = "delete from account_book where user_id=:user_id and id=:account_book_id";
        Map<String,Object> param = new HashMap<>();
        param.put("user_id",user_id);
        param.put("account_book_id",accountBookId);
        jdbcTemplate.update(sql, param);
    }


    //가계부 복구
    //가계부 리스트
    public List<AccountBook> findAll(){
        return null;
    }


}
