package com.yndarksy.maven.summer.userserver.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author YDX
 * @since 2024-07-18
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("permission")
public class Permission implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 权限资源名
     */
    @TableField("perm_name")
    private String permName;

    /**
     * 父id
     */
    @TableField("parent_id")
    private Integer parentId;

    /**
     * 权限资源编码
     */
    @TableField("perm_key")
    private String permKey;

    /**
     * 权限资源页面路由
     */
    @TableField("perm_url")
    private String permUrl;

    /**
     * 创建时间
     */
    @TableField(value = "create_time", fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 创建人
     */
    @TableField(value = "create_by", fill = FieldFill.INSERT)
    private String createBy;

    /**
     * 修改时间
     */
    @TableField(value = "update_time", fill = FieldFill.UPDATE)
    private LocalDateTime updateTime;

    /**
     * 修改人
     */
    @TableField("operator")
    private String operator;

    /**
     * 权限类型
     */
    @TableField("perm_type")
    private Integer permType;


}
