package com.szboanda.epsm.common.execption;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title  公共异常类
 * @author 钟声辉 
 * @date 创建时间：2017年10月18日 上午8:57:57 
 * @version 1.0
 */
public class CommonException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 131795810670248285L;

    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public CommonException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public CommonException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public CommonException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public CommonException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public CommonException(String message, Throwable cause) {
        super(message, cause);
    }
}
