package com.yndarksy.maven.summer.userserver.service;

import com.yndarksy.maven.summer.userserver.dto.AddRolePerm;
import com.yndarksy.maven.summer.userserver.dto.QueryPagePerm;
import com.yndarksy.maven.summer.userserver.entity.RolePerm;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDX
 * @since 2024-07-27
 */
public interface RolePermService extends IService<RolePerm> {
    List<Integer> getRolePermId(Map<String, Object>map);

    boolean giveRolePerm(AddRolePerm addRolePerm);
}
