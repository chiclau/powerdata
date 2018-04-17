package com.szboanda.emdc.select.utils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @title:      ServletUtils 辅助类
 * @fileName:   ServletHelper.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public class ServletUtils {

    /**
     * 反射工具
     * 
     * @author:     赵运
     * @date:       2017-08-15
     */
    public static class Reflect<E> {
        /**
         * 根据类名获取无参实例对象
         * 
         * @param className
         * @return
         * @throws Exception 
         */
        public E getInstanct(String className) throws Exception {
            if (StringUtils.isBlank(className)) {
                throw new Exception("实现类className=" + className);
            }
            try {
                @SuppressWarnings("unchecked")
                Class<E> _class = (Class<E>) Class.forName(className);
                E instance = _class.newInstance();
                return instance;
            } catch (ClassNotFoundException e) {
                throw new Exception("找不到实现类:" + className, e);
            } catch (Exception e) {
                throw new Exception("实现类:" + className + "实例化异常：", e);
            }
        }
    }

    /**
     * 组装输出boolean结果
     * 
     * @param   response
     * @param   results 结果对象
     * @author  赵运
     * @date    2017-08-15
     */
    public static void outPutBoolean(HttpServletResponse response, boolean result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.print(result);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 组装输出json字符串
     * 
     * @param   response
     * @param   results 结果对象
     * @author  赵运
     * @date    2017-08-15
     */
    public static void outPutJson(HttpServletResponse response, Map<String, Object> result) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONObject json = JSONObject.fromObject(result);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(json.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    /**
     * 组装输出json字符串数组
     * 
     * @param   response
     * @param   results 结果集合
     * @author  赵运
     * @date    2017-08-15
     */
    public static void outPutJsons(HttpServletResponse response, List<Map<String, Object>> results) {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        JSONArray jsons = JSONArray.fromObject(results);
        PrintWriter out = null;
        try {
            out = response.getWriter();
            out.write(jsons.toString());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
