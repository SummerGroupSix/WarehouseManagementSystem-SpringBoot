package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.dto.UpdateUserRole;
import com.yndarksy.maven.summer.userserver.entity.UserRole;
import com.yndarksy.maven.summer.userserver.mapper.UserRoleMapper;
import com.yndarksy.maven.summer.userserver.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {
    @Autowired
    private UserRoleService userRoleService;
    @Override
    public Boolean updateUser(UpdateUserRole updateUserRole) {
        log.info(updateUserRole.toString());
        QueryWrapper<UserRole> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",updateUserRole.getUserId());
        userRoleService.remove(queryWrapper);

        UserRole userRole = new UserRole();
        userRole.setUserId(updateUserRole.getUserId());

        for (int i = 0; i < updateUserRole.getRoleId().size(); i++) {
            userRole.setId(null);
            userRole.setRoleId(updateUserRole.getRoleId().get(i));
            if(!userRoleService.save(userRole)){return false;}
        }

        return true;
    }
}
