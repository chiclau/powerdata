/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.emdc.select.dataresourse.provider.impl;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.ProviderException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.szboanda.emdc.select.dataresourse.provider.AbstractConnectionProvider;

/**
* @Title: 基础平台4.0 数据连接提供实现类
* @author  TODO
* @since   JDK1.6
* @history 2017年6月7日 TODO 新建
*/
public class Platform4ConnectionProvider extends AbstractConnectionProvider {

    /**
     * 数据源集合
     */
    private Map<String, DataSource> dataSources = new HashMap<String, DataSource>();

    /**
     * 工具类名
     */
    private String className = "com.szboanda.platform.common.utils.SpringBeanUtils";

    /**
     * 获取数据源方法名称
     */
    private String methodName = "getSpringBean";

    /**
     * 获取数据源方法对象
     */
    private Method getSpringBeanMethod = null;

    /**
     * 数据源连接实现类
     * @throws ClassNotFoundException
     * @throws NoSuchMethodException
     * @throws SecurityException
     */
    @SuppressWarnings("unchecked")
    public Platform4ConnectionProvider() throws ClassNotFoundException, NoSuchMethodException, SecurityException {
        @SuppressWarnings("rawtypes")
        Class toolClass = Class.forName(className);
        getSpringBeanMethod = toolClass.getDeclaredMethod(methodName, String.class);
        System.out.println(getSpringBeanMethod);
    }

    /**
     * 获取数据源
     * @param dataSourceName 数据源名称
     * @return
     * @throws NamingException
     * @throws InvocationTargetException 
     * @throws IllegalArgumentException 
     * @throws IllegalAccessException 
     */
    private DataSource getDataSource(String dataSourceName) throws NamingException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        if (null != dataSources.get(dataSourceName)) {
            return dataSources.get(dataSourceName);
        }
        return (DataSource) getSpringBeanMethod.invoke(null, dataSourceName);
    }

    @Override
    public Connection getConnection(String dataSourceName) throws ProviderException {
        try {
            DataSource ds = this.getDataSource(dataSourceName);
            return ds.getConnection();
        } catch (Exception e) {
            throw new ProviderException("获取连接报错", e);
        }
    }

}
