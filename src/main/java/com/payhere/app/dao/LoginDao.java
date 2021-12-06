package com.payhere.app.dao;

import com.payhere.app.domain.User;
import com.payhere.app.dto.UserDto;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Repository
public class LoginDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public LoginDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    public User findByEmailAndPassword(UserDto userDto){
        String sql = "select id,email,password from user where email=:email and password=:pw";
        Map<String, Object> param = new HashMap<>();
        param.put("email",userDto.getEmail());
        param.put("pw",userDto.getPassword());
        try {
            return jdbcTemplate.queryForObject(sql, param, userRowMapper);
        }
        catch (EmptyResultDataAccessException e){
            return null;
        }
    }


}
