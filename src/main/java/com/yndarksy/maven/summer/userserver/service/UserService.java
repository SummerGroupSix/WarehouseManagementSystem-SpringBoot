package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.common.result.Result;
import com.yndarksy.maven.summer.userserver.dto.LoginUser;
import com.yndarksy.maven.summer.userserver.dto.QueryPageUser;
import com.yndarksy.maven.summer.userserver.dto.UpdateUser;
import com.yndarksy.maven.summer.userserver.dto.addUser;
import com.yndarksy.maven.summer.userserver.entity.Role;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.vo.UserPermTree;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author YDX
 * @since 2024-07-18
 */
public interface UserService extends IService<User> {
    User login(LoginUser loginUser);

    /*
     * 通过用户名查找用户
     * */
    User selectUser(String username);

    /*
     * 用于查找侧边栏信息
     * */
    List<UserPermTree> selectUserPermTreeByUserId(Map<String,Object> param);

    /*
    * 用于分页查找，返回Page对象
    * */
    Page<User> getPageUser(QueryPageUser queryPageUser);

    /*
    * 用于保存前端上传图片，传入为图片，返回图片URL
    */
    String saveUserImg(MultipartFile multipartFile);

    /**
     * 插入用户信息
     */
    Boolean insertUser(addUser user);

    /**
     * 更新用户
     * @param user
     * @return
     */
    Boolean updateUser(UpdateUser user);

    /**
     * 获取验证码图片
     * @return
     */
    String getVcode();

    List<String>selectPermListByUserId(Map<String, Object>map);

}
