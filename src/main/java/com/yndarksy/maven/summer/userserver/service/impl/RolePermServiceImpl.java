package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yndarksy.maven.summer.userserver.dto.AddRolePerm;
import com.yndarksy.maven.summer.userserver.dto.QueryPagePerm;
import com.yndarksy.maven.summer.userserver.entity.RolePerm;
import com.yndarksy.maven.summer.userserver.mapper.RolePermMapper;
import com.yndarksy.maven.summer.userserver.service.RolePermService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDX
 * @since 2024-07-27
 */
@Service
public class RolePermServiceImpl extends ServiceImpl<RolePermMapper, RolePerm> implements RolePermService {
    @Resource
    private RolePermMapper rolePermMapper;

    @Override
    public List<Integer> getRolePermId(Map<String, Object> map) {
        return rolePermMapper.selectRolePermID(map);
    }

    @Override
    public boolean giveRolePerm(AddRolePerm addRolePerm) {

        QueryWrapper<RolePerm> w = new QueryWrapper<>();
        w.eq("role_id", addRolePerm.getRoleId());
        this.remove(w);

        Integer roleId = addRolePerm.getRoleId();
        List<Integer> permIdList = addRolePerm.getPermIdList();
        
        for(Integer permId : permIdList) {
            RolePerm rolePerm = new RolePerm();
            rolePerm.setRoleId(roleId);
            rolePerm.setPermId(permId);
            this.save(rolePerm);
        }

        return true;
    }
}
