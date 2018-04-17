package com.szboanda.epsm.workbench.datareportnotice.service;

import java.util.List;
import java.util.Map;

/**
 * @title  数据催报Service
 * @author 钟声辉 
 * @date 创建时间：2017年10月19日 下午6:57:35 
 * @version 1.0
 */
public interface IDataReportNoticeService {

	/**
	 * 查询催报通知列表(催报本人的)
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryMyNotice(Map<String, Object> params);
	
	/**
	 * 查询催报通知列表(本人催报别人的)
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryOtherNotice(Map<String, Object> params);	
	
	
	/**
	 * 查询单个催报信息
	 * @param params
	 * @return
	 */
	Map<String, Object> queryOtherNoticeById(String id);	
	
	/**
	 * 	新增催报信息
	 * @return
	 */
	int  saveDataReportNotice(Map<String, Object> param);
	
	/**
	 * 查询可以催报的用户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryDataReportNoticeUser(Map<String, Object> params);	
}
