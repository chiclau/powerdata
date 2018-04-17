package com.szboanda.epsm.common.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.common.cache.SystemUtilCodeCache;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.execption.CommonException;
import com.szboanda.epsm.common.service.ICommonService;
import com.szboanda.platform.common.utils.CollectionUtils;
import com.szboanda.platform.common.utils.StringUtils;

/**
 * @title  
 * @author 钟声辉 
 * @date 创建时间：2017年10月18日 下午4:23:20 
 * @version 1.0
 */
@Controller
@RequestMapping("/public/epsm/common/commoncontroller")
public class CommonController extends BaseBusinessController{
	/**
	 * 数据字典缓存
	 */
	@Autowired
	SystemUtilCodeCache systemUtilCodeCache;

	/**
	 * 公共Service
	 */
	@Autowired
	ICommonService commonService;
    /**
     * 在缓存中获取标准类型 --标准类型(D_TYPE)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getstandardtype")
    @ResponseBody
    public List<Map<String, Object>> getStandardType() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.D_TYPE);
    }
    
    /**
     * 在缓存中获取标准分类-- 污染源类型代码表(DEVICE_TYPE)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getstandardclassify")
    @ResponseBody
    public List<Map<String, Object>> getStandardClassify(@RequestBody Map<String, Object> param) throws CommonException {
    	if(StringUtils.isNotEmpty(param.get("field"))) {
    		String field=param.get("field").toString();
    		String value=param.get("value").toString();
    		List<Map<String, Object>> params=systemUtilCodeCache.getCaches(CommonBussinessConstants.DEVICE_TYPE);
    		List<Map<String, Object>> result= new ArrayList<Map<String, Object>>();
    		for (Map<String, Object> map : params) {
    			if(StringUtils.isNotEmpty(map.get(field))&&map.get(field).toString().equals(value)) {
    				result.add(map);
    			}
			}
    		if(CollectionUtils.isNotEmpty(result)) {
    			return result;
    		}
    	}
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.DEVICE_TYPE);
    } 
    
    /**
     * 在缓存中获取 -- 污染源类型代码表(EAP_SYS_UTIL_CODECLASS)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getcodeclass")
    @ResponseBody
    public List<Map<String, Object>> getCodeClass() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.EAP_SYS_UTIL_CODECLASS);
    }
    
    /**
     * 在缓存中获取 -- 数据字典(DEVICE)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getdevice")
    @ResponseBody
    public List<Map<String, Object>> getDevice() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.DEVICE);
    }
    
    /**
     *查询行政区划 -- (T_PLATFORM_XZQHDM)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getxzqh")
    @ResponseBody
    public List<Map<String, Object>> getXZQH(Map<String, Object> params) throws CommonException {
        return commonService.queryXZQH(params);
    }
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_QYGMDMB)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getqygmdm")
    @ResponseBody
    public List<Map<String, Object>> getQYGMDM() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.QY_GMDM);
    }
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_DJZCLXDMB)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getqyzclx")
    @ResponseBody
    public List<Map<String, Object>> getQYZCLX() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.QY_ZCLX);
    } 
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_HYLBDMB)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getqyhylb")
    @ResponseBody
    public List<Map<String, Object>> getQYHYLB() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.QY_HYLB);
    } 
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_QYLX)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getqylx")
    @ResponseBody
    public List<Map<String, Object>> getQYLX() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.QY_LXDM);
    } 
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_ZDYSX)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getqyzdysx")
    @ResponseBody
    public List<Map<String, Object>> getQYZDYSX() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.QY_ZDYSX);
    } 
    
    /**
     * 在缓存中获取 -- 数据字典(XTGL_XTSZ_WSCLCLB)
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getwsclclb")
    @ResponseBody
    public List<Map<String, Object>> getWSCLCLB() throws CommonException {
        return systemUtilCodeCache.getCaches(CommonBussinessConstants.WSCLCLB);
    } 
    
    
    /**
     * 在缓存中获取公共参数
     * @param param
     * @return
     * @throws CommonException
     */
    @RequestMapping("/getcommoncode")
    @ResponseBody
    public List<Map<String, Object>> getCommonCode(@RequestBody Map<String, Object> param) throws CommonException {
    	if(StringUtils.isNotEmpty(param.get(CommonBussinessConstants.PARENT))) {
        	String parent=param.get(CommonBussinessConstants.PARENT).toString();
            return systemUtilCodeCache.getCaches(parent);
    	}
    	return null;
    }     
}
