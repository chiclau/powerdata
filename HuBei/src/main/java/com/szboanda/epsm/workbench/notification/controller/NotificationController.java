/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.notification.controller;

import java.util.HashMap;
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
import com.szboanda.epsm.workbench.notification.exception.NotificationException;
import com.szboanda.epsm.workbench.notification.service.INotificationService;

/**
* @Title:  通知公告
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月21日 新建
*/
@Controller
public class NotificationController extends BaseBusinessController {

    @Autowired
    private INotificationService notificationService;
    
    
    /** 
    * @Title: notificationPage 
    * @Description: 返回通知公告列表页面
    * @param @return
    * @param @throws NotificationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/both/epsm/workbench/notification/controller/notificationcontroller/searchpage")
    public String notificationSearchPage() throws NotificationException {
        this.setToken();
        return "epsm/workbench/notification/notificationsearchlist";
    }
    
    /** 
     * @Title: notificationPage 
     * @Description: 返回通知公告列表页面
     * @param @return
     * @param @throws NotificationException
     * @return 
     * String
     * @throws 
     */
     @RequestMapping("/ep/epsm/workbench/notification/controller/notificationcontroller/page")
     public String notificationPage() throws NotificationException {
         this.setToken();
         return "epsm/workbench/notification/notificationlist";
     }
     
     /** 
    * @Title: caseInformationPageDetail 
    * @Description: 返回通知公告列表详情页面
    * @param @param zbalid
    * @param @param model
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/both/epsm/workbench/notification/controller/notificationcontroller/pagedetail")
     public String notificationPageDetail(String id,Model model) throws NotificationException {
         this.setToken();
         model.addAttribute("id",id);
         return "epsm/workbench/notification/notificationdetail";
     }
     
     /** 
    * @Title: caseInformationPageAdd 
    * @Description: 返回增加通知公告页面 
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/ep/epsm/workbench/notification/controller/notificationcontroller/pageadd")
     public String notificationPageAdd() throws NotificationException {
         this.setToken();
         return "epsm/workbench/notification/notificationadd";
     }
     
     /** 
     * @Title: findSelfMonitoringMethod 
     * @Description: 查询通知公告列表
     * @param map
     * @return 
     * List<Map<String,Object>>
     * @throws 
     */
     @RequestMapping("/ep/epsm/workbench/notification/controller/notificationcontroller/findnotification")
     public @ResponseBody PageInfo<Map<String,Object>> findNotification(@RequestBody Map<String,Object> map){
         return notificationService.findNotification(map);
     }
     
     
    /** 
    * @Title: findArea 
    * @Description: 查询省级或市级管辖区域
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/both/epsm/workbench/notification/controller/notificationcontroller/findArea")
     public @ResponseBody List<Map<String,Object>> findArea(){
        Map<String,Object> map = new HashMap<String,Object>();
         map.put("yhjb", this.getCurrUser().getYhjb());
         return notificationService.findArea(map);
     }
     
     /** 
     * @Title: findSelfMonitoringMethodTitle 
     * @Description: 查询用户信息
     * @param @param map
     * @return 
     * List<Map<String,Object>>
     * @throws 
     */
     @RequestMapping("/both/epsm/workbench/notification/controller/notificationcontroller/findUser")
     public @ResponseBody List<Map<String,Object>> findUser(){
         return notificationService.findUser();
     }
     

    /** 
    * @Title: findMyOwenNotification 
    * @Description: 查询发布给自己的通知公告
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    @RequestMapping("/both/epsm/workbench/notification/controller/notificationcontroller/findmyowennotification")
     public @ResponseBody PageInfo<Map<String,Object>> findMyOwenNotification(@RequestBody Map<String,Object> map){
         return notificationService.findMyOwenNotification(map);
     }

    /** 
    * @Title: addNotification 
    * @Description: 新增通知公告
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/ep/epsm/workbench/notification/controller/notificationcontroller/addnotification")
    public @ResponseBody Map<String,Object> addNotification(@RequestBody Map<String,Object> map){
        return this.getResultMap(notificationService.addNotification(map));
    }
}
