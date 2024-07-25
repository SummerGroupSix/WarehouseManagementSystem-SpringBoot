package com.yndarksy.maven.summer.userserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.config.JWTConfig;
import com.yndarksy.maven.summer.userserver.dto.QueryPageUser;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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


    @GetMapping("/rolepage")
    public Result<?> getRolePage(QueryPageUser queryPageUser) {
        Page<Role> rolePage = roleService.getRolePage(queryPageUser);
        JSONObject obj = new JSONObject();
        obj.put("total", rolePage.getTotal());
        obj.put("rows", rolePage.getRecords());

        return new Result<>().success().put(obj);
    }

    @GetMapping("/roleNameList")
    public Result<?> getRoleNameList() {
        return new Result<>().success().put(roleService.getRoleList());
    }

    @PostMapping("/roleChange")
    public Result<?> roleChange() {
        return new Result<>().success().put(roleService.getRoleList());
    }
}
