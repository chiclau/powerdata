package com.szboanda.epsm.workbench.datareportnotice.controllor;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.workbench.datareportnotice.exception.DataReportNoticeException;
import com.szboanda.epsm.workbench.datareportnotice.service.IDataReportNoticeService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.StringUtils;

/**
 * @title  数据催报controller
 * @author 钟声辉 
 * @date 创建时间：2017年10月19日 下午7:26:26 
 * @version 1.0
 */
@Controller
@RequestMapping("/both/epsm/workbench/datareportnoticecontroller/datareportnotice")
public class DataReportNoticeController extends BaseBusinessController {

	/**
	 * 数据催报ID
	 */
	private static final String ID="UUID";
	
	@Autowired
	IDataReportNoticeService dataReportNoticeService;
    /**
     * 访问我的催报页面
     * 
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/mydatareportnotice")
    public String gotoMyDataReportNotice() throws DataReportNoticeException {
        return "/epsm/workbench/datareportnotice/mydatareportnotice";
    }

    /**
     * 访问已发送的催报页面
     * 
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/seeddatareportnotice")
    public String gotoSeedDataReportNotice() throws DataReportNoticeException {
        return "/epsm/workbench/datareportnotice/seeddatareportnotice";
    }
    
    /**
     * 获取自己的催报数据
     * @param params
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/getmydatareportnotice")
    @ResponseBody
    public PageInfo<Map<String, Object>> getMyDataReportNotice(@RequestBody Map<String, Object> params) throws DataReportNoticeException{
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String,Object>>(dataReportNoticeService.queryMyNotice(params));    	
    }
    
    /**
     * 获取已发送的催报数据
     * @param params
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/getseeddatareportnotice")
    @ResponseBody
    public PageInfo<Map<String, Object>> getSeedDataReportNotice(@RequestBody Map<String, Object> params) throws DataReportNoticeException{
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String,Object>>(dataReportNoticeService.queryOtherNotice(params));    	
    }
    
    /**
     * 进入编辑页面
     * @param model
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/edit")
    public String editPage(ModelMap model) throws DataReportNoticeException {
        this.setToken();
        String id = this.getRequest().getParameter(ID);
        String type = this.getRequest().getParameter("openType");
        String ids	= this.getRequest().getParameter("ids");
        String QYID	= this.getRequest().getParameter("QYID");
        String qyids = this.getRequest().getParameter("qyids");
        Map<String, Object> noticeMode =new HashMap<String, Object>();
        if (StringUtils.isNotEmpty(id)) {
            noticeMode = dataReportNoticeService.queryOtherNoticeById(id);
        }
        noticeMode.put("QYID", QYID);
        noticeMode.put("qyids", qyids);   
        noticeMode.put("ids", ids);
        noticeMode.put("openType", type);
        noticeMode.put("BCBDX", this.getRequest().getParameter("BCBDX"));
        model.addAllAttributes(noticeMode);
        return "/epsm/workbench/datareportnotice/datareportnoticeedit";
    }
    
    /**
     * 保存催报信息
     * @param params
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping(value = "/savedatareportnotice", method = { RequestMethod.POST })
    @ResponseBody
    public void saveDataReportNotice(@RequestBody Map<String, Object> params) throws DataReportNoticeException {
    	dataReportNoticeService.saveDataReportNotice(params);
    }    

    /**
     * 访问可以催报用户页面
     * 
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/datareportnoticeuser")
    public String gotoDataReportNoticeUser(ModelMap model) throws DataReportNoticeException {
    	//根据用户级别  划分展示类别
    	model.addAttribute("queryType", this.getBusinessCurrUser().getUserVO().getYhjb());   	
        return "/epsm/workbench/datareportnotice/datareportnoticeuser";
    }
    
    
    /**
     * 获取可以催报用户数据
     * @param params
     * @return
     * @throws DataReportNoticeException
     */
    @RequestMapping("/getdatareportnoticeuser")
    @ResponseBody
    public PageInfo<Map<String, Object>> getDataReportNoticeUser(@RequestBody Map<String, Object> params) throws DataReportNoticeException{
    	DataHelper.startPage(this.getPageNum(params), this.getPageSize(params));
    	return new PlatformPageInfo<Map<String,Object>>(dataReportNoticeService.queryDataReportNoticeUser(params));    	
    }
       
}
