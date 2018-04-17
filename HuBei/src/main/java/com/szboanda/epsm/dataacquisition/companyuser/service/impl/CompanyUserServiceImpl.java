/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.companyuser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.dataacquisition.companyuser.dao.CompanyUserDAO;
import com.szboanda.epsm.dataacquisition.companyuser.exception.CompanyUserException;
import com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.SQLUtils;

/**
* @Title:  企业用户模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
@Service
public class CompanyUserServiceImpl extends BaseBusinessService implements ICompanyUserService {

    /**
     * 企业用户模块DAO实现
     */
    @Autowired
    private CompanyUserDAO companyUserDAO;
    
    /**
     * 用户模块Service实现
     */
    @Autowired
    private IBusinessUserService businessUserService;

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService#findCompanyUser(java.lang.String)
     */
    @Override
    public List<Map<String, Object>> findCompanyUser(Map<String, Object> param) throws CompanyUserException {
        try {
            SQLUtils.fillLike(param, "YHJS");
            return companyUserDAO.findCompanyUser(param);
        } catch (Exception e) {
            LoggerUtil.error(CompanyUserServiceImpl.class, "查询企业用户异常 : " + param, e);
            throw new CompanyUserException("查询企业用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService#addCompanyUser(java.util.Map)
     */
    @Override
    public int addCompanyUser(Map<String, Object> param) throws CompanyUserException {
        try {
            return businessUserService.addUser(param);
        } catch (Exception e) {
            LoggerUtil.error(CompanyUserServiceImpl.class, "添加企业用户异常 : " + param, e);
            throw new CompanyUserException("添加企业用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService#updateCompanyUser(java.util.Map)
     */
    @Override
    public int updateCompanyUser(Map<String, Object> param) throws CompanyUserException {
        try {
            return businessUserService.updateUser(param);
        } catch (Exception e) {
            LoggerUtil.error(CompanyUserServiceImpl.class, "更新企业用户异常 : " + param, e);
            throw new CompanyUserException("更新企业用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService#delCompanyUser(java.lang.String)
     */
    @Override
    public int delCompanyUser(String userId) throws CompanyUserException {
        try {
            return businessUserService.delUser(userId);
        } catch (Exception e) {
            LoggerUtil.error(CompanyUserServiceImpl.class, "删除企业用户异常 : " + userId, e);
            throw new CompanyUserException("删除企业用户异常 : " + userId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService#getCompanyUserByAccount(java.lang.String)
     */
    @Override
    public Map<String, Object> getCompanyUserByAccount(String account) throws CompanyUserException {
        try {
            return businessUserService.getUserByAccount(account);
        } catch (Exception e) {
            LoggerUtil.error(CompanyUserServiceImpl.class, "根据系统账号查询企业用户异常 : " + account, e);
            throw new CompanyUserException("根据系统账号查询企业用户异常 : " + account, e);
        }
    }
}
