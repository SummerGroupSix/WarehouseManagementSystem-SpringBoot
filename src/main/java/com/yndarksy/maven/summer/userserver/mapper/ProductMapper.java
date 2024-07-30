package com.yndarksy.maven.summer.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yndarksy.maven.summer.userserver.entity.Product;
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
public interface ProductMapper extends BaseMapper<Product> {
    /**
     * 查询不同品牌的数量
     * @author YDX
     * @return
     */
    @Select("SELECT product.product_brand, SUM(depot_product.quantity) AS total_count FROM product,depot_product WHERE product.id=depot_product.product_id GROUP BY product_brand")
    List<Map<String, Object>> selectProductCountByBrand();
}
