package com.szboanda.epsm.common.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.execption.CommonException;
import com.szboanda.epsm.common.service.ICommonService;
import com.szboanda.platform.cache.template.CacheTemplate;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.helper.JsonHelper;
import com.szboanda.platform.soa.rpc.ICache;


/**
 * @title  数据字典缓存类
 * @author 钟声辉 
 * @date 创建时间：2017年10月17日 下午7:03:01 
 * @version 1.0
 */
@Component("SystemUtilCodeCache")
public class SystemUtilCodeCache extends CacheTemplate<Map<String, Object>> {

	
    /**
     * 缓存名称
     */
    private static final String CACHE_CODE_NAME = CommonBussinessConstants.CACHE_SYSTEM_UTIL_CODE;

    /**
     * 缓存名称
     */
    private static final String CACHE_CODE_CLASS_NAME = CommonBussinessConstants.CACHE_SYSTEM_UTIL_CODE_CLASS;
	
    /**
     *  缓存接口
     */
    @Autowired
    private ICache codeCache;
    
    /**
     * 公共服务接口
     */
    @Autowired
    ICommonService commonService;
    
    
    /**
     * 获取缓存key
     */
    @Override
    public String getKey() {
        return CACHE_CODE_NAME;
    }
    
    /**
     * 删除配置项缓存
     */
	@Override
	public void deleteCache(Map<String, Object> arg0) {
        // 从缓存中删除配置信息
	
	}
	
    /**
     * 获取全部配置缓存
     */
	@Override
	public List<Map<String, Object>> getAll() {
        List<Map<String, Object>> result = new ArrayList<Map<String, Object>>();
        List<String> data = codeCache.getAll(CACHE_CODE_NAME);
        data.addAll(codeCache.getAll(CACHE_CODE_CLASS_NAME));
        for (String config : data) {
            result.add(JsonHelper.jsonString2Map(config));
        }
        return result;
	}
	
    /**
     * 获取缓存数据 
     */
	public List<Map<String, Object>> getCaches(String key) {
		try {
	        String data = codeCache.getCache(CACHE_CODE_NAME, key);	       
	        return  JsonHelper.jsonArrayString2List(data);	        
	        } catch (Exception ex) {
	            LoggerUtil.error(this.getClass(), "获取缓存数据字典失败。"+key, ex);
	            throw new CommonException("获取缓存数据字典失败 : "+key, ex);
	        }    
	}
		
    /**
     * 初始化
     */
    @Override
    public boolean init() {
    	System.out.println("-------------------------------------------------------初始化缓存");
        if (codeCache.isNotExists(CACHE_CODE_NAME)) {
            reload();
        }
        return true;
    }


    /**
     * 从新加载缓存
     */
    @Override
    public boolean reload() {
        // 删除缓存中的空间
    	codeCache.deleteCache(CACHE_CODE_NAME);
    	codeCache.deleteCache(CACHE_CODE_CLASS_NAME);
        loadData();
        return true;
    }
    
    /**
     * 加载数据到缓存
     */
    private void loadData() {
        try {
            Map<String, String> utilCodeCache = new HashMap<String, String>();
            //得到全部分类
            List<String> typeList= commonService.queryUtilCodeType();
            List<Map<String, Object>> codeList=null;
            for (String parent : typeList) {            	
            	codeList=commonService.queryUtilCode(parent);               
                utilCodeCache.put(parent, JsonHelper.parseObject(codeList));
			}
            //EAP_SYS_UTIL_CODECLASS表
            codeList=commonService.queryUtilCodeClass(null); 
            utilCodeCache.put(CommonBussinessConstants.EAP_SYS_UTIL_CODECLASS, JsonHelper.parseObject(codeList));
            
            codeCache.saveCache(CACHE_CODE_NAME, utilCodeCache);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "缓存数据字典失败。", ex);
        }    	
    }
    
    /**
     * 缓存配置项
     */   
	@Override
	public void saveCache(Map<String, Object> arg0) {

	}

	@Override
	public Map<String, Object> getCache(String arg0) {
		return null;
	}

}
