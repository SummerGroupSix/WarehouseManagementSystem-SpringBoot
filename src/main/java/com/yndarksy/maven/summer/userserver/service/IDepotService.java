package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.DepotDto;
import com.yndarksy.maven.summer.userserver.entity.Depot;
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
public interface IDepotService extends IService<Depot> {

    Result updateDepotById(DepotDto depotDto);

//    PageDTO<Depot> queryDepotPage(PageQuery pageQuery);

    Page<Depot> getPageDepot(PageQuery pageQuery);


}
