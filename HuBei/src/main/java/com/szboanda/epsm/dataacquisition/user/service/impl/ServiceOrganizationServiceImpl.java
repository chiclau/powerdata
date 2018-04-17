/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.dataacquisition.user.dao.ServiceOrganizationDAO;
import com.szboanda.epsm.dataacquisition.user.exception.ServiceOrganizationException;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.epsm.dataacquisition.user.service.IServiceOrganizationService;
import com.szboanda.platform.common.utils.CollectionUtils;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  服务机构模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月22日 蔡楚涛 新建
*/
@Service
public class ServiceOrganizationServiceImpl extends BaseBusinessService implements IServiceOrganizationService {

    /**
     * 服务机构模块DAO实现
     */
    @Autowired
    private ServiceOrganizationDAO serviceOrganizationDAO;
    
    /**
     * 用户模块Service实现
     */
    @Autowired
    private IBusinessUserService businessUserService;

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.dataacquisition.user.service.
     * IServiceOrganizationService#findServiceOrganization(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException {
        try {
            SQLUtils.fillLike(param, "JBXX_NAME");
            return serviceOrganizationDAO.findServiceOrganization(param);
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "查询服务机构异常 : " + param, e);
            throw new ServiceOrganizationException("查询服务机构异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.dataacquisition.user.service.
     * IServiceOrganizationService#getServiceOrganizationById(java.lang.String)
     */
    @Override
    public Map<String, Object> getServiceOrganizationById(String serviceOrganizationId) throws ServiceOrganizationException {
        try {
            // 根据ID获取服务机构
            Map<String, Object> serviceOrganization = serviceOrganizationDAO.getServiceOrganizationById(serviceOrganizationId);
            
            // 根据服务机构ID查询监测项目范围
            List<Map<String, Object>> projectScope = serviceOrganizationDAO.findProjectScopeByOrgId(serviceOrganizationId);
            
            // 根据服务机构ID查询资质证书
            List<Map<String, Object>> certificate = serviceOrganizationDAO.findCertificateByOrgId(serviceOrganizationId);
            
            serviceOrganization.put("projectScope", projectScope);
            serviceOrganization.put("certificate", certificate);
            
            return serviceOrganization;
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "根据ID获取服务机构异常 : " + serviceOrganizationId, e);
            throw new ServiceOrganizationException("根据ID获取服务机构异常 : " + serviceOrganizationId, e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IServiceOrganizationService#getServiceOrganizationByAccount(java.lang.String)
     */
    @Override
    public Map<String, Object> getServiceOrganizationByAccount(String account) throws ServiceOrganizationException {
        try {
            // 根据ID获取服务机构
            Map<String, Object> serviceOrganization = serviceOrganizationDAO.getServiceOrganizationByAccount(account);
            
            String serviceOrganizationId = MapUtils.getString(serviceOrganization, "JBXX_ID");
            
            // 根据服务机构ID查询监测项目范围
            List<Map<String, Object>> projectScope = serviceOrganizationDAO.findProjectScopeByOrgId(serviceOrganizationId);
            
            // 根据服务机构ID查询资质证书
            List<Map<String, Object>> certificate = serviceOrganizationDAO.findCertificateByOrgId(serviceOrganizationId);
            
            serviceOrganization.put("projectScope", projectScope);
            serviceOrganization.put("certificate", certificate);
            
            return serviceOrganization;
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "根据登录账号获取服务机构异常 : " + account, e);
            throw new ServiceOrganizationException("根据登录账号获取服务机构异常 : " + account, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.dataacquisition.user.service.
     * IServiceOrganizationService#addServiceOrganization(java.util.Map)
     */
    @Override
    public int addServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException {
        try {
            param.put("JBXX_ID", Toolkit.getID());
            param.put("JBXX_SFYX", "1");
            // 添加服务机构
            int count = serviceOrganizationDAO.addServiceOrganization(param);
            
            // 添加备案
            String isDetector = MapUtils.getString(param, "JBXX_JCJG");
            if ("1".equals(isDetector)) {
                param.put("JCJG_ID", Toolkit.getID());
                serviceOrganizationDAO.addRecord(param);
            }
           
//            // 批量添加监测项目范围
//            serviceOrganizationDAO.addProjectScopeBatch(param);
//            
//            // 批量添加资质证书
//            serviceOrganizationDAO.addCertificateBatch(param);
            
            // 设置角色
            List<Map<String, Object>> addRoles = new ArrayList<Map<String, Object>>();
            Map<String, Object> addRole = new HashMap<String, Object>();
            addRole.put("JSBH", CommonBussinessConstants.ROLE_FWJGYH);
            addRoles.add(addRole);
            param.put("addRoles", addRoles);
            param.put("YHJS", CommonBussinessConstants.ROLE_FWJGYH);
            
            // 设置用户级别
            param.put("YHJB", "8");
            
            // 设置服务机构ID
            param.put("FWJGID", MapUtils.getString(param, "JBXX_ID"));
            
            // 添加用户
            businessUserService.addUser(param);
            
            return count;
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "添加服务机构异常 : " + param, e);
            throw new ServiceOrganizationException("添加服务机构异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.dataacquisition.user.service.
     * IServiceOrganizationService#updateServiceOrganization(java.util.Map)
     */
    @Override
    public int updateServiceOrganization(Map<String, Object> param) throws ServiceOrganizationException {
        try {
            // 更新服务机构
            int count = serviceOrganizationDAO.updateServiceOrganization(param);
            
            if ("1".equals(MapUtils.getString(param, "JBXX_JCJG"))) {
                if (CollectionUtils.isEmpty(serviceOrganizationDAO.getRecordByOrgId(MapUtils.getString(param, "JBXX_ID")))) {
                    param.put("JCJG_ID", Toolkit.getID());
                    serviceOrganizationDAO.addRecord(param);
                } else {
                    // 根据服务机构ID更新备案
                    serviceOrganizationDAO.updateRecordByOrgId(param);
                }
            } else {
                // 根据服务机构ID删除备案
                serviceOrganizationDAO.delRecordByOrgId(MapUtils.getString(param, "JBXX_ID"));
            }
            
            return count;
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "更新服务机构异常 : " + param, e);
            throw new ServiceOrganizationException("更新服务机构异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IServiceOrganizationService#delServiceOrganization(java.lang.String)
     */
    @Override
    public int delServiceOrganization(String serviceOrganizationId) throws ServiceOrganizationException {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("JBXX_ID", serviceOrganizationId);
            param.put("JBXX_SFYX", "0");
            
            return serviceOrganizationDAO.updateServiceOrganization(param);
        } catch (Exception e) {
            LoggerUtil.error(ServiceOrganizationServiceImpl.class, "删除服务机构异常 : " + serviceOrganizationId, e);
            throw new ServiceOrganizationException("删除服务机构异常 : " + serviceOrganizationId, e);
        }
    }

}
