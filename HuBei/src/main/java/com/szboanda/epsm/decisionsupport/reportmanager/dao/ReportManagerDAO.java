/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.decisionsupport.reportmanager.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title:      报告管理
 * @fileName:   ReportManagerDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@AutoLoadDAO
public interface ReportManagerDAO extends BaseBusinessDAO {
    
    /**
     * 查询报告模版
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月17日
     */
    List<Map<String,Object>> queryReporTemplate(Map<String,Object> map);
    
    /**
     * 查询报告
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月17日
     */
    List<Map<String,Object>> queryReport(Map<String,Object> map);
    
    /**
     * 根据ID查询报告模版
     * @return Map<String,Object>
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryReporTemplateById(String id);
    
    /**
     * 根据ID查询报告
     * @return Map<String,Object>
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryReportById(String id);
    
    /**
     * 新增报告模版
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int addReporTemplate(Map<String, Object> model);
    
    /**
     * 新增报告
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int addReport(Map<String, Object> model);
    
    /**
     * 修改报告模版
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int updateReporTemplate(Map<String, Object> model);
    
    /**
     * 修改报告
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int updateReport(Map<String, Object> model);
    
    /**
     * 删除报告模版
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int delReporTemplate(Map<String, Object> param);
    
    /**
     * 删除报告
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int delReport(Map<String, Object> param);
    
}
