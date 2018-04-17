/******************************************************************************
 * Copyright (C) ShenZhen Powerdata Information Technology Co.,Ltd All Rights
 * Reserved. 本软件为深圳市博安达信息技术股份有限公司开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、 复制、修改或发布本软件.
 *****************************************************************************/

package com.szboanda.epsm.common.base;

import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.dataacquisition.user.model.BusinessUserVO;
import com.szboanda.epsm.dataacquisition.user.model.UserExtendVO;
import com.szboanda.platform.common.base.BaseService;
import com.szboanda.platform.common.utils.Toolkit;
import com.szboanda.platform.rms.user.mode.UserVO;

/**
 * @title:       业务处理Service
 * @fileName:    BaseBusinessService.java
 * @description: 该【业务处理Service】继承 平台部门的Service,具体项目的Service需要全部继承这个业务处理Service
 * @copyright:   PowerData Software Co.,Ltd. Rights Reserved.
 * @company:     深圳市博安达信息技术股份有限公司
 * @author：                唐肖肖
 * @date：                      2016年12月6日  
 * @version：             V1.0
 */
public class BaseBusinessService extends BaseService {
    
    /**
     * 获取当前用户信息
     * 
     * @return
     */
    protected BusinessUserVO getBusinessCurrUser() {
        UserVO userVO = super.getCurrUser();
        UserExtendVO userExtendVO = (UserExtendVO) Toolkit.getRequest().getSession().getAttribute(CommonBussinessConstants.SESSION_USER_EXTEND);
        BusinessUserVO businessUserVO = new BusinessUserVO();
        businessUserVO.setUserVO(userVO);
        businessUserVO.setUserExtendVO(userExtendVO);
        return businessUserVO;
    }
}
