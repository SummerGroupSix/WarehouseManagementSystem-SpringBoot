package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class addUser {
    private String username;
    private String password;
    private String sex;
    private String age;
    private String address;
    private String imgUrl;
}
