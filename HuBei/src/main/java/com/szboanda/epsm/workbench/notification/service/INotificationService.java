/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.notification.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.workbench.notification.exception.NotificationException;

/**
* @Title:  企业自行监测数据
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月21日 新建
*/
public interface INotificationService {

    /** 
     * @Title: findSelfMonitoringMethod 
     * @Description: 查询通知公告
     * @param 
     * @return List<Map<String,Object>>
     * List<Map<String,Object>>
     * @throws 
     */
    PageInfo<Map<String,Object>> findNotification(Map<String,Object> map) 
            throws NotificationException;
    
    /** 
    * @Title: findSelfMonitoringMethodTitle 
    * @Description: 查询省级或市级管辖区域
    * @param @param map
    * @param @return
    * @param @throws SelfMonitoringMethodException
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findArea(Map<String,Object> map)
            throws NotificationException;
    
    /** 
    * @Title: findUser 
    * @Description: 查询发布公告的用户
    * @param @return
    * @param @throws NotificationException
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findUser()throws NotificationException;
    
    /** 
    * @Title: findMyOwenNotification 
    * @Description: 查询发布给自己的通知公告
    * @param @return
    * @param @throws NotificationException
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    PageInfo<Map<String,Object>> findMyOwenNotification(Map<String,Object> map)throws NotificationException;
    
    /** 
    * @Title: addNotification 
    * @Description: 新增通知公告
    * @param @param ma
    * @param @return
    * @param @throws NotificationException
    * @return 
    * int
    * @throws 
    */
    int addNotification(Map<String,Object> ma)throws NotificationException;
}
