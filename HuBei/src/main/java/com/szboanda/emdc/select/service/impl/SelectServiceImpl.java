package com.szboanda.emdc.select.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

import com.szboanda.emdc.select.common.Constants;
import com.szboanda.emdc.select.dao.SelectDAO;
import com.szboanda.emdc.select.dataresourse.ConnectionDB;
import com.szboanda.emdc.select.service.ISelectService;

import net.sf.json.JSONObject;

/**
 * @title:      选择功能服务类
 * @fileName:   SelectServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public class SelectServiceImpl implements ISelectService {

    /**
     * 通用数据库操作
     */
    private final ConnectionDB db = new ConnectionDB(null);

    /**
     * 服务管理操作
     */
    private SelectDAO selectDAO = new SelectDAO(db);

    /**
     * Object 对象转换为string
     * @param   Object
     * @return  String
     * 
     * @author  赵运
     * @date    2017-08-15
     */
    public static String toString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 组装参数map
     * 
     * @param   map     组装的容器   
     * @param   request 
     * @param   key     参数key
     * @author  赵运
     * @date    2017-08-15
     */
    private void addParameter(Map<String, Object> map, HttpServletRequest request, String key) {
        String value = request.getParameter(key);
        map.put(key, value);
    }

    /**
     * 组装参数map
     * 
     * @param   map         组装的容器   
     * @param   map容器key    mapKey 
     * @param   HttpServletRequest参数key     requestkey
     * @author  赵运
     * @date    2017-08-15
     */
    private void addParameterFromJson(Map<String, Object> map, String mapKey, HttpServletRequest request, String paramKey) {
        String params = request.getParameter("urlParams");
        String value = "";
        if (StringUtils.isNotEmpty(params)) {
            JSONObject paramsJson = JSONObject.fromObject(params);
            value = paramsJson.getString(paramKey);
        }
        map.put(mapKey, value);
    }

    /**
     * 获取request中json字符参数urlParams中的paramKey数据
     * 
     * @author  赵运
     * @date    2017-08-15
     */
    private String getParamsValue(HttpServletRequest request, String paramKey) {
        String params = request.getParameter("urlParams");
        JSONObject paramsJson = JSONObject.fromObject(params);
        return paramsJson.getString(paramKey);
    }

    /**
     * 获取点位模板列表
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryTemplates(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> templates = null;

        Map<String, Object> queryInfo = new HashMap<String, Object>();

        this.addParameterFromJson(queryInfo, "MBLX", request, "dataType");
        this.addParameterFromJson(queryInfo, "CJR", request, "lander");

        try {
            templates = selectDAO.queryTemplates(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (templates != null && !templates.isEmpty()) {
            for (Map<String, Object> template : templates) {
                Map<String, Object> result = new HashMap<String, Object>();
                // 模板序号
                result.put("id", template.get("XH"));
                // 模板名称
                result.put("name", template.get("MBMC"));
                // 模板是否公有
                result.put("isPublic", template.get("QXJB"));
                // 创建人
                result.put("createUser", template.get("CJR"));
                // 模板类型
                result.put("templateType", template.get("MBLX"));
                // 模板包含代码串
                String pointCodes = toString(template.get("NRBH"));
                // 模板包含名称串
                String pointNames = toString(template.get("NRMC"));
                result.put("selectedCode", Arrays.asList(pointCodes.split(Constants.SPLIT_COMM)));
                result.put("selectedName", Arrays.asList(pointNames.split(Constants.SPLIT_COMM)));
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 添加选择模板列表
     * @param   dataInfo 待保存的数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public boolean addTemplate(HttpServletRequest request, HttpServletResponse response) {
        int rows = 0;
        Map<String, Object> dataInfo = new HashMap<String, Object>();
        this.addParameter(dataInfo, request, "XH");
        this.addParameter(dataInfo, request, "MBMC");
        this.addParameter(dataInfo, request, "NRBH");
        this.addParameter(dataInfo, request, "NRMC");
        this.addParameter(dataInfo, request, "QXJB");
        this.addParameterFromJson(dataInfo, "MBLX", request, "dataType");
        this.addParameterFromJson(dataInfo, "CJR", request, "lander");
        try {
            rows = selectDAO.addTemplate(dataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0 ? true : false;
    }

    /**
     * 编辑选择模板列表
     * @param   dataInfo 待保存的数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public boolean updateTemplate(HttpServletRequest request, HttpServletResponse response) {
        int rows = 0;
        Map<String, Object> dataInfo = new HashMap<String, Object>();
        this.addParameter(dataInfo, request, "XH");
        this.addParameter(dataInfo, request, "MBMC");
        this.addParameter(dataInfo, request, "NRBH");
        this.addParameter(dataInfo, request, "NRMC");
        this.addParameter(dataInfo, request, "QXJB");
        this.addParameterFromJson(dataInfo, "MBLX", request, "dataType");
        this.addParameterFromJson(dataInfo, "CJR", request, "lander");

        try {
            rows = selectDAO.updateTemplate(dataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0 ? true : false;
    }

    /**
     * 删除选择模板列表
     * @param   dataInfo 条件
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public boolean deleteTemplate(HttpServletRequest request, HttpServletResponse response) {
        int rows = 0;

        Map<String, Object> dataInfo = new HashMap<String, Object>();
        this.addParameter(dataInfo, request, "XH");

        try {
            rows = selectDAO.deleteTemplate(dataInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rows > 0 ? true : false;
    }

    /**
     * 获取并组装点位数据及分组数据
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public Map<String, Object> queryPointsInfo(HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("dataList", queryPoints(request, response));
        result.put("groupList", getGroupData(request, response));
        return result;
    }

    /**
     * 获取点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryPoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        // 数据类型
        String dataType = this.getParamsValue(request, "dataType");

        if (StringUtils.isEmpty(dataType)) {
            try {
                throw new Exception("缺失必要参数");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 判断类型，调用不同的类型点位查询方法
        if (Constants.DXS_CD.equals(dataType)) {
            // 测点-地下水
            results = queryGroundWaterPoints(request, response);
        } else if (dataType.contains(Constants.HL_CD) || dataType.contains(Constants.HK_CD) || dataType.contains(Constants.HLYYS_CD) || dataType.contains(Constants.HKYYS_CD)
                || dataType.contains(Constants.DXSYYS_CD)) {
            // 测点-水
            results = querySurfaceWaterPoints(request, response);
        } else if (dataType.contains(Constants.HL_DX) || dataType.contains(Constants.HK_DX)) {
            // 对象-水
            results = querySurfaceWaterObjects(request, response);
        } else if (Constants.KQ_CD.equals(dataType)) {
            // 测点-空气
            results = queryAirPoints(request, response);
        } else if (Constants.DLJTZS_CD.equals(dataType)) {
            // 测点-道路交通噪声
            results = queryRoadTrafficNoisePoints(request, response);
        } else if (Constants.GNQZS_CD.equals(dataType)) {
            // 测点-功能区噪声
            results = queryFunctionNoisePoints(request, response);
        } else if (Constants.QYHJZS_CD.equals(dataType)) {
            // 测点-区域环境噪声
            results = queryAreaNoisePoints(request, response);
        } else if (Constants.JCXM_CD.equals(dataType)) {
            // 测点-区域环境噪声
            results = queryMonitorItemObjects(request, response);
        }
        return results;

    }

    /**
     * 获取地表水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> querySurfaceWaterPoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "SJLX", request, "dataType");
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryWaterPoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("KZSX", point.get("JGJB")); // 监管级别
                result.put("KZSXMC", point.get("JGJBMC")); // 监管级别名称
                result.put("LYDM", point.get("SSLY")); // 所属流域
                result.put("LYMC", point.get("SSLYMC")); // 所属流域名称
                result.put("SXDM", point.get("SSSX")); // 所属水系
                result.put("SXMC", point.get("SSSXMC")); // 所属水系名称
                result.put("JCFLBS", point.get("JCFLBS"));
                result.put("JCFLMC", point.get("JCFLMC"));
                result.put("DMXZBS", point.get("DMXZBS"));

                result.put("code", point.get("DWDM")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", point.get("DXMC")); // 对象名称
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取对象信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> querySurfaceWaterObjects(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "SJLX", request, "dataType");

        try {
            points = selectDAO.queryWaterObjects(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("LYDM", point.get("SSLY")); // 所属流域
                result.put("LYMC", point.get("SSLYMC")); // 所属流域名称
                result.put("SXDM", point.get("SSSX")); // 所属水系
                result.put("SXMC", point.get("SSSXMC")); // 所属水系名称
                result.put("JCFLBS", point.get("JCFLBS"));

                result.put("code", point.get("BH")); // 对象代码
                result.put("id", point.get("BH")); // 对象编号
                result.put("name", point.get("DXMC")); // 对象名称
                result.put("parentName", point.get("SSSXMC")); // 所属水系名称
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取环境空气点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryAirPoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryAirPoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("KZSX", point.get("JGJB")); // 监管级别
                result.put("KZSXMC", point.get("JGJBMC")); // 监管级别名称

                result.put("code", point.get("CDDM")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", ""); // 父名称
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取地下水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryGroundWaterPoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryGroundWaterPoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("LYDM", point.get("SSLY")); // 所属流域
                result.put("LYMC", point.get("SSLYMC")); // 所属流域名称
                result.put("SXDM", point.get("SSSX")); // 所属水系
                result.put("SXMC", point.get("SSSXMC")); // 所属水系名称
                result.put("SSST", point.get("SSST")); // 所属水体
                result.put("SSSTMC", point.get("SSSTMC")); // 所属水体名称

                result.put("code", point.get("DWDM")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", point.get("SSSTMC")); // 对象名称
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取功能区噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryFunctionNoisePoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryFunctionNoisePoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("JCND", point.get("JCND"));
                result.put("GNQDM", point.get("GNQDM"));

                result.put("code", point.get("DWBH")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", "");
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取区域环境噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryAreaNoisePoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryAreaNoisePoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("JCND", point.get("JCND"));

                result.put("code", point.get("DWBM")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", "");
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取道路交通噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, Object>> queryRoadTrafficNoisePoints(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "NF", request, "year");

        try {
            points = selectDAO.queryFunctionNoisePoints(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("XZQDM")); // 行政区代码
                result.put("CSMC", point.get("SSXZQ")); // 所属行政区
                result.put("JCND", point.get("JCNF"));

                result.put("code", point.get("DWBM")); // 点位代码
                result.put("id", point.get("CDBH")); // 测点编号
                result.put("name", point.get("CDMC")); // 测点名称
                result.put("parentName", "");
                results.add(result);
            }
        }
        return results;
    }

    /**
     * 获取分组信息主方法
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getGroupData(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();

        String groupsInfo = this.getParamsValue(request, "groupParams"); // 数据类型
        String dataType = this.getParamsValue(request, "dataType"); // 数据类型

        if (StringUtils.isEmpty(dataType)) {
            try {
                throw new Exception("缺失必要参数");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        // 优先取自定义分组
        groupDataList = getCustomGroup(groupsInfo);

        // 未自定义分组再去默认分组
        if (groupDataList == null || groupDataList.isEmpty()) {
            // 判断类型，调用不同的类型分组数据
            if (dataType.contains(Constants.HL_CD) || dataType.contains(Constants.HK_CD) || dataType.contains(Constants.HLYYS_CD) || dataType.contains(Constants.HKYYS_CD)
                    || dataType.contains(Constants.DXSYYS_CD)) {
                // 测点-水 通用分组数据
                groupDataList = getWaterPointGroup();
            } else if (dataType.contains(Constants.HL_DX) || dataType.contains(Constants.HK_DX)) {
                // 对象-水 通用分组数据
                groupDataList = getWaterObjectGroup();
            } else if (Constants.KQ_CD.equals(dataType)) {
                // 测点-空气 分组数据
                groupDataList = getAirPointGroup();
            } else if (Constants.DXS_CD.equals(dataType)) {
                // 测点-地下水 分组数据
                groupDataList = getGroundWaterGroup();
            } else if (Constants.DLJTZS_CD.equals(dataType)) {
                // 测点-道路交通噪声 分组数据
                groupDataList = getRoadTrafficNoiseGroup();
            } else if (Constants.GNQZS_CD.equals(dataType)) {
                // 测点-功能区噪声 分组数据
                groupDataList = getFunctionNoiseGroup();
            } else if (Constants.QYHJZS_CD.equals(dataType)) {
                // 测点-区域环境噪声 分组数据
                groupDataList = getAreaNoiseGroup();
            } else if (Constants.JCXM_CD.equals(dataType)) {
                // 监测项目  分组数据
                groupDataList = getMonitorItemGroup();
            }
        }

        return groupDataList;
    }

    /**
     * 获取自定义分组信息
     * @param   groupsInfo 分组信息:CSDM,按城市,CSMC#LYDM,按流域,LYMC
     * @author  赵运
     * @date    2017-08-17
     */
    @Override
    public List<Map<String, String>> getCustomGroup(String groupsInfo) {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        if (StringUtils.isNotEmpty(groupsInfo)) {
            String[] groups = groupsInfo.split(Constants.SPLIT);
            for (String group : groups) {
                // CSDM,按城市,CSMC
                String[] keys = group.split(Constants.SPLIT_COMM);
                if (keys.length == 3) {
                    groupMap = new HashMap<String, String>();
                    groupMap.put("key", keys[0]);
                    groupMap.put("name", keys[1]);
                    groupMap.put("showName", keys[2]);
                    groupDataList.add(groupMap);
                }
            }
        }

        return groupDataList;
    }

    /**
     * 组装测点-水类通用分组数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getWaterPointGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "LYDM");
        groupMap.put("name", "按流域");
        groupMap.put("showName", "LYMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "KZSX");
        groupMap.put("name", "按控制属性");
        groupMap.put("showName", "KZSXMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }

    /**
     * 组装对象-水类通用分组数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getWaterObjectGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "SXDM");
        groupMap.put("name", "按水系");
        groupMap.put("showName", "SXMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "LYDM");
        groupMap.put("name", "按流域");
        groupMap.put("showName", "LYMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }

    /**
     * 组装测点-空气通用分组数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getAirPointGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "KZSX");
        groupMap.put("name", "按控制属性");
        groupMap.put("showName", "KZSXMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }

    /**
     * 获取地下水点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getGroundWaterGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "LYDM");
        groupMap.put("name", "按流域");
        groupMap.put("showName", "LYMC");
        groupDataList.add(groupMap);

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "SXDM");
        groupMap.put("name", "按水系");
        groupMap.put("showName", "SXDMMC");
        groupDataList.add(groupMap);
        return groupDataList;
    }

    /**
     * 获取功能区噪声点位点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getFunctionNoiseGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }

    /**
     * 获取区域环境噪声点位点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getAreaNoiseGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }

    /**
     * 获取道路交通噪声点位点位分组信息
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getRoadTrafficNoiseGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按城市");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }
	/**
	 * 获取监测项目
	 * @param request
	 * @param response
	 * @return
	 */
    @Override
    public List<Map<String, Object>> queryMonitorItemObjects(HttpServletRequest request, HttpServletResponse response) {
        List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
        List<Map<String, Object>> points = null;

        // 组装条件
        Map<String, Object> queryInfo = new HashMap<String, Object>();
        this.addParameterFromJson(queryInfo, "ZBLX", request, "LX");
        try {
            points = selectDAO.queryMonitorItem(queryInfo);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (points != null && !points.isEmpty()) {
            for (Map<String, Object> point : points) {
                Map<String, Object> result = new HashMap<String, Object>();
                result.put("CSDM", point.get("MC")); // 项目 类型
                result.put("CSMC", point.get("MC")); // 项目类型名称


                result.put("code", point.get("ZBBH")); // 点位代码
                result.put("id", point.get("ZB_ID")); // 指标ID
                result.put("name", point.get("ZBMC")); // 测点名称
                result.put("parentName","");
                results.add(result);
            }
        }
        return results;
    }
    
    /**
     * 组装测点-水类通用分组数据
     * @author  赵运
     * @date    2017-08-15
     */
    @Override
    public List<Map<String, String>> getMonitorItemGroup() {
        List<Map<String, String>> groupDataList = new ArrayList<Map<String, String>>();
        Map<String, String> groupMap = null;

        groupMap = new HashMap<String, String>();
        groupMap.put("key", "CSDM");
        groupMap.put("name", "按首字母");
        groupMap.put("showName", "CSMC");
        groupDataList.add(groupMap);

        return groupDataList;
    }    
}
