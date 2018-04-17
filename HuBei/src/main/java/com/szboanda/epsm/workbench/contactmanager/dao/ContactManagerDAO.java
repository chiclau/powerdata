/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.contactmanager.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
 * @title:      联系人管理
 * @fileName:   ContactManagerDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     王观发
 * @date:       2017年10月17日 
 * @version:    V1.0
 */
@AutoLoadDAO
public interface ContactManagerDAO extends BaseBusinessDAO {
    
    /**
     * 查询联系人
     * @param map
     * @return List<Map<String,Object>>
     * @author 王观发
     * @date 2017年10月17日
     */
    List<Map<String,Object>> queryContacts(Map<String,Object> map);
    
    /**
     * 根据ID查询联系人
     * @return Map<String,Object>
     * @author 王观发
     * @date 2017年10月21日
     */
    Map<String, Object> queryContactsById(String id);
    
    /**
     * 新增联系人
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int addContacts(Map<String, Object> model);
    
    /**
     * 修改联系人
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int updateContacts(Map<String, Object> model);
    
    /**
     * 删除联系人
     * @return int
     * @author 王观发
     * @date 2017年10月20日
     */
    int delContacts(Map<String, Object> param);
    
}
