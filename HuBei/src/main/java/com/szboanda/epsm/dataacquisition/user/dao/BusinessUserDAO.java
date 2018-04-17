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
* @Title:  用户模块DAO接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月10日 蔡楚涛 新建
*/
@AutoLoadDAO
public interface BusinessUserDAO extends BaseBusinessDAO {
    /**
     * 添加用户扩展字段
     * 
     * @param param
     * @return
     */
    int addExtendedUser(Map<String, Object> param);

    /**
     * 查询用户
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findSystemUser(Map<String, Object> param);

    /**
     * 批量添加用户与角色关系
     * 
     * @param param
     * @return
     */
    int addUserRoleRelBatch(Map<String, Object> param);

    /**
     * 批量删除用户与角色关系
     * 
     * @param param
     * @return
     */
    int delUserRoleRelBatch(Map<String, Object> param);
    
    /**
     * 根据用户ID查询用户扩展信息
     * 
     * @param userId
     * @return
     */
    Map<String, Object> getUserExtendByUserId(String userId);
    
    /**
     * 根据系统账号查询用户
     * 
     * @param userAccount
     * @return
     */
    Map<String, Object> getUserByAccount(String userAccount);
    
    /**
     * 根据用户ID查询用户
     * 
     * @param userId
     * @return
     */
    Map<String, Object> getUserById(String userId);
    
    /**
     * 查询本级用户
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findSameRegionUser(Map<String, Object> param);
    
    /**
     * 更新用户扩展信息
     * 
     * @param param
     * @return
     */
    int updateUserExtend(Map<String, Object> param);
    
    /**
     * 查询所有用户
     * 
     * @return
     */
    List<Map<String, Object>> findAllUser();
}
