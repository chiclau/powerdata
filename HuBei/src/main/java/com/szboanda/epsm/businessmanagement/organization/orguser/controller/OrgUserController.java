/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orguser.controller;

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
import com.szboanda.epsm.businessmanagement.organization.orguser.exception.OrgUserException;
import com.szboanda.epsm.businessmanagement.organization.orguser.service.IOrgUserService;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
* @Title:  监测机构用户模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/ep/epsm/businessmanagement/organization/orguser/orgusercontroller")
public class OrgUserController extends BaseBusinessController {

    /**
     * 监测机构用户模块Service实现
     */
    @Autowired
    private IOrgUserService orgUserService;
    
    /**
     * 跳转到本机构用户管理页面
     * 
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/orgusermanage")
    public String orgUserManage() throws OrgUserException {
        this.setToken();
        return "epsm/businessmanagement/organization/orguser/orgusermanage";
    }
    
    /**
     * 跳转到本机构用户详情页面
     * 
     * @param orgUserId
     * @param model
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/orguserdetail/{operationType}/{orgUserId}")
    public String orgUserDetail(@PathVariable String operationType, @PathVariable String orgUserId, Model model) throws OrgUserException {
        this.setToken();
        
        // 传递监测机构用户ID
        model.addAttribute("JCJGRY_ID", orgUserId);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        
        return "epsm/businessmanagement/organization/orguser/orguserdetail";
    }
    
    /**
     * 查询监测机构用户(分页)
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/findorguserpaging")
    public @ResponseBody PageInfo<Map<String, Object>> findOrgUserPaging(@RequestBody Map<String, Object> param) throws OrgUserException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(orgUserService.findOrgUser(param));
    }
    
    /**
     * 查询监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/findorguser")
    public @ResponseBody List<Map<String, Object>> findOrgUser(@RequestBody Map<String, Object> param) throws OrgUserException {
        return orgUserService.findOrgUser(param);
    }
    
    /**
     * 根据ID查询监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/getorguserbyid")
    public @ResponseBody Map<String, Object> getOrgUserById(@RequestBody Map<String, Object> param) throws OrgUserException {
        return orgUserService.getOrgUserById(MapUtils.getString(param, "JCJGRY_ID"));
    }
    
    /**
     * 添加监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/addorguser")
    public @ResponseBody Map<String, Object> addOrgUser(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(orgUserService.addOrgUser(param));
    }
    
    /**
     * 更新监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/updateorguser")
    public @ResponseBody Map<String, Object> updateOrgUser(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgUserService.updateOrgUser(param));
    }
    
    /**
     * 删除监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/delorguser")
    public @ResponseBody Map<String, Object> delOrgUser(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgUserService.delOrgUser(MapUtils.getString(param, "JCJGRY_ID")));
    }
    
    /**
     * 跳转到监测机构人员证书管理页面
     * 
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/certificatemanage/{orgUserId}")
    public String certificateManage(@PathVariable String orgUserId, Model model) throws OrgUserException {
        this.setToken();
        
        // 传递人员ID
        model.addAttribute("JCJGRY_RYBH", orgUserId);
        
        return "epsm/businessmanagement/organization/orguser/certificatemanage";
    }
    
    /**
     * 跳转到监测机构人员证书详情页面
     * 
     * @param orgUserId
     * @param model
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/certificatedetail/{operationType}/{orgUserId}/{certificateId}")
    public String certificateDetail(@PathVariable String operationType, @PathVariable String orgUserId, @PathVariable String certificateId, Model model) throws OrgUserException {
        this.setToken();
        
        // 传递人员ID
        model.addAttribute("JCJGRY_RYBH", orgUserId);
        
        // 传递监测机构人员证书ID
        model.addAttribute("JCJGRY_ZSBH", certificateId);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        
        return "epsm/businessmanagement/organization/orguser/certificatedetail";
    }
    
    /**
     * 查询监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/findcertificatepaging")
    public @ResponseBody PageInfo<Map<String, Object>> findCertificatePaging(@RequestBody Map<String, Object> param) throws OrgUserException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(orgUserService.findCertificate(param));
    }
    
    /**
     * 根据ID查询监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/getcertificatebyid")
    public @ResponseBody Map<String, Object> getCertificateById(@RequestBody Map<String, Object> param) throws OrgUserException {
        return orgUserService.getCertificateById(MapUtils.getString(param, "JCJGRY_ZSBH"));
    }
    
    /**
     * 添加监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/addcertificate")
    public @ResponseBody Map<String, Object> addCertificate(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(orgUserService.addCertificate(param));
    }
    
    /**
     * 更新监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/updatecertificate")
    public @ResponseBody Map<String, Object> updateCertificate(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgUserService.updateCertificate(param));
    }
    
    /**
     * 删除监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    @RequestMapping("/delcertificate")
    public @ResponseBody Map<String, Object> delCertificate(@RequestBody Map<String, Object> param) throws OrgUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(orgUserService.delCertificate(MapUtils.getString(param, "JCJGRY_ZSBH")));
    }
}
