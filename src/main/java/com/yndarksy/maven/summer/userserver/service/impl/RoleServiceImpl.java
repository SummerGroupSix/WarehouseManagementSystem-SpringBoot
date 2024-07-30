package com.yndarksy.maven.summer.userserver.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.dto.*;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.mapper.RoleMapper;
import com.yndarksy.maven.summer.userserver.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

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
    public Page<Role> getRolePage(QueryPageRole queryPageRole) {

        Page<Role> page = new Page<>(queryPageRole.getPageNumber(),queryPageRole.getPageSize());

        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        if(!ObjectUtils.isEmpty(queryPageRole.getRole_name())){
            queryWrapper.like("role_name",queryPageRole.getRole_name());
        }


        return this.page(page,queryWrapper);
    }

    @Override
    public List<Role> getRoleList() {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("id","role_name");
        return roleMapper.selectList(queryWrapper);
    }

    @Override
    public boolean addRole(AddRole addRole) {
        Role role = new Role();

        BeanUtils.copyProperties(addRole,role);

        return this.save(role);

    }

    @Override
    public boolean updateRole(UpdateRole updateRole) {
        Role role = new Role();
        BeanUtils.copyProperties(updateRole,role);

        return this.updateById(role);
    }

    @Override
    public List<RolePermTree> getRolePermTree(Map<String, Object> param) {
        return roleMapper.selectRolePermTree(param);
    }
}
