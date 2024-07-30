package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class QueryPageRole {
    private Long pageSize;
    private Long pageNumber;
    //角色名
    private String role_name;
}
