package com.szboanda.emdc.select.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
/**
 * @title:      选择功能服务接口
 * @fileName:   ISelectService.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public interface ISelectService {

    /**
     * 获取选择模板列表
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryTemplates(HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新选择模板列表
     * @param   dataInfo 待保存的数据
     * @author  赵运
     * @date    2017-08-15
     */
    boolean addTemplate(HttpServletRequest request, HttpServletResponse response);

    /**
     * 更新选择模板列表
     * @param   dataInfo 待保存的数据
     * @author  赵运
     * @date    2017-08-15
     */
    boolean updateTemplate(HttpServletRequest request, HttpServletResponse response);

    /**
     * 删除选择模板列表
     * @param   dataInfo 条件
     * @author  赵运
     * @date    2017-08-15
     */
    boolean deleteTemplate(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取并组装点位数据及分组数据
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    Map<String, Object> queryPointsInfo(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryPoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取地表水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> querySurfaceWaterPoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取环境空气点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryAirPoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取地下水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryGroundWaterPoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取功能区噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryFunctionNoisePoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取区域环境噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryAreaNoisePoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取道路交通噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> queryRoadTrafficNoisePoints(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取对象信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, Object>> querySurfaceWaterObjects(HttpServletRequest request, HttpServletResponse response);
	/**
	 * 获取监测项目
	 * @param request
	 * @param response
	 * @return
	 */
    List<Map<String, Object>> queryMonitorItemObjects(HttpServletRequest request, HttpServletResponse response);
    /**
     * 获取监测项目分组信息
     * @return
     */
    List<Map<String, String>> getMonitorItemGroup();
    /**
     * 获取分组信息住方法
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getGroupData(HttpServletRequest request, HttpServletResponse response);

    /**
     * 获取自定义分组信息
     * @param   groupsInfo 分组信息
     * @author  赵运
     * @date    2017-08-17
     */
    List<Map<String, String>> getCustomGroup(String groupsInfo);

    /**
     * 获取地表水测点分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getWaterPointGroup();

    /**
     * 获取地表水对象分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getWaterObjectGroup();

    /**
     * 获取环境空气测点分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getAirPointGroup();

    /**
     * 获取地下水点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getGroundWaterGroup();

    /**
     * 获取功能区噪声点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getFunctionNoiseGroup();

    /**
     * 获取区域环境噪声点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getAreaNoiseGroup();

    /**
     * 获取道路交通噪声点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    List<Map<String, String>> getRoadTrafficNoiseGroup();
}
