package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class UpdatePerm {
    private Integer id;
    private String permName;
    private String permType;
    private String permKey;
    //操作人
    private String operator;
}
