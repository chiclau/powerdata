/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.companyuser.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.dataacquisition.companyuser.exception.CompanyUserException;

/**
* @Title:  企业用户模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
public interface ICompanyUserService {
    /**
     * 查询企业用户
     * 
     * @param companyId
     * @return
     */
    List<Map<String, Object>> findCompanyUser(Map<String, Object> param) throws CompanyUserException;
    
    /**
     * 添加企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    int addCompanyUser(Map<String, Object> param) throws CompanyUserException;
    
    /**
     * 更新企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    int updateCompanyUser(Map<String, Object> param) throws CompanyUserException;
    
    /**
     * 删除企业用户
     * 
     * @param userId
     * @return
     * @throws CompanyUserException
     */
    int delCompanyUser(String userId) throws CompanyUserException;
    
    /**
     * 根据系统账号查询企业用户
     * 
     * @param account
     * @return
     * @throws CompanyUserException
     */
    Map<String, Object> getCompanyUserByAccount(String account) throws CompanyUserException;
}
