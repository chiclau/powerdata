/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.datamanagement.dao;

import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title:      企业-资料管理
 * @fileName:   DataManagementDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@AutoLoadDAO
public interface DataManagementDAO extends BaseBusinessDAO {
    
    
    /**
     * 根据ID查询资料信息
     * @return Map<String,Object>
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryDataInfoById(String id);
    
    /**
     * 修改资料信息
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int updateDataInfo(Map<String, Object> model);
    
    /**
     * 添加企业固废处置厂附属表记录
     * @return int
     * @author 王观发
     * @date 2017年10月26日
     */
    int addSolidWastefactory(Map<String, Object> model);
    
    /**
     * 修改企业固废处置厂信息
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int updateSolidWastefactory(Map<String, Object> model);
    
    /**
     * 删除企业固废处置厂信息
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int delSolidWastefactory(Map<String, Object> param);
    
}
