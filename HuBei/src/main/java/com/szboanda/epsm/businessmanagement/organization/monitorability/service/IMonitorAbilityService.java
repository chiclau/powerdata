package com.szboanda.epsm.businessmanagement.organization.monitorability.service;

import java.util.List;
import java.util.Map;

/**
 * @title  机构监测能力Service
 * @author 钟声辉 
 * @date 创建时间：2017年10月23日 下午5:19:01 
 * @version 1.0
 */
public interface IMonitorAbilityService {

	/**
	 * 查询本机构监测能力
	 * @return
	 */
	List<Map<String, Object>>   queryMonitorAbility();
	/**
	 *	查询辖区监测能力 
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryAreaMonitorAbility(Map<String, Object> params);	
}
