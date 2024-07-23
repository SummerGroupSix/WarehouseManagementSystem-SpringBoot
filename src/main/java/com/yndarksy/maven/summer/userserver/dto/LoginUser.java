package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class LoginUser {
    private String username;
    private String password;
    private Integer id;
    private String vcode;
}
