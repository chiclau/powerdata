/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.selfmonitoringmethod.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.dao.SelfMonitoringMethodDAO;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.exception.SelfMonitoringMethodException;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;

/**
* @Title:  企业自行监测数据
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月12日 新建
*/
@Service
public class SelfMonitoringMethodServiceImpl extends BaseBusinessService implements ISelfMonitoringMethodService {
    
    @Autowired
    private SelfMonitoringMethodDAO selfMonitoringMethodDAO;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findSelfMonitoringMethod(Map<String,Object> map) 
            throws SelfMonitoringMethodException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        if(!StringUtils.isEmpty(map.get("ffmc"))){
            SQLUtils.fillLike(map, "ffmc");
        }
        if(!StringUtils.isEmpty(map.get("ffbzmc"))){
            SQLUtils.fillLike(map, "ffbzmc");        
        }
        if(!StringUtils.isEmpty(map.get("ffbzbh"))){
            SQLUtils.fillLike(map, "ffbzbh");
        }
        if(!StringUtils.isEmpty(map.get("ffbzfl"))){
            SQLUtils.fillLike(map, "ffbzfl");
        }
        if(!StringUtils.isEmpty(map.get("jcxmmc"))){
            SQLUtils.fillLike(map, "jcxmmc");
        }
        List<Map<String,Object>> list = selfMonitoringMethodDAO.findSelfMonitoringMethod(map);
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethodTitle(java.util.Map)
     */
    public List<Map<String,Object>> findSelfMonitoringMethodTitle(Map<String,Object> map)
            throws SelfMonitoringMethodException{
        SQLUtils.fillLike(map, "ffmc");
        SQLUtils.fillLike(map, "ffbzmc");
        SQLUtils.fillLike(map, "ffbzbh");
        SQLUtils.fillLike(map, "ffbzfl");
        SQLUtils.fillLike(map, "jcxmmc");
        return selfMonitoringMethodDAO.findSelfMonitoringMethod(map);
    }
}
