/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.decisionsupport.reportmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.decisionsupport.reportmanager.dao.ReportManagerDAO;
import com.szboanda.epsm.decisionsupport.reportmanager.exception.ReportManagerException;
import com.szboanda.epsm.decisionsupport.reportmanager.service.IReportManagerService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.Toolkit;

/**
 * @title:      报告管理
 * @fileName:   ReportManagerServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Service
public class ReportManagerServiceImpl  extends BaseBusinessService implements IReportManagerService{
    
    @Autowired
    ReportManagerDAO reportManagerDAO;
    
    //查询报告模版
	@Override
	public List<Map<String, Object>> queryReporTemplate(Map<String, Object> map) throws ReportManagerException {
		return reportManagerDAO.queryReporTemplate(map);
	}
	
    //查询报告
	@Override
	public List<Map<String, Object>> queryReport(Map<String, Object> map) throws ReportManagerException {
		return reportManagerDAO.queryReport(map);
	}
	
	 //根据ID查询报告模版
	@Override
	public Map<String, Object> queryReporTemplateById(String id) throws ReportManagerException {
		return reportManagerDAO.queryReporTemplateById(id);
	}
	
	 //根据ID查询报告
	@Override
	public Map<String, Object> queryReportById(String id) throws ReportManagerException {
		return reportManagerDAO.queryReportById(id);
	}
	
	//新增报告模版
	@Override
	public int addReporTemplate(Map<String, Object> model) throws ReportManagerException {
        int count = 0;
        try {
            model.put("M_ID", Toolkit.getID());
            count = reportManagerDAO.addReporTemplate(model);
        } catch (Exception e) {
        	LoggerUtil.error(this.getClass(), "新增报告模版异常 : " + model, e);
            throw new ReportManagerException("新增报告模版异常：" + model, e);
        } 
        return count;
	}
	
	//新增报告
	@Override
	public int addReport(Map<String, Object> model) throws ReportManagerException {
        int count = 0;
        try {
            model.put("J_ID", Toolkit.getID());
            count = reportManagerDAO.addReport(model);
        } catch (Exception e) {
        	LoggerUtil.error(this.getClass(), "新增报告异常 : " + model, e);
            throw new ReportManagerException("新增报告异常：" + model, e);
        } 
        return count;
	}
	
	//修改报告模版
	@Override
	public int updateReporTemplate(Map<String, Object> model) throws ReportManagerException {
		int count = 0;
        try {
            this.createBaseInfo(model);
            count = reportManagerDAO.updateReporTemplate(model);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "更新报告模版异常：" + model, ex);
            throw new ReportManagerException("更新报告模版异常：" + model, ex);
        }
		return count;
	}
	
	//修改报告
	@Override
	public int updateReport(Map<String, Object> model) throws ReportManagerException {
		int count = 0;
        try {
            this.createBaseInfo(model);
            count = reportManagerDAO.updateReport(model);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "更新报告异常：" + model, ex);
            throw new ReportManagerException("更新报告异常：" + model, ex);
        }
		return count;
	}
	
	//删除报告模版
	@Override
	public int delReporTemplate(String id) throws ReportManagerException {
		try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ID", id);
            int count = reportManagerDAO.delReporTemplate(param);
            return count;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "删除报告模版异常：" + id, ex);
            throw new ReportManagerException("删除报告模版异常：" + id, ex);
        }
	}
	
	//删除报告
	@Override
	public int delReport(String id) throws ReportManagerException {
		try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ID", id);
            int count = reportManagerDAO.delReport(param);
            return count;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "删除报告异常：" + id, ex);
            throw new ReportManagerException("删除报告异常：" + id, ex);
        }
	}
	
}