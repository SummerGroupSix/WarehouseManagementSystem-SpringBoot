package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.DepotDto;
import com.yndarksy.maven.summer.userserver.dto.ProductDto;
import com.yndarksy.maven.summer.userserver.entity.Depot;
import com.yndarksy.maven.summer.userserver.entity.DepotProduct;
import com.yndarksy.maven.summer.userserver.mapper.DepotMapper;
import com.yndarksy.maven.summer.userserver.mapper.DepotProductMapper;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.service.IDepotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
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
public class DepotServiceImpl extends ServiceImpl<DepotMapper, Depot> implements IDepotService {

    @Resource
    private DepotProductMapper depotProductMapper;

    @Override
    public Result updateDepotById(DepotDto depotDto) {
        QueryWrapper<DepotProduct> QueryWrapper = new QueryWrapper<>();
        QueryWrapper.select("sum(quantity) as sum_quantity")
                .eq("depot_id",depotDto.getId());
        Map<String, Object> stringObjectMap = depotProductMapper.selectMaps(QueryWrapper).get(0);
        Integer sum = 0;
        if(stringObjectMap != null){
            sum = Integer.valueOf(stringObjectMap.get("sum_quantity").toString());
        }
        if(Integer.valueOf(depotDto.getDepotCapacity()) < sum){
            return new Result<>().error("现仓库中库存容量大于修改后的容量无法修改");
        }
        lambdaUpdate().eq(Depot::getId,depotDto.getId())
                .set(depotDto.getDepotName()!=null,Depot::getDepotName,depotDto.getDepotName())
                .set(depotDto.getDepotAddress()!=null,Depot::getDepotAddress,depotDto.getDepotAddress())
                .set(depotDto.getDepotCapacity()!=null,Depot::getDepotCapacity,depotDto.getDepotCapacity())
                .set(Depot::getDepotRemain,Integer.valueOf(depotDto.getDepotCapacity()) - sum)
                .update();
        return new Result<>().success();
    }

//    @Override
//    public PageDTO<Depot> queryDepotPage(PageQuery pageQuery) {
//        Page<Depot> page = Page.of(pageQuery.getPageNo(),pageQuery.getPageSize());
//        Page<Depot> p = lambdaQuery()
//                .page(page);
//
//        PageDTO<Depot> pageDTO = new PageDTO<>();
//        pageDTO.setTotal(p.getTotal());
//        pageDTO.setPages(p.getPages());
//        List<Depot> list = BeanUtil.copyToList(p.getRecords(), Depot.class);
//        pageDTO.setList(list);
//        return pageDTO;
//    }

    @Override
    public Page<Depot> getPageDepot(PageQuery pageQuery) {
        Page<Depot> page = new Page<>(pageQuery.getPageNumber(),pageQuery.getPageSize());
        QueryWrapper<Depot> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(pageQuery.getName())){
            queryWrapper.like("depot_name",pageQuery.getName());
        }
        return this.page(page,queryWrapper);
    }
}
