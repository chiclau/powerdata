package com.szboanda.epsm.emissionstandard.standardmanager.service.impl;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.szboanda.epsm.emissionstandard.standardmanager.dao.LevelStandardManagerDAO;
import com.szboanda.epsm.emissionstandard.standardmanager.exception.LevelStandardManagerException;
import com.szboanda.epsm.emissionstandard.standardmanager.service.ILevelStandardManagerService;
import com.szboanda.platform.common.base.BaseService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;
import com.szboanda.platform.common.utils.Toolkit;


/**
 * @title  本级排放标准管理Service
 * @author 钟声辉 
 * @date 创建时间：2017年10月16日 下午7:45:22 
 * @version 1.0
 */
@Service
public class LevelStandardManagerServiceImpl extends BaseService implements ILevelStandardManagerService{

    /**
     * 标准ID
     */
    private static final String BZ_ID = "BZ_ID";
    
	@Autowired
	LevelStandardManagerDAO levelStandardManagerDao;
	
	/**
	 * 查询排放标准基本信息列表
	 */
	@Override
	public List<Map<String, Object>> queryStandard(Map<String, Object> map) {
		try {
			if(StringUtils.isNotEmpty(map.get("searchTxt"))) {
				SQLUtils.fillLike(map, "searchTxt");
			}
			if(MapUtils.getString(map, "bzfl")!="") {
				SQLUtils.fillLike(map, "bzfl");
			}			
            return levelStandardManagerDao.queryStandard(map);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询排放标准基本信息异常，map：" + map, e);
            throw new LevelStandardManagerException("查询排放标准基本信息异常，map：" + map, e);
        }
	}
	/**
	 * 查询单个标准基本信息
	 */
	@Override
	public Map<String, Object> queryStandardById(String bz_id) {
		try {
            return levelStandardManagerDao.queryStandardById(bz_id);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询单个标准基本信息异常，bz_id：" + bz_id, e);
            throw new LevelStandardManagerException("查询单个标准基本信息异常，bz_id：" + bz_id, e);
        }
	}
	
	/**
	 * 新增标准信息
	 * @param param
	 */
	@Override
	public void saveStandard(Map<String, Object> param) {		
		try {
			if (isExist(MapUtils.getString(param, BZ_ID))) {
				levelStandardManagerDao.updateStandard(param);
            }else {
            	param.put(BZ_ID, Toolkit.getID());
    			levelStandardManagerDao.insertStandard(param);
            }
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "新增标准信息异常，param：" + param, e);
            throw new LevelStandardManagerException("新增标准信息异常，param：" + param, e);
        }		
	}
	/**
	 * 验证该标准是否存在
	 * @param bz_id
	 * @return
	 */
    private boolean isExist(String bz_id) {
        boolean result = true;
    	if(StringUtils.isEmpty(bz_id)) {
            return false;
    	}
        if (null == this.queryStandardById(bz_id)) {
        	return false;
        }
        return result;
    }
    /**
     * 删除标准
	 * @param param
	 * @return
     */
	@Override
	public int deleteStandard(String bz_id) {
		try {
			return levelStandardManagerDao.deleteStandard(bz_id);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "新增标准信息异常，param：" + bz_id, e);
            throw new LevelStandardManagerException("新增标准信息异常，param：" + bz_id, e);
        }
	}
}
