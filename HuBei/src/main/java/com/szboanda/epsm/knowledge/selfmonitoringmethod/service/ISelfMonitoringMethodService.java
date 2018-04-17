/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.selfmonitoringmethod.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.exception.SelfMonitoringMethodException;

/**
* @Title:  企业自行监测数据
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月12日 新建
*/
public interface ISelfMonitoringMethodService {

    /** 
     * @Title: findSelfMonitoringMethod 
     * @Description: 查询自行监测方法库
     * @param 
     * @return List<Map<String,Object>>
     * List<Map<String,Object>>
     * @throws 
     */
    PageInfo<Map<String,Object>> findSelfMonitoringMethod(Map<String,Object> map) 
            throws SelfMonitoringMethodException;
    
    /** 
    * @Title: findSelfMonitoringMethodTitle 
    * @Description: 查询自行监测方法库
    * @param @param map
    * @param @return
    * @param @throws SelfMonitoringMethodException
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findSelfMonitoringMethodTitle(Map<String,Object> map)
            throws SelfMonitoringMethodException;
}
