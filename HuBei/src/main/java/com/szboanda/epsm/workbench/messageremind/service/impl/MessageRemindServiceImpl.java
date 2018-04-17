/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.messageremind.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.workbench.messageremind.dao.MessageRemindDAO;
import com.szboanda.epsm.workbench.messageremind.exception.MessageRemindException;
import com.szboanda.epsm.workbench.messageremind.service.IMessageRemindService;
import com.szboanda.epsm.workbench.notification.exception.NotificationException;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;

/**
* @Title:  通知公告
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月21日 新建
*/
@Service
public class MessageRemindServiceImpl extends BaseBusinessService implements IMessageRemindService {
    
    @Autowired
    private MessageRemindDAO messageRemindDao;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findMessageRemind(Map<String,Object> map) 
            throws MessageRemindException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        if(!StringUtils.isEmpty(map.get("bt"))){
            SQLUtils.fillLike(map, "bt");
        }
        if(!StringUtils.isEmpty(map.get("nr"))){
            SQLUtils.fillLike(map, "nr");
        }
        if(!StringUtils.isEmpty(map.get("sender"))){
            SQLUtils.fillLike(map, "sender");
        }
        map.put("receiver", this.getBusinessCurrUser().getUserVO().getYhid());
        List<Map<String,Object>> list = messageRemindDao.findMessageRemind(map);
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
   
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.messageremind.service.IMessageRemindService#delMessageRemind(java.util.Map)
     */
    public int delMessageRemind(Map<String,Object> map) 
            throws MessageRemindException{
        return messageRemindDao.delMessageRemind(map);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#findUser()
     */
    public List<Map<String,Object>> findUser()throws NotificationException{
        return messageRemindDao.findUser();
    }
}
