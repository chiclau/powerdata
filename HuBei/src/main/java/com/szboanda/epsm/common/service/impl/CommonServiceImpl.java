package com.szboanda.epsm.common.service.impl;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.dao.CommonDAO;
import com.szboanda.epsm.common.execption.CommonException;
import com.szboanda.epsm.common.service.ICommonService;
import com.szboanda.platform.common.utils.LoggerUtil;

import bsh.This;

/**
 * @title  
 * @author 钟声辉 
 * @date 创建时间：2017年10月17日 下午7:57:46 
 * @version 1.0
 */
@Service
public class CommonServiceImpl extends BaseBusinessService implements ICommonService {

	@Autowired
	CommonDAO commonDao;
	/**
	 * 查询数据字典列表
	 */
	@Override
	public List<Map<String, Object>> queryUtilCode(String parent) {
        try {
            return commonDao.queryUtilCode(parent);
        } catch (Exception e) {
            LoggerUtil.error(CommonServiceImpl.class, "查询数据字典列表异常 : "+parent, e);
            throw new CommonException("查询数据字典列表异常 : "+parent, e);
        }			
	}
	
	/**
	 * 查询数据字典分类EAP_SYS_UTIL_CODE
	 */
	@Override
	public List<String> queryUtilCodeType() {
        try {
            return commonDao.queryUtilCodeType();
        } catch (Exception e) {
            LoggerUtil.error(CommonServiceImpl.class, "查询数据字典分类异常 : ", e);
            throw new CommonException("查询数据字典分类异常 : ", e);
        }	
	}
	
	/**
	 * 查询数据字典列表EAP_SYS_UTIL_CODECLASS
	 * @return
	 */	
	@Override
	public List<Map<String, Object>> queryUtilCodeClass(String parent) {
        try {
            return commonDao.queryUtilCodeClass(parent);
        } catch (Exception e) {
            LoggerUtil.error(CommonServiceImpl.class, "查询数据字典异常 : "+parent, e);
            throw new CommonException("查询数据字典异常 : "+parent, e);
        }	
	}

	/**
	 * 查询行政区划 T_PLATFORM_XZQHDM
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryXZQH(Map<String, Object> params){
        try {
        	String yhjb=this.getBusinessCurrUser().getUserVO().getYhjb();
        	if(yhjb.equals("1")) {//省级
            	params.put(CommonBussinessConstants.XZQHDM_FDM, this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());        		
        	}else if(yhjb.equals("2")) {//市级
        		params.put(CommonBussinessConstants.XZQHDM_FDM, this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        	}else if(yhjb.equals("3")) {//县级
        		params.put(CommonBussinessConstants.XZQHDM_FDM, this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        	}
            return commonDao.queryXZQH(params);
        } catch (Exception e) {
            LoggerUtil.error(CommonServiceImpl.class, "查询行政区划异常 : "+params, e);
            throw new CommonException("查询行政区划异常 : "+params, e);
        }			
	}	
}
