/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgequipment.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.businessmanagement.organization.orgequipment.exception.OrgEquipmentException;

/**
* @Title:  监测机构设备模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
public interface IOrgEquipmentService {
    /**
     * 查询监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    List<Map<String, Object>> findOrgEquipment(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 根据ID查询监测机构设备
     * 
     * @param orgEquipmentId
     * @return
     * @throws OrgEquipmentException
     */
    Map<String, Object> getOrgEquipmentById(String orgEquipmentId) throws OrgEquipmentException;

    /**
     * 添加监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    int addOrgEquipment(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 更新监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    int updateOrgEquipment(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 删除监测机构设备
     * 
     * @param orgEquipmentId
     * @return
     * @throws OrgEquipmentException
     */
    int delOrgEquipment(String orgEquipmentId) throws OrgEquipmentException;

    /**
     * 查询监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    List<Map<String, Object>> findProject(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 根据ID查询监测机构设备的监测项目
     * 
     * @param projectId
     * @return
     * @throws OrgEquipmentException
     */
    Map<String, Object> getProjectById(String projectId) throws OrgEquipmentException;

    /**
     * 添加监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    int addProject(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 更新监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    int updateProject(Map<String, Object> param) throws OrgEquipmentException;

    /**
     * 删除监测机构设备的监测项目
     * 
     * @param projectId
     * @return
     * @throws OrgEquipmentException
     */
    int delProject(String projectId) throws OrgEquipmentException;
}
