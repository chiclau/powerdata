/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.knowledge.caseinformation.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.knowledge.caseinformation.dao.CaseInformationDAO;
import com.szboanda.epsm.knowledge.caseinformation.exception.CaseInformationException;
import com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.exception.SelfMonitoringMethodException;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.BaseToolkit;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;

/**
* @Title:  企业自行监测数据
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月19日 新建
*/
@Service
public class CaseInformationServiceImpl extends BaseBusinessService implements ICaseInformationService {
    
    @Autowired
    private CaseInformationDAO caseInformationDAO;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findCaseInformation(Map<String,Object> map) 
            throws SelfMonitoringMethodException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        if(!StringUtils.isEmpty(map.get("almc"))){
            SQLUtils.fillLike(map, "almc");
        }
        if(!StringUtils.isEmpty(map.get("allb"))){
            SQLUtils.fillLike(map, "allb");
        }
        if(!StringUtils.isEmpty(map.get("sgmc"))){
            SQLUtils.fillLike(map, "sgmc");
        }
        if(!StringUtils.isEmpty(map.get("fsdd"))){
            SQLUtils.fillLike(map, "fsdd");
        }
        List<Map<String,Object>> list = caseInformationDAO.findCaseInformation(map);
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethodTitle(java.util.Map)
     */
    public List<Map<String,Object>> findCaseInformationDetail(Map<String,Object> map)
            throws SelfMonitoringMethodException{
        return caseInformationDAO.findCaseInformation(map);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService#delcaseinformation(java.util.Map)
     */
    public int delCaseInformation(Map<String,Object> map) throws CaseInformationException{
        return caseInformationDAO.delCaseInformation(map);
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService#addCaseInformation(java.util.Map)
     */
    public int addCaseInformation(Map<String,Object> map) throws CaseInformationException{
        if(map.containsKey("zbalid")){
            return caseInformationDAO.updateCaseInformation(map);
        }
        map.put("zbalid", BaseToolkit.getID());
        map.put("sheng", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
        map.put("shi", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        map.put("xian", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        return caseInformationDAO.addCaseInformation(map);
    }
}
