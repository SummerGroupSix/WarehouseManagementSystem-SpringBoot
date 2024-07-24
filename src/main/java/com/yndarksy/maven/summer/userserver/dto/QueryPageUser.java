package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class QueryPageUser {
    //分页字段
    private Long pageSize;
    private Long pageNumber;

    //用户名
    private String username;
    //角色名
    private String roleName;
}
