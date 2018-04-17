package com.szboanda.epsm.businessmanagement.organization.monitorability.exception;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title  机构监测能力Exception
 * @author 钟声辉 
 * @date 创建时间：2017年10月23日 下午5:15:53 
 * @version 1.0
 */
public class MonitorAbilityException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -7733962418828741686L;
    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public MonitorAbilityException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public MonitorAbilityException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public MonitorAbilityException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public MonitorAbilityException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public MonitorAbilityException(String message, Throwable cause) {
        super(message, cause);
    }
}
