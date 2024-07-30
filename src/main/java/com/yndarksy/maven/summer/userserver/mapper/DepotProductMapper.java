package com.yndarksy.maven.summer.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.userserver.entity.DepotProduct;
import com.yndarksy.maven.summer.userserver.vo.DepotProductVO;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
public interface DepotProductMapper extends BaseMapper<DepotProduct> {
    @Select("SELECT d.depot_name, p.product_name, SUM(qi.quantity) AS total_quantity " +
            "FROM depot d " +
            "JOIN depot_product qi ON d.id = qi.depot_id " +
            "JOIN product p ON qi.product_id = p.id " +
            "GROUP BY d.depot_name, p.product_name " +
            "ORDER BY d.depot_name, p.product_name")

    List<Map<String, Object>> selectDepotProductQuantity();

    Page<DepotProductVO> selectDepotProductVO(Page<DepotProduct> page, @Param("param")Map<String,Object> param);


}
