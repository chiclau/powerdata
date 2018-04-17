package com.szboanda.epsm.emissionstandard.quotaquery.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.platform.common.annotation.AutoLoadDAO;
import com.szboanda.platform.common.base.BaseDAO;

/** 
 * @title  指标查询
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午9:44:27 
 * @version 1.0 
 */
@AutoLoadDAO
public interface QuotaQueryDAO extends BaseDAO {

	/**
	 * 查询指标
	 * @param map
	 * @return
	 */
	List<Map<String,Object>> queryQuota(Map<String, Object> map);
}
