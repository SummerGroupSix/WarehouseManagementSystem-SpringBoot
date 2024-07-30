package com.yndarksy.maven.summer.userserver.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
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
@TableName("depot_product")
@Builder
public class DepotProduct implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer depotId;

    private Integer productId;

    /**
     * 仓库中的商品数量
     */
    private Integer quantity;

    /**
     * 商品更新时间
     */
    private LocalDateTime updateTime;

    /**
     * 商品创建时间
     */
    private LocalDateTime createTime;

    /**
     * 操作人id
     */
    private Integer operatorId;


}
