package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.ProductDto;
import com.yndarksy.maven.summer.userserver.entity.Product;
import com.yndarksy.maven.summer.userserver.query.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
public interface IProductService extends IService<Product> {

    void updateProductById(ProductDto productDto);

//    PageDTO<Product> queryProductPage(PageQuery pageQuery);

    Page<Product> getPageProduct(PageQuery pageQuery);

    List<Map<String, Object>>getProductCountByBrand();
}
