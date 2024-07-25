package com.yndarksy.maven.summer.userserver.vo;

import lombok.Data;

@Data
public class UserRoleAndRoleId {
    private Integer userId;
    private Integer roleId;
    private String roleName;
}
