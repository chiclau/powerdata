/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.organization.orguser.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.businessmanagement.organization.orguser.exception.OrgUserException;

/**
* @Title:  监测机构用户模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月17日 蔡楚涛 新建
*/
public interface IOrgUserService {
    /**
     * 查询监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    List<Map<String, Object>> findOrgUser(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 根据ID查询监测机构用户
     * 
     * @param orgUserId
     * @return
     * @throws OrgUserException
     */
    Map<String, Object> getOrgUserById(String orgUserId) throws OrgUserException;
    
    /**
     * 添加监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    int addOrgUser(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 更新监测机构用户
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    int updateOrgUser(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 删除监测机构用户
     * 
     * @param orgUserId
     * @return
     * @throws OrgUserException
     */
    int delOrgUser(String orgUserId) throws OrgUserException;
    
    /**
     * 查询监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    List<Map<String, Object>> findCertificate(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 根据ID查询监测机构人员证书
     * 
     * @param certificateId
     * @return
     * @throws OrgUserException
     */
    Map<String, Object> getCertificateById(String certificateId) throws OrgUserException;
    
    /**
     * 添加监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    int addCertificate(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 更新监测机构人员证书
     * 
     * @param param
     * @return
     * @throws OrgUserException
     */
    int updateCertificate(Map<String, Object> param) throws OrgUserException;
    
    /**
     * 删除监测机构人员证书
     * 
     * @param certificateId
     * @return
     * @throws OrgUserException
     */
    int delCertificate(String certificateId) throws OrgUserException;
}
