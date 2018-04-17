package com.szboanda.epsm.businessmanagement.organization.monitorability.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title  机构检测能力DAO
 * @author 钟声辉 
 * @date 创建时间：2017年10月23日 下午5:14:10 
 * @version 1.0
 */
@AutoLoadDAO
public interface MonitorAbilityDAO extends BaseBusinessDAO {

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
