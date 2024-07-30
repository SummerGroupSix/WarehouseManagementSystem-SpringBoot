package com.yndarksy.maven.summer.userserver.controller;


import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.DepotDto;
import com.yndarksy.maven.summer.userserver.dto.DepotSaveDTO;
import com.yndarksy.maven.summer.userserver.entity.Depot;
import com.yndarksy.maven.summer.userserver.entity.DepotProduct;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IDepotProductService;
import com.yndarksy.maven.summer.userserver.service.IDepotService;
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
@RequestMapping("/depot")
public class DepotController {
    @Resource
    private IDepotService depotService;
    @Resource
    private IDepotProductService depotProductService;

    @PostMapping("/add")
    public Result addDepot(@RequestBody DepotSaveDTO depotSaveDTO){
        Depot depot = new Depot();
        BeanUtil.copyProperties(depotSaveDTO,depot);
        depot.setDepotRemain(Integer.valueOf(depotSaveDTO.getDepotCapacity()));
        depotService.save(depot);
        return new Result<>().success();
    }

    @GetMapping("/delete")
    public Result rmDepotById(Integer id){
        depotService.removeById(id);
        QueryWrapper<DepotProduct> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("depot_id",id);
        depotProductService.remove(queryWrapper);
        return new Result<>().success();
    }

    @PostMapping("/update")
    public Result updateDepotById(@RequestBody DepotDto depotDto){
        return depotService.updateDepotById(depotDto);
    }

//    @GetMapping("/list")
//    public Result<List<Depot>> listProducts(){
//        List<Depot> list = depotService.lambdaQuery().list();
//        return new Result<List<Depot>>().put(list);
//    }

//    @GetMapping("/page")
//    public Result<PageDTO<Depot>> queryDepotPage(PageQuery pageQuery){
//        return new Result<PageDTO<Depot>>().put(depotService.queryDepotPage(pageQuery));
//    }

    @GetMapping("/page")
    public Result<?> page( PageQuery pageQuery){
        Page<Depot> pageDepot = depotService.getPageDepot(pageQuery);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageDepot.getTotal());
        jsonObject.put("rows",pageDepot.getRecords());
        return new Result<>().success().put(jsonObject);
    }

//    @GetMapping("/list/{id}")
//    public Result<List<DepotProduct>> listDepotProducts(@PathVariable Integer id){
//        List<DepotProduct> list = depotProductService.lambdaQuery().eq(DepotProduct::getDepotId,id)
//                .list();
//        return new Result<List<DepotProduct>>().put(list);
//    }

}
