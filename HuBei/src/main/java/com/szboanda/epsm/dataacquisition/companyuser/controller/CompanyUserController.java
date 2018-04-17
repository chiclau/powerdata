/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.companyuser.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.utils.BusinessUserHelper;
import com.szboanda.epsm.dataacquisition.companyuser.exception.CompanyUserException;
import com.szboanda.epsm.dataacquisition.companyuser.service.ICompanyUserService;
import com.szboanda.epsm.dataacquisition.user.exception.BusinessUserException;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/**
* @Title:  企业用户模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/co/epsm/dataacquisition/companyuser/companyusercontroller")
public class CompanyUserController extends BaseBusinessController {
    
    /**
     * 企业用户模块Service实现
     */
    @Autowired
    private ICompanyUserService companyUserService;
    
    /**
     * 跳转到企业用户信息管理页面
     * 
     * @return
     * @throws CompanyUserException
     */
    @RequestMapping("/companyusermanage")
    public String companyUserManage() throws CompanyUserException {
        this.setToken();
        return "epsm/dataacquisition/companyuser/companyusermanage";
    }
    
    /**
     * 查询用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/findsystemuser")
    public @ResponseBody PageInfo<Map<String, Object>> findCompanyUser(@RequestBody Map<String, Object> param) throws CompanyUserException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(companyUserService.findCompanyUser(param));
    }
    
    /**
     * 跳转到管理用户详情页面
     * 
     * @param model
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/companyuserdetail/{operationType}/{account}")
    public String companyUserDetail(@PathVariable String operationType, @PathVariable String account, Model model) throws CompanyUserException {
        this.setToken();
        
        // 新增类型操作，则需要生成系统账号
        if ("add".equals(operationType)) {
            // 生成唯一的系统账号
            account = BusinessUserHelper.getUserAccount();
        }
        
        // 传递系统账号
        model.addAttribute("XTZH", account);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        return "epsm/dataacquisition/companyuser/companyuserdetail";
    }
    
    /**
     * 添加企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    @RequestMapping("/addcompanyuser")
    public @ResponseBody Map<String, Object> addCompanyUser(@RequestBody Map<String, Object> param) throws CompanyUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(companyUserService.addCompanyUser(param));
    }
    
    /**
     * 更新企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    @RequestMapping("/updatecompanyuser")
    public @ResponseBody Map<String, Object> updateCompanyUser(@RequestBody Map<String, Object> param) throws CompanyUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        List<String> delRoles = new ArrayList<String>();
        delRoles.add(CommonBussinessConstants.ROLE_QYYH_TB);
        delRoles.add(CommonBussinessConstants.ROLE_QYYH_SH);
        param.put("delRoles", delRoles);
        
        return this.getResultMap(companyUserService.updateCompanyUser(param));
    }
    
    /**
     * 删除企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    @RequestMapping("/delcompanyuser")
    public @ResponseBody Map<String, Object> delCompanyUser(@RequestBody Map<String, Object> param) throws CompanyUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(companyUserService.delCompanyUser(MapUtils.getString(param, "YHID")));
    }
    
    /**
     * 根据系统账号查询企业用户
     * 
     * @param param
     * @return
     * @throws CompanyUserException
     */
    @RequestMapping("/getcompanyuserbyaccount")
    public @ResponseBody Map<String, Object> getCompanyUserByAccount(@RequestBody Map<String, Object> param) throws CompanyUserException {
        return companyUserService.getCompanyUserByAccount(MapUtils.getString(param, "XTZH"));
    }
}
