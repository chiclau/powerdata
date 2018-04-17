/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orguser.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService;
import com.szboanda.epsm.businessmanagement.organization.orguser.dao.OrgUserDAO;
import com.szboanda.epsm.businessmanagement.organization.orguser.exception.OrgUserException;
import com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  监测机构用户模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Service
public class OrgUserServiceImpl extends BaseBusinessService implements IOrgUserService {

    /**
     * 监测机构用户模块DAO实现
     */
    @Autowired
    private OrgUserDAO orgUserDAO;
    
    /**
     * 监测机构模块Service实现
     */
    @Autowired
    private IOrganizationService organizationService;

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgUserService#findOrgUser(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findOrgUser(Map<String, Object> param) throws OrgUserException {
        try {
            SQLUtils.fillLike(param, "JCJGRY_NAME");
            
            // 添加省市区
            param.put("JCJG_SHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
            param.put("JCJG_SHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
            param.put("JCJG_XIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
            
            return orgUserDAO.findOrgUser(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "查询监测机构用户异常 : " + param, e);
            throw new OrgUserException("查询监测机构用户异常 : " + param, e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.IOrgUserService#getOrgUserById(java.lang.String)
     */
    @Override
    public Map<String, Object> getOrgUserById(String orgUserId) throws OrgUserException {
        try {
            return orgUserDAO.getOrgUserById(orgUserId);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "根据ID查询监测机构用户异常 : " + orgUserId, e);
            throw new OrgUserException("根据ID查询监测机构用户异常 : " + orgUserId, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgUserService#addOrgUser(java.util.Map)
     */
    @Override
    public int addOrgUser(Map<String, Object> param) throws OrgUserException {
        try {
            // 获取当前用户的监测机构
            Map<String, Object> currUserOrganization = organizationService.getCurrUserOrganization();
            
            param.put("JCJGRY_ID", Toolkit.getID());
            param.put("JCJGRY_JGBH", currUserOrganization.get("JCJG_ID"));
            
            return orgUserDAO.addOrgUser(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "添加监测机构用户异常 : " + param, e);
            throw new OrgUserException("添加监测机构用户异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgUserService#updateOrgUser(java.util.Map)
     */
    @Override
    public int updateOrgUser(Map<String, Object> param) throws OrgUserException {
        try {
            return orgUserDAO.updateOrgUser(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "更新监测机构用户异常 : " + param, e);
            throw new OrgUserException("更新监测机构用户异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgUserService#delOrgUser(java.lang.String)
     */
    @Override
    public int delOrgUser(String orgUserId) throws OrgUserException {
        try {
            return orgUserDAO.delOrgUser(orgUserId);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "删除监测机构用户异常 : " + orgUserId, e);
            throw new OrgUserException("删除监测机构用户异常 : " + orgUserId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService#findCertificate(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findCertificate(Map<String, Object> param) throws OrgUserException {
        try {
            return orgUserDAO.findCertificate(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "查询监测机构人员证书异常 : " + param, e);
            throw new OrgUserException("查询监测机构人员证书异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService#getCertificateById(java.lang.String)
     */
    @Override
    public Map<String, Object> getCertificateById(String certificateId) throws OrgUserException {
        try {
            return orgUserDAO.getCertificateById(certificateId);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "根据ID查询监测机构人员证书异常 : " + certificateId, e);
            throw new OrgUserException("根据ID查询监测机构人员证书异常 : " + certificateId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService#addCertificate(java.util.Map)
     */
    @Override
    public int addCertificate(Map<String, Object> param) throws OrgUserException {
        try {
            param.put("JCJGRY_ZSBH", Toolkit.getID());
            
            return orgUserDAO.addCertificate(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "添加监测机构人员证书异常 : " + param, e);
            throw new OrgUserException("添加监测机构人员证书异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService#updateCertificate(java.util.Map)
     */
    @Override
    public int updateCertificate(Map<String, Object> param) throws OrgUserException {
        try {
            return orgUserDAO.updateCertificate(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "更新监测机构人员证书异常 : " + param, e);
            throw new OrgUserException("更新监测机构人员证书异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService#delCertificate(java.lang.String)
     */
    @Override
    public int delCertificate(String certificateId) throws OrgUserException {
        try {
            return orgUserDAO.delCertificate(certificateId);
        } catch (Exception e) {
            LoggerUtil.error(OrgUserServiceImpl.class, "删除监测机构人员证书异常 : " + certificateId, e);
            throw new OrgUserException("删除监测机构人员证书异常 : " + certificateId, e);
        }
    }

}
