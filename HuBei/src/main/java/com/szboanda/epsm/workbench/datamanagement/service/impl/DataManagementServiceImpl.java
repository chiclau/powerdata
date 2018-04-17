/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.datamanagement.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.workbench.contactmanager.exception.ContactManagerException;
import com.szboanda.epsm.workbench.datamanagement.dao.DataManagementDAO;
import com.szboanda.epsm.workbench.datamanagement.exception.DataManagementException;
import com.szboanda.epsm.workbench.datamanagement.service.IDataManagementService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.Toolkit;

/**
 * @title:      企业-资料管理
 * @fileName:   DataManagementServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Service
public class DataManagementServiceImpl  extends BaseBusinessService implements IDataManagementService{
    
    @Autowired
    DataManagementDAO dataManagementDAO;
    
	
	 //根据ID查询资料信息
	@Override
	public Map<String, Object> queryDataInfoById(String id) throws DataManagementException {
		return dataManagementDAO.queryDataInfoById(id);
	}
	
	//修改资料信息
	@Override
	public int updateDataInfo(Map<String, Object> model) throws DataManagementException {
		int count = 0;
        try {
            this.createBaseInfo(model);
            count = dataManagementDAO.updateDataInfo(model);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "更新资料信息异常：" + model, ex);
            throw new DataManagementException("更新资料信息异常：" + model, ex);
        }
		return count;
	}
	
	//添加企业固废处置厂附属表记录
	@Override
	public int addSolidWastefactory(Map<String, Object> model) throws DataManagementException {
		int count = 0;
        try {
            model.put("ID", Toolkit.getID());
            count = dataManagementDAO.addSolidWastefactory(model);
        } catch (Exception e) {
        	LoggerUtil.error(this.getClass(), "添加企业固废处置厂异常 : " + model, e);
            throw new ContactManagerException("添加企业固废处置厂异常：" + model, e);
        } 
        return count;
	}
	
	//修改企业固废处置厂信息
	@Override
	public int updateSolidWastefactory(Map<String, Object> model) throws DataManagementException {
		int count = 0;
        try {
            this.createBaseInfo(model);
            count = dataManagementDAO.updateDataInfo(model);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "更新企业固废处置厂信息异常：" + model, ex);
            throw new DataManagementException("更新企业固废处置厂信息异常：" + model, ex);
        }
		return count;
	}
	
	//删除企业固废处置厂信息
	@Override
	public int delSolidWastefactory(String qyid) throws DataManagementException {
		try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("QYID", qyid);
            int count = dataManagementDAO.delSolidWastefactory(param);
            return count;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "删除企业固废处置厂信息异常：" + qyid, ex);
            throw new DataManagementException("删除企业固废处置厂信息异常：" + qyid, ex);
        }
	}
	
}