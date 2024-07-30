package com.yndarksy.maven.summer.userserver.vo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Inventory {
    private Integer id;
    private Integer depotId;
    private Integer productId;
    private Integer quantity;
    private LocalDateTime updateTime;
    private LocalDateTime createTime;
    private Integer operatorId;
}
