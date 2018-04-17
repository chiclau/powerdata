/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.contactmanager.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.workbench.contactmanager.exception.ContactManagerException;

/**
 * @title:      联系人管理 
 * @fileName:   IContactManagerService.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
public interface IContactManagerService {

   /**
    * 查询联系人
    * @param Map<String,Object>
    * @return List<Map<String,Object>>
    * @throws ContactManagerException
    * @author 王观发
    * @date 2017年10月17日
    */
    List<Map<String,Object>> queryContacts (Map<String,Object> map) 
            throws ContactManagerException;
    
    /**
     * 根据ID查询联系人
     * @return Map<String,Object>
     * @throws ContactManagerException
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryContactsById(String id)
            throws ContactManagerException;
    /**
     * 添加联系人
     * 
     * @param model
     * @return
     */
    int addContacts(Map<String, Object> model) throws ContactManagerException;

    /**
     * 修改联系人
     * 
     * @param model
     * @return
     */
    int updateContacts(Map<String, Object> model) throws ContactManagerException;
    
    /**
     * 根据ID删除联系人
     * 
     * @param id
     * @return
     */
    int delContacts(String id) throws ContactManagerException;
    
}
