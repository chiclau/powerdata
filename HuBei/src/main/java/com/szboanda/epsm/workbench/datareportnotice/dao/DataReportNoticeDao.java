package com.szboanda.epsm.workbench.datareportnotice.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title  数据催报Dao
 * @author 钟声辉 
 * @date 创建时间：2017年10月19日 下午6:48:00 
 * @version 1.0
 */
@AutoLoadDAO
public interface DataReportNoticeDao extends BaseBusinessDAO{

	/**
	 * 查询催报通知列表(催报本人的 或 本人催报别人的)
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryDataReportNotice(Map<String, Object> params);
	
	/**
	 * 	查询单个催报信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryOtherNoticeById(@Param("UUID")String id);
	
	/**
	 * 	新增催报信息
	 * @param params
	 * @return
	 */
	int insertDataReportNotice(Map<String, Object> params);
	/**
	 * 查询可以催报的用户列表
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryDataReportNoticeUser(Map<String, Object> params);
	
	/**
	 * 批量新增催报信息
	 * @param params
	 * @return
	 */
	int insertDataReportNoticeBatch(List<Map<String, Object>> params);
	
	/**
	 * 批量信息提醒
	 * @param params
	 * @return
	 */
	int insertInformationReminderBatch(List<Map<String, Object>> params);
}
