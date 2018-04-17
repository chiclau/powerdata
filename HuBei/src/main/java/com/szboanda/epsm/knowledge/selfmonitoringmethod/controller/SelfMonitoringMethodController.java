/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.selfmonitoringmethod.controller;

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
import com.szboanda.epsm.knowledge.selfmonitoringmethod.exception.SelfMonitoringMethodException;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService;

/**
* @Title:  自行监测方法库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月14日 新建
*/
@Controller
@RequestMapping("/both/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller")
public class SelfMonitoringMethodController extends BaseBusinessController {

    @Autowired
    private ISelfMonitoringMethodService selfMonitoringMethodService;
    
    
    /** 
    * @Title: selfMonitoringMethodpage 
    * @Description: 返回自行监测方法库页面
    * @param @return
    * @param CaseInformationException
    * @return
    * @throws 
    */
    @RequestMapping("/page")
    public String selfMonitoringMethodPage() throws SelfMonitoringMethodException {
        this.setToken();
        return "epsm/knowledge/selfmonitoringmethod/selfmonitoringmethod";
    }
    
    @RequestMapping("/pagedetail")
    public String selfMonitoringMethodPageDetail(String id,Model model) throws SelfMonitoringMethodException {
        this.setToken();
        model.addAttribute("id",id);
        return "epsm/knowledge/selfmonitoringmethod/selfmonitoringmethoddetail";
    }
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询自行监测方法库
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findselfmonitoringmethod")
    public @ResponseBody PageInfo<Map<String,Object>> findSelfMonitoringMethod(@RequestBody Map<String,Object> map){
        return selfMonitoringMethodService.findSelfMonitoringMethod(map);
    }
    
    /** 
    * @Title: findSelfMonitoringMethodTitle 
    * @Description: 查询自行监测方法库
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findselfmonitoringmethodtitle")
    public @ResponseBody List<Map<String,Object>> findSelfMonitoringMethodTitle(@RequestBody Map<String,Object> map){
        return selfMonitoringMethodService.findSelfMonitoringMethodTitle(map);
    }
}
