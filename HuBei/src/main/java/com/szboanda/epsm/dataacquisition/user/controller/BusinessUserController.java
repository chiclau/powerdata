/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.common.base.BaseBusinessController;
import com.szboanda.epsm.common.constants.CommonBussinessConstants;
import com.szboanda.epsm.common.utils.BusinessUserHelper;
import com.szboanda.epsm.dataacquisition.user.exception.BusinessUserException;
import com.szboanda.epsm.dataacquisition.user.service.IBusinessUserService;
import com.szboanda.platform.common.component.datahelper.DataHelper;
import com.szboanda.platform.common.framext.mybatits.PlatformPageInfo;
import com.szboanda.platform.common.utils.MapUtils;

/**
* @Title:  用户模块Controller类
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月10日 蔡楚涛 新建
*/
@Controller
@RequestMapping("/ep/epsm/dataacquisition/user/businessusercontroller")
public class BusinessUserController extends BaseBusinessController {

    /**
     * 用户模块Service实现
     */
    @Autowired
    private IBusinessUserService businessUserService;

    /**
     * 跳转到管理账号分配页面
     * 
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/systemusermanage")
    public String systemUserManage() throws BusinessUserException {
        this.setToken();
        return "epsm/dataacquisition/user/systemusermanage";
    }

    /**
     * 跳转到管理用户详情页面
     * 
     * @param model
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/systemuserdetail/{operationType}/{account}")
    public String systemUserDetail(@PathVariable String operationType, @PathVariable String account, Model model) throws BusinessUserException {
        this.setToken();
        
        // 新增类型操作，则需要生成系统账号
        if ("add".equals(operationType)) {
            // 生成唯一的系统账号
            account = BusinessUserHelper.getUserAccount();
        }
        
        // 传递系统账号
        model.addAttribute("XTZH", account);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        return "epsm/dataacquisition/user/systemuserdetail";
    }
    
    /**
     * 添加管理用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/addsystemuser")
    public @ResponseBody Map<String, Object> addSystemUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        // 判断是否存在相同行政区划的用户
        if (businessUserService.isExistSameRegionUser(param)) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("existRegion", true);
            return result;
        } else {
            return this.getResultMap(businessUserService.addUser(param));
        }
    }
    
    /**
     * 查询用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/findsystemuser")
    public @ResponseBody PageInfo<Map<String, Object>> findSystemUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        return new PlatformPageInfo<Map<String, Object>>(businessUserService.findSystemUser(param));
    }
    
    /**
     * 根据用户ID删除用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/deluser")
    public @ResponseBody Map<String, Object> delUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        return this.getResultMap(businessUserService.delUser(MapUtils.getString(param, "YHID")));
    }
    
    /**
     * 根据系统账号查询用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/getuserbyaccount")
    public @ResponseBody Map<String, Object> getUserByAccount(@RequestBody Map<String, Object> param) throws BusinessUserException {
        return businessUserService.getUserByAccount(MapUtils.getString(param, "XTZH"));
    }
    
    /**
     * 更新用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/updateuser")
    public @ResponseBody Map<String, Object> updateUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        String isCheckRegion = MapUtils.getString(param, "isCheckRegion");
        if ("true".equalsIgnoreCase(isCheckRegion) && businessUserService.isExistSameRegionUser(param)) {
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("existRegion", true);
            return result;
        }
        
        List<String> delRoles = new ArrayList<String>();
        delRoles.add(CommonBussinessConstants.ROLE_HBYH_HBTB);
        delRoles.add(CommonBussinessConstants.ROLE_HBYH_JCZTB);
        delRoles.add(CommonBussinessConstants.ROLE_HBYH_FASH);
        delRoles.add(CommonBussinessConstants.ROLE_HBYH_JDXJCSH);
        delRoles.add(CommonBussinessConstants.ROLE_HBYH_CK);
        param.put("delRoles", delRoles);
        
        return this.getResultMap(businessUserService.updateUser(param));
    }
    
    /**
     * 跳转到本级环保用户管理页面
     * 
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/sameregionusermanage")
    public String sameRegionUserManage() throws BusinessUserException {
        this.setToken();
        return "epsm/dataacquisition/user/sameregionusermanage";
    } 
    
    /**
     * 查询本级用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/findsameregionuser")
    public @ResponseBody PageInfo<Map<String, Object>> findSameRegionUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        DataHelper.startPage(this.getPageNum(param), this.getPageSize(param));
        param.put("XZQHDMSHENG", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
        param.put("XZQHDMSHI", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
        param.put("XZQHDMXIAN", this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
        return new PlatformPageInfo<Map<String, Object>>(businessUserService.findSameRegionUser(param));
    }
    
    /**
     * 跳转到管理用户详情页面
     * 
     * @param model
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/sameregionuserdetail/{operationType}/{account}")
    public String sameRegionUserDetail(@PathVariable String operationType, @PathVariable String account, Model model) throws BusinessUserException {
        this.setToken();
        
        // 新增类型操作，则需要生成系统账号
        if ("add".equals(operationType)) {
            // 生成唯一的系统账号
            account = BusinessUserHelper.getUserAccount();
        }
        
        // 传递系统账号
        model.addAttribute("XTZH", account);
        
        // 传递操作类型, 操作类型包含read,add,edit
        model.addAttribute("operationType", operationType);
        return "epsm/dataacquisition/user/sameregionuserdetail";
    }
    
    /**
     * 添加本级用户
     * 
     * @param param
     * @return
     * @throws BusinessUserException
     */
    @RequestMapping("/addsameregionuser")
    public @ResponseBody Map<String, Object> addSameRegionUser(@RequestBody Map<String, Object> param) throws BusinessUserException {
        if (!this.validToken(param)) {
            return this.getFailMap();
        }
        
        return this.getResultMap(businessUserService.addUser(param));
    }

}
