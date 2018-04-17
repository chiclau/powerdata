/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgdept.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.organization.orgdept.exception.OrgDeptException;
import com.szboanda.epsm.businessmanagement.organization.orgdept.service.IOrgDeptService;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
* @Title:  监测机构部门模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller")
public class OrgDeptController extends BaseBusinessController {

    /**
     * 监测机构模块Service实现
     */
    @Autowired
    private IOrgDeptService orgDeptService;
    
    /**
     * 跳转到本机构部门管理页面
     * 
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/orgdeptmanage")
    public String orgDeptManage() throws OrgDeptException {
        this.setToken();
        return "epsm/businessmanagement/organization/orgdept/orgdeptmanage";
    }
    
    /**
     * 跳转到本机构部门详情页面
     * 
     * @param orgDeptId
     * @param model
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/orgdeptdetail/{operationType}/{orgDeptId}")
    public String orgDeptDetail(@PathVariable String operationType, @PathVariable String orgDeptId, Model model) throws OrgDeptException {
        this.setToken();
        
        // 传递监测机构部门ID
        model.addAttribute("JCJGBM_ID", orgDeptId);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        
        return "epsm/businessmanagement/organization/orgdept/orgdeptdetail";
    }
    
    /**
     * 查询监测机构部门(分页)
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/findorgdeptpaging")
    public @ResponseBody PageInfo<Map<String, Object>> findOrgDeptPaging(@RequestBody Map<String, Object> param) throws OrgDeptException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(orgDeptService.findOrgDept(param));
    }
    
    /**
     * 查询监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/findorgdept")
    public @ResponseBody List<Map<String, Object>> findOrgDept(@RequestBody Map<String, Object> param) throws OrgDeptException {
        return orgDeptService.findOrgDept(param);
    }
    
    /**
     * 根据ID查询监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/getorgdeptbyid")
    public @ResponseBody Map<String, Object> getOrgDeptById(@RequestBody Map<String, Object> param) throws OrgDeptException {
        return orgDeptService.getOrgDeptById(MapUtils.getString(param, "JCJGBM_ID"));
    }
    
    /**
     * 添加监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/addorgdept")
    public @ResponseBody Map<String, Object> addOrgDept(@RequestBody Map<String, Object> param) throws OrgDeptException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(orgDeptService.addOrgDept(param));
    }
    
    /**
     * 更新监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/updateorgdept")
    public @ResponseBody Map<String, Object> updateOrgDept(@RequestBody Map<String, Object> param) throws OrgDeptException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgDeptService.updateOrgDept(param));
    }
    
    /**
     * 删除监测机构部门
     * 
     * @param param
     * @return
     * @throws OrgDeptException
     */
    @RequestMapping("/delorgdept")
    public @ResponseBody Map<String, Object> delOrgDept(@RequestBody Map<String, Object> param) throws OrgDeptException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgDeptService.delOrgDept(MapUtils.getString(param, "JCJGBM_ID")));
    }
}
