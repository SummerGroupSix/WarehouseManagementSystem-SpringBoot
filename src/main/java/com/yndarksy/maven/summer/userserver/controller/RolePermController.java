package com.yndarksy.maven.summer.userserver.controller;

import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.AddRolePerm;
import com.yndarksy.maven.summer.userserver.service.RolePermService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/rolePerm")
public class RolePermController {
    @Resource
    private RolePermService rolePermService;

    @GetMapping("/permid")
    public Result<?> getPermId(Integer roleId) {
        Map<String, Object> map = new HashMap<>();
        map.put("roleId", roleId);
        List<Integer> list = rolePermService.getRolePermId(map);
        return new Result<>().success().put(list);
    }

    @PostMapping("/giveperm")
    public Result<?> givePerm(@RequestBody AddRolePerm addRolePerm) {

        boolean flag = rolePermService.giveRolePerm(addRolePerm);
        if (flag){
            return new Result<>().success();
        }else {
            return new Result<>().error();
        }
    }
}
