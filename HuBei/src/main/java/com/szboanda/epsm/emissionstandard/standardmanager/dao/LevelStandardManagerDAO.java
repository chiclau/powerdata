package com.szboanda.epsm.emissionstandard.standardmanager.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.szboanda.platform.common.annotation.AutoLoadDAO;
import com.szboanda.platform.common.base.BaseDAO;

/**
 * @title  本级排放标准管理
 * @author 钟声辉 
 * @date 创建时间：2017年10月16日 下午7:41:37 
 * @version 1.0
 */
@AutoLoadDAO
public interface LevelStandardManagerDAO extends BaseDAO{
	/**
	 * 查询标准基本信息列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryStandard(Map<String, Object> param);
	
	/**
	 * 查询单个标准信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryStandardById(@Param("BZ_ID")String bz_id);
	
	/**
	 * 新增标准信息
	 * @param param
	 */
	void insertStandard(Map<String, Object> param);
	/**
	 * 	修改标准信息
	 * @param param
	 * @return
	 */
	int updateStandard(Map<String, Object> param);      
	/**
	 * 	删除标准信息
	 * @param bz_id
	 * @return
	 */
	int deleteStandard(@Param("BZ_ID")String bz_id);
}
