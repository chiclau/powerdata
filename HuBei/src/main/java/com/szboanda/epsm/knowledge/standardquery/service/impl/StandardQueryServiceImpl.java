/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.standardquery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.knowledge.standardquery.dao.StandardQueryDAO;
import com.szboanda.epsm.knowledge.standardquery.exception.StandardQueryException;
import com.szboanda.epsm.knowledge.standardquery.service.IStandardQueryService;

/**
 * @title:      知识库-标准查询
 * @fileName:   IStandardQueryServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Service
public class StandardQueryServiceImpl  extends BaseBusinessService implements IStandardQueryService{
    
    @Autowired
    StandardQueryDAO standardQueryDAO;
    
    //标准查询
	@Override
	public List<Map<String, Object>> queryStandard(Map<String, Object> map) throws StandardQueryException {
		return standardQueryDAO.queryStandard(map);
	}
	
	//标准详情查询
	@Override
	public Map<String, Object> queryStandarDetail(String bzid) throws StandardQueryException {
		// TODO Auto-generated method stub
		return standardQueryDAO.queryStandarDetail(bzid);
	}
}