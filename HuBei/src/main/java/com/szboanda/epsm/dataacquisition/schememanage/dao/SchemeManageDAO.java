/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.schememanage.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title: 监测方案管理
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月26日  新建
*/
@AutoLoadDAO
public interface SchemeManageDAO extends BaseBusinessDAO {
    
    /** 
    * @Title: findSchemeManage 
    * @Description: 查询案例信息库
    * @param 
    * @return List<Map<String,Object>>
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findSchemeManage(Map<String,Object> map);
    
    /** 
    * @Title: delSchemeManage 
    * @Description: 删除监测方案
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int delSchemeManage(Map<String,Object> map);
    
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
    * @Title: updateSchemeManage 
    * @Description: 修改案例信息状态
    * @param @param map
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int updateSchemeManage(Map<String,Object> map);
}
