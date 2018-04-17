/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.standardquery.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.knowledge.standardquery.exception.StandardQueryException;

/**
 * @title:      知识库-标准查询 
 * @fileName:   IStandardQueryService.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
public interface IStandardQueryService {

   /**
    * 标准查询
    * @param Map<String,Object>
    * @return List<Map<String,Object>>
    * @throws StandardQueryException
    * @author 王观发
    * @date 2017年10月17日
    */
    List<Map<String,Object>> queryStandard (Map<String,Object> map) 
            throws StandardQueryException;
    
    /**
     * 查询标准详情
     * @return Map<String,Object>
     * @throws StandardQueryException
     * @author 王观发
     * @date 2017年10月19日
     */
    Map<String,Object> queryStandarDetail(String bzid) throws StandardQueryException;
}
