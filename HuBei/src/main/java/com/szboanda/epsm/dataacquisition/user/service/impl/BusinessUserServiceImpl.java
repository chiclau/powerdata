/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.dataacquisition.user.cache.BusinessUserCache;
import com.szboanda.epsm.dataacquisition.user.dao.BusinessUserDAO;
import com.szboanda.epsm.dataacquisition.user.exception.BusinessUserException;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.common.constants.CommonConstants;
import com.szboanda.platform.common.utils.CollectionUtils;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;
import com.szboanda.platform.common.utils.Toolkit;
import com.szboanda.platform.common.utils.security.PasswordHelper;
import com.szboanda.platform.rms.user.service.IUserService;

/**
* @Title:  用户模块Service实现类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月10日 蔡楚涛 新建
*/
@Service
public class BusinessUserServiceImpl extends BaseBusinessService implements IBusinessUserService {
    /**
     * 用户模块DAO实现
     */
    @Autowired
    private BusinessUserDAO businessUserDAO;
    
    /**
     * 消息通知服务
     */
    @Resource(name = "BusinessUserNoticeServiceImpl")
    private BusinessUserNoticeServiceImpl noticeService;
    
    /**
     * 基础平台用户Service实现
     */
    @Autowired
    private IUserService userService;
    
    /**
     * 业务处理_用户缓存
     */
    @Autowired
    private BusinessUserCache businessUserCache;

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#addUser(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public int addUser(Map<String, Object> param) throws BusinessUserException {
        try {
            // 获取用户密码
            String password = MapUtils.getString(param, "YHMM");
            if (!StringUtils.hasText(password)) {
                // 如果用户没有设置密码，则设置默认密码
                password = CommonBussinessConstants.USER_DEFAULT_PASSWORD;
            }
            // 设置加密后的密码
            param.put("YHMM", PasswordHelper.encryptString(password));
            
            // 设置是否有效为有效
            param.put("SFYX", "1");
            // 添加用户
            int count = userService.addUser(param);
            
            // 添加用户扩展字段
            param.put("XH", Toolkit.getID());
            businessUserDAO.addExtendedUser(param);
            
            // 更新缓存
            noticeService.notice(CommonConstants.NOTICE_ADD, businessUserDAO.getUserById(MapUtils.getString(param, "YHID")));
            
            // 添加用户与角色关系
            List<Map<String, Object>> addRoles = (List<Map<String, Object>>) param.get("addRoles");
            for (Map<String, Object> role : addRoles) {
                role.put("XH", Toolkit.getID());
            }
            businessUserDAO.addUserRoleRelBatch(param);
            return count;
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "添加用户异常 : " + param, e);
            throw new BusinessUserException("添加用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#findUser(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findSystemUser(Map<String, Object> param) throws BusinessUserException {
        try {
            SQLUtils.fillLike(param, "YHMC");
            SQLUtils.fillLike(param, "ZHJGMC");
            SQLUtils.fillLike(param, "YHJS");
            return businessUserDAO.findSystemUser(param);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "查询用户异常 : " + param, e);
            throw new BusinessUserException("查询用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#getUserExtendByUserId(java.lang.String)
     */
    @Override
    public Map<String, Object> getUserExtendByUserId(String userId) throws BusinessUserException {
        try {
            return businessUserDAO.getUserExtendByUserId(userId);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "根据用户ID查询用户扩展信息异常 : " + userId, e);
            throw new BusinessUserException("根据用户ID查询用户扩展信息异常 : " + userId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#delUser(java.lang.String)
     */
    @Override
    public int delUser(String userId) throws BusinessUserException {
        try {
            return userService.deleteUser(userId);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "根据用户ID删除用户异常 : " + userId, e);
            throw new BusinessUserException("根据用户ID删除用户异常 : " + userId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#getUserByAccount(java.lang.String)
     */
    @Override
    public Map<String, Object> getUserByAccount(String userAccount) throws BusinessUserException {
        try {
            return businessUserDAO.getUserByAccount(userAccount);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "根据系统账号查询用户异常 : " + userAccount, e);
            throw new BusinessUserException("根据系统账号查询用户异常 : " + userAccount, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#findSameRegionUser(java.util.Map)
     */
    @Override
    public List<Map<String, Object>> findSameRegionUser(Map<String, Object> param) {
        try {
            SQLUtils.fillLike(param, "YHJS");
            return businessUserDAO.findSameRegionUser(param);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "查询本级用户异常 : " + param, e);
            throw new BusinessUserException("查询本级用户异常 : " + param, e);
        }
    }
    
    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#isExistSameRegionUser(java.util.Map)
     */
    @Override
    public boolean isExistSameRegionUser(Map<String, Object> param) {
        try {
            Map<String, Object> parameter = new HashMap<String, Object>();
            parameter.put("XZQHDMSHENG", param.get("XZQHDMSHENG"));
            parameter.put("XZQHDMSHI", param.get("XZQHDMSHI"));
            parameter.put("XZQHDMXIAN", param.get("XZQHDMXIAN"));
            return CollectionUtils.isNotEmpty(businessUserDAO.findSameRegionUser(parameter));
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "判断是否存在相同行政区划的用户异常 : " + param, e);
            throw new BusinessUserException("判断是否存在相同行政区划的用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#updateUser(java.util.Map)
     */
    @SuppressWarnings("unchecked")
    @Override
    public int updateUser(Map<String, Object> param) {
        try {
            
            // 获取用户密码
            String password = MapUtils.getString(param, "YHMM");
            if (StringUtils.hasText(password)) {
                // 设置加密后的密码
                param.put("YHMM", PasswordHelper.encryptString(password));
            }
            
            // 更新用户
            int count = userService.updateUser(param);
            
            // 更新用户扩展信息
            businessUserDAO.updateUserExtend(param);
            
            // 删除用户与角色关系
            businessUserDAO.delUserRoleRelBatch(param);
            
            // 添加用户与角色关系
            List<Map<String, Object>> addRoles = (List<Map<String, Object>>) param.get("addRoles");
            if (CollectionUtils.isNotEmpty(addRoles)) {
                for (Map<String, Object> role : addRoles) {
                    role.put("XH", Toolkit.getID());
                }
                businessUserDAO.addUserRoleRelBatch(param);
            }
            
            // 更新缓存
            noticeService.notice(CommonConstants.NOTICE_UPDATE, businessUserDAO.getUserById(MapUtils.getString(param, "YHID")));
            
            return count;
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "更新用户异常 : " + param, e);
            throw new BusinessUserException("更新用户异常 : " + param, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#findAllUserExtend()
     */
    @Override
    public List<Map<String, Object>> findAllUser() {
        try {
            return businessUserDAO.findAllUser();
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "查询所有用户异常", e);
            throw new BusinessUserException("查询所有用户异常", e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#getUserById4Cache(java.lang.String)
     */
    @Override
    public Map<String, Object> getUserById4Cache(String userId) {
        try {
            return businessUserCache.getCache(userId);
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "缓存方法：根据用户ID查询用户异常 : " + userId, e);
            throw new BusinessUserException("缓存方法：根据用户ID查询用户异常 : " + userId, e);
        }
    }

    /* (non-Javadoc)
     * @see com.szboanda.epsm.dataacquisition.user.service.IUserService#findAllUser4Cache()
     */
    @Override
    public List<Map<String, Object>> findAllUser4Cache() {
        try {
            return businessUserCache.getAll();
        } catch (Exception e) {
            LoggerUtil.error(BusinessUserServiceImpl.class, "缓存方法：查询所有用户异常", e);
            throw new BusinessUserException("缓存方法：查询所有用户异常", e);
        }
    }
}
