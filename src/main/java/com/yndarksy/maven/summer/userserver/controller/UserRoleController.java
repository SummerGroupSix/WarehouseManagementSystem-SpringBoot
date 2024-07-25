package com.yndarksy.maven.summer.userserver.controller;

import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.UpdateUserRole;
import com.yndarksy.maven.summer.userserver.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/userRole")
public class UserRoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping("/update")
    public Result<?> updateUserRole(@RequestBody UpdateUserRole userRole){
        Boolean s = userRoleService.updateUser(userRole);
        if (!s){
            return new Result<>().error("更新失败").put(s);
        }
        else{
            return new Result<>().success("更新成功").put(s);
        }
    }
}
