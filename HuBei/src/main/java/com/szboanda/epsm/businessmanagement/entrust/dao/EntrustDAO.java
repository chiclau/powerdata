/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.entrust.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:委托机构
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日  新建
*/
@AutoLoadDAO
public interface EntrustDAO extends BaseBusinessDAO {
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询委托机构
    * @param 
    * @return List<Map<String,Object>>
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findEntrust(Map<String,Object> map);
    
    /** 
    * @Title: findDivision 
    * @Description: 查询行政区划代码
    * @param @param xzqhdm
    * @param @return
    * @return 
    * String
    * @throws 
    */
    String findDivision(String xzqhdm);
}
