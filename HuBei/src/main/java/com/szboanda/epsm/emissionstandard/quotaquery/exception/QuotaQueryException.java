package com.szboanda.epsm.emissionstandard.quotaquery.exception;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title  指标查询异常类
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午10:36:34 
 * @version 1.0
 */
public class QuotaQueryException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = 752230208727634288L;

    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public QuotaQueryException() {
        super();
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public QuotaQueryException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public QuotaQueryException(String message, Throwable cause) {
        super(message, cause);
    }
}
