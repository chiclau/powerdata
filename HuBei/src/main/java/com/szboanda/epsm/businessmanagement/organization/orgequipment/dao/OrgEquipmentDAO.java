/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgequipment.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  监测机构设备模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface OrgEquipmentDAO extends BaseBusinessDAO  {
    /**
     * 查询监测机构设备
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findOrgEquipment(Map<String, Object> param);
    
    /**
     * 根据ID查询监测机构设备
     * 
     * @param orgEquipmentId
     * @return
     */
    Map<String, Object> getOrgEquipmentById(String orgEquipmentId);
    
    /**
     * 添加监测机构设备
     * 
     * @param param
     * @return
     */
    int addOrgEquipment(Map<String, Object> param);
    
    /**
     * 更新监测机构设备
     * 
     * @param param
     * @return
     */
    int updateOrgEquipment(Map<String, Object> param);
    
    /**
     * 删除监测机构设备
     * 
     * @param orgEquipmentId
     * @return
     */
    int delOrgEquipment(String orgEquipmentId);
    
    /**
     * 查询监测机构设备的监测项目
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findProject(Map<String, Object> param);
    
    /**
     * 根据ID查询监测机构设备的监测项目
     * 
     * @param projectId
     * @return
     */
    Map<String, Object> getProjectById(String projectId);
    
    /**
     * 添加监测机构设备的监测项目
     * 
     * @param param
     * @return
     */
    int addProject(Map<String, Object> param);
    
    /**
     * 更新监测机构设备的监测项目
     * 
     * @param param
     * @return
     */
    int updateProject(Map<String, Object> param);
    
    /**
     * 删除监测机构设备的监测项目
     * 
     * @param projectId
     * @return
     */
    int delProject(String projectId);
}
