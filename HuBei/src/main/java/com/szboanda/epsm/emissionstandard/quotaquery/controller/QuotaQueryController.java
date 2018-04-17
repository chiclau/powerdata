package com.szboanda.epsm.emissionstandard.quotaquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.emissionstandard.quotaquery.exception.QuotaQueryException;
import com.szboanda.epsm.emissionstandard.quotaquery.service.IQuotaQueryService;
import com.szboanda.platform.common.base.BaseController;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/** 
 * @title  指标查询
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午9:44:27 
 * @version 1.0
 */
@Controller
@RequestMapping("/both/epsm/emissionstandard/quotaquerycontroller/quotaquery")
public class QuotaQueryController extends BaseController {

	@Autowired
	IQuotaQueryService emissionStandardService;

    /**
     * 访问指标页面
     * 
     * @return
     * @throws QuotaQueryException
     */
    @RequestMapping("/queryquotapage")
    public String queryQuota() throws QuotaQueryException {
    	this.setToken();
        return "/epsm/emissionstandard/quotaquery/queryquota";
    }
    
    /**
     * 查询指标列表
     * @param map
     * @return
     * @throws QuotaQueryException
     */
    @RequestMapping("/queryquota")
    @ResponseBody
    public PageInfo<Map<String, Object>> queryQuota(@RequestBody Map<String, Object> params) throws QuotaQueryException {
        DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
        return new PlatformPageInfo<Map<String, Object>>(emissionStandardService.queryQuota(params));
    }    
}
