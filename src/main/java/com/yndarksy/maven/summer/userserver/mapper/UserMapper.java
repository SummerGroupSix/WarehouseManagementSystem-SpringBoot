package com.yndarksy.maven.summer.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yndarksy.maven.summer.userserver.entity.User;
import com.yndarksy.maven.summer.userserver.vo.UserPermTree;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YDX
 * @since 2024-07-18
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    List<User> selectUser(String username);

    List<UserPermTree>selectUserPermTreeByUserId(Map<String, Object> param);

    List<String>selectPermListByUserId(Map<String, Object>param);
}