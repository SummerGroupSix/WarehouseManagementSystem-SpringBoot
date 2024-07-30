package com.yndarksy.maven.summer.userserver.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DepotProductVO {

    private Integer productId;
    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 仓库中的商品数量
     */
    private Integer quantity;

    /**
     * 商品更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 操作人id
     */
    private Integer operatorId;
}
