/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgdept.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.businessmanagement.organization.orgdept.exception.OrgDeptException;

/**
* @Title:  监测机构部门模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
public interface IOrgDeptService {
    /**
     * 查询监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    List<Map<String, Object>> findOrgDept(Map<String, Object> param) throws OrgDeptException;
    
    /**
     * 根据ID查询监测机构部门
     * 
     * @param orgDeptId
     * @return
     * @throws OrgDeptException
     */
    Map<String, Object> getOrgDeptById(String orgDeptId) throws OrgDeptException;
    
    /**
     * 添加监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    int addOrgDept(Map<String, Object> param) throws OrgDeptException;
    
    /**
     * 更新监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    int updateOrgDept(Map<String, Object> param) throws OrgDeptException;
    
    /**
     * 删除监测机构部门
     * 
     * @param orgDeptId
     * @return
     * @throws OrgDeptException
     */
    int delOrgDept(String orgDeptId) throws OrgDeptException;
}
