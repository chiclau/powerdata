package com.szboanda.epsm.emissionstandard.quotaquery.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.emissionstandard.quotaquery.dao.QuotaQueryDAO;
import com.szboanda.epsm.emissionstandard.quotaquery.exception.QuotaQueryException;
import com.szboanda.epsm.emissionstandard.quotaquery.service.IQuotaQueryService;
import com.szboanda.platform.common.base.BaseService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;

/**
 * @title  指标查询
 * @author 钟声辉 
 * @date 创建时间：2017年10月14日 上午9:46:30 
 * @version 1.0
 */
@Service
public class QuotaQueryServiceImpl extends BaseService implements IQuotaQueryService {

	/**
	 * 指标查询DAO
	 */
    @Autowired
    QuotaQueryDAO quotaqueryDAO;
    

	/**
	 * 查询指标
	 */
	@Override
	public List<Map<String, Object>> queryQuota(Map<String, Object> map) {
		try {
			if(StringUtils.isNotEmpty(map.get("searchTxt"))) {
				SQLUtils.fillLike(map, "searchTxt");
			}
			if(StringUtils.isNotEmpty(map.get("zblx"))) {
				SQLUtils.fillLike(map, "zblx");
			}
            return quotaqueryDAO.queryQuota(map);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询所有指标信息异常，map：" + map, e);
            throw new QuotaQueryException("查询所有指标信息异常，map：" + map, e);
        }
	}
	
}
