package com.yndarksy.maven.summer.userserver.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.yndarksy.maven.summer.userserver.dto.*;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.vo.UserPermTree;
import com.yndarksy.maven.summer.userserver.vo.UserRoleAndRoleId;
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

    /**
     * 通过用户名查找用户
     * @param username
     * @return
     */
    User selectUser(String username);

    /**
     * 通过用户id查找用户
     * @param id
     * @return
     */
    User selectUserById(Integer id);

    /**
     *  用于查找侧边栏信息
     * @param param
     * @return
     */
    List<UserPermTree> selectUserPermTreeByUserId(Map<String,Object> param);

    /**
     * 用于分页查找，返回Page对象
     * @param queryPageUser
     * @return
     */
    Page<User> getPageUser(QueryPageUser queryPageUser);

    /**
     * 用于保存前端上传图片，传入为图片，返回图片URL
     * @param multipartFile
     * @return
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

    /**
     * 通过userId获取侧边栏权限
     * @param map
     * @return
     */
    List<String>selectPermListByUserId(Map<String, Object>map);

    /**
     * 通过用户id获取角色
     * @param map
     * @return
     */
    List<UserRoleAndRoleId> getUserAndRolePageByUsername(Map<String, Integer> map);


}
