/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.schememanage.service;

import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.dataacquisition.schememanage.exception.SchemeManageException;

/**
* @Title:  案例信息库
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月26日 新建
*/
public interface ISchemeManageService {

    /** 
    * @Title: findSchemeManage 
    * @Description: 查询监测方案
    * @param @param map
    * @param @return
    * @param @throws SchemeManageException
    * @return 
    * PageInfo<Map<String,Object>>
    * @throws 
    */
    PageInfo<Map<String,Object>> findSchemeManage(Map<String,Object> map) 
            throws SchemeManageException;
    
    /** 
    * @Title: delSchemeManage 
    * @Description: 删除监测方案
    * @param @return
    * @return 
    * int
    * @throws 
    */
    int delSchemeManage(Map<String,Object> map) throws SchemeManageException;
    
    /** 
    * @Title: updateSchemeManage 
    * @Description: 更新监测状态
    * @param @param map
    * @param @return
    * @return 
    * Map<String,Object>
    * @throws 
    */
    int updateSchemeManage(@RequestBody Map<String,Object> map) throws SchemeManageException;
}
