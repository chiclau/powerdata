/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.datamanagement.service;

import java.util.Map;

import com.szboanda.epsm.workbench.datamanagement.exception.DataManagementException;

/**
 * @title:      企业-资料管理 
 * @fileName:   IDataManagementService.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
public interface IDataManagementService {

    
    /**
     * 根据ID查询资料信息
     * @return Map<String,Object>
     * @throws DataManagementException
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryDataInfoById(String id)
            throws DataManagementException;

    /**
     * 修改资料信息
     * @return int
     * @throws DataManagementException
     * @author 王观发
     * @date 2017年10月23日
     */
    int updateDataInfo(Map<String, Object> model) throws DataManagementException;
    
    /**
     * 添加企业固废处置厂附属表记录
     * @return int
     * @throws DataManagementException
     * @author 王观发
     * @date 2017年10月26日
     */
    int addSolidWastefactory(Map<String, Object> model) throws DataManagementException;
    
    /**
     * 修改企业固废处置厂信息
     * 
     * @param model
     * @return
     */
    int updateSolidWastefactory(Map<String, Object> model) throws DataManagementException;
    
    /**
     * 根据企业ID删除企业固废处置厂信息
     * 
     * @param qyid
     * @return
     */
    int delSolidWastefactory(String qyid) throws DataManagementException;
}
