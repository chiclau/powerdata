/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.decisionsupport.reportmanager.controller;

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
import com.szboanda.epsm.decisionsupport.reportmanager.exception.ReportManagerException;
import com.szboanda.epsm.decisionsupport.reportmanager.service.IReportManagerService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
 * @title:      报告管理
 * @fileName:   ContactManagerController.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Controller
@RequestMapping("/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller")
public class ReportManagerController extends BaseBusinessController {
	@Autowired
	IReportManagerService reportManagerService;
    
	//跳转报告模版页面
	@RequestMapping("/reportemplate")
    public String queryReporTemplatePage() throws ReportManagerException {
        this.setToken();
        return "epsm/decisionsupport/reportmanager/reportemplate";
    }
	
	//跳转报告模版页面
	@RequestMapping("/reportfile")
    public String queryReportFile() throws ReportManagerException {
        this.setToken();
        return "epsm/decisionsupport/reportmanager/filetest";
    }	
	
	//跳转报告模版详情页面
    @RequestMapping("/reportemplatedetail/{operationType}/{id}")
    public  String queryReporTemplateDetail(@PathVariable String operationType, @PathVariable String id,Model model) throws ReportManagerException {
    	 model.addAttribute("ID",id);
    	 
         // 传递操作类型, 操作类型包含read,add,edit
         model.addAttribute("operationType", operationType);
         return "epsm/decisionsupport/reportmanager/reportemplatedetail";
    }											    
    
   //跳转报告页面
    @RequestMapping("/report")
    public String queryReportPage() throws ReportManagerException {
        this.setToken();
        return "epsm/decisionsupport/reportmanager/report";
    }			
    
   //跳转报告详情页面
    @RequestMapping("/reportdetail/{operationType}/{id}")
    public  String queryReportDetail(@PathVariable String operationType, @PathVariable String id,Model model) throws ReportManagerException {
    	 model.addAttribute("ID",id);
    	 
         // 传递操作类型, 操作类型包含read,add,edit
         model.addAttribute("operationType", operationType);
         return "epsm/decisionsupport/reportmanager/reportdetail";
    }
    
    /**
     * 查询报告模版
     * @return PageInfo<Map<String,Object>>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping("/queryreportemplate")
    public  @ResponseBody PageInfo<Map<String, Object>> queryReporTemplate(@RequestBody Map<String,Object> param) throws ReportManagerException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(reportManagerService.queryReporTemplate(param));
    }
    
    /**
     * 查询报告
     * @return PageInfo<Map<String,Object>>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping("/queryreport")
    public  @ResponseBody PageInfo<Map<String, Object>> queryReport(@RequestBody Map<String,Object> param) throws ReportManagerException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(reportManagerService.queryReport(param));
    }
    
    
   /**
    * 根据ID查询报告模版
    * @return Map<String,Object>
    * @throws ContactManagerException
    * @author 王观发
    * @date 2017年10月21日
    */
    @RequestMapping("/queryreportemplatebyid")
    public @ResponseBody Map<String, Object> queryReporTemplateById(@RequestBody Map<String, Object> param) throws ReportManagerException {
        return reportManagerService.queryReporTemplateById(MapUtils.getString(param, "ID"));
    }
    
    /**
     * 根据ID查询报告
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月21日
     */
     @RequestMapping("/queryreportbyid")
     public @ResponseBody Map<String, Object> queryReportById(@RequestBody Map<String, Object> param) throws ReportManagerException {
         return reportManagerService.queryReportById(MapUtils.getString(param, "ID"));
     }
    
    /**
     * 新增报告模版
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/addreportemplate", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addReporTemplate(@RequestBody Map<String, Object> model) throws ReportManagerException {
        if (!this.validToken(model)) {
            return this.getFailMap();
        }
        int count = reportManagerService.addReporTemplate(model);
        return this.getResultMap(count, model);
    }
    
    /**
     * 新增报告
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/addreport", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> addReport(@RequestBody Map<String, Object> model) throws ReportManagerException {
        if (!this.validToken(model)) {
            return this.getFailMap();
        }
        int count = reportManagerService.addReport(model);
        return this.getResultMap(count, model);
    }
    
    /**
     * 更新报告模版
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/updatereportemplate")
    @ResponseBody
    public Map<String, Object> updateReporTemplate(@RequestBody Map<String, Object> model) throws ReportManagerException {
        if (!this.validToken(model)) { // 重复提交
            return this.getFailMap();
        }
        if (model.get("ID") == null) {
            throw new ReportManagerException("更新联系人ID为空");
        }
        int count = reportManagerService.updateReporTemplate(model);
        return this.getResultMap(count);
    }
    
    /**
     * 更新报告
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/updatereport")
    @ResponseBody
    public Map<String, Object> updateReport(@RequestBody Map<String, Object> model) throws ReportManagerException {
        if (!this.validToken(model)) { // 重复提交
            return this.getFailMap();
        }
        if (model.get("ID") == null) {
            throw new ReportManagerException("更新联系人ID为空");
        }
        int count = reportManagerService.updateReport(model);
        return this.getResultMap(count);
    }
    
    /**
     * 删除报告模版
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/delreportemplate")
    @ResponseBody
    public Map<String, Object> delReporTemplate(@RequestBody Map<String, Object> param) throws ReportManagerException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(reportManagerService.delReporTemplate(MapUtils.getString(param, "ID")));
    }
    
    /**
     * 删除报告
     * @return Map<String,Object>
     * @throws ReportManagerException
     * @author 王观发
     * @date 2017年10月20日
     */
    @RequestMapping(value = "/delreport")
    @ResponseBody
    public Map<String, Object> delReport(@RequestBody Map<String, Object> param) throws ReportManagerException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(reportManagerService.delReport(MapUtils.getString(param, "ID")));
    }
}
