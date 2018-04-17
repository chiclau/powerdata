/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.standardquery.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.knowledge.standardquery.exception.StandardQueryException;
import com.szboanda.epsm.knowledge.standardquery.service.IStandardQueryService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/**
 * @title:      知识库-标准查询
 * @fileName:   StandardQueryController.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@Controller
@RequestMapping("/both/epsm/knowledge/standardquery/standardquerycontroller")
public class StandardQueryController extends BaseBusinessController {
	
	@Autowired
	IStandardQueryService standardQueryService;
    
    
    @RequestMapping("/page")
    public String queryStandardpage() throws StandardQueryException {
        this.setToken();
        return "epsm/knowledge/standardquery/querystandard";
    }			
    
    @RequestMapping("/pagedetail")
    public  String queryStandardDetail(String id,Model model) throws StandardQueryException {
    	 model.addAttribute("id",id);
         return "epsm/knowledge/standardquery/standardetail";
    }
    
    /**
     * 标准列表查询
     * @return PageInfo<Map<String,Object>>
     * @throws 
     * @author 王观发
     * @date 2017年10月19日
     */
    @RequestMapping("/querystandard")
    public  @ResponseBody PageInfo<Map<String, Object>> queryStandard(@RequestBody Map<String,Object> param) throws StandardQueryException {
    	DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(standardQueryService.queryStandard(param));
    }
    
    /**
     * 标准详情查询
     * @return Map<String,Object>
     * @throws StandardQueryException
     * @author 王观发
     * @date 2017年10月19日
     */
    @RequestMapping("/querystandardetail")
    public @ResponseBody Map<String, Object> queryStandarDetail(@RequestBody Map<String, Object> param)
            throws StandardQueryException {
        return standardQueryService.queryStandarDetail(param.get("id").toString());
    }
    
    
}
