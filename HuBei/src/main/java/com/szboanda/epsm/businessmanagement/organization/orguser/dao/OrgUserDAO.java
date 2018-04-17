/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orguser.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  监测机构用户模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface OrgUserDAO extends BaseBusinessDAO  {
    /**
     * 查询监测机构用户
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findOrgUser(Map<String, Object> param);
    
    /**
     * 根据ID查询监测机构用户
     * 
     * @param orgUserId
     * @return
     */
    Map<String, Object> getOrgUserById(String orgUserId);
    
    /**
     * 添加监测机构用户
     * 
     * @param param
     * @return
     */
    int addOrgUser(Map<String, Object> param);
    
    /**
     * 更新监测机构用户
     * 
     * @param param
     * @return
     */
    int updateOrgUser(Map<String, Object> param);
    
    /**
     * 删除监测机构用户
     * 
     * @param orgUserId
     * @return
     */
    int delOrgUser(String orgUserId);
    
    /**
     * 查询监测机构人员证书
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findCertificate(Map<String, Object> param);
    
    /**
     * 根据ID查询监测机构人员证书
     * 
     * @param certificateId
     * @return
     */
    Map<String, Object> getCertificateById(String certificateId);
    
    /**
     * 添加监测机构人员证书
     * 
     * @param param
     * @return
     */
    int addCertificate(Map<String, Object> param);
    
    /**
     * 更新监测机构人员证书
     * 
     * @param param
     * @return
     */
    int updateCertificate(Map<String, Object> param);
    
    /**
     * 删除监测机构人员证书
     * 
     * @param certificateId
     * @return
     */
    int delCertificate(String certificateId);
}
