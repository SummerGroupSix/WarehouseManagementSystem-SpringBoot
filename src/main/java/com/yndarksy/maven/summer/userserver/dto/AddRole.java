package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddRole {
    private String roleCode;
    private String roleName;
    private String createBy;
}

