/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.decisionsupport.reportmanager.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.decisionsupport.reportmanager.exception.ReportManagerException;

/**
 * @title:      报告管理 
 * @fileName:   IReportManagerService.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
public interface IReportManagerService {

   /**
    * 查询报告模版
    * @param Map<String,Object>
    * @return List<Map<String,Object>>
    * @throws ReportManagerException
    * @author 王观发
    * @date 2017年10月17日
    */
    List<Map<String,Object>> queryReporTemplate (Map<String,Object> map) 
            throws ReportManagerException;
    
    /**
     * 查询报告
     * @param Map<String,Object>
     * @return List<Map<String,Object>>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月17日
     */
     List<Map<String,Object>> queryReport (Map<String,Object> map) 
             throws ReportManagerException;
    
    /**
     * 根据ID查询报告模版
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryReporTemplateById(String id)
            throws ReportManagerException;
    
    /**
     * 根据ID查询报告
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryReportById(String id)
            throws ReportManagerException;
    
    /**
     * 添加报告模版
     * 
     * @param model
     * @return
     */
    int addReporTemplate(Map<String, Object> model) throws ReportManagerException;
    
    /**
     * 添加报告
     * 
     * @param model
     * @return
     */
    int addReport(Map<String, Object> model) throws ReportManagerException;

    /**
     * 修改报告模版
     * 
     * @param model
     * @return
     */
    int updateReporTemplate(Map<String, Object> model) throws ReportManagerException;
    
    /**
     * 修改报告
     * 
     * @param model
     * @return
     */
    int updateReport(Map<String, Object> model) throws ReportManagerException;
    
    /**
     * 根据ID删除报告模版
     * 
     * @param id
     * @return
     */
    int delReporTemplate(String id) throws ReportManagerException;
    
    /**
     * 根据ID删除报告
     * 
     * @param id
     * @return
     */
    int delReport(String id) throws ReportManagerException;
    
}
