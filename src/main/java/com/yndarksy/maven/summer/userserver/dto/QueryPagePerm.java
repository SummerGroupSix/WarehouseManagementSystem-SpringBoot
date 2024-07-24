package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class QueryPagePerm {
    //分页字段
    private Long pageSize;
    private Long pageNumber;

    //权限名
    private String permName;
}
