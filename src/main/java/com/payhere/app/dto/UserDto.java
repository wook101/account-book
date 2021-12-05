package com.payhere.app.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class UserDto {
    private String email;
    private String password;
}
