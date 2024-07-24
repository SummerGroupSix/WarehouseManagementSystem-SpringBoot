package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.dto.QueryPagePerm;
import com.yndarksy.maven.summer.userserver.dto.UpdatePerm;
import com.yndarksy.maven.summer.userserver.entity.Permission;
import com.yndarksy.maven.summer.userserver.mapper.PermMapper;
import com.yndarksy.maven.summer.userserver.service.PermService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;

@Service
public class PermServiceImpl extends ServiceImpl<PermMapper, Permission> implements PermService {
    @Resource
    PermMapper permMapper;

    /**
     * 分页查找权限
     * @param queryPagePerm
     * @return
     */
    @Override
    public Page<Permission> getPagePerm(QueryPagePerm queryPagePerm) {
        Page<Permission> page = new Page<>(queryPagePerm.getPageNumber(),queryPagePerm.getPageSize());
        QueryWrapper<Permission> queryWrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(queryPagePerm.getPermName())){
            queryWrapper.like("perm_name",queryPagePerm.getPermName());
        }

        return this.page(page,queryWrapper);
    }

    /**
     * 更新权限
     * @param perm
     * @return
     */
    @Override
    public Boolean updatePerm(UpdatePerm perm) {
        Permission updatePerm = new Permission();
        BeanUtils.copyProperties(perm,updatePerm);
        return this.updateById(updatePerm);
    }
}
