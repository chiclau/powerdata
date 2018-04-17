/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.schememanage.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.dataacquisition.schememanage.dao.SchemeManageDAO;
import com.szboanda.epsm.dataacquisition.schememanage.exception.SchemeManageException;
import com.szboanda.epsm.dataacquisition.schememanage.service.ISchemeManageService;
import com.szboanda.epsm.knowledge.selfmonitoringmethod.exception.SelfMonitoringMethodException;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.SQLUtils;

/**
* @Title:  案例信息库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月26日 新建
*/
@Service
public class SchemeManageServiceImpl extends BaseBusinessService implements ISchemeManageService {
    
    @Autowired
    private SchemeManageDAO schemeManageDAO;
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethod(java.util.Map)
     */
    public PageInfo<Map<String,Object>> findSchemeManage(Map<String,Object> map) 
            throws SelfMonitoringMethodException{
        if(map.get("pageNum")!=null&&map.get("pageSize")!=null){
            DataHelper.startPage((Integer)map.get("pageNum"), (Integer)map.get("pageSize"));
        }
        SQLUtils.fillLike(map, "famc");
        SQLUtils.fillLike(map, "bb");
        List<Map<String,Object>> list = schemeManageDAO.findSchemeManage(map);
        Map<String,Object> commMap = new HashMap<String,Object>();
        commMap.put("0", "未提交");
        commMap.put("1", "审核中");
        commMap.put("2", "审核失败");
        commMap.put("3", "审核通过(当前在用)");
        commMap.put("4", "审核通过");
        for (Map<String, Object> map2 : list) {
            for (Map.Entry<String, Object> entry : commMap.entrySet()) {
                if(map2.get("zt")!=null&&entry.getKey().equals(map2.get("zt"))){
                    map2.replace("zt", entry.getValue());
                }
            }
        }
        return new PlatformPageInfo<Map<String,Object>>(list);
    }
    
//    /* (non-Javadoc)
//     * @see com.szboanda.epsm.knowledge.selfmonitoringmethod.service.ISelfMonitoringMethodService#findSelfMonitoringMethodTitle(java.util.Map)
//     */
//    public List<Map<String,Object>> findCaseInformationDetail(Map<String,Object> map)
//            throws SelfMonitoringMethodException{
//        return caseInformationDAO.findCaseInformation(map);
//    }
//    
//    /* (non-Javadoc)
//     * @see com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService#delcaseinformation(java.util.Map)
//     */
    public int delSchemeManage(Map<String,Object> map) throws SchemeManageException{
        return schemeManageDAO.delSchemeManage(map);
    }
//    
//    /* (non-Javadoc)
//     * @see com.szboanda.epsm.knowledge.caseinformation.service.ICaseInformationService#addCaseInformation(java.util.Map)
//     */
//    public int addCaseInformation(Map<String,Object> map) throws CaseInformationException{
//        if(map.containsKey("zbalid")){
//            return caseInformationDAO.updateCaseInformation(map);
//        }
//        map.put("zbalid", BaseToolkit.getID());
//        map.put("sheng", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
//        map.put("shi", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
//        map.put("xian", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
//        return caseInformationDAO.addCaseInformation(map);
//    }

 
    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.schememanage.service.ISchemeManageService#updateSchemeManage(java.util.Map)
     */
    public int updateSchemeManage(Map<String, Object> map) throws SchemeManageException {
        return schemeManageDAO.updateSchemeManage(map);
    }
}
