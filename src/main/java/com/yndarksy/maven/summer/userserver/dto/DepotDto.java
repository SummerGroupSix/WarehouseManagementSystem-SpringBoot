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
public class DepotDto implements Serializable {

    private Integer id;
    /**
     * 仓库名称
     */
    private String depotName;

    /**
     * 仓库地址
     */
    private String depotAddress;

    /**
     * 仓库容量
     */
    private String depotCapacity;
}
