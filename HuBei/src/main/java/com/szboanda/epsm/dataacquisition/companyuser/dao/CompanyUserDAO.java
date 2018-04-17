/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.companyuser.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  企业用户模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface CompanyUserDAO extends BaseBusinessDAO {
    /**
     * 查询企业用户
     * 
     * @param companyId
     * @return
     */
    List<Map<String, Object>> findCompanyUser(Map<String, Object> param);
}
