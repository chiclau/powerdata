package com.szboanda.epsm.emissionstandard.quotaquery.service;

import java.util.List;
import java.util.Map;

/**
 * @title  指标查询
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午9:44:27 
 * @version 1.0
 */
public interface IQuotaQueryService {

	//查询指标
	List<Map<String, Object>> queryQuota(Map<String, Object> map);
}
