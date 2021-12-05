package com.payhere.app.dao;

import com.payhere.app.domain.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.Collections;



@Repository
public class RegisterDao {
    private NamedParameterJdbcTemplate jdbcTemplate;
    private RowMapper<User> userRowMapper = BeanPropertyRowMapper.newInstance(User.class);

    public RegisterDao(DataSource dataSource){
        this.jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    public User findByEmail(String email){
        String sql = "select id,email,password from user where email=:email";
        return jdbcTemplate.queryForObject(sql, Collections.singletonMap("email",email), userRowMapper);
    }

}
