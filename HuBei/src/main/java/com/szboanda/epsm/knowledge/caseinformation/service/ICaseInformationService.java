/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.caseinformation.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.knowledge.caseinformation.exception.CaseInformationException;

/**
* @Title:  案例信息库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月19日 新建
*/
public interface ICaseInformationService {

    /** 
    * @Title: findCaseInformation 
    * @Description: 查询案例信息库
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * PageInfo<Map<String,Object>>
    * @throws 
    */
    PageInfo<Map<String,Object>> findCaseInformation(Map<String,Object> map) 
            throws CaseInformationException;
    
    
    /** 
    * @Title: findCaseInformationDetail 
    * @Description: 查询案例信息库详情 
    * @param @param map
    * @param @throws SelfMonitoringMethodException
    * @return 
    * List<Map<String,Object>>
    * @throws 
    */
    List<Map<String,Object>> findCaseInformationDetail(Map<String,Object> map)
            throws CaseInformationException;
    
    /** 
    * @Title: delcaseinformation 
    * @Description: 删除对应的案例信息库
    * @param @param map
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * int
    * @throws 
    */
    int delCaseInformation(Map<String,Object> map) throws CaseInformationException;
    
    /** 
    * @Title: addCaseInformation 
    * @Description: 添加案例信息
    * @param @param map
    * @param @return
    * @param @throws CaseInformationException
    * @return 
    * int
    * @throws 
    */
    int addCaseInformation(Map<String,Object> map) throws CaseInformationException;
}
