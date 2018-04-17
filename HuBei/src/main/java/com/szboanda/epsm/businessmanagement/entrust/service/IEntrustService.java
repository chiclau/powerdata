/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.entrust.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.entrust.exception.EntrustException;

/**
* @Title:  委托机构统计
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日 新建
*/
public interface IEntrustService {

    /** 
     * @Title: findSelfMonitoringMethod 
     * @Description: 查询委托机构统计
     * @param 
     * @return List<Map<String,Object>>
     * List<Map<String,Object>>
     * @throws 
     */
    PageInfo<Map<String,Object>> findEntrust(Map<String,Object> map) 
            throws EntrustException;
}
