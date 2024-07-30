package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.*;
import com.yndarksy.maven.summer.userserver.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDX
 * @since 2024-07-24
 */
public interface RoleService extends IService<Role> {

    /**
     * 分页查找Role信息，返回page对象
     * @param queryPageRole
     * @return
     */
    /*
     * 分页查找Role信息，返回page对象
     * */
    Page<Role> getRolePage(QueryPageRole queryPageRole);

    List<Role> getRoleList();

    boolean addRole(AddRole addRole);

    boolean updateRole(UpdateRole updateRole);

    List<RolePermTree> getRolePermTree(Map<String,Object> param);


}
