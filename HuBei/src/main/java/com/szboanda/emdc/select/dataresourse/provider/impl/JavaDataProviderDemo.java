/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.emdc.select.dataresourse.provider.impl;

import java.security.ProviderException;
import java.util.Map;

import com.szboanda.emdc.select.dataresourse.provider.IJavaDataProvider;

/**
* @Title:
* @author  TODO
* @since   JDK1.6
* @history 2017年6月12日 TODO 新建
*/
public class JavaDataProviderDemo implements IJavaDataProvider {

    @Override
    public Object getData(Map<String, String> params, String serviceCode) throws ProviderException {
        //
        return "JavaDataProviderDemo";
    }

}
