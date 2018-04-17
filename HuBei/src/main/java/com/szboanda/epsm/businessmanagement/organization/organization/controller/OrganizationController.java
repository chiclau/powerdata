/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.organization.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szboanda.epsm.businessmanagement.organization.organization.exception.OrganizationException;
import com.szboanda.epsm.businessmanagement.organization.organization.service.IOrganizationService;
import com.szboanda.epsm.common.base.BaseBusinessController;

/**
* @Title:  监测机构模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/ep/epsm/businessmanagement/organization/organization/organizationcontroller")
public class OrganizationController extends BaseBusinessController {
    
    /**
     * 监测机构模块Service实现
     */
    @Autowired
    private IOrganizationService organizationService;
    
    /**
     * 跳转到本机构信息维护页面
     * 
     * @return
     * @throws OrganizationException
     */
    @RequestMapping("/organizationdetail")
    public String organizationDetail() throws OrganizationException {
        this.setToken();
        return "epsm/businessmanagement/organization/organization/organizationdetail";
    }
    
    /**
     * 添加监测机构
     * 
     * @param param
     * @return
     * @throws OrganizationException
     */
    @RequestMapping("/addorganization")
    public @ResponseBody Map<String, Object> addOrganization(@RequestBody Map<String, Object> param) throws OrganizationException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(organizationService.addOrganization(param), param);
    }
    
    /**
     * 更新监测机构
     * 
     * @param param
     * @return
     * @throws OrganizationException
     */
    @RequestMapping("/updateorganization")
    public @ResponseBody Map<String, Object> updateOrganization(@RequestBody Map<String, Object> param) throws OrganizationException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(organizationService.updateOrganization(param));
    }
    
    /**
     * 根据行政区划查询监测机构
     * 
     * @param param
     * @return
     * @throws OrganizationException
     */
    @RequestMapping("/getorganizationbyregion")
    public @ResponseBody Map<String, Object> getOrganizationByRegion() throws OrganizationException {
        return organizationService.getCurrUserOrganization();
    }
}
