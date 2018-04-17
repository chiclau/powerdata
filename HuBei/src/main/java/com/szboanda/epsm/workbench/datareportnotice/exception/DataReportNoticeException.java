package com.szboanda.epsm.workbench.datareportnotice.exception;

import com.szboanda.platform.common.base.BaseException;

/**
 * @title  数据催报异常类
 * @author 钟声辉 
 * @date 创建时间：2017年10月19日 下午6:51:11 
 * @version 1.0
 */
public class DataReportNoticeException extends BaseException {

	/**
	 * 序列号
	 */
	private static final long serialVersionUID = -6777958179210768099L;
	
    /**
     * 构造一个不带参数的异常实例
     * 
     */
    public DataReportNoticeException() {
        super();
    }
    
    /**
     * 使用指定异常构造一个异常实例
     * 
     * @param message
     */
    public DataReportNoticeException(Exception ex) {
        super(ex);
    }

    /**
     * 使用指定消息构造一个异常实例
     * 
     * @param message
     */
    public DataReportNoticeException(String message) {
        super(message);
    }

    /**
     * 使用指定消息和异常码构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public DataReportNoticeException(String message, String code) {
        super(message, code);
    }

    /**
     * 使用指定消息和cause构造一个异常实例
     * 
     * @param message
     * @param cause
     */
    public DataReportNoticeException(String message, Throwable cause) {
        super(message, cause);
    }
}
