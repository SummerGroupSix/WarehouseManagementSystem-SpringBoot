package com.yndarksy.maven.summer.userserver.vo;

import lombok.Data;

import java.util.List;

@Data
public class UserPermTree {
    private Integer id;
    private String permName;
    private String permType;
    private String permKey;
    private String permUrl;
    private List<UserPermTree> children;
}
