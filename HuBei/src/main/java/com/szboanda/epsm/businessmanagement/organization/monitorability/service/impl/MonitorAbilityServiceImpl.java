package com.szboanda.epsm.businessmanagement.organization.monitorability.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.szboanda.epsm.businessmanagement.organization.monitorability.dao.MonitorAbilityDAO;
import com.szboanda.epsm.businessmanagement.organization.monitorability.exception.MonitorAbilityException;
import com.szboanda.epsm.businessmanagement.organization.monitorability.service.IMonitorAbilityService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.platform.common.utils.LoggerUtil;

/**
 * @title  机构检测能力
 * @author 钟声辉 
 * @date 创建时间：2017年10月23日 下午5:19:44 
 * @version 1.0
 */
@Service
public class MonitorAbilityServiceImpl extends BaseBusinessService implements IMonitorAbilityService {

	@Autowired
	MonitorAbilityDAO monitorAbilityDAO;

	/**
	 * 查询本机构监测能力
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryMonitorAbility() {
		try {
			return monitorAbilityDAO.queryMonitorAbility();
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询本机构监测能力异常" , e);
            throw new MonitorAbilityException("查询本机构监测能力异常" , e);
        }

	}
	/**
	 *	查询辖区监测能力 
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryAreaMonitorAbility(Map<String, Object> params) {
		try {
			return monitorAbilityDAO.queryAreaMonitorAbility(params);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询辖区监测能力异常 params:"+params , e);
            throw new MonitorAbilityException("查询辖区监测能力异常 params:"+params , e);
        }		
	}
		
}
