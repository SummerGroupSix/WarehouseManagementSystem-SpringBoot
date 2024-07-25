package com.yndarksy.maven.summer.userserver.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.dto.QueryPageUser;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.mapper.RoleMapper;
import com.yndarksy.maven.summer.userserver.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDX
 * @since 2024-07-24
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {
    @Resource
    RoleMapper roleMapper;

    @Override
    public Page<Role> getRolePage(QueryPageUser queryPageUser) {
        return null;
    }

    @Override
    public List<Role> getRoleList() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","role_name");
        return roleMapper.selectList(queryWrapper);
    }
}
