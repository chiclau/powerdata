/******************************************************************************
* Copyright (C) 2017 ShenZhen Powerdata Information Technology Co.,Ltd
* All Rights Reserved.
* 本软件为深圳博安达开发研制。未经本公司正式书面同意，其他任何个人、团体不得使用、
* 复制、修改或发布本软件.
*****************************************************************************/

package com.szboanda.epsm.dataacquisition.user.model;

import java.io.Serializable;

/**
* @Title:  用户扩展信息VO
* @author  蔡楚涛
* @since   JDK1.6
* @history 2017年10月19日 蔡楚涛 新建
*/
public class UserExtendVO implements Serializable {

    /**
     * 序列化号
     */
    private static final long serialVersionUID = -190411664891854470L;
    
    /**
     * 序号
     */
    private String xh;
    
    /**
     * 用户ID,与T_ADMIN_RMS_YH的主键对应
     */
    private String yhid;
    
    /**
     * 综合机构名称
     */
    private String zhjgmc;
    
    /**
     * 用户传真
     */
    private String yhcz;
    
    /**
     * 邮政编码
     */
    private String yzbm;
    
    /**
     * 电子邮箱
     */
    private String dzyx;
    
    /**
     * 行政区划省
     */
    private String xzqhdmsheng;
    
    /**
     * 行政区划市
     */
    private String xzqhdmshi;
    
    /**
     * 行政区划县
     */
    private String xzqhdmxian;
    
    /**
     * 用户角色
     */
    private String yhjs;
    
    /**
     * 企业ID
     */
    private String qyid;
    
    /**
     * 服务机构ID
     */
    private String fwjgid;

    /**
     * @return the xh
     */
    public String getXh() {
        return xh;
    }

    /**
     * @param xh the xh to set
     */
    public void setXh(String xh) {
        this.xh = xh;
    }

    /**
     * @return the yhid
     */
    public String getYhid() {
        return yhid;
    }

    /**
     * @param yhid the yhid to set
     */
    public void setYhid(String yhid) {
        this.yhid = yhid;
    }

    /**
     * @return the zhjgmc
     */
    public String getZhjgmc() {
        return zhjgmc;
    }

    /**
     * @param zhjgmc the zhjgmc to set
     */
    public void setZhjgmc(String zhjgmc) {
        this.zhjgmc = zhjgmc;
    }

    /**
     * @return the yhcz
     */
    public String getYhcz() {
        return yhcz;
    }

    /**
     * @param yhcz the yhcz to set
     */
    public void setYhcz(String yhcz) {
        this.yhcz = yhcz;
    }

    /**
     * @return the yzbm
     */
    public String getYzbm() {
        return yzbm;
    }

    /**
     * @param yzbm the yzbm to set
     */
    public void setYzbm(String yzbm) {
        this.yzbm = yzbm;
    }

    /**
     * @return the dzyx
     */
    public String getDzyx() {
        return dzyx;
    }

    /**
     * @param dzyx the dzyx to set
     */
    public void setDzyx(String dzyx) {
        this.dzyx = dzyx;
    }

    /**
     * @return the xzqhdmsheng
     */
    public String getXzqhdmsheng() {
        return xzqhdmsheng;
    }

    /**
     * @param xzqhdmsheng the xzqhdmsheng to set
     */
    public void setXzqhdmsheng(String xzqhdmsheng) {
        this.xzqhdmsheng = xzqhdmsheng;
    }

    /**
     * @return the xzqhdmshi
     */
    public String getXzqhdmshi() {
        return xzqhdmshi;
    }

    /**
     * @param xzqhdmshi the xzqhdmshi to set
     */
    public void setXzqhdmshi(String xzqhdmshi) {
        this.xzqhdmshi = xzqhdmshi;
    }

    /**
     * @return the xzqhdmxian
     */
    public String getXzqhdmxian() {
        return xzqhdmxian;
    }

    /**
     * @param xzqhdmxian the xzqhdmxian to set
     */
    public void setXzqhdmxian(String xzqhdmxian) {
        this.xzqhdmxian = xzqhdmxian;
    }

    /**
     * @return the yhjs
     */
    public String getYhjs() {
        return yhjs;
    }

    /**
     * @param yhjs the yhjs to set
     */
    public void setYhjs(String yhjs) {
        this.yhjs = yhjs;
    }

    /**
     * @return the qyid
     */
    public String getQyid() {
        return qyid;
    }

    /**
     * @param qyid the qyid to set
     */
    public void setQyid(String qyid) {
        this.qyid = qyid;
    }

    /**
     * @return the fwjgid
     */
    public String getFwjgid() {
        return fwjgid;
    }

    /**
     * @param fwjgid the fwjgid to set
     */
    public void setFwjgid(String fwjgid) {
        this.fwjgid = fwjgid;
    }
    
}
