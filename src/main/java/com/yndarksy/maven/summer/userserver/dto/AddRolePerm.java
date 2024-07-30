package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class AddRolePerm {

    private Integer roleId;
    private List<Integer> permIdList;
}
