package com.szboanda.epsm.emissionstandard.standardmanager.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.emissionstandard.quotaquery.exception.QuotaQueryException;
import com.szboanda.epsm.emissionstandard.standardmanager.exception.LevelStandardManagerException;
import com.szboanda.epsm.emissionstandard.standardmanager.service.ILevelStandardManagerService;
import com.szboanda.platform.common.base.BaseController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.StringUtils;


/** 
 * @title  本级排放标准管理
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午9:44:27 
 * @version 1.0
 */
@Controller
@RequestMapping("/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard")
public class LevelStandardManagerController extends BaseController {
	/**
	 * 排放标准ID
	 */
    private static final String ID = "standardID";

    
    /**
     * 本级标准service
     */
	@Autowired
	ILevelStandardManagerService iLevelStandardManagerService;

    /**
     * 访问指标页面
     * 
     * @return
     * @throws QuotaQueryException
     */
    @RequestMapping("/levelstandardmanagerpage")
    public String levelStandardManager() throws LevelStandardManagerException {
        return "/epsm/emissionstandard/levelstandardmanager/levelstandardmanager";
    }
    
    /**
     * 查询标准基本信息
     * @param map
     * @return
     * @throws QuotaQueryException
     */
    @RequestMapping("/querystandard")
    @ResponseBody
    public PageInfo<Map<String, Object>> queryStandard(@RequestBody Map<String, Object> params) throws LevelStandardManagerException {
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String, Object>>(iLevelStandardManagerService.queryStandard(params));
    } 
    
    /**
     * 进入编辑页面
     * @param model
     * @return
     * @throws LevelStandardManagerException
     */
    @RequestMapping("/edit")
    public String editPage(ModelMap model) throws LevelStandardManagerException {
        this.setToken();
        String id = this.getRequest().getParameter(ID);
        String type = this.getRequest().getParameter("openType");
        if (StringUtils.isNotEmpty(id)) {
            Map<String, Object> standardMode = iLevelStandardManagerService.queryStandardById(id);
            standardMode.put("openType", type);
            model.addAllAttributes(standardMode);
        }
        return "/epsm/emissionstandard/levelstandardmanager/standardedit";
    }
    /**
     * 查询某个标准信息
     * @param standardID
     * @return
     * @throws LevelStandardManagerException
     */
    @RequestMapping(value = "/getstandard", method = { RequestMethod.POST })
    @ResponseBody
    public Map<String, Object> getStandard(@RequestBody Map<String, Object> params) throws LevelStandardManagerException {
        return iLevelStandardManagerService.queryStandardById(MapUtils.getString(params, ID));
    }

    /**
     * 保存标准信息
     * @param params
     * @return
     * @throws LevelStandardManagerException
     */
    @RequestMapping(value = "/savestandard", method = { RequestMethod.POST })
    @ResponseBody
    public void saveStandard(@RequestBody Map<String, Object> params) throws LevelStandardManagerException {
    	iLevelStandardManagerService.saveStandard(params);
    }
    /**
     * 删除标准信息
     * @param standardID
     * @throws LevelStandardManagerException
     */
    @RequestMapping(value = "/deletestandard", method = { RequestMethod.POST })
    @ResponseBody
    public void deleteStandard(@RequestBody Map<String, Object> params) throws LevelStandardManagerException {
    	iLevelStandardManagerService.deleteStandard(MapUtils.getString(params, ID));
    }
}
