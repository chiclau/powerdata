/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.exception;

import com.szboanda.platform.common.base.BaseException;

/**
* @Title:  用户模块异常处理类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月11日 蔡楚涛 新建
*/
public class BusinessUserException extends BaseException {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -2347627245944564650L;

    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public BusinessUserException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public BusinessUserException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public BusinessUserException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public BusinessUserException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public BusinessUserException(String message, Throwable cause) {
        super(message, cause);
    }
}
