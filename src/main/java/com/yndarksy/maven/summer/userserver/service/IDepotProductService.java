package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.DepotProductInDto;
import com.yndarksy.maven.summer.userserver.dto.DepotProductOutDto;
import com.yndarksy.maven.summer.userserver.entity.DepotProduct;
import com.yndarksy.maven.summer.userserver.query.PageQuery;
import com.yndarksy.maven.summer.userserver.vo.DepotProductVO;

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
public interface IDepotProductService extends IService<DepotProduct> {

    Result savaIn(DepotProductInDto depotProductDto);

    Result Out(DepotProductOutDto depotProductOutDto);

    /**
     * 查询满足库存占比图需求格式的数据
     * @author YDX
     * @return
     */
    List<Map<String, Object>>  getProductStorage();

    Page<DepotProductVO> getDepotProductVO(PageQuery pageQuery);

}
