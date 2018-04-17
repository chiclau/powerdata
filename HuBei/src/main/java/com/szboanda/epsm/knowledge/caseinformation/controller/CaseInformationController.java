/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.caseinformation.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.knowledge.caseinformation.exception.CaseInformationException;
import com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService;
/**
* @Title:  案例信息库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月19日 新建
*/
@Controller
@RequestMapping("/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller")
public class CaseInformationController extends BaseBusinessController {

    @Autowired
    private ICaseInformationService caseInformationService;
    
    
    /** 
    * @Title: caseInformationPage 
    * @Description: 返回案例信息库页面
    * @param SchemeManageException
    * @return
    * @throws 
    */
    @RequestMapping("/page")
    public String caseInformationPage() throws CaseInformationException {
        this.setToken();
        return "epsm/knowledge/caseinformation/caseinformationlist";
    }
    
    /** 
    * @Title: caseInformationPageDetail 
    * @Description: 返回案例信息库详情页面
    * @param @param id
    * @param @param model
    * @param @throws CaseInformationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/pagedetail")
    public String caseInformationPageDetail(String zbalid,Model model) throws CaseInformationException {
        this.setToken();
        model.addAttribute("zbalid",zbalid);
        model.addAttribute("isRead","true");
        return "epsm/knowledge/caseinformation/caseinformationdetail";
    }
    
    /** 
    * @Title: caseInformationPageAdd 
    * @Description: 返回新增页面
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/pageadd")
    public String caseInformationPageAdd(String xxlb,Model model) throws CaseInformationException {
        this.setToken();
        model.addAttribute("xxlb",xxlb);
        model.addAttribute("isRead","false");
        return "epsm/knowledge/caseinformation/caseinformationadd";
    }
    
    @RequestMapping("/pageedit")
    public String caseInformationPageEdit(String zbalid,Model model) throws CaseInformationException {
        this.setToken();
        model.addAttribute("zbalid",zbalid);
        return "epsm/knowledge/caseinformation/caseinformationedit";
    }
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询案例信息库
    * @param map
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findcaseinformation")
    public @ResponseBody PageInfo<Map<String,Object>> findCaseInformation(@RequestBody Map<String,Object> map){
        return caseInformationService.findCaseInformation(map);
    }
    
    /** 
    * @Title: findSelfMonitoringMethodTitle 
    * @Description: 查询案例信息库详情
    * @param @param map
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findcaseinformationdetail")
    public @ResponseBody List<Map<String,Object>> findCaseInformationDetail(@RequestBody Map<String,Object> map){
        return caseInformationService.findCaseInformationDetail(map);
    }
    
    /** 
    * @Title: delCaseInformation 
    * @Description: 删除对应的案例信息库
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/delcaseinformation")
    public @ResponseBody Map<String,Object> delCaseInformation(@RequestBody Map<String,Object> map){
        return this.getResultMap(caseInformationService.delCaseInformation(map));
    }
    
    /** 
    * @Title: addCaseInformation 
    * @Description: 添加案例信息 
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/addcaseinformation")
    public @ResponseBody Map<String,Object> addCaseInformation(@RequestBody Map<String,Object> map){
        return this.getResultMap(caseInformationService.addCaseInformation(map));
    }
}
