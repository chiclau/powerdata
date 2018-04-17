/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.organization.service;

import java.util.Map;

import com.szboanda.epsm.businessmanagement.organization.organization.exception.OrganizationException;

/**
* @Title:  监测机构模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
public interface IOrganizationService {
    /**
     * 添加监测机构
     * 
     * @param param
     * @return
     */
    int addOrganization(Map<String, Object> param) throws OrganizationException;
    
    /**
     * 更新监测机构
     * 
     * @param param
     * @return
     * @throws OrganizationException
     */
    int updateOrganization(Map<String, Object> param) throws OrganizationException;
    
    /**
     * 获取当前用户所属的监测机构
     * 
     * @param param
     * @return
     * @throws OrganizationException
     */
    Map<String, Object> getCurrUserOrganization() throws OrganizationException;
}
