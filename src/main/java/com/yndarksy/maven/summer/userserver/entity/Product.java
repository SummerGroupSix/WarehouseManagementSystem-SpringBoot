package com.yndarksy.maven.summer.userserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
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

    /**
     * 商品更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 商品创建时间
     */
    private LocalDateTime createTime;

    /**
     * 商品创建人
     */
    private String operator;


}
