/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.dao;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.common.base.BaseBusinessDAO;
import com.szboanda.platform.common.annotation.AutoLoadDAO;

/**
* @Title:  服务机构DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月22日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface ServiceOrganizationDAO extends BaseBusinessDAO {
    /**
     * 查询服务机构
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findServiceOrganization(Map<String, Object> param);

    /**
     * 根据ID获取服务机构
     * 
     * @param serviceOrganizationId
     * @return
     */
    Map<String, Object> getServiceOrganizationById(String serviceOrganizationId);

    /**
     * 根据登录账号获取服务机构
     * 
     * @param account
     * @return
     */
    Map<String, Object> getServiceOrganizationByAccount(String account);
    
    /**
     * 根据服务机构ID查询备案
     * 
     * @param serviceOrganizationId
     * @return
     */
    Map<String, Object> getRecordByOrgId(String serviceOrganizationId);

    /**
     * 根据服务机构ID查询监测项目范围
     * 
     * @param serviceOrganizationId
     * @return
     */
    List<Map<String, Object>> findProjectScopeByOrgId(String serviceOrganizationId);

    /**
     * 根据服务机构ID查询资质证书
     * 
     * @param serviceOrganizationId
     * @return
     */
    List<Map<String, Object>> findCertificateByOrgId(String serviceOrganizationId);

    /**
     * 添加服务机构
     * 
     * @param param
     * @return
     */
    int addServiceOrganization(Map<String, Object> param);

    /**
     * 添加备案
     * 
     * @param param
     * @return
     */
    int addRecord(Map<String, Object> param);

    /**
     * 批量添加监测项目范围
     * 
     * @param param
     * @return
     */
    int addProjectScopeBatch(Map<String, Object> param);

    /**
     * 批量添加资质证书
     * 
     * @param param
     * @return
     */
    int addCertificateBatch(Map<String, Object> param);

    /**
     * 更新服务机构
     * 
     * @param param
     * @return
     */
    int updateServiceOrganization(Map<String, Object> param);

    /**
     * 根据服务机构ID更新备案
     * 
     * @param param
     * @return
     */
    int updateRecordByOrgId(Map<String, Object> param);

    /**
     * 根据服务机构ID删除备案
     * 
     * @param serviceOrganizationId
     * @return
     */
    int delRecordByOrgId(String serviceOrganizationId);
}
