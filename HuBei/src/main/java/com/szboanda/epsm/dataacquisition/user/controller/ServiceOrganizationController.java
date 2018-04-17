/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.common.utils.BusinessUserHelper;
import com.szboanda.epsm.dataacquisition.user.exception.BusinessUserException;
import com.szboanda.epsm.dataacquisition.user.exception.ServiceOrganizationException;
import com.szboanda.epsm.dataacquisition.user.service.IServiceOrganizationService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
* @Title:  服务机构模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月22日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/both/epsm/dataacquisition/user/serviceorganizationcontroller")
public class ServiceOrganizationController extends BaseBusinessController {

    /**
     * 服务机构模块Service实现
     */
    @Autowired
    private IServiceOrganizationService serviceOrganizationService;

    /**
     * 跳转到委托机构管理页面
     * 
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/serviceorganizationmanage")
    public String serviceOrganizationManage() throws ServiceOrganizationException {
        this.setToken();
        return "epsm/dataacquisition/user/serviceorganizationmanage";
    }

    /**
     * 跳转到管理用户详情页面
     * 
     * @param model
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/serviceorganizationdetail/{operationType}/{account}")
    public String serviceOrganizationDetail(@PathVariable String operationType, @PathVariable String account, Model model) throws ServiceOrganizationException {
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
        return "epsm/dataacquisition/user/serviceorganizationdetail";
    }

    /**
     * 查询服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/findserviceorganization")
    public @ResponseBody PageInfo<Map<String, Object>> findServiceOrganization(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(serviceOrganizationService.findServiceOrganization(param));
    }

    /**
     * 根据ID获取服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/getserviceorganizationbyid")
    public @ResponseBody Map<String, Object> getServiceOrganizationById(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        return serviceOrganizationService.getServiceOrganizationById(MapUtils.getString(param, "JBXX_ID"));
    }
    
    /**
     * 根据登录账号获取服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/getserviceorganizationbyaccount")
    public @ResponseBody Map<String, Object> getServiceOrganizationByAccount(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        return serviceOrganizationService.getServiceOrganizationByAccount(MapUtils.getString(param, "USERDLMC"));
    }

    /**
     * 添加服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/addserviceorganization")
    public @ResponseBody Map<String, Object> addServiceOrganization(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }

        return this.getResultMap(serviceOrganizationService.addServiceOrganization(param));
    }

    /**
     * 更新服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/updateserviceorganization")
    public @ResponseBody Map<String, Object> updateServiceOrganization(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }

        return this.getResultMap(serviceOrganizationService.updateServiceOrganization(param));
    }
    
    /**
     * 删除服务机构
     * 
     * @param param
     * @return
     * @throws ServiceOrganizationException
     */
    @RequestMapping("/delserviceorganization")
    public @ResponseBody Map<String, Object> delServiceOrganization(@RequestBody Map<String, Object> param) throws ServiceOrganizationException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(serviceOrganizationService.delServiceOrganization(MapUtils.getString(param, "JBXX_ID")));
    }
}
