package com.yndarksy.maven.summer.userserver.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.context.BaseContext;
import com.yndarksy.maven.summer.userserver.dto.DepotProductInDto;
import com.yndarksy.maven.summer.userserver.dto.DepotProductOutDto;
import com.yndarksy.maven.summer.userserver.dto.ProductDto;
import com.yndarksy.maven.summer.userserver.entity.Product;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IDepotProductService;
import com.yndarksy.maven.summer.userserver.service.IProductService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/product")
public class ProductController {

    @Resource
    private IProductService productService;

    @PostMapping("/add")
    public Result addProduct(@RequestBody ProductDto productDto){
        Product product = BeanUtil.copyProperties(productDto, Product.class);
        product.setCreateTime(LocalDateTime.now());
        product.setUpdateTime(LocalDateTime.now());
        product.setOperator(BaseContext.getCurrentId().toString());
        productService.save(product);
        return new Result<>().success();
    }

    @GetMapping("/delete")
    public Result rmProductById(Integer id){
        Boolean s =productService.removeById(id);
        if (!s){
            return new Result<>().error("删除失败").put(s);
        }
        else{
            return new Result<>().success("删除成功").put(s);
        }
    }

    @PostMapping("/update")
    public Result updateProductById(@RequestBody ProductDto productDto){
        productService.updateProductById(productDto);
        return new Result<>().success();
    }


    @GetMapping("/page")
    public Result<?> page( PageQuery pageQuery){
        Page<Product> pageProduct = productService.getPageProduct(pageQuery);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageProduct.getTotal());
        jsonObject.put("rows",pageProduct.getRecords());
        return new Result<>().success().put(jsonObject);
    }

    @Resource
    private IDepotProductService depotProductService;

    @PostMapping("/in")
    public Result ProductInDepot(@RequestBody DepotProductInDto depotProductInDto){
        return depotProductService.savaIn(depotProductInDto);
    }

    @GetMapping("/getBrandPart")
    public Result getBrandPart(){
        return new Result<>().success().put(productService.getProductCountByBrand());
    }

}
