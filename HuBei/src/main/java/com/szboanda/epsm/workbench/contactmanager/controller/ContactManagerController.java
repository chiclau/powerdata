/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.contactmanager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.workbench.contactmanager.exception.ContactManagerException;
import com.szboanda.epsm.workbench.contactmanager.service.IContactManagerService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
 * @title:      联系人管理
 * @fileName:   ContactManagerController.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Controller
@RequestMapping("/both/epsm/workbench/contactmanager/contactmanagercontroller")
public class ContactManagerController extends BaseBusinessController {
	@Autowired
	IContactManagerService contactManagerService;
    
    @RequestMapping("/page")
    public String queryContactsPage() throws ContactManagerException {
        this.setToken();
        return "epsm/workbench/contactmanager/contactmanager";
    }			
    
    @RequestMapping("/pagedetail/{operationType}/{id}")
    public  String queryContactsDetail(@PathVariable String operationType, @PathVariable String id,Model model) throws ContactManagerException {
    	 model.addAttribute("ID",id);
    	 
         // 传递操作类型, 操作类型包含read,add,edit
         model.addAttribute("operationType", operationType);
         return "epsm/workbench/contactmanager/contactdetail";
    }
    
    /**
     * 查询联系人
     * @return PageInfo<Map<String,Object>>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping("/querycontacts")
    public  @ResponseBody PageInfo<Map<String, Object>> queryContacts(@RequestBody Map<String,Object> param) throws ContactManagerException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(contactManagerService.queryContacts(param));
    }
    
    
   /**
    * 根据ID查询联系人
    * @return Map<String,Object>
    * @throws ContactManagerException
    * @author 王观发
    * @date 2017年10月21日
    */
    @RequestMapping("/querycontactsbyid")
    public @ResponseBody Map<String, Object> queryContactsById(@RequestBody Map<String, Object> param) throws ContactManagerException {
        return contactManagerService.queryContactsById(MapUtils.getString(param, "ID"));
    }
    
    /**
     * 新增联系人
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/addcontacts", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addContacts(@RequestBody Map<String, Object> model) throws ContactManagerException {
        if (!this.validToken(model)) {
            return this.getFailMap();
        }
        int count = contactManagerService.addContacts(model);
        return this.getResultMap(count, model);
    }
    
    /**
     * 更新联系人
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/updatecontacts")
    @ResponseBody
    public Map<String, Object> updateContacts(@RequestBody Map<String, Object> model) throws ContactManagerException {
        if (!this.validToken(model)) { // 重复提交
            return this.getFailMap();
        }
        if (model.get("ID") == null) {
            throw new ContactManagerException("更新联系人ID为空");
        }
        int count = contactManagerService.updateContacts(model);
        return this.getResultMap(count);
    }
    
    /**
     * 删除联系人
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/delcontacts")
    @ResponseBody
    public Map<String, Object> delContacts(@RequestBody Map<String, Object> param) throws ContactManagerException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(contactManagerService.delContacts(MapUtils.getString(param, "ID")));
    }
}
