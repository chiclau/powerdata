/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgequipment.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService;
import com.szboanda.epsm.businessmanagement.organization.orgequipment.dao.OrgEquipmentDAO;
import com.szboanda.epsm.businessmanagement.organization.orgequipment.exception.OrgEquipmentException;
import com.szboanda.epsm.businessmanagement.organization.orgequipment.service.IOrgEquipmentService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  监测机构设备模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Service
public class OrgEquipmentServiceImpl extends BaseBusinessService implements IOrgEquipmentService {

    /**
     * 监测机构设备模块DAO实现
     */
    @Autowired
    private OrgEquipmentDAO orgEquipmentDAO;
    
    /**
     * 监测机构模块Service实现
     */
    @Autowired
    private IOrganizationService organizationService;

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgEquipmentService#findOrgEquipment(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findOrgEquipment(Map<String, Object> param) throws OrgEquipmentException {
        try {
            SQLUtils.fillLike(param, "JCJGSB_SBBH");
            SQLUtils.fillLike(param, "JCJGSB_SBMC");
            SQLUtils.fillLike(param, "JCJGSB_JCSBXH");
            SQLUtils.fillLike(param, "JCJGSB_SCCJ");
            
            // 添加省市区
            param.put("JCJG_SHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
            param.put("JCJG_SHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
            param.put("JCJG_XIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
            
            return orgEquipmentDAO.findOrgEquipment(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "查询监测机构设备异常 : " + param, e);
            throw new OrgEquipmentException("查询监测机构设备异常 : " + param, e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.IOrgEquipmentService#getOrgEquipmentById(java.lang.String)
     */
    @Override
    public Map<String, Object> getOrgEquipmentById(String orgEquipmentId) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.getOrgEquipmentById(orgEquipmentId);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "根据ID查询监测机构设备异常 : " + orgEquipmentId, e);
            throw new OrgEquipmentException("根据ID查询监测机构设备异常 : " + orgEquipmentId, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgEquipmentService#addOrgEquipment(java.util.Map)
     */
    @Override
    public int addOrgEquipment(Map<String, Object> param) throws OrgEquipmentException {
        try {
            // 获取当前设备的监测机构
            Map<String, Object> currUserOrganization = organizationService.getCurrUserOrganization();
            
            param.put("JCJGSB_ID", Toolkit.getID());
            param.put("JCJGSB_JGBH", currUserOrganization.get("JCJG_ID"));
            
            return orgEquipmentDAO.addOrgEquipment(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "添加监测机构设备异常 : " + param, e);
            throw new OrgEquipmentException("添加监测机构设备异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgEquipmentService#updateOrgEquipment(java.util.Map)
     */
    @Override
    public int updateOrgEquipment(Map<String, Object> param) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.updateOrgEquipment(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "更新监测机构设备异常 : " + param, e);
            throw new OrgEquipmentException("更新监测机构设备异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgEquipmentService#delOrgEquipment(java.lang.String)
     */
    @Override
    public int delOrgEquipment(String orgEquipmentId) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.delOrgEquipment(orgEquipmentId);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "删除监测机构设备异常 : " + orgEquipmentId, e);
            throw new OrgEquipmentException("删除监测机构设备异常 : " + orgEquipmentId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgEquipmentService#findProject(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findProject(Map<String, Object> param) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.findProject(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "查询监测机构设备的监测项目异常 : " + param, e);
            throw new OrgEquipmentException("查询监测机构设备的监测项目异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgEquipmentService#getProjectById(java.lang.String)
     */
    @Override
    public Map<String, Object> getProjectById(String projectId) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.getProjectById(projectId);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "根据ID查询监测机构设备的监测项目异常 : " + projectId, e);
            throw new OrgEquipmentException("根据ID查询监测机构设备的监测项目异常 : " + projectId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgEquipmentService#addProject(java.util.Map)
     */
    @Override
    public int addProject(Map<String, Object> param) throws OrgEquipmentException {
        try {
            param.put("JCJG_SBJCX_ID", Toolkit.getID());
            
            return orgEquipmentDAO.addProject(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "添加监测机构设备的监测项目异常 : " + param, e);
            throw new OrgEquipmentException("添加监测机构设备的监测项目异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgEquipmentService#updateProject(java.util.Map)
     */
    @Override
    public int updateProject(Map<String, Object> param) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.updateProject(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "更新监测机构设备的监测项目异常 : " + param, e);
            throw new OrgEquipmentException("更新监测机构设备的监测项目异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgEquipmentService#delProject(java.lang.String)
     */
    @Override
    public int delProject(String projectId) throws OrgEquipmentException {
        try {
            return orgEquipmentDAO.delProject(projectId);
        } catch (Exception e) {
            LoggerUtil.error(OrgEquipmentServiceImpl.class, "删除监测机构设备的监测项目异常 : " + projectId, e);
            throw new OrgEquipmentException("删除监测机构设备异常 : " + projectId, e);
        }
    }

}
