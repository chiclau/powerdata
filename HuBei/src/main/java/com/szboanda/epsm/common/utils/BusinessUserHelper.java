/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.common.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.szboanda.epsm.dataacquisition.user.model.BusinessUserVO;
import com.szboanda.epsm.dataacquisition.user.model.UserExtendVO;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.epsm.dataacquisition.user.service.impl.BusinessUserServiceImpl;
import com.szboanda.platform.common.utils.CollectionUtils;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.Toolkit;
import com.szboanda.platform.rms.user.mode.UserVO;
import com.szboanda.platform.rms.user.utils.UserUtil;

/**
* @Title:  业务处理_用户帮助类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月23日 蔡楚涛 新建
*/
public class BusinessUserHelper {
    
    /**
     * 根据用户ID，获取用户信息
     * 
     * @param userId
     * @return
     */
    public static BusinessUserVO getUser(String userId) {
        Map<String, Object> user = ((IBusinessUserService) Toolkit.getSpringBean(BusinessUserServiceImpl.class)).getUserById4Cache(userId);
        return map2BusinessUserVO(user);
    }
    
    /**
     * 获取所有用户
     * 
     * @return
     */
    public static List<BusinessUserVO> findAllUser() {
        List<Map<String, Object>> userMapList = ((IBusinessUserService) Toolkit.getSpringBean(BusinessUserServiceImpl.class)).findAllUser4Cache();
        List<BusinessUserVO> userList = new ArrayList<BusinessUserVO>();
        for (Map<String, Object> user : userMapList) {
            userList.add(map2BusinessUserVO(user));
        }
        return userList;
    }
    
    /**
     * 将Map转换为BusinessUserVO
     * 
     * @param map
     * @return
     */
    public static BusinessUserVO map2BusinessUserVO(Map<String, Object> map) {
        // 转换UserVO
        UserUtil util = new UserUtil();
        UserVO userVO = util.map2UserVO(map);
        
        // 转换UserExtendVO
        UserExtendVO userExtendVO = map2UserExtendVO(map);
        
        BusinessUserVO businessUserVO = new BusinessUserVO();
        businessUserVO.setUserVO(userVO);
        businessUserVO.setUserExtendVO(userExtendVO);
        
        return businessUserVO;
    }
    
    /**
     * 将Map转换为UserExtendVO
     * 
     * @param map
     * @return
     */
    public static UserExtendVO map2UserExtendVO(Map<String, Object> map) {
        UserExtendVO userExtendVO = new UserExtendVO();
        if (map != null) {
            userExtendVO.setXh(MapUtils.getString(map, "XH"));
            userExtendVO.setYhid(MapUtils.getString(map, "YHID"));
            userExtendVO.setZhjgmc(MapUtils.getString(map, "ZHJGMC"));
            userExtendVO.setYhcz(MapUtils.getString(map, "YHCZ"));
            userExtendVO.setYzbm(MapUtils.getString(map, "YZBM"));
            userExtendVO.setDzyx(MapUtils.getString(map, "DZYX"));
            userExtendVO.setXzqhdmsheng(MapUtils.getString(map, "XZQHDMSHENG"));
            userExtendVO.setXzqhdmshi(MapUtils.getString(map, "XZQHDMSHI"));
            userExtendVO.setXzqhdmxian(MapUtils.getString(map, "XZQHDMXIAN"));
            userExtendVO.setYhjs(MapUtils.getString(map, "YHJS"));
            userExtendVO.setQyid(MapUtils.getString(map, "QYID"));
            userExtendVO.setFwjgid(MapUtils.getString(map, "FWJGID"));
        }
        return userExtendVO;
    }
    
    /**
     * 获取唯一的用户账号
     * 
     * @return
     */
    public static String getUserAccount() {
        // 随机生成账号
        String account = generateAccount();
        // 判断系统账号是否存在
        while (CollectionUtils.isNotEmpty(((IBusinessUserService) Toolkit.getSpringBean(BusinessUserServiceImpl.class)).getUserByAccount(account))) {
            account = generateAccount();
        }
        return account;
    }
    
    /**
     * 随机生成账号
     * 
     * @return
     */
    private static String generateAccount() {
        String str = "";
        Random rand = new Random();
        for (int i = 0; i < 6; i++) {
            int num = rand.nextInt(2);
            switch (num) {
            case 0:
                char c2 = (char) (rand.nextInt(26) + 'A');// 生成随机大写字母
                str += c2;
                break;
            case 1:
                str += rand.nextInt(10);// 生成随机数字
            }
        }
        return str;
    }
}
