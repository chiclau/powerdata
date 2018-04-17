/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.notification.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:企业自行监测信息
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月14日  新建
*/
@AutoLoadDAO
public interface NotificationDAO extends BaseBusinessDAO {
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询自行监测方法库
    * @param 
    * @return List<Map<String,Object>>
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findNotification(Map<String,Object> map);
    
    /** 
    * @Title: findAreaSheng 
    * @Description: 查询省级管辖区域
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findAreaSheng(Map<String,Object> map);
    
    /** 
    * @Title: findAreaShi 
    * @Description: 查询市级管辖区域
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findAreaShi(Map<String,Object> map);
    
    /** 
    * @Title: findAreaShi 
    * @Description: 查询县级管辖企业
    * @param @param map
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findAreaXian(Map<String,Object> map);
    
    /** 
    * @Title: findUser 
    * @Description: 查询发布公告的用户
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findUser();
    
    /** 
    * @Title: findMyOwenNotification 
    * @Description: 查询发布给自己的通知公告
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findMyOwenNotification(Map<String,Object> map);
    
    /** 
    * @Title: addNotification 
    * @Description: 新增通知公告
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int addNotification(Map<String,Object> map);
    
    /** 
    * @Title: findXzqh 
    * @Description: 查询行政区划 
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    Map<String,Object> findXzqh(Map<String,Object> map);
}
