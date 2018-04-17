/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgdept.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService;
import com.szboanda.epsm.businessmanagement.organization.orgdept.dao.OrgDeptDAO;
import com.szboanda.epsm.businessmanagement.organization.orgdept.exception.OrgDeptException;
import com.szboanda.epsm.businessmanagement.organization.orgdept.service.IOrgDeptService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  监测机构部门模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Service
public class OrgDeptServiceImpl extends BaseBusinessService implements IOrgDeptService {

    /**
     * 监测机构部门模块DAO实现
     */
    @Autowired
    private OrgDeptDAO orgDeptDAO;
    
    /**
     * 监测机构模块Service实现
     */
    @Autowired
    private IOrganizationService organizationService;

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgDeptService#findOrgDept(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findOrgDept(Map<String, Object> param) throws OrgDeptException {
        try {
            
            SQLUtils.fillLike(param, "JCJGBM_BMMC");
            SQLUtils.fillLike(param, "JCJGBM_BMLXR");
            SQLUtils.fillLike(param, "JCJGBM_BMLXRDH");
            
            // 添加省市区
            param.put("JCJG_SHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
            param.put("JCJG_SHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
            param.put("JCJG_XIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
            
            return orgDeptDAO.findOrgDept(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgDeptServiceImpl.class, "查询监测机构部门异常 : " + param, e);
            throw new OrgDeptException("查询监测机构部门异常 : " + param, e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.IOrgDeptService#getOrgDeptById(java.lang.String)
     */
    @Override
    public Map<String, Object> getOrgDeptById(String orgDeptId) throws OrgDeptException {
        try {
            return orgDeptDAO.getOrgDeptById(orgDeptId);
        } catch (Exception e) {
            LoggerUtil.error(OrgDeptServiceImpl.class, "根据ID查询监测机构部门异常 : " + orgDeptId, e);
            throw new OrgDeptException("根据ID查询监测机构部门异常 : " + orgDeptId, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgDeptService#addOrgDept(java.util.Map)
     */
    @Override
    public int addOrgDept(Map<String, Object> param) throws OrgDeptException {
        try {
            // 获取当前用户的监测机构
            Map<String, Object> currUserOrganization = organizationService.getCurrUserOrganization();
            
            param.put("JCJGBM_ID", Toolkit.getID());
            param.put("JCJGBM_JGBH", currUserOrganization.get("JCJG_ID"));
            
            return orgDeptDAO.addOrgDept(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgDeptServiceImpl.class, "添加监测机构部门异常 : " + param, e);
            throw new OrgDeptException("添加监测机构部门异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgDeptService#updateOrgDept(java.util.Map)
     */
    @Override
    public int updateOrgDept(Map<String, Object> param) throws OrgDeptException {
        try {
            return orgDeptDAO.updateOrgDept(param);
        } catch (Exception e) {
            LoggerUtil.error(OrgDeptServiceImpl.class, "更新监测机构部门异常 : " + param, e);
            throw new OrgDeptException("更新监测机构部门异常 : " + param, e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.szboanda.epsm.businessmanagement.organization.orgdept.service.
     * IOrgDeptService#delOrgDept(java.lang.String)
     */
    @Override
    public int delOrgDept(String orgDeptId) throws OrgDeptException {
        try {
            return orgDeptDAO.delOrgDept(orgDeptId);
        } catch (Exception e) {
            LoggerUtil.error(OrgDeptServiceImpl.class, "删除监测机构部门异常 : " + orgDeptId, e);
            throw new OrgDeptException("删除监测机构部门异常 : " + orgDeptId, e);
        }
    }

}
