package com.szboanda.epsm.emissionstandard.standardmanager.service;

import java.util.List;
import java.util.Map;

/**
 * @title  本级排放标准管理
 * @author 钟声辉 
 * @date 创建时间：2017年10月16日 下午7:43:58 
 * @version 1.0
 */
public interface ILevelStandardManagerService {

	/**
	 * 查询标准基本信息列表
	 * @param map
	 * @return
	 */
	List<Map<String, Object>> queryStandard(Map<String, Object> map);
	/**
	 * 查询标准基本信息
	 * @param id
	 * @return
	 */
	Map<String, Object> queryStandardById(String bz_id);
	/**
	 * 保存标准信息
	 * @param param
	 */
	void saveStandard(Map<String, Object> param);
	/**
	 * 删除标准信息
	 * @param param
	 * @return
	 */
	int deleteStandard(String bz_id);
}
