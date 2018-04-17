package com.szboanda.epsm.common.dao;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Param;
import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title  公共Dao
 * @author 钟声辉 
 * @date 创建时间：2017年10月17日 下午7:55:11 
 * @version 1.0
 */
@AutoLoadDAO
public interface CommonDAO extends BaseBusinessDAO {
	
	/**
	 * 查询数据字典列表EAP_SYS_UTIL_CODE
	 * @return
	 */
	List<Map<String, Object>>  queryUtilCode(@Param("parent")String parent);
	
	/**
	 * 查询数据字典分类
	 * @return
	 */
	List<String>   queryUtilCodeType();
	

	/**
	 * 查询数据字典列表EAP_SYS_UTIL_CODECLASS
	 * @return
	 */
	List<Map<String, Object>>  queryUtilCodeClass(@Param("parent")String parent);
	/**
	 * 查询行政区划 T_PLATFORM_XZQHDM
	 * @param params
	 * @return
	 */
	List<Map<String, Object>> queryXZQH(Map<String, Object> params);
}
