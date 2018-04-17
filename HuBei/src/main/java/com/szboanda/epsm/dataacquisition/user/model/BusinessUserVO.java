/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.model;

import java.io.Serializable;

import com.szboanda.platform.rms.user.mode.UserVO;

/**
* @Title:  业务处理_用户VO
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月19日 蔡楚涛 新建
*/
public class BusinessUserVO implements Serializable{
    /**
     * 序列化号
     */
    private static final long serialVersionUID = -367790230486971117L;

    /**
     * 用户信息VO
     */
    private UserVO userVO;
    
    /**
     * 用户扩展信息VO
     */
    private UserExtendVO userExtendVO;

    /**
     * @return the userVO
     */
    public UserVO getUserVO() {
        return userVO;
    }

    /**
     * @param userVO the userVO to set
     */
    public void setUserVO(UserVO userVO) {
        this.userVO = userVO;
    }

    /**
     * @return the userExtendVO
     */
    public UserExtendVO getUserExtendVO() {
        return userExtendVO;
    }

    /**
     * @param userExtendVO the userExtendVO to set
     */
    public void setUserExtendVO(UserExtendVO userExtendVO) {
        this.userExtendVO = userExtendVO;
    }
    
}
