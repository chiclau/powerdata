/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.datamanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.workbench.datamanagement.exception.DataManagementException;
import com.szboanda.epsm.workbench.datamanagement.service.IDataManagementService;

/**
 * @title:      企业-资料管理
 * @fileName:   ContactManagerController.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Controller
@RequestMapping("/co/epsm/workbench/datamanagement/datamanagementController")
public class DataManagementController extends BaseBusinessController {
	@Autowired
	IDataManagementService dataManagementService;
    
    //资料信息详情
    @RequestMapping("/datainfodetail/{accessType}")
    public String queryDataInfoDetail(@PathVariable String accessType,Model model) throws DataManagementException {
    	 model.addAttribute("accessType", accessType);
         return "epsm/workbench/datamanagement/datainfodetail";
    }
    
    
   /**
    * 根据ID查询资料信息
    * @return Map<String,Object>
    * @throws ContactManagerException
    * @author 王观发
    * @date 2017年10月21日
    */
    @RequestMapping("/querydatainfobyid")
    public @ResponseBody Map<String, Object> queryDataInfoById() throws DataManagementException {
    	String qyid = this.getBusinessCurrUser().getUserExtendVO().getQyid();
    	return dataManagementService.queryDataInfoById(qyid);
    }
    
    /**
     * 更新资料信息
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/updatedatainfo")
    @ResponseBody
    public Map<String, Object> updateDataInfo(@RequestBody Map<String, Object> model) throws DataManagementException {
        if (!this.validToken(model)) { // 重复提交
            return this.getFailMap();
        }
        if (model.get("ID") == null) {
        	//model.put("ID", this.getBusinessCurrUser().getUserExtendVO().getQyid());
            throw new DataManagementException("更新资料信息ID为空");
        }
        int count = dataManagementService.updateDataInfo(model);
        return this.getResultMap(count);
    }
    
}
