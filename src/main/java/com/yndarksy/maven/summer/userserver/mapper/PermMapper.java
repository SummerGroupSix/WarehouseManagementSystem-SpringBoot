package com.yndarksy.maven.summer.userserver.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yndarksy.maven.summer.userserver.entity.Permission;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PermMapper extends BaseMapper<Permission> {
}
