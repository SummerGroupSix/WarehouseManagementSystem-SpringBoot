package com.yndarksy.maven.summer.userserver.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.config.JWTConfig;
import com.yndarksy.maven.summer.userserver.dto.LoginUser;
import com.yndarksy.maven.summer.userserver.dto.QueryPageUser;
import com.yndarksy.maven.summer.userserver.dto.UpdateUser;
import com.yndarksy.maven.summer.userserver.dto.addUser;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.service.UserService;
import com.yndarksy.maven.summer.userserver.vo.UserPermTree;
import com.yndarksy.maven.summer.userserver.vo.UserRoleAndRoleId;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

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
 * @since 2024-07-18
 */
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;
    @Autowired
    private JWTConfig jwtConfig;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 登录
     * @param user
     * @return
     */
    @PostMapping("/login")
    public Result<?> login(@RequestBody LoginUser user){
        User login = userService.login(user);
        String s = stringRedisTemplate.opsForValue().get(user.getVcode());
        if(ObjectUtils.isEmpty(s)){
            return  new Result<>().error("验证码错误");
        }
        if(!ObjectUtils.isEmpty(login)){
            String sign = jwtConfig.sign(login.getUsername(), login.getPassword());
            JSONObject jsonObject = new JSONObject();

            jsonObject.put("token",sign);
            jsonObject.put("id",login.getId());

            return new Result<>().success("登陆成功").put(jsonObject);
        }
        else{
            return new Result<>().error("密码或账号错误");
        }
    }

    /**
     *
     * @param username
     * @return
     */
    @GetMapping("/selectUser")
    public User selectUser( String username){
        return userService.selectUser(username);
    }

    /**
     * 查找侧边栏信息
     * @param userId
     * @return
     */
    @GetMapping("/menu")
    public Result<?> getMenuByUserId(Integer userId){
        Map<String,Object> map = new HashMap<>();
        map.put("userId",userId);
        List<UserPermTree> userPermTrees = userService.selectUserPermTreeByUserId(map);
        return new Result<>().success().put(userPermTrees);
    }

    /**
     * 查找用户信息（用户名）
     * @param userId
     * @return
     */
    @GetMapping("/info")
    public Result<?> getUserInfo(Integer userId){
        User user = userService.getById(userId);
        JSONObject obj = new JSONObject();
        if(ObjectUtils.isEmpty(user)){
            return new Result<>().error();
        }
        else{
            obj.put("username",user.getUsername());
        }
        //data中的数据格式为json
        return new Result<>().success().put(obj);
    }

    /**
     * 分页查找
     * @param queryPageUser
     * @return
     */
    @GetMapping("/page")
    public Result<?> page(QueryPageUser queryPageUser){
        Page<User> pageUser = userService.getPageUser(queryPageUser);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("total",pageUser.getTotal());
        jsonObject.put("rows",pageUser.getRecords());
        return new Result<>().success().put(jsonObject);
    }

    @GetMapping("/getUserRoleByUserId")
    public Result<?> userRolePage(Integer userId){
        Map<String, Integer> map = new HashMap<>();
        map.put("userId",userId);
        List<UserRoleAndRoleId> userAndRolePageByUsername = userService.getUserAndRolePageByUsername(map);
        log.info(userAndRolePageByUsername.toString());

//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("")
        return new Result<>().success().put(userAndRolePageByUsername);
    }

    /**
     * 上传图片
     * @param file
     * @return
     */
    @PostMapping("/img")
    public Result<?> saveUserImg(MultipartFile file){
        String imgUrl = userService.saveUserImg(file);
        return new Result<>().success().put(imgUrl);
    }

    @PostMapping("/add")
    public Result<?> addUser(@RequestBody addUser user){
        Boolean s = userService.insertUser(user);
        if (!s){
            return new Result<>().error("添加失败").put(s);
        }
        else{
            return new Result<>().success("添加成功").put(s);
        }
    }

    @PostMapping("/update")
    public Result<?> updateUser(@RequestBody UpdateUser user){
        Boolean s = userService.updateUser(user);
        if (!s){
            return new Result<>().error("更新失败").put(s);
        }
        else{
            return new Result<>().success("更新成功").put(s);
        }
    }

    @GetMapping("/delete")
    public Result<?> deleteUser(Integer id){
        Boolean s = userService.removeById(id);
        if (!s){
            return new Result<>().error("删除失败").put(s);
        }
        else{
            return new Result<>().success("删除成功").put(s);
        }
    }

    @GetMapping("/vcode")
    public Result<?> getVcode() {
        String vcode = userService.getVcode();
        return new Result<>().success().put(vcode);
    }

    @GetMapping("/permList")
    public Result<?> getPermListByUserId(Integer id) {
        Map<String, Object> map = new HashMap<>();
        map.put("id",id);
        List<String> strings = userService.selectPermListByUserId(map);
        return new Result<>().success().put(strings);
    }
}
