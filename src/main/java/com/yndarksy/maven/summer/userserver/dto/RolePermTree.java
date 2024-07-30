package com.yndarksy.maven.summer.userserver.dto;

import lombok.Data;

import java.util.List;

@Data
public class RolePermTree {
    private Integer id;
    private String label;
    private List<RolePermTree> children;
}
