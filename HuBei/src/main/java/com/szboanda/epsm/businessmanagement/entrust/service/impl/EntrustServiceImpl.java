/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.entrust.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.entrust.dao.EntrustDAO;
import com.szboanda.epsm.businessmanagement.entrust.exception.EntrustException;
import com.szboanda.epsm.businessmanagement.entrust.service.IEntrustService;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;

/**
* @Title:  通知公告
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月21日 新建
*/
@Service
public class EntrustServiceImpl extends BaseBusinessService implements IEntrustService {
    
    @Autowired
    private EntrustDAO entrustDao;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findEntrust(Map<String,Object> map) 
            throws EntrustException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        List<Map<String,Object>> list = entrustDao.findEntrust(map);
        StringBuffer sb = new StringBuffer();
        for (Map<String, Object> map2 : list) {
            if(map.containsKey("sheng")&&!map.get("sheng").equals("")){
                sb.append(entrustDao.findDivision(map.get("sheng").toString()));
            }
            if(map.containsKey("shi")&&!map.get("shi").equals("")){
                sb.append(","+entrustDao.findDivision(map.get("shi").toString()));
            }
            if(map.containsKey("xian")&&!map.get("xian").equals("")){
                sb.append(","+entrustDao.findDivision(map.get("xian").toString()));
            }
            map2.put("xzqh",sb);
            map2 = new HashMap<String,Object>();
        }
        
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
}
