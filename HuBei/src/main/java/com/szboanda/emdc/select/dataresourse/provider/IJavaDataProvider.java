/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.emdc.select.dataresourse.provider;

import java.security.ProviderException;
import java.util.Map;

/**
* @Title: java数据提供者
* @author  zhangsheng
* @since   JDK1.6
* @history 2017年6月8日 zhangsheng 新建
*/
public interface IJavaDataProvider {

    /**
     * 获取数据
     * @param params http请求中的参数
     * @param serviceCode 服务代码
     * @return
     * @throws ProviderException
     */
    Object getData(Map<String, String> params, String serviceCode);

}
