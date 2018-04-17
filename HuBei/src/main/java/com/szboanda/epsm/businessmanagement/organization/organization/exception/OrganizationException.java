/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.organization.exception;

import com.szboanda.platform.common.base.BaseException;

/**
* @Title:  监测机构模块异常处理类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
public class OrganizationException extends BaseException {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -3338275329476112440L;

    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public OrganizationException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public OrganizationException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public OrganizationException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public OrganizationException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public OrganizationException(String message, Throwable cause) {
        super(message, cause);
    }
}
