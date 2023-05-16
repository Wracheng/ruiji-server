package org.wrang.reggie.common;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;

import java.time.LocalDateTime;

/**
 * @author Mr.Wang
 * @version 1.0
 * @since 1.8
 * 用来对实体类一些通用的更新数据进行配置，配合实体类中@TableField注解使用
 */
public class myMetaObjectHandler implements MetaObjectHandler {
    // 添加时要更新的字段
    @Override
    public void insertFill(MetaObject metaObject) {
        // MetaObject是指元对象，指的是实体类的元对象，持有实体类对象的元信息，并提供了一些基本的操作方法
        metaObject.setValue("createTime",LocalDateTime.now());
        metaObject.setValue("updateTime", LocalDateTime.now());
        metaObject.setValue("createUser",BaseContext.getThreadLocal());
        metaObject.setValue("updateUser",BaseContext.getThreadLocal());

    }
    // 更新时要更新的字段
    @Override
    public void updateFill(MetaObject metaObject) {
        metaObject.setValue("updateTime",LocalDateTime.now());
        metaObject.setValue("updateUser",BaseContext.getThreadLocal());
    }
}
