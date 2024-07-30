package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.QueryPagePerm;
import com.yndarksy.maven.summer.userserver.dto.UpdatePerm;
import com.yndarksy.maven.summer.userserver.entity.Permission;

public interface PermService extends IService<Permission> {
    /**
     * 分页查找
     * @param queryPagePerm
     * @return
     */
    Page<Permission> getPagePerm(QueryPagePerm queryPagePerm);

    /**
     * 更新权限
     * @param perm
     * @return
     */
    Boolean updatePerm(UpdatePerm perm);

    /**
     * 添加权限
     * @param permission
     * @return
     */
    int savePerm(UpdatePerm permission);
}
