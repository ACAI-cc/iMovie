package com.gec.system.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;


// 做自动填充  工具类
@Component
public class MybatisplusObjectMetaHandler implements MetaObjectHandler {


    // mp 在执行添加的时候，这个方法会被执行
    @Override
    public void insertFill(MetaObject metaObject) {

        this.setFieldValByName("createTime",new Date(),metaObject);

    }

    // mp 在执行修改的时候，这个方法会执行
    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime",new Date(),metaObject);

    }
}