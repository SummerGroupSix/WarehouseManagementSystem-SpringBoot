package com.yndarksy.maven.summer.userserver.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

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
public class DepotProductInDto implements Serializable {
    private Integer productId;
    private String depotName;
    /**
     * 仓库中的商品数量
     */
    private Integer quantity;
}
