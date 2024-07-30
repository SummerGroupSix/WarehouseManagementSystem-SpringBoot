package com.yndarksy.maven.summer.userserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.DepotProductOutDto;
import com.yndarksy.maven.summer.userserver.entity.Depot;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IDepotProductService;
import com.yndarksy.maven.summer.userserver.service.IProductService;
import com.yndarksy.maven.summer.userserver.vo.DepotProductVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/depot-product")
public class DepotProductController {
    @Resource
    private IDepotProductService depotProductService;

    @Resource
    private IProductService productService;
    @GetMapping("/getGraphStorage")
    public Result getGraphStorage(){
        return new Result<>().success().put(depotProductService.getProductStorage());
    }

    @GetMapping("/page")
    public Result<?> page(PageQuery pageQuery){

//        List<DepotProductVO> depotProductVOList = new ArrayList<>();
//        List<DepotProduct> list = depotProductService.lambdaQuery().eq(DepotProduct::getDepotId, pageQuery.getDepotId())
//                .list();
//        for (DepotProduct depotProduct : list) {
//            Product product = productService.lambdaQuery().eq(Product::getId, depotProduct.getProductId())
//                    .one();
//            DepotProductVO depotProductVO = BeanUtil.copyProperties(product, DepotProductVO.class);
//            depotProductVO.setProductId(product.getId());
//            depotProductVO.setOperatorId(BaseContext.getCurrentId());
//            depotProductVO.setUpdateTime(depotProduct.getUpdateTime());
//            depotProductVO.setQuantity(depotProduct.getQuantity());
//            depotProductVOList.add(depotProductVO);
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("total",depotProductVOList.size());
//        jsonObject.put("rows",depotProductVOList);
//        return new Result<>().success().put(jsonObject);
        Page<DepotProductVO> page = depotProductService.getDepotProductVO(pageQuery);
        JSONObject obj = new JSONObject();
        obj.put("rows",page.getRecords());
        obj.put("total",page.getTotal());
        return new Result<>().success().put(obj);
    }

    @PostMapping("/out")
    public Result ProductOutDepot(@RequestBody DepotProductOutDto depotProductOutDto){
        return depotProductService.Out(depotProductOutDto);
    }
}
