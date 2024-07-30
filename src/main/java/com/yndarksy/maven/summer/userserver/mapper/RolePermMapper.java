package com.yndarksy.maven.summer.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yndarksy.maven.summer.userserver.entity.RolePerm;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author YDX
 * @since 2024-07-27
 */
@Mapper
public interface RolePermMapper extends BaseMapper<RolePerm> {
    List<Integer> selectRolePermID(Map<String, Object> param);
}
