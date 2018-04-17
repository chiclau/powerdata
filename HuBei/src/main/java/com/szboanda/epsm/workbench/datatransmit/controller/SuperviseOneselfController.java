/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.workbench.datatransmit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import com.szboanda.epsm.workbench.datatransmit.exception.SuperviseOneselfException;
import com.szboanda.platform.common.base.BaseController;

/**
* @Title:  企业自行监测信息
* @author  陈鹏
* @since   JDK1.6
* @history 2017年10月12日 新建
*/
@Controller("/superviseOneselfController")
@RequestMapping("/epsm/workbench/datatransmit/controller/superviseoneselfcontroller")
public class SuperviseOneselfController extends BaseController {

    @RequestMapping("/superviseoneselfpage")
    public String superviseOneselfPage() throws SuperviseOneselfException {
        this.setToken();
        return "epsm/workbench/datatransmit/superviseoneself";
    }
}
