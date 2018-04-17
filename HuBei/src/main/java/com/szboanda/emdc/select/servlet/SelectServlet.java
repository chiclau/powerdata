package com.szboanda.emdc.select.servlet;

import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.szboanda.emdc.select.dataresourse.provider.AbstractConnectionProvider;
import com.szboanda.emdc.select.service.ISelectService;
import com.szboanda.emdc.select.service.impl.SelectServiceImpl;
import com.szboanda.emdc.select.utils.ServletUtils;

/**
 * @title:      通用选择功能Servlet
 * @fileName:   SelectServlet.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public class SelectServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    /**
     * init-param 中数据源class key
     */
    private String dataSourceKey = "dataSourceClass";

    /**
     * init-params
     */
    private Map<String, String> initParams = new HashMap<String, String>();

    /**
     * 初始化操作，完成数据源配置
     * 
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化init-param
        this.initParam(config);
        // 数据源注册
        String dataSourceClassName = config.getInitParameter(dataSourceKey);
        if (StringUtils.isNotEmpty(dataSourceClassName)) {
            AbstractConnectionProvider.register(dataSourceClassName);
        }
    }

    /**
     * 初始化web.xml init-param
     * 
     * @author  赵运
     * @date    2017-08-15
     */
    private void initParam(ServletConfig config) {
        Enumeration<String> keys = config.getInitParameterNames();
        while (keys.hasMoreElements()) {
            String key = keys.nextElement();
            this.initParams.put(key, config.getInitParameter(key));
        }
    }

    /**
     * get请求
     * 
     * @param   request
     * @param   response
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    /**
     * post请求
     * 
     * @param   request
     * @param   response
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 请求方法
        String method = request.getParameter("method");
        ISelectService manager = new SelectServiceImpl();

        if ("queryTemplates".equals(method)) {
            // 查询模板列表
            ServletUtils.outPutJsons(response, manager.queryTemplates(request, response));
        } else if ("queryPointsInfo".equals(method)) {
            // 查询点位数据及分组数据
            ServletUtils.outPutJson(response, manager.queryPointsInfo(request, response));
        } else if ("updateTemplate".equals(method)) {
            // 更新模板
            ServletUtils.outPutBoolean(response, manager.updateTemplate(request, response));
        } else if ("deleteTemplate".equals(method)) {
            // 删除模板
            ServletUtils.outPutBoolean(response, manager.deleteTemplate(request, response));
        } else if ("addTemplate".equals(method)) {
            // 添加模板
            ServletUtils.outPutBoolean(response, manager.addTemplate(request, response));
        }
    }

}