package com.szboanda.epsm.common.service;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

/**
 * @title  
 * @author 钟声辉 
 * @date 创建时间：2017年10月17日 下午7:55:41 
 * @version 1.0
 */
public interface ICommonService {

	/**
	 * 查询数据字典列表EAP_SYS_UTIL_CODE
	 * @return
	 */
	List<Map<String, Object>>  queryUtilCode(String parent);
	
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
