/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.login.listener;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.utils.BusinessUserHelper;
import com.szboanda.epsm.dataacquisition.user.model.UserExtendVO;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.common.notice.service.INoticeService;
import com.szboanda.platform.common.notice.service.impl.AbstractNoticeListener;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  登录成功监听器
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月18日 蔡楚涛 新建
*/
@Component
public class LoginSuccessListenerImpl extends AbstractNoticeListener<Map<String, Object>> {
    
    /**
     * 用户模块Service实现
     */
    @Autowired
    private IBusinessUserService userService;

    /**
     * 事件通知
     */
    @Resource(name = "LoginNoticeServiceImpl")
    private INoticeService<Map<String, Object>> noticeService;

    /**
     * 初始化
     */
    public boolean init() {
        // 注册监听
        noticeService.addListener(this);
        return true;
    }

    /**
     * 得到key
     */
    @Override
    public String getKey() {
        return "LoginSuccessListenerImpl";
    }

    /**
     * 处理通知
     */
    @Override
    public void handle(String type, Map<String, Object> message) {
        // 登录成功时
        if ("LoginSuccess".equals(type)) {
            // 获取用户ID
            String userId = MapUtils.getString(message, "userId");
            
            // 根据用户ID查询用户扩展信息
            Map<String, Object> userMap = userService.getUserExtendByUserId(userId);
            UserExtendVO userExtendVO = BusinessUserHelper.map2UserExtendVO(userMap);
            
            Toolkit.getRequest().getSession().setAttribute(CommonBussinessConstants.SESSION_USER_EXTEND, userExtendVO);
        }
    }
}
