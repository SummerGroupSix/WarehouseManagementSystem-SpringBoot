package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

@Data
public class UpdateRole {
    private int id;
    private String roleCode;
    private String roleName;
    private String operator;
}
