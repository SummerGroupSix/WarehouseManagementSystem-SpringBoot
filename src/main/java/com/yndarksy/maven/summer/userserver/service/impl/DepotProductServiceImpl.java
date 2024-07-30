package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.context.BaseContext;
import com.yndarksy.maven.summer.userserver.dto.DepotProductInDto;
import com.yndarksy.maven.summer.userserver.dto.DepotProductOutDto;
import com.yndarksy.maven.summer.userserver.entity.Depot;
import com.yndarksy.maven.summer.userserver.entity.DepotProduct;
import com.yndarksy.maven.summer.userserver.mapper.DepotProductMapper;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IDepotProductService;
import com.yndarksy.maven.summer.userserver.service.IDepotService;
import com.yndarksy.maven.summer.userserver.vo.DepotProductVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 
 * @since 2024-07-24
 */
@Slf4j
@Service
public class DepotProductServiceImpl extends ServiceImpl<DepotProductMapper, DepotProduct> implements IDepotProductService {

    @Resource
    private IDepotService depotService;
    @Resource
    private IDepotProductService depotProductService;

    @Resource
    private DepotProductMapper depotProductMapper;

    @Override
    public Result savaIn(DepotProductInDto depotProductInDto) {
        Depot depot = depotService.lambdaQuery().eq(Depot::getDepotName, depotProductInDto.getDepotName())
                .one();
        if(depot == null){
            return new Result<>().error("无该仓库");
        }
        Integer depotId = depot.getId();


        QueryWrapper<DepotProduct> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.select("sum(quantity) as sum_quantity")
                .eq("depot_id",depotId)
                .eq("product_id",depotProductInDto.getProductId());

        Map<String, Object> stringObjectMap = depotProductMapper.selectMaps(QueryWrapper).get(0);
        Integer sum = 0;
        if(stringObjectMap != null) {
            sum = Integer.valueOf(stringObjectMap.get("sum_quantity").toString());
        }
        if(depot.getDepotRemain() < sum + depotProductInDto.getQuantity()){
            return new Result<>().error("仓库容量不足");
        }

        Long count = depotProductService.lambdaQuery().eq(DepotProduct::getProductId, depotProductInDto.getProductId())
                .eq(DepotProduct::getDepotId,depotId)
                .count();
        if(count!=0){
            depotProductService.lambdaUpdate().eq(DepotProduct::getProductId,depotProductInDto.getProductId())
                    .eq(DepotProduct::getDepotId,depotId)
                    .setSql("quantity = quantity + " + depotProductInDto.getQuantity())
                    .update();
            depotService.lambdaUpdate().eq(Depot::getDepotName,depotProductInDto.getDepotName())
                    .setSql("depot_remain = depot_remain - "+depotProductInDto.getQuantity())
                    .update();
            return new Result<>().success();
        }
        DepotProduct depotProduct = DepotProduct.builder()
                .depotId(depotId)
                .productId(depotProductInDto.getProductId())
                .quantity(depotProductInDto.getQuantity())
                .updateTime(LocalDateTime.now())
                .createTime(LocalDateTime.now())
                .operatorId(BaseContext.getCurrentId())
                .build();
        save(depotProduct);

        depotService.lambdaUpdate().eq(Depot::getId,depotId)
                .setSql("depot_remain = depot_remain - "+depotProductInDto.getQuantity())
                .update();
        return new Result<>().success();
    }

    @Override
    public Result Out(DepotProductOutDto depotProductOutDto) {
        DepotProduct depotProduct = lambdaQuery().eq(DepotProduct::getProductId, depotProductOutDto.getProductId())
                .eq(DepotProduct::getDepotId,depotProductOutDto.getDepotId())
                .one();
        if(depotProduct.getQuantity() - depotProductOutDto.getQuantity() < 0){
            return new Result<>().error("库存数量不足，无法出库");
        }
        lambdaUpdate().eq(DepotProduct::getProductId,depotProductOutDto.getProductId())
                .eq(DepotProduct::getDepotId,depotProductOutDto.getDepotId())
                .setSql("quantity = quantity - " + depotProductOutDto.getQuantity())
                .update();
        depotService.lambdaUpdate().eq(Depot::getId,depotProductOutDto.getDepotId())
                .setSql("depot_remain = depot_remain + " + depotProductOutDto.getQuantity())
                .update();
        return new Result<>().success();

    }

    @Override
    public List<Map<String, Object>> getProductStorage() {
        List<Map<String, Object>> maps = depotProductMapper.selectDepotProductQuantity();
        return maps;
    }

    @Override
    public Page<DepotProductVO> getDepotProductVO(PageQuery pageQuery) {
        Page<DepotProduct> page = new Page<>(pageQuery.getPageNumber(), pageQuery.getPageSize());

        Map<String,Object> param = new HashMap<>();
        param.put("id",pageQuery.getDepotId());
        if(!ObjectUtils.isEmpty(pageQuery.getName())){
            param.put("name",pageQuery.getName());

        }
        return depotProductMapper.selectDepotProductVO(page, param);
    }
}
