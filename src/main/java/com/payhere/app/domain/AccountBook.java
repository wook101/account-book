package com.payhere.app.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Getter @Setter @ToString
public class AccountBook {
    private int id;
    private int price;
    private String memo;
    private String category;
    private Date create_at;
    private int user_id;
}
