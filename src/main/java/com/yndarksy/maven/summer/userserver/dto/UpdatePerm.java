package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class UpdatePerm {
    private Integer id;
    private String permName;
    private String permType;
    private String permKey;
    private String permUrl;
    //创建人
    private String createBy;
    //更新人
    private String operator;
}
