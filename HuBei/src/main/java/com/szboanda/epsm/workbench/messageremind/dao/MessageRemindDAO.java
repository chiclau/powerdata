/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.messageremind.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:企业自行监测信息
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日  新建
*/
@AutoLoadDAO
public interface MessageRemindDAO extends BaseBusinessDAO {
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询站内信息提醒
    * @param 
    * @return List<Map<String,Object>>
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findMessageRemind(Map<String,Object> map);
    
    /** 
    * @Title: delMessageRemind 
    * @Description: 删除站内信息提醒
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int delMessageRemind(Map<String,Object> map);
    
    /** 
    * @Title: findUser 
    * @Description: 查询用户信息
    * @param @return
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findUser();
}
