/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.schememanage.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.dataacquisition.schememanage.exception.SchemeManageException;
import com.szboanda.epsm.dataacquisition.schememanage.service.ISchemeManageService;

/**
* @Title:  案例信息库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月26日 新建
*/
@Controller
@RequestMapping("co/epsm/dataacquisition/schememanage/controller/schememanagecontroller")
public class SchemeManageController extends BaseBusinessController {

    @Autowired
    private ISchemeManageService schemeManageService;
    
    
    /** 
    * @Title: caseInformationPage 
    * @Description: 监测方案管理页面
    * @param @return
    * @param @throws SchemeManageException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/page")
    public String schemeManagePage() throws SchemeManageException {
        this.setToken();
        return "epsm/dataacquisition/schememanage/schememanagelist";
    }
    
    /** 
    * @Title: schemeManagePageDetail 
    * @Description: 方案信息详情
    * @param @param id
    * @param @param model
    * @param @return
    * @param @throws SchemeManageException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/pagedetail")
    public String schemeManagePageDetail(String id,Model model) throws SchemeManageException {
        this.setToken();
        model.addAttribute("isRead","true");
        return "epsm/dataacquisition/schememanage/schememanagedetail";
    }
    
//    /** 
//    * @Title: caseInformationPageAdd 
//    * @Description: 返回新增页面
//    * @param @return
//    * @param @throws CaseInformationException
//    * @return 
//    * String
//    * @throws 
//    */
//    @RequestMapping("/pageadd")
//    public String caseInformationPageAdd(String xxlb,Model model) throws CaseInformationException {
//        this.setToken();
//        model.addAttribute("xxlb",xxlb);
//        model.addAttribute("isRead","false");
//        return "epsm/knowledge/caseinformation/caseinformationadd";
//    }
//    
//    @RequestMapping("/pageedit")
//    public String caseInformationPageEdit(String zbalid,Model model) throws CaseInformationException {
//        this.setToken();
//        model.addAttribute("zbalid",zbalid);
//        return "epsm/knowledge/caseinformation/caseinformationedit";
//    }
//    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询监测方案
    * @param map
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findschememanage")
    public @ResponseBody PageInfo<Map<String,Object>> findSchemeManage(@RequestBody Map<String,Object> map){
        return schemeManageService.findSchemeManage(map);
    }
    
//    /** 
//    * @Title: findSelfMonitoringMethodTitle 
//    * @Description: 查询案例信息库详情
//    * @param @param map
//    * @return 
//    * List<Map<String,Object>>
//    * @throws 
//    */
//    @RequestMapping("/findcaseinformationdetail")
//    public @ResponseBody List<Map<String,Object>> findCaseInformationDetail(@RequestBody Map<String,Object> map){
//        return caseInformationService.findCaseInformationDetail(map);
//    }

    
    /** 
    * @Title: delSchemeManage 
    * @Description: 删除监测方案
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/delschememanage")
    public @ResponseBody Map<String,Object> delSchemeManage(@RequestBody Map<String,Object> map){
        return this.getResultMap(schemeManageService.delSchemeManage(map));
    }
    
    /** 
    * @Title: updateSchemeManage 
    * @Description: 更新监测方案状态
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/updateschememanage")
    public @ResponseBody Map<String,Object> updateSchemeManage(@RequestBody Map<String,Object> map){
        return this.getResultMap(schemeManageService.updateSchemeManage(map));
    }
}
