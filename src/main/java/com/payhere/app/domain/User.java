package com.payhere.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter @Getter @ToString
public class User {
    private int id;
    private String email;
    private String password;
}
