/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.notification.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.workbench.notification.dao.NotificationDAO;
import com.szboanda.epsm.workbench.notification.exception.NotificationException;
import com.szboanda.epsm.workbench.notification.service.INotificationService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;
import com.szboanda.platform.common.utils.Toolkit;

/**
* @Title:  通知公告
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月21日 新建
*/
@Service
public class NotificationServiceImpl extends BaseBusinessService implements INotificationService {
    
    @Autowired
    private NotificationDAO notificationDAO;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findNotification(Map<String,Object> map) 
            throws NotificationException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        if(!StringUtils.isEmpty(map.get("yhmc"))){
            SQLUtils.fillLike(map, "yhmc");
        }
        if(!StringUtils.isEmpty(map.get("title"))){
            SQLUtils.fillLike(map, "title");
        }
        List<Map<String,Object>> list = notificationDAO.findNotification(map);
        String fbfw = "";
        String[] str = null;
        StringBuffer rtStr = new StringBuffer();
        if(list!=null&&list.size()>0){
            for (Map<String, Object> map1 : list) {
                fbfw = map1.get("fbfw").toString();
                if(fbfw!=null&&fbfw.length()>0){
                    str = fbfw.split(",");
                    for (int i = 0; i < str.length; i++) {
                        if(i==str.length-1&&str[i].length()>0){
                            rtStr.append(findXzqh(str[i]).get("xzqh"));
                            break;
                        }
                        if(str[i].length()>0){
                            rtStr.append(findXzqh(str[i]).get("xzqh")+",");
                        }
                    }
                }
                map1.put("fbfw", rtStr.toString());
                rtStr = new StringBuffer();
            }
            
        }
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#findArea(java.util.Map)
     */
    public List<Map<String,Object>> findArea(Map<String,Object> map)
            throws NotificationException{
        if("1".equals(map.get("yhjb"))){
            map.put("xzqhdmsheng", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
            return notificationDAO.findAreaSheng(map);
        }else if("2".equals(map.get("yhjb"))){
            map.put("xzqhdmshi", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
            return notificationDAO.findAreaShi(map);
        }else if("3".equals(map.get("yhjb"))){
            map.put("xzqhdmxian", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
            return notificationDAO.findAreaXian(map);
        }
        return null;
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#findUser()
     */
    public List<Map<String,Object>> findUser()throws NotificationException{
        return notificationDAO.findUser();
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#findMyOwenNotification()
     */
    public PageInfo<Map<String,Object>> findMyOwenNotification(Map<String,Object> map)throws NotificationException{
        if("1".equals(this.getCurrUser().getYhjb())){
            map.put("fbfw", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
        }else if("2".equals(this.getCurrUser().getYhjb())){
            map.put("fbfw", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        }else if("3".equals(this.getCurrUser().getYhjb())){
            map.put("fbfw", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        }else if("9".equals(this.getCurrUser().getYhjb())){
            map.put("fbfw", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        }
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        if(!StringUtils.isEmpty(map.get("title"))){
            SQLUtils.fillLike(map, "title");
        }
        if(!StringUtils.isEmpty(map.get("yhmc"))){
            SQLUtils.fillLike(map, "yhmc");        
        }
        if(!StringUtils.isEmpty(map.get("fbfw"))){
            SQLUtils.fillLike(map, "fbfw");
        }
        List<Map<String,Object>> list = notificationDAO.findMyOwenNotification(map);
        String fbfw = "";
        String[] str = null;
        StringBuffer rtStr = new StringBuffer();
        if(list!=null&&list.size()>0){
            for (Map<String, Object> map1 : list) {
                fbfw = map1.get("fbfw").toString();
                if(fbfw!=null&&fbfw.length()>0){
                    str = fbfw.split(",");
                    for (int i = 0; i < str.length; i++) {
                        if(i==str.length-1&&str[i].length()>0){
                            rtStr.append(findXzqh(str[i]).get("xzqh"));
                            break;
                        }
                        if(str[i].length()>0){
                            rtStr.append(findXzqh(str[i]).get("xzqh")+",");
                        }
                    }
                }
                map1.put("fbfw", rtStr.toString());
                rtStr = new StringBuffer();
            }
            
        }
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.workbench.notification.service.INotificationService#addNotification(java.util.Map)
     */
    @SuppressWarnings("rawtypes")
    public int addNotification(Map<String,Object> map)throws NotificationException{
        ArrayList arr = null;
        if(map.get("fbfw")!=null &&! map.get("fbfw").equals("")){
            arr = (ArrayList)map.get("fbfw");
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < arr.size(); i++) {
            if(i==arr.size()-1){
                sb.append(arr.get(i));
                break;
            }
            sb.append(arr.get(i)+",");
        }
        map.put("fbfw", sb.toString());
        map.put("id",Toolkit.getUUID());
        map.put("fbr", this.getBusinessCurrUser().getUserVO().getYhid());
        return notificationDAO.addNotification(map);
    }
    
    /** 
    * @Title: findXzqh 
    * @Description: 根据行政区划代码传行政区划
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    public Map<String,Object> findXzqh(String xzqhdm){
        Map<String,Object> map = new HashMap<String,Object>();
        if(xzqhdm!=null&&xzqhdm.length()>0){
            map.put("xzqhdm", xzqhdm);
        }
        return notificationDAO.findXzqh(map);
    }
}
