/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.organization.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.businessmanagement.organization.organization.dao.OrganizationDAO;
import com.szboanda.epsm.businessmanagement.organization.organization.exception.OrganizationException;
import com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  监测机构模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Service
public class OrganizationServiceImpl extends BaseBusinessService implements IOrganizationService {

    /**
     * 监测机构模块DAO实现
     */
    @Autowired
    private OrganizationDAO organizationDAO;
    
    /**
     * 用户模块Service实现
     */
    @Autowired
    private IBusinessUserService businessUserService;

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService#addOrganization(java.util.Map)
     */
    @Override
    public int addOrganization(Map<String, Object> param)  throws OrganizationException {
        // 添加机构ID
        param.put("JCJG_ID", Toolkit.getID());
        
        Map<String, Object> userParam = new HashMap<String, Object>();
        userParam.put("XZQHDMSHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
        userParam.put("XZQHDMSHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        userParam.put("XZQHDMXIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        userParam.put("YHJS", CommonBussinessConstants.ROLE_HBYH_GL);
        // 添加管理员编号
        param.put("JCJG_GLYBH", businessUserService.findSameRegionUser(userParam).get(0).get("YHID"));
        
        // 添加省市县
        param.put("JCJG_SHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
        param.put("JCJG_SHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        param.put("JCJG_XIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        
        // 添加用户登录名称
        param.put("USERDLMC", this.getBusinessCurrUser().getUserVO().getYhmc());
        
        return organizationDAO.addOrganization(param);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService#updateOrganization(java.util.Map)
     */
    @Override
    public int updateOrganization(Map<String, Object> param) throws OrganizationException {
        try {
            return organizationDAO.updateOrganization(param);
        } catch (Exception e) {
            LoggerUtil.error(OrganizationServiceImpl.class, "更新监测机构异常 : " + param, e);
            throw new OrganizationException("更新监测机构异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService#getOrganizationByRegion(java.util.Map)
     */
    @Override
    public Map<String, Object> getCurrUserOrganization() throws OrganizationException {
        try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("JCJG_SHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
            param.put("JCJG_SHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
            param.put("JCJG_XIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
            
            return organizationDAO.getOrganizationByRegion(param);
        } catch (Exception e) {
            LoggerUtil.error(OrganizationServiceImpl.class, "根据行政区划查询监测机构异常", e);
            throw new OrganizationException("根据行政区划查询监测机构异常", e);
        }
    }

}
