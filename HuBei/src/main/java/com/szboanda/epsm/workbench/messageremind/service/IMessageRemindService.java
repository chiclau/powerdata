/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.messageremind.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.workbench.messageremind.exception.MessageRemindException;

/**
* @Title:  企业自行监测数据
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日 新建
*/
public interface IMessageRemindService {

    /** 
     * @Title: findSelfMonitoringMethod 
     * @Description: 查询站内信息
     * @param 
     * @return List<Map<String,Object>>
     * List<Map<String,Object>>
     * @throws 
     */
    PageInfo<Map<String,Object>> findMessageRemind(Map<String,Object> map) 
            throws MessageRemindException;
    
    /** 
    * @Title: delMessageRemind 
    * @Description: 删除站内信息
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int delMessageRemind(Map<String,Object> map);
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#findUser()
     */
    List<Map<String,Object>> findUser()throws MessageRemindException;
}
