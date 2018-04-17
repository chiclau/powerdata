/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.emissionstandard.standardmanagement.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.emissionstandard.standardmanagement.dao.StandardManagementDAO;
import com.szboanda.epsm.emissionstandard.standardmanagement.exception.StandardManagementException;
import com.szboanda.epsm.emissionstandard.standardmanagement.service.IStandardManagementService;

/**
 * @title:      标准管理
 * @fileName:   StandardManagementServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Service
public class StandardManagementServiceImpl  extends BaseBusinessService implements IStandardManagementService{
    
    @Autowired
    StandardManagementDAO standardManagementDAO;
    
    //查询未监测情况
	@Override
	public List<Map<String, Object>> queryNotMonitored(Map<String, Object> map) throws StandardManagementException {
		return standardManagementDAO.queryNotMonitored(map);
	}
	
	//查询监测点所属标准
	@Override
	public List<Map<String, Object>> queryPointStandard(Map<String, Object> map) throws StandardManagementException {
		return standardManagementDAO.queryPointStandard(map);
	}
	
	
}