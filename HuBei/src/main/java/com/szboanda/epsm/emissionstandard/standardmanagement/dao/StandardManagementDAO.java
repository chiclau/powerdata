/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.emissionstandard.standardmanagement.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title:      标准管理
 * @fileName:   StandardManagementDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@AutoLoadDAO
public interface StandardManagementDAO extends BaseBusinessDAO {
    
    /**
     * 查询未监测情况
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月17日
     */
    List<Map<String,Object>> queryNotMonitored(Map<String,Object> map);
    
    /**
     * 查询测点所属标准
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月20日
     */
    List<Map<String,Object>> queryPointStandard(Map<String,Object> map);
}
