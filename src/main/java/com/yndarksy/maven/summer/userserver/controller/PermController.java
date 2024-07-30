package com.yndarksy.maven.summer.userserver.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.QueryPagePerm;
import com.yndarksy.maven.summer.userserver.dto.UpdatePerm;
import com.yndarksy.maven.summer.userserver.entity.Permission;
import com.yndarksy.maven.summer.userserver.service.PermService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/perm")
public class PermController {
    @Resource
    private PermService permService;

    /**
     * 分页查找
     * @param queryPagePerm
     * @return
     */
    @GetMapping("/page")
    public Result<?> getPermPage(QueryPagePerm queryPagePerm){
        Page<Permission> pagePerm = permService.getPagePerm(queryPagePerm);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pagePerm.getTotal());
        jsonObject.put("rows",pagePerm.getRecords());
        return new Result<>().success().put(jsonObject);
    }

    /**
     * 更新权限
     * @param updatePerm
     * @return
     */
    @PostMapping("/update")
    public Result<?> updatePerm(@RequestBody UpdatePerm updatePerm){
        Boolean s = permService.updatePerm(updatePerm);
        if (!s){
            return new Result<>().error("更新失败").put(s);
        }
        else{
            return new Result<>().success("更新成功").put(s);
        }
    }

    @GetMapping("/delete")
    public Result<?> deletePerm(Integer id){
        Boolean s = permService.removeById(id);
        if (!s){
            return new Result<>().error("删除失败").put(s);
        }
        else{
            return new Result<>().success("删除成功").put(s);
        }
    }

    @PostMapping("/savePerm")
    public Result<?> savePerm(@RequestBody UpdatePerm permission){
        int s = permService.savePerm(permission);
        if (s<=0){
            return new Result<>().error("插入失败").put(s);
        }
        else{
            return new Result<>().success("插入成功").put(s);
        }
    }
}
