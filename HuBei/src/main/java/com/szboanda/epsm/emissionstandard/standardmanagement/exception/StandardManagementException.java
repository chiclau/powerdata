/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.emissionstandard.standardmanagement.exception;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title:      标准管理
 * @fileName:   StandardManagementException.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
public class StandardManagementException extends BaseException {
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -2347627245944564650L;

    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public StandardManagementException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public StandardManagementException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public StandardManagementException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public StandardManagementException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public StandardManagementException(String message, Throwable cause) {
        super(message, cause);
    }
}
