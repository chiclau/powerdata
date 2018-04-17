/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd
 * All Rights Reserved.
 * 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
 * 复制、修改或发布本软件.
 *****************************************************************************/

package com.szboanda.platform.common.utils;

import org.springframework.context.ApplicationContext;

import com.szboanda.platform.common.framext.extension.SpringContextHolder;

/**
 * @title:      获取数据源工具类:通用选择功能使用
 * @fileName:   SpringBeanUtils.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public final class SpringBeanUtils {

    private SpringBeanUtils() {
    }

    /**
     * 获取数据源
     * @param   key
     * @return  Object
     *
     * @author  赵运
     * @date:   2017-08-15
     */
    public static Object getSpringBean(String key) {
        try {
            ApplicationContext context = SpringContextHolder.getApplicationContext();
            return context != null ? context.getBean(key) : null;
        } catch (Exception e) {
            return null;
        }
    }
}
