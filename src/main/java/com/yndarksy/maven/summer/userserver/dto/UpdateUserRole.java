package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class UpdateUserRole {
    private Integer userId;
    private List<Integer> roleId;
}
