/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.emdc.select.dataresourse.provider;

import java.sql.Connection;

import com.szboanda.emdc.select.utils.ServletUtils;

/**
* @Title: 获取数据源抽象方法
* @author  zhangsheng
* @since   JDK1.6
* @history 2017年6月2日 zhangsheng 新建
*/
public abstract class AbstractConnectionProvider {

    /**
     * 单例
     */
    private static AbstractConnectionProvider connectionProvider = null;

    /**
     * 数据源获取类名称
     */
    private static String className;

    /**
     * 注册实现类
     * @param className
     */
    public static void register(String className) {
        AbstractConnectionProvider.className = className;
    }

    /**
     * 
     * @return
     * @throws DataSourceException
     */
    public static AbstractConnectionProvider getInstance() throws Exception {
        if (null == connectionProvider) {
            if (null == className) {
                throw new Exception("数据源实现类className=" + className);
            }
            connectionProvider = (new ServletUtils.Reflect<AbstractConnectionProvider>()).getInstanct(className);
        }
        return connectionProvider;
    }

    /**
     * 获取数据连接
     * @param dataSourceName 数据源名称
     * @return
     */
    public abstract Connection getConnection(String dataSourceName);

    /**
     * 关闭连接
     */
    public void Close(Connection connection) {

    }

}
