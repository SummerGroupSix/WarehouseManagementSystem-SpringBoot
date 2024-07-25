package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.QueryPageUser;
import com.yndarksy.maven.summer.userserver.entity.Role;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDX
 * @since 2024-07-24
 */
public interface RoleService extends IService<Role> {

    /*
     * 分页查找Role信息，返回page对象
     * */
    Page<Role> getRolePage(QueryPageUser queryPageUser);

    List<Role> getRoleList();
}
