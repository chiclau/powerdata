/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.contactmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.workbench.contactmanager.dao.ContactManagerDAO;
import com.szboanda.epsm.workbench.contactmanager.exception.ContactManagerException;
import com.szboanda.epsm.workbench.contactmanager.service.IContactManagerService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.Toolkit;

/**
 * @title:      联系人管理
 * @fileName:   ContactManagerServiceImpl.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Service
public class ContactManagerServiceImpl  extends BaseBusinessService implements IContactManagerService{
    
    @Autowired
    ContactManagerDAO contactManagerDAO;
    
    //查询联系人
	@Override
	public List<Map<String, Object>> queryContacts(Map<String, Object> map) throws ContactManagerException {
		return contactManagerDAO.queryContacts(map);
	}
	
	 //根据ID查询联系人
	@Override
	public Map<String, Object> queryContactsById(String id) throws ContactManagerException {
		return contactManagerDAO.queryContactsById(id);
	}
	
	//新增联系人
	@Override
	public int addContacts(Map<String, Object> model) throws ContactManagerException {
        int count = 0;
        try {
            model.put("ID", Toolkit.getID());
            count = contactManagerDAO.addContacts(model);
        } catch (Exception e) {
        	LoggerUtil.error(this.getClass(), "新增联系人异常 : " + model, e);
            throw new ContactManagerException("新增联系人异常：" + model, e);
        } 
        return count;
	}
	
	//修改联系人
	@Override
	public int updateContacts(Map<String, Object> model) throws ContactManagerException {
		int count = 0;
        try {
            this.createBaseInfo(model);
            count = contactManagerDAO.updateContacts(model);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "更新联系人异常：" + model, ex);
            throw new ContactManagerException("更新联系人异常：" + model, ex);
        }
		return count;
	}
	
	//删除联系人
	@Override
	public int delContacts(String id) throws ContactManagerException {
		try {
            Map<String, Object> param = new HashMap<String, Object>();
            param.put("ID", id);
            int count = contactManagerDAO.delContacts(param);
            return count;
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "删除联系人异常：" + id, ex);
            throw new ContactManagerException("删除联系人异常：" + id, ex);
        }
	}

	
}