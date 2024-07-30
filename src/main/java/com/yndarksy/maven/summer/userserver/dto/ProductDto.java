package com.yndarksy.maven.summer.userserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto implements Serializable {

    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品类型
     */
    private String productType;

    /**
     * 商品品牌
     */
    private String productBrand;

    /**
     * 商品产地
     */
    private String productOrigin;


}
