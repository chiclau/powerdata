/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.emdc.select.dataresourse.provider.impl;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.szboanda.emdc.select.dataresourse.provider.AbstractConnectionProvider;

/**
* @Title: 基于平台3.0获取数据源的实现
* @author  zhangsheng
* @since   JDK1.6
* @history 2017年6月7日 zhangsheng 新建
*/
public class Platform3ConnectionProvider extends AbstractConnectionProvider {

    /**
     * 数据源集合
     */
    private Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

    /**
     * 获取数据源
     * @param dataSourceName 数据源名称
     * @return
     * @throws NamingException
     */
    private DataSource getDataSource(String dataSourceName) throws NamingException {
        if (null != dataSources.get(dataSourceName)) {
            return dataSources.get(dataSourceName);
        }
        try {
            // 初始化上下文
            Context cxt = new InitialContext();
            // 获取与逻辑名相关联的数据源对象
            DataSource ds = (DataSource) cxt.lookup("java:comp/env/" + dataSourceName);
            dataSources.put(dataSourceName, ds);
            return ds;
        } catch (NamingException e) {
            throw e;
        }
    }

    @Override
    public Connection getConnection(String dataSourceName) {
        try {
            DataSource ds = this.getDataSource(dataSourceName);
            return ds.getConnection();
        } catch (NamingException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
