/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgdept.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  监测机构部门模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface OrgDeptDAO extends BaseBusinessDAO  {
    /**
     * 查询监测机构部门
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findOrgDept(Map<String, Object> param);
    
    /**
     * 根据ID查询监测机构部门
     * 
     * @param orgDeptId
     * @return
     */
    Map<String, Object> getOrgDeptById(String orgDeptId);
    
    /**
     * 添加监测机构部门
     * 
     * @param param
     * @return
     */
    int addOrgDept(Map<String, Object> param);
    
    /**
     * 更新监测机构部门
     * 
     * @param param
     * @return
     */
    int updateOrgDept(Map<String, Object> param);
    
    /**
     * 删除监测机构部门
     * 
     * @param orgDeptId
     * @return
     */
    int delOrgDept(String orgDeptId);
}
