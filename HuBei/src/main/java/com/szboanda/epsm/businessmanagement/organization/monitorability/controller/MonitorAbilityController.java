package com.szboanda.epsm.businessmanagement.organization.monitorability.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.organization.monitorability.exception.MonitorAbilityException;
import com.szboanda.epsm.businessmanagement.organization.monitorability.service.IMonitorAbilityService;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/**
 * @title  机构监测能力controller
 * @author 钟声辉 
 * @date 创建时间：2017年10月23日 下午5:11:58 
 * @version 1.0
 */
@Controller
@RequestMapping("/both/epsm/businessmanagement/organization/monitorabilitycontroller")
public class MonitorAbilityController extends BaseBusinessController {

	
	@Autowired
	IMonitorAbilityService monitorAbilityService;
	
	/**
	 * 跳转本机构监测能力页面
	 * @return
	 * @throws MonitorAbilityException
	 */
    @RequestMapping("/monitorability")
    public String gotoMonitorAbility() throws MonitorAbilityException {
        return "/epsm/businessmanagement/organization/monitorability/monitorability";
    }
    
    /**
     * 获取本机构监测能力列表
     * @param params
     * @return
     * @throws MonitorAbilityException
     */
    @RequestMapping("/getmonitorability")
    @ResponseBody
    public PageInfo<Map<String, Object>> getMonitorAbility(@RequestBody Map<String, Object> params) throws MonitorAbilityException{
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String,Object>>(monitorAbilityService.queryMonitorAbility());    	
    }  
    
	/**
	 * 跳转辖区监测能力页面
	 * @return
	 * @throws MonitorAbilityException
	 */
    @RequestMapping("/areamonitorability")
    public String gotoAreaMonitorAbility(ModelMap model) throws MonitorAbilityException {
    	model.addAttribute("currUserProvince", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
    	model.addAttribute("currUserCity", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
    	model.addAttribute("currUserArea", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        return "/epsm/businessmanagement/organization/monitorability/areamonitorability";
    } 
    
    /**
     * 获取辖区监测能力列表
     * @param params
     * @return
     * @throws MonitorAbilityException
     */
    @RequestMapping("/getareamonitorability")
    @ResponseBody
    public PageInfo<Map<String, Object>> getAreaMonitorAbility(@RequestBody Map<String, Object> params) throws MonitorAbilityException{
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String,Object>>(monitorAbilityService.queryAreaMonitorAbility(params));    	
    }     
    
}
