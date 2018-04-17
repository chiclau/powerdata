/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.service;

import java.util.List;
import java.util.Map;

import com.szboanda.epsm.dataacquisition.user.exception.BusinessUserException;

/**
* @Title:  用户模块Service接口定义
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月10日 蔡楚涛 新建
*/
public interface IBusinessUserService {
    /**
     * 添加用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    int addUser(Map<String, Object> param) throws BusinessUserException;
    
    /**
     * 查询用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    List<Map<String, Object>> findSystemUser(Map<String, Object> param) throws BusinessUserException;
    
    /**
     * 根据用户ID查询用户扩展信息
     * 
     * @param userId
     * @return
     * @throws BusinessUserException
     */
    Map<String, Object> getUserExtendByUserId(String userId) throws BusinessUserException;
    
    /**
     * 根据用户ID删除用户
     * @param userId
     * @return
     * @throws BusinessUserException
     */
    int delUser(String userId) throws BusinessUserException;
    
    /**
     * 根据系统账号查询用户
     * 
     * @param userAccount
     * @return
     * @throws BusinessUserException
     */
    Map<String, Object> getUserByAccount(String userAccount) throws BusinessUserException;
    
    /**
     * 查询本级用户
     * 
     * @param param
     * @return
     */
    List<Map<String, Object>> findSameRegionUser(Map<String, Object> param);
    
    /**
     * 判断是否存在相同行政区划的用户
     * 
     * @param param
     * @return
     */
    boolean isExistSameRegionUser(Map<String, Object> param);
    
    /**
     * 更新用户
     * 
     * @param param
     * @return
     */
    int updateUser(Map<String, Object> param);
    
    /**
     * 查询所有用户
     * 
     * @return
     */
    List<Map<String, Object>> findAllUser();
    
    /**
     * 缓存方法：根据用户ID查询用户
     * 
     * @param userId
     * @return
     */
    Map<String, Object> getUserById4Cache(String userId);
    
    /**
     * 缓存方法：查询所有用户
     * 
     * @return
     */
    List<Map<String, Object>> findAllUser4Cache();
}
