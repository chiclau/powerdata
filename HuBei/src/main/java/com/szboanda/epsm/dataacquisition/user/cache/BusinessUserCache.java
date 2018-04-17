/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.cache.template.CacheTemplate;
import com.szboanda.platform.common.notice.service.INoticeService;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.helper.JsonHelper;
import com.szboanda.platform.soa.rpc.ICache;

/**
* @Title:  业务处理_用户缓存
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
@Component("BusinessUserCache")
public class BusinessUserCache extends CacheTemplate<Map<String, Object>> {

    /**
     * 缓存名称
     */
    private static final String CACHE_NAME = CommonBussinessConstants.CACHE_BUSINESS_USER;

    /**
     * 系统配置项缓存接口
     */
    @Autowired
    private ICache cache;

    /**
     * 数据库查询服务
     */
    @Autowired
    private IBusinessUserService userService;

    /**
     * 通知者服务
     */
    @Resource(name = "BusinessUserNoticeServiceImpl")
    private INoticeService<Map<String, Object>> noticeService;

    /**
     * 初始化
     */
    @Override
    public boolean init() {

        if (cache.isNotExists(CACHE_NAME)) {
            loadData();
        }

        // 注册配置信息变化监听
        noticeService.addListener(this);

        return true;
    }

    /**
     * 重新加载缓存
     */
    @Override
    public boolean reload() {
        // 删除缓存中的空间
        cache.deleteCache(CACHE_NAME);
        loadData();
        return true;
    }

    /**
     * 加载数据到缓存
     */
    private void loadData() {
        // 查询数据库中所有的配置信息
        try {
            List<Map<String, Object>> lstUserExtends = userService.findAllUser();

            Map<String, String> userCache = new HashMap<String, String>();

            // 将数据放到缓存中
            for (Map<String, Object> map : lstUserExtends) {
                userCache.put("" + map.get("YHID"), JsonHelper.parseObject(map));
            }
            cache.saveCache(CACHE_NAME, userCache);
        } catch (Exception ex) {
            LoggerUtil.error(this.getClass(), "缓存用户列表失败。", ex);
        }
    }

    /**
     * 获取缓存KEY
     */
    @Override
    public String getKey() {
        return CACHE_NAME;
    }

    /**
     * 保存用户缓存
     */
    @Override
    public void saveCache(Map<String, Object> message) {
        Map<String, String> data = new HashMap<String, String>();
        data.put((String) message.get("YHID"), JsonHelper.parseObject(message));
        cache.saveCache(CACHE_NAME, data);
    }

    /**
     * 根据用户ID删除缓存
     */
    @Override
    public void deleteCache(Map<String, Object> message) {
        cache.deleteCache(CACHE_NAME, (String) message.get("YHID"));
    }

    /**
     * 根据用户ID获取用户缓存
     */
    @Override
    public Map<String, Object> getCache(String userId) {
        String data = cache.getCache(CACHE_NAME, userId);
        return JsonHelper.jsonString2Map(data);
    }

    /**
     * 获取所有用户缓存
     */
    @Override
    public List<Map<String, Object>> getAll() {
        List<String> userList = cache.getAll(CACHE_NAME);
        List<Map<String, Object>> users = new ArrayList<Map<String, Object>>();
        for (String user : userList) {
            users.add(JsonHelper.jsonString2Map(user));
        }
        return users;
    }
    
}
