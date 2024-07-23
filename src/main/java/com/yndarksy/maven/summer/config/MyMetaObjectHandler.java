package com.yndarksy.maven.summer.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.time.LocalDateTime;

/**
 * 字段填充
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {
    /**
     * 新增操作时填充
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        Object createTime = getFieldValByName("createTime", metaObject);
        if (ObjectUtils.isEmpty(createTime)) {
            log.info("开始插入填充...");
            this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
        }

    }

    /**
     * 更新时填充
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        Object updateTime = getFieldValByName("updateTime", metaObject);
        if(ObjectUtils.isEmpty(updateTime)){
            setFieldValByName("updateTime",LocalDateTime.now(),metaObject);
        }
    }
}
