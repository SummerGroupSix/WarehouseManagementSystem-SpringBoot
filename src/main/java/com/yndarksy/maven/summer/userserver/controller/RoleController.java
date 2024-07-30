package com.yndarksy.maven.summer.userserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.config.JWTConfig;
import com.yndarksy.maven.summer.userserver.dto.*;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author YDX
 * @since 2024-07-24
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Resource
    private RoleService roleService;

    /**
     * 获取所有角色名
     * @return
     */
    @GetMapping("/roleNameList")
    public Result<?> getRoleNameList() {
        return new Result<>().success().put(roleService.getRoleList());
    }

    /**
     * 授予角色
     * @return
     */
    @PostMapping("/roleChange")
    public Result<?> roleChange() {
        return new Result<>().success().put(roleService.getRoleList());
    }

    @GetMapping("/rolepage")
    public Result<?> getRolePage(QueryPageRole queryPageRole) {
        Page<Role> rolePage = roleService.getRolePage(queryPageRole);
        JSONObject obj = new JSONObject();
        obj.put("total", rolePage.getTotal());
        obj.put("rows", rolePage.getRecords());

        return new Result<>().success().put(obj);
    }

    @PostMapping("/add")
    public Result<?> addRole(@RequestBody AddRole addrole) {
        boolean flag = roleService.addRole(addrole);
        if (flag) {
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }

    @PostMapping("/update")
    public Result<?> updateRole(@RequestBody UpdateRole updateRole) {
        boolean flag = roleService.updateRole(updateRole);
        if (flag) {
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }

    @GetMapping("/delete")
    public Result<?> deleteRole(Integer id) {
        boolean result = roleService.removeById(id);
        if (result) {
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }

    @GetMapping("/assign")
    public Result<?> assignRole(Integer parentId) {
        Map<String,Object> map = new HashMap<>();
        map.put("parentId",parentId);
        List<RolePermTree> list = roleService.getRolePermTree(map);
        return new Result<>().success().put(list);
    }
}
