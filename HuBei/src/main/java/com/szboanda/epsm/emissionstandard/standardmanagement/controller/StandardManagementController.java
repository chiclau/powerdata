/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.emissionstandard.standardmanagement.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.emissionstandard.standardmanagement.exception.StandardManagementException;
import com.szboanda.epsm.emissionstandard.standardmanagement.service.IStandardManagementService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/**
 * @title:      标准管理
 * @fileName:   StandardManagementController.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Controller
@RequestMapping("/ep/epsm/emissionstandard/standardmanagement/standardmanagementController")
public class StandardManagementController extends BaseBusinessController {
	
	@Autowired
	IStandardManagementService standardManagementService;
    
    
    @RequestMapping("/withoutmonitorpage")
    public String queryNotMonitoredPage() throws StandardManagementException {
        this.setToken();
        return "epsm/emissionstandard/standardmanagement/querywithoutmonitor";
    }
    
    @RequestMapping("/pointstandardpage")
    public String queryPointStandardPage() throws StandardManagementException {
        this.setToken();
        return "epsm/emissionstandard/standardmanagement/pointstandard";
    }	
    
    
    //未监测情况查询
    @RequestMapping("/querynotmonitored")
    public  @ResponseBody PageInfo<Map<String, Object>> queryNotMonitored(@RequestBody Map<String,Object> param) throws StandardManagementException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(standardManagementService.queryNotMonitored(param));
    }
    
    //测点所属标准查询
    @RequestMapping("/querypointstandard")
    public  @ResponseBody PageInfo<Map<String, Object>> queryPointStandard(@RequestBody Map<String,Object> param) throws StandardManagementException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(standardManagementService.queryPointStandard(param));
    }
    
    
}
