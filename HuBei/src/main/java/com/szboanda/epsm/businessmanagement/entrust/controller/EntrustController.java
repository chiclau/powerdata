/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.businessmanagement.entrust.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.szboanda.epsm.businessmanagement.entrust.exception.EntrustException;
import com.szboanda.epsm.businessmanagement.entrust.service.IEntrustService;
import com.szboanda.epsm.common.base.BaseBusinessController;

/**
* @Title:  委托机构统计
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月24日 新建
*/
@Controller
@RequestMapping("/ep/epsm/businessmanagement/entrust/controller/entrustcontroller")
public class EntrustController extends BaseBusinessController {

    @Autowired
    private IEntrustService entrustService;
    
    /** 
    * @Title: MessageRemindPage 
    * @Description: 返回委托机构统计
    * @param @return
    * @param @throws MessageRemindException
    * @return 
    * String
    * @throws 
    */
    @RequestMapping("/page")
    public String entrustPage() throws EntrustException {
        this.setToken();
        return "epsm/businessmanagement/entrust/entrustlist";
    }
    
     /** 
    * @Title: findMessageRemind 
    * @Description: 查询信息提醒列表
    * @param @param map
    * @param @return
    * @return 
    * PageInfo<Map<String,Object>>
    * @throws 
    */
    @RequestMapping("/findentrust")
     public @ResponseBody PageInfo<Map<String,Object>> findEntrust(@RequestBody Map<String,Object> map){
         return entrustService.findEntrust(map);
     }
}
