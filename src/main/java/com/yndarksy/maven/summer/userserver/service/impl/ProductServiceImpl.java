package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.dto.ProductDto;
import com.yndarksy.maven.summer.userserver.entity.Product;
import com.yndarksy.maven.summer.userserver.mapper.ProductMapper;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {
    @Autowired
    private ProductMapper productMapper;

    @Override
    public void updateProductById(ProductDto productDto) {
        // 检查 productDto 是否为 null
        if (productDto == null) {
            throw new IllegalArgumentException("ProductDto must not be null");
        }
        // 检查 ID 是否为 null
        if (productDto.getId() == null) {
            throw new IllegalArgumentException("Product ID must not be null");
        }
        // 使用 LambdaUpdateWrapper 进行更新
        lambdaUpdate()
                .set(productDto.getProductName() != null, Product::getProductName, productDto.getProductName())
                .set(productDto.getProductBrand() != null, Product::getProductBrand, productDto.getProductBrand())
                .set(productDto.getProductOrigin() != null, Product::getProductOrigin, productDto.getProductOrigin())
                .set(productDto.getProductType() != null, Product::getProductType, productDto.getProductType())
                .set(productDto.getProductPrice() != null, Product::getProductPrice, productDto.getProductPrice())
                .set(Product::getUpdateTime, LocalDateTime.now())
                .eq(Product::getId, productDto.getId())
                .update();
    }

    public List<Map<String, Object>> getProductCountByBrand() {
        return productMapper.selectProductCountByBrand();
    }

//    @Override
//    public PageDTO<Product> queryProductPage(PageQuery pageQuery) {
//        Page<Product> page = Page.of(pageQuery.getPageNo(),pageQuery.getPageSize());
//        Page<Product> p = lambdaQuery()
//                .page(page);
//        PageDTO<Product> pageDTO = new PageDTO<>();
//        pageDTO.setTotal(p.getTotal());
//        pageDTO.setPages(p.getPages());
//        List<Product> list = BeanUtil.copyToList(p.getRecords(), Product.class);
//        pageDTO.setList(list);
//        return pageDTO;
//    }

    @Override
    public Page<Product> getPageProduct(@RequestBody PageQuery pageQuery) {
        Page<Product> page = new Page<>(pageQuery.getPageNumber(),pageQuery.getPageSize());
        QueryWrapper<Product> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(pageQuery.getName())){
            queryWrapper.like("product_name",pageQuery.getName());
        }
        return this.page(page,queryWrapper);
    }

}
