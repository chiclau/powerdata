/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.messageremind.controller;

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
import com.szboanda.epsm.workbench.messageremind.exception.MessageRemindException;
import com.szboanda.epsm.workbench.messageremind.service.IMessageRemindService;

/**
* @Title:  信息提醒
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日 新建
*/
@Controller
@RequestMapping("/both/epsm/workbench/messageremind/controller/messageremindcontroller")
public class MessageRemindController extends BaseBusinessController {

    @Autowired
    private IMessageRemindService messageRemindService;
    
    /** 
    * @Title: MessageRemindPage 
    * @Description: 返回信息提醒列表页面
    * @param @return
    * @param @throws MessageRemindException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/page")
    public String messageRemindPage() throws MessageRemindException {
        this.setToken();
        return "epsm/workbench/messageremind/messageremindlist";
    }
    
    /** 
    * @Title: notificationPageDetail 
    * @Description: 返回信息提醒详情页面
    * @param @param id
    * @param @param model
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/pagedetail")
     public String messageRemindPageDetail(String id,Model model) 
             throws MessageRemindException {
         this.setToken();
         model.addAttribute("id",id);
         return "epsm/workbench/messageRemind/messageReminddetail";
     }

     /** 
    * @Title: findMessageRemind 
    * @Description: 查询信息提醒列表
    * @param @param map
    * @param @return
    * @return 
    * PageInfo<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findmessageremind")
     public @ResponseBody PageInfo<Map<String,Object>> findMessageRemind(@RequestBody Map<String,Object> map){
         return messageRemindService.findMessageRemind(map);
     }
    
    /** 
    * @Title: delMessageRemind 
    * @Description: 删除站内信息
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/delmessageremind")
    public @ResponseBody Map<String,Object> delMessageRemind(@RequestBody Map<String,Object> map){
        return this.getResultMap(messageRemindService.delMessageRemind(map));
    }
    
    /** 
     * @Title: findSelfMonitoringMethodTitle 
     * @Description: 查询用户信息
     * @param @param map
     * @return 
     * List<Map<String,Object>>
     * @throws 
     */
     @RequestMapping("/findUser")
     public @ResponseBody List<Map<String,Object>> findUser(){
         return messageRemindService.findUser();
     }
}
