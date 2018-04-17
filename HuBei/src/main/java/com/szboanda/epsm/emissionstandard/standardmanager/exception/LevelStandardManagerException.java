package com.szboanda.epsm.emissionstandard.standardmanager.exception;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title  
 * @author 钟声辉 
 * @date 创建时间：2017年10月16日 下午7:42:43 
 * @version 1.0
 */
public class LevelStandardManagerException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 1309754337075574176L;

	 /**
     * 构造一个不带参数的异常实例
     * 
     */
    public LevelStandardManagerException() {
        super();
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public LevelStandardManagerException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public LevelStandardManagerException(String message, Throwable cause) {
        super(message, cause);
    }
}
