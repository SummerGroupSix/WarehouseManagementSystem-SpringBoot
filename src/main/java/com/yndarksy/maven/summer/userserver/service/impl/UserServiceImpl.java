package com.yndarksy.maven.summer.userserver.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.util.BeanUtil;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.*;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.mapper.UserMapper;
import com.yndarksy.maven.summer.userserver.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yndarksy.maven.summer.userserver.vo.UserPermTree;
import com.yndarksy.maven.summer.userserver.vo.UserRoleAndRoleId;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.Base64Utils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author YDX
 * @since 2024-07-18
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Resource
    UserMapper userMapper;
    @Autowired
    private DefaultKaptcha defaultKaptcha;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 用于登录
     * @param loginUser
     * @return
     */
    @Override
    public User login(LoginUser loginUser) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",loginUser.getUsername());
        queryWrapper.eq("password",loginUser.getPassword());
//        String u = loginUser.getUsername();
//        String p = loginUser.getPassword();

        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    @Override
    public User selectUser(String username) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
        return userMapper.selectOne(queryWrapper);
    }

    /**
     * 通过用户ID查找侧边栏功能
     * @param param
     * @return
     */
    @Override
    public List<UserPermTree> selectUserPermTreeByUserId(Map<String, Object> param) {
        return userMapper.selectUserPermTreeByUserId(param);
    }

    /**
     * 用于分页查找
     * @param queryPageUser
     * @return
     */
    @Override
    public Page<User> getPageUser(QueryPageUser queryPageUser) {

        Page<User> page = new Page<>(queryPageUser.getPageNumber(),queryPageUser.getPageSize());
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();

        if(!ObjectUtils.isEmpty(queryPageUser.getUsername())){
            queryWrapper.like("username",queryPageUser.getUsername());
        }

        return this.page(page,queryWrapper);
    }

    @Override
    public List<UserRoleAndRoleId> getUserAndRolePageByUsername(Map<String, Integer> map) {
        return userMapper.selectUserAndRoleByUsername(map);
    }

    /**
     * 通过用户id查找用户信息
     * @param id
     * @return
     */
    @Override
    public User selectUserById(Integer id) {
        User user = userMapper.selectById(id);
        User formatUser = new User();
        formatUser.setId(id);
        formatUser.setAddress(user.getAddress());
        formatUser.setImgUrl(user.getImgUrl());
        formatUser.setUsername(user.getUsername());
        return formatUser;
    }

    /**
     * 用于存储前端传图，并返回URL
     * @param multipartFile
     * @return
     */
    @Override
    public String saveUserImg(MultipartFile multipartFile) {
        //将文件保存到本地
        String desDir = "E:\\UserImg\\";
        String filename = multipartFile.getOriginalFilename();
        String filepath = desDir + File.separator + filename;
        File file = new File(filepath);
        try {
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        //映射的URL
        String imgUrl = "http://localhost:8088/view/" + filename;
        return imgUrl;
    }

    /**
     * 新增用户信息
     * @param user
     * @return
     */
    @Override
    public Boolean insertUser(addUser user) {
        User targetUser = new User();
        BeanUtils.copyProperties(user,targetUser);
        return this.save(targetUser);
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public Boolean updateUser(UpdateUser user) {
        User updateUser = new User();
        BeanUtils.copyProperties(user,updateUser);
        return this.updateById(updateUser);
    }


    /**
     * 获取验证码
     * @return
     */
    @Override
    public String getVcode() {
        String text = defaultKaptcha.createText();
        BufferedImage image = defaultKaptcha.createImage(text);
        stringRedisTemplate.opsForValue().set(text,text,100, TimeUnit.MINUTES);
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        String code = null;
        try {
            ImageIO.write(image,"jpg",os);
            code = Base64Utils.encodeToString(os.toByteArray());
            code = "data:image/jpeg;base64," + code;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return code;
    }

    @Override
    public List<String> selectPermListByUserId(Map<String, Object> map) {
        return userMapper.selectPermListByUserId(map);
    }


}
