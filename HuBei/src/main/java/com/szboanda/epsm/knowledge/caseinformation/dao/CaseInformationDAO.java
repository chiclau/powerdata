/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.caseinformation.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.platform.common.annotation.AutoLoadDAO;
import com.szboanda.platform.common.base.BaseDAO;

/**
* @Title:企业自行监测信息
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月19日  新建
*/
@AutoLoadDAO
public interface CaseInformationDAO extends BaseDAO {
    
    /** 
    * @Title: findSelfMonitoringMethod 
    * @Description: 查询案例信息库
    * @param 
    * @return List<Map<String,Object>>
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findCaseInformation(Map<String,Object> map);
    
    /** 
    * @Title: delcaseinformation 
    * @Description: 删除对应的案例信息库
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int delCaseInformation(Map<String,Object> map);
    
    /** 
    * @Title: addCaseInformation 
    * @Description: 添加案例信息
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int addCaseInformation(Map<String,Object> map);
    
    /** 
    * @Title: updateCaseInformation 
    * @Description: 修改案例信息库
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int updateCaseInformation(Map<String,Object> map);
}
