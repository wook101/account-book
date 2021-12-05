package com.payhere.app.dao;

import com.payhere.app.domain.User;
import com.payhere.app.dto.UserDto;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


@Repository
public class JoinDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public JoinDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public User findByEmail(String email){
        String sql = "select id,email,password from user where email=:email";
        return jdbcTemplate.queryForObject(sql, Collections.singletonMap("email",email), userRowMapper);
    }

    //회원 정보 저장하기
    public UserDto save(UserDto userDto){
        String sql = "insert into user(email,password) values(:email,:password)";
        Map<String,Object> param = new HashMap<>();
        param.put("email",userDto.getEmail());
        param.put("password",userDto.getPassword());
        jdbcTemplate.update(sql,param);
        return userDto;
    }

}
