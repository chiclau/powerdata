/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.standardquery.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title:      知识库-标准查询
 * @fileName:   StandardQueryDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@AutoLoadDAO
public interface StandardQueryDAO extends BaseBusinessDAO {
    
    /**
     * 标准查询
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月17日
     */
    List<Map<String,Object>> queryStandard(Map<String,Object> map);
    
    /**
     * 查询标准详情
     * @param  bzid 标准ID
     * @return Map<String,Object>
     * @throws 
     * @author 王观发
     * @date 2017年10月19日
     */
    Map<String,Object> queryStandarDetail(String bzid);
}
