package com.szboanda.emdc.select.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.szboanda.emdc.select.common.Constants;
import com.szboanda.emdc.select.dataresourse.ConnectionDB;

/**
 * @title:      选择功能数据库DAO服务类
 * @fileName:   SelectDAO.java
 * @copyright:  PowerData Software Co.,Ltd. Rights Reserved.
 * @company:    深圳市博安达信息技术股份有限公司
 * @author:     赵运
 * @date:       2017-08-15
 * @version:    V1.0
 */
public class SelectDAO {
    /**
     * 数据库操作
     */
    private final ConnectionDB db;

    /**
     * db构造方法
     * @param db
     */
    public SelectDAO(ConnectionDB db) {
        this.db = db;
    }

    /**
     * Object 对象转换为string
     * @param   Object
     * @return  String
     * 
     * @author  赵运
     * @date    2017-08-15
     */
    public static String toString(Object obj) {
        return obj == null ? "" : String.valueOf(obj);
    }

    /**
     * 获取点位模板列表
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryTemplates(Map<String, Object> queryInfo) throws Exception {
        if (queryInfo == null || queryInfo.isEmpty() || queryInfo.get("MBLX") == null || queryInfo.get("CJR") == null) {
            throw new Exception("缺失必要参数");
        }

        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer("SELECT * FROM T_JCSJZX_MBGL_XZMB ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL)");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String type = toString(queryInfo.get("MBLX"));
            if (StringUtils.isNotEmpty(type)) {
                params.add(type);
                sql.append(" AND MBLX = ?");
            }

            String creater = toString(queryInfo.get("CJR"));
            if (StringUtils.isNotEmpty(creater)) {
                params.add(creater);
                sql.append(" AND ( QXJB = 'public' OR CJR = ? )");
            } else {
                sql.append(" AND QXJB = 'public' ");
            }
        }

        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 添加模板
     * @param   dataInfo 待保存的参数
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public int addTemplate(Map<String, Object> dataInfo) throws Exception {
        List<Object> params = new ArrayList<Object>();

        if (dataInfo == null || dataInfo.isEmpty() || dataInfo.get("MBLX") == null || dataInfo.get("CJR") == null || dataInfo.get("NRBH") == null || dataInfo.get("NRMC") == null) {
            throw new Exception("缺失必要参数");
        }

        StringBuilder sql = new StringBuilder(128);
        sql.append("INSERT INTO T_JCSJZX_MBGL_XZMB (XH,MBLX,MBMC,NRBH,NRMC,QXJB,SFYX,BZ,CJR,CJSJ,XGR,XGSJ,ORGID)");
        sql.append("values");
        sql.append("(?, ?, ?, ?, ?, ?, 1, ?, ?,SYSDATE,?,SYSDATE,?)");
        params.add(toString(dataInfo.get("XH")));
        params.add(toString(dataInfo.get("MBLX")));
        params.add(toString(dataInfo.get("MBMC")));
        params.add(toString(dataInfo.get("NRBH")));
        params.add(toString(dataInfo.get("NRMC")));
        params.add(toString(dataInfo.get("QXJB")));
        params.add(toString(dataInfo.get("BZ")));
        params.add(toString(dataInfo.get("CJR")));
        params.add(toString(dataInfo.get("CJR")));
        params.add("");

        return this.db.executeUpdate(sql.toString(), params.toArray(new Object[params.size()]));
    }

    /**
     * 更新模板
     * @param   dataInfo 待保存的参数
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public int updateTemplate(Map<String, Object> dataInfo) throws Exception {
        List<Object> params = new ArrayList<Object>();

        if (dataInfo == null || dataInfo.isEmpty() || dataInfo.get("XH") == null || dataInfo.get("CJR") == null || dataInfo.get("NRBH") == null || dataInfo.get("NRMC") == null) {
            throw new Exception("缺失必要参数");
        }

        // 模板序号
        String id = toString(dataInfo.get("XH"));

        StringBuilder sql = new StringBuilder(128);
        sql.append("UPDATE T_JCSJZX_MBGL_XZMB SET XGSJ = SYSDATE ,XGR = ?");
        params.add(toString(dataInfo.get("CJR")));

        for (String key : dataInfo.keySet()) {
            if ("XH".equals(key) || "CJR".equals(key)) {
                continue;
            }

            sql.append(",").append(key).append(" = ? ");
            params.add(toString(dataInfo.get(key)));
        }
        sql.append(" WHERE XH = ?");
        params.add(id);
        return this.db.executeUpdate(sql.toString(), params.toArray(new Object[params.size()]));
    }

    /**
     * 删除模板
     * @param   dataInfo 条件
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public int deleteTemplate(Map<String, Object> dataInfo) throws Exception {
        List<Object> params = new ArrayList<Object>();
        if (dataInfo == null || dataInfo.isEmpty() || dataInfo.get("XH") == null) {
            throw new Exception("缺失必要参数");
        }

        String id = toString(dataInfo.get("XH"));// 模板序号
        StringBuilder sql = new StringBuilder();
        sql.append("DELETE FROM T_JCSJZX_MBGL_XZMB WHERE XH = ?");
        params.add(id);
        return this.db.executeUpdate(sql.toString(), params.toArray(new Object[params.size()]));
    }

    /**
     * 获取地表水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryWaterPoints(Map<String, Object> queryInfo) throws Exception {
        if (queryInfo == null || queryInfo.isEmpty() || queryInfo.get("SJLX") == null) {
            throw new Exception("缺失必要参数");
        }

        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(
                " DWDM,CDBH,CDMC,DXMC,XZQDM,SSXZQ,JGJB,DECODE(JGJB,'1','国控','2','省控','3','市控','4','国控跨省界','5','国控跨市界') JGJBMC,SSLY,LYJBXX.LYMC SSLYMC,SSSX, SXJBXX.SXMC SSSXMC,JCD.JCFLBS,JCD.JCFLMC,JCD.DMXZBS ");
        sql.append(" FROM T_JCSJZX_DBS_JCD JCD ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_LYJBXX LYJBXX ON JCD.SSLY = LYJBXX.LYDM ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_SXJBXX SXJBXX ON JCD.SSSX = SXJBXX.SXDM  ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL) ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String dataType = toString(queryInfo.get("SJLX"));

            if (dataType.contains(Constants.HL_CD) && dataType.contains(Constants.HK_CD)) {
                sql.append(" AND JCD.JCFLBS IN ('HL','HK') AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HL_CD)) {
                sql.append(" AND JCD.JCFLBS = 'HL' AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HK_CD)) {
                sql.append(" AND JCD.JCFLBS = 'HK' AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HLYYS_CD) && dataType.contains(Constants.HKYYS_CD) && dataType.contains(Constants.DXSYYS_CD)) {
                sql.append(" AND JCD.JCFLBS IN ('HLYYS','HKYYS','DXSYYS') AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HLYYS_CD) && dataType.contains(Constants.HKYYS_CD)) {
                sql.append(" AND JCD.JCFLBS IN ('HLYYS','HKYYS') AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HLYYS_CD) && dataType.contains(Constants.DXSYYS_CD)) {
                sql.append(" AND JCD.JCFLBS IN ('HLYYS','DXSYYS') AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HKYYS_CD) && dataType.contains(Constants.DXSYYS_CD)) {
                sql.append(" AND JCD.JCFLBS IN ('HKYYS','DXSYYS') AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HLYYS_CD)) {
                sql.append(" AND JCD.JCFLBS = 'HLYYS' AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.HKYYS_CD)) {
                sql.append(" AND JCD.JCFLBS = 'HKYYS' AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else if (dataType.contains(Constants.DXSYYS_CD)) {
                sql.append(" AND JCD.JCFLBS = 'DXSYYS' AND INSTR(JCD.DMXZBS,'DBSCG') > 0 ");
            } else {
                sql.append(" AND 1=2");
            }

            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCD.JCNF = ? ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取对象信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryWaterObjects(Map<String, Object> queryInfo) throws Exception {
        if (queryInfo == null || queryInfo.isEmpty() || queryInfo.get("SJLX") == null) {
            throw new Exception("缺失必要参数");
        }

        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" JCDX.BH,JCDX.HLDM,JCDX.DXMC,JCDX.SSLY,LYJBXX.LYMC SSLYMC,JCDX.SSSX,SXJBXX.SXMC SSSXMC,JCFLBS ");
        sql.append(" FROM T_JCSJZX_DBS_JCDX JCDX ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_LYJBXX LYJBXX ON JCDX.SSLY = LYJBXX.LYDM ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_SXJBXX SXJBXX ON JCDX.SSSX = SXJBXX.SXDM ");
        sql.append(" WHERE 1 = 1 ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String dataType = toString(queryInfo.get("SJLX"));
            if (dataType.contains(Constants.HL_DX) && dataType.contains(Constants.HK_DX)) {
                sql.append(" AND JCDX.JCFLBS = 'HL' OR JCDX.JCFLBS = 'HK' ");
            } else if (dataType.contains(Constants.HL_DX)) {
                sql.append(" AND JCDX.JCFLBS = 'HL' ");
            } else if (dataType.contains(Constants.HK_DX)) {
                sql.append(" AND JCDX.JCFLBS = 'HK' ");
            } else {
                sql.append(" AND 1 = 2 ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取环境空气点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryAirPoints(Map<String, Object> queryInfo) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" CDBH,CDMC,CDDM,XZQDM,SSXZQ,JGJB,DECODE(JGJB,'1','国控'，'2','省控','3','市控') JGJBMC ");
        sql.append(" FROM T_JCSJZX_HJKQ_JCD ");
        sql.append(" WHERE 1 = 1  ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCNF = ?");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取地下水点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryGroundWaterPoints(Map<String, Object> queryInfo) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" JCD.CDBH,JCD.CDMC,JCD.JCNF,JCD.SSXZQ,JCD.XZQDM,JCD.SYDMC,JCD.SSLY,LYJBXX.LYMC,JCD.SSSX,SXJBXX.SXMC SSSXMC,JCD.SSST,JCD.SSSTMC,JCD.DWDM ");
        sql.append(" FROM T_JCSJZX_DXS_JCD JCD ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_LYJBXX LYJBXX ON JCD.SSLY = LYJBXX.LYDM ");
        sql.append(" LEFT JOIN T_JCSJZX_SHJ_SXJBXX SXJBXX ON JCD.SSSX = SXJBXX.SXDM ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL) ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCD.JCNF = ? ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取功能区噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryFunctionNoisePoints(Map<String, Object> queryInfo) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM T_JCSJZX_GNQZS_JCD ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL) ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCND = ? ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取道路交通噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryRoadTrafficNoisePoints(Map<String, Object> queryInfo) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM T_JCSJZX_DLJTZS_JCD ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL) ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCNF = ? ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }

    /**
     * 获取区域环境噪声点位信息
     * @param   queryInfo 查询条件map
     * @author  赵运
     * @throws  Exception 
     * @date    2017-08-15
     */
    public List<Map<String, Object>> queryAreaNoisePoints(Map<String, Object> queryInfo) throws Exception {
        List<String> params = new ArrayList<String>();
        StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ");
        sql.append(" * ");
        sql.append(" FROM T_JCSJZX_QYHJZS_JCD ");
        sql.append(" WHERE (SFYX = 1 OR SFYX IS NULL) ");

        if (queryInfo != null && !queryInfo.isEmpty()) {
            String year = toString(queryInfo.get("NF"));
            if (StringUtils.isNotEmpty(year)) {
                params.add(year);
                sql.append(" AND JCND = ? ");
            }
        }
        return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));
    }
    /**
     * 获取监测项目
     * @param queryInfo
     * @return
     * @throws Exception
     */   
    public List<Map<String, Object>> queryMonitorItem(Map<String, Object> queryInfo)throws Exception{
        List<String> params = new ArrayList<String>();
    	StringBuffer sql = new StringBuffer();
        sql.append(" SELECT ZB_ID,dbo.getFirstLetter(ZBMC) MC,ZBMC,ZBBH");
        sql.append(" FROM HB_BZ_XMZB XMZB ");
        if (queryInfo != null && !queryInfo.isEmpty()) {
        	sql.append(" WHERE ");
            String zblx = toString(queryInfo.get("ZBLX"));
            if (StringUtils.isNotEmpty(zblx)) {
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                params.add(zblx);
                sql.append("(XMZB.ZBLX=? OR XMZB.ZBLX2=? " + 
                		   " OR XMZB.ZBLX3=? OR XMZB.ZBLX4=? OR XMZB.ZBLX5=?" + 
                		   " OR XMZB.ZBLX6=? OR XMZB.ZBLX7=? OR XMZB.ZBLX8=?" + 
                		   " OR XMZB.ZBLX9=? OR XMZB.ZBLX10=? )");
            }
        }   
        sql.append(" ORDER BY ZBMC ");
		return this.db.excuteQuery(sql.toString(), params.toArray(new String[params.size()]));  	
    }
}
