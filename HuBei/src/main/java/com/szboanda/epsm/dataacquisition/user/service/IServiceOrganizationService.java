/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.dataacquisition.user.exception.ServiceOrganizationException;

/**
* @Title:  服务机构Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月22日 蔡楚涛 新建
*/
public interface IServiceOrganizationService {
    /**
     * 查询服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    List<Map<String, Object>> findServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException;
    
    /**
     * 根据ID获取服务机构
     * 
     * @param serviceOrganizationId
     * @return
     * @throws ServiceOrganizationException
     */
    Map<String, Object> getServiceOrganizationById(String serviceOrganizationId) throws ServiceOrganizationException;
    
    /**
     * 根据登录账号获取服务机构
     * 
     * @param account
     * @return
     * @throws ServiceOrganizationException
     */
    Map<String, Object> getServiceOrganizationByAccount(String account) throws ServiceOrganizationException;
    
    /**
     * 添加服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    int addServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException;
    
    /**
     * 更新服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    int updateServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException;
    
    /**
     * 删除服务机构
     * 
     * @param serviceOrganizationId
     * @return
     * @throws ServiceOrganizationException
     */
    int delServiceOrganization(String serviceOrganizationId) throws ServiceOrganizationException;
}
