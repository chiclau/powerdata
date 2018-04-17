/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.organization.dao;

import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  监测机构模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface OrganizationDAO extends BaseBusinessDAO  {
    
    /**
     * 添加监测机构
     * 
     * @param param
     * @return
     */
    int addOrganization(Map<String, Object> param);
    
    /**
     * 更新监测机构
     * 
     * @param param
     * @return
     */
    int updateOrganization(Map<String, Object> param);
    
    /**
     * 根据行政区划查询监测机构
     * 
     * @param param
     * @return
     */
    Map<String, Object> getOrganizationByRegion(Map<String, Object> param);
}
