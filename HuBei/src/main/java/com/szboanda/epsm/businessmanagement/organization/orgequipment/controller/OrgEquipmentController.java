/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orgequipment.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.organization.orgequipment.exception.OrgEquipmentException;
import com.szboanda.epsm.businessmanagement.organization.orgequipment.service.IOrgEquipmentService;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
* @Title:  监测机构设备模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller")
public class OrgEquipmentController extends BaseBusinessController {

    /**
     * 监测机构设备模块Service实现
     */
    @Autowired
    private IOrgEquipmentService orgEquipmentService;
    
    /**
     * 跳转到本机构设备管理页面
     * 
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/orgequipmentmanage")
    public String orgEquipmentManage() throws OrgEquipmentException {
        this.setToken();
        return "epsm/businessmanagement/organization/orgequipment/orgequipmentmanage";
    }
    
    /**
     * 跳转到本机构设备详情页面
     * 
     * @param orgEquipmentId
     * @param model
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/orgequipmentdetail/{operationType}/{orgEquipmentId}")
    public String orgEquipmentDetail(@PathVariable String operationType, @PathVariable String orgEquipmentId, Model model) throws OrgEquipmentException {
        this.setToken();
        
        // 传递监测机构设备ID
        model.addAttribute("JCJGSB_ID", orgEquipmentId);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        
        return "epsm/businessmanagement/organization/orgequipment/orgequipmentdetail";
    }
    
    /**
     * 查询监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/findorgequipmentpaging")
    public @ResponseBody PageInfo<Map<String, Object>> findOrgEquipmentPaging(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(orgEquipmentService.findOrgEquipment(param));
    }
    
    /**
     * 根据ID查询监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/getorgequipmentbyid")
    public @ResponseBody Map<String, Object> getOrgEquipmentById(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        return orgEquipmentService.getOrgEquipmentById(MapUtils.getString(param, "JCJGSB_ID"));
    }
    
    /**
     * 添加监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/addorgequipment")
    public @ResponseBody Map<String, Object> addOrgEquipment(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(orgEquipmentService.addOrgEquipment(param));
    }
    
    /**
     * 更新监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/updateorgequipment")
    public @ResponseBody Map<String, Object> updateOrgEquipment(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgEquipmentService.updateOrgEquipment(param));
    }
    
    /**
     * 删除监测机构设备
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/delorgequipment")
    public @ResponseBody Map<String, Object> delOrgEquipment(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgEquipmentService.delOrgEquipment(MapUtils.getString(param, "JCJGSB_ID")));
    }
    
    /**
     * 跳转到监测机构设备的监测项目管理页面
     * 
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/projectmanage/{orgEquipmentId}")
    public String projectManage(@PathVariable String orgEquipmentId, Model model) throws OrgEquipmentException {
        this.setToken();
        
        // 传递设备ID
        model.addAttribute("JCJG_SBJCX_SBID", orgEquipmentId);
        
        return "epsm/businessmanagement/organization/orgequipment/projectmanage";
    }
    
    /**
     * 跳转到监测机构设备的监测项目详情页面
     * 
     * @param orgEquipmentId
     * @param model
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/projectdetail/{operationType}/{orgEquipmentId}/{projectId}")
    public String projectDetail(@PathVariable String operationType, @PathVariable String orgEquipmentId, @PathVariable String projectId, Model model) throws OrgEquipmentException {
        this.setToken();
        
        // 传递设备ID
        model.addAttribute("JCJG_SBJCX_SBID", orgEquipmentId);
        
        // 传递监测机构设备的监测项目ID
        model.addAttribute("JCJG_SBJCX_ID", projectId);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        
        return "epsm/businessmanagement/organization/orgequipment/projectdetail";
    }
    
    /**
     * 查询监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/findprojectpaging")
    public @ResponseBody PageInfo<Map<String, Object>> findProjectPaging(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(orgEquipmentService.findProject(param));
    }
    
    /**
     * 根据ID查询监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/getprojectbyid")
    public @ResponseBody Map<String, Object> getProjectById(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        return orgEquipmentService.getProjectById(MapUtils.getString(param, "JCJG_SBJCX_ID"));
    }
    
    /**
     * 添加监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/addproject")
    public @ResponseBody Map<String, Object> addProject(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(orgEquipmentService.addProject(param));
    }
    
    /**
     * 更新监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/updateproject")
    public @ResponseBody Map<String, Object> updateProject(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgEquipmentService.updateProject(param));
    }
    
    /**
     * 删除监测机构设备的监测项目
     * 
     * @param param
     * @return
     * @throws OrgEquipmentException
     */
    @RequestMapping("/delproject")
    public @ResponseBody Map<String, Object> delProject(@RequestBody Map<String, Object> param) throws OrgEquipmentException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgEquipmentService.delProject(MapUtils.getString(param, "JCJG_SBJCX_ID")));
    }
}
