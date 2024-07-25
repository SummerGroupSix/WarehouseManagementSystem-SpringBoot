package com.yndarksy.maven.summer.userserver.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.UpdateUserRole;
import com.yndarksy.maven.summer.userserver.entity.UserRole;

public interface UserRoleService extends IService<UserRole> {
    /**
     * 更新角色
     * @param updateUserRole
     * @return
     */
    Boolean updateUser(UpdateUserRole updateUserRole);
}
