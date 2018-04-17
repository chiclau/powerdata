package com.szboanda.epsm.workbench.datareportnotice.service.impl;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sun.org.apache.bcel.internal.generic.NEW;
import com.szboanda.epsm.common.base.BaseBusinessService;
import com.szboanda.epsm.common.utils.BusinessUserHelper;
import com.szboanda.epsm.workbench.datareportnotice.dao.DataReportNoticeDao;
import com.szboanda.epsm.workbench.datareportnotice.exception.DataReportNoticeException;
import com.szboanda.epsm.workbench.datareportnotice.service.IDataReportNoticeService;
import com.szboanda.platform.common.utils.DateUtils;
import com.szboanda.platform.common.utils.LoggerUtil;
import com.szboanda.platform.common.utils.MapUtils;
import com.szboanda.platform.common.utils.SQLUtils;
import com.szboanda.platform.common.utils.StringUtils;
import com.szboanda.platform.common.utils.Toolkit;

import net.sf.json.JSONArray;

/**
 * @title  数据催报Service
 * @author 钟声辉 
 * @date 创建时间：2017年10月19日 下午7:01:32 
 * @version 1.0
 */
@Service
public class DataReportNoticeServiceImpl extends BaseBusinessService implements IDataReportNoticeService {

	/**
	 * 催报ID
	 */
	private static final String UUID ="UUID";
	/**
	 * 发送催报对象
	 */
	private static final String FSCBDX ="FSCBDX";
	/**
	 * 发送催报对象名称
	 */
	private static final String FSCBDXMC ="FSCBDXMC";	
	
	/**
	 * 被催报对象
	 */
	private static final String BCBDX ="BCBDX";	
	
	/**
	 * 被催报对象名称
	 */
	private static final String BCBDXMC ="BCBDXMC";	
	
	/**
	 * 省
	 */
	private static final String XZQHDMSHENG ="SHENG";		
	
	/**
	 * 市
	 */
	private static final String XZQHDMSHI ="SHI";		
	
	/**
	 * 县
	 */
	private static final String XZQHDMXIAN ="XIAN";
	
	/**
	 * 用户级别
	 */
	private static final String YHJB ="YHJB";
	
	/**
	 * 用户级别LIST
	 */
	private static final List<String> JB_LIST =Arrays.asList("1","2","3","9");
	
	/**
	 * 数据催报dao
	 */
	@Autowired
	DataReportNoticeDao  dataReportNoticeDao;
	
	/**
	 * 查询催报通知列表(催报本人的)
	 * @param params
	 * @return
	 */	
	@Override
	public List<Map<String, Object>> queryMyNotice(Map<String, Object> params){
		try {
			params.put(BCBDX,this.getBusinessCurrUser().getUserExtendVO().getYhid());			
			if(MapUtils.getString(params, "CBZT")!="") {
				SQLUtils.fillLike(params, "CBZT");
			}
			if(MapUtils.getString(params, "CBNR")!="") {
				SQLUtils.fillLike(params, "CBNR");
			}			
			return dataReportNoticeDao.queryDataReportNotice(params);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询催报通知列表(催报本人的)异常，params：" + params, e);
            throw new DataReportNoticeException("查询催报通知列表(催报本人的)异常，params：" + params, e);
        }

	}
	
	/**
	 * 查询催报通知列表(本人催报别人的)
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryOtherNotice(Map<String, Object> params) {
		try {
			params.put(FSCBDX,this.getBusinessCurrUser().getUserExtendVO().getYhid());
			if(MapUtils.getString(params, "CBZT")!="") {
				SQLUtils.fillLike(params, "CBZT");
			}
			if(MapUtils.getString(params, "CBNR")!="") {
				SQLUtils.fillLike(params, "CBNR");
			}			
			return dataReportNoticeDao.queryDataReportNotice(params);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询催报通知列表(本人催报别人的)异常，params：" + params, e);
            throw new DataReportNoticeException("查询催报通知列表(本人催报别人的)异常，params：" + params, e);
        }
	}
	
	/**
	 * 查询单个催报信息
	 */
	@Override
	public Map<String, Object> queryOtherNoticeById(String id) {
		try {
			return dataReportNoticeDao.queryOtherNoticeById(id);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询单个催报信息异常，id：" + id, e);
            throw new DataReportNoticeException("查询单个催报信息异常，id：" + id, e);
        }		
	}
	
	/**
	 * 保存催报信息
	 */
	@Override
	public int saveDataReportNotice(Map<String, Object> params) {
		try {
			String ids="";
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			//催报记录	
			List<Map<String, Object>> list_cb =null; new ArrayList<Map<String, Object>>();	
			//信息提醒
			List<Map<String, Object>> list_xx =null; new ArrayList<Map<String, Object>>();	
	
			Map<String, Object> param=null;
			if(StringUtils.isNotEmpty(params.get("ids"))) {
				list_cb = new ArrayList<Map<String, Object>>();
				list_xx = new ArrayList<Map<String, Object>>();	
				
				ids=params.get("ids").toString();
				String[] id=ids.split(",");
				boolean flag=false;
				String[] qyid= {};
				if(StringUtils.isNotEmpty(params.get("qyids"))) {
					qyid=params.get("qyids").toString().split(",");
					flag=true;
				}
				for (int i = 0; i < id.length; i++) {
					String noticeId=Toolkit.getID();
					param=new HashMap<String, Object>();
					param.put(UUID,noticeId);
					param.put("CBZT",params.get("CBZT"));
					param.put("CBNR",params.get("CBNR"));
					param.put(FSCBDX,this.getBusinessCurrUser().getUserExtendVO().getYhid());
					param.put(FSCBDXMC,this.getBusinessCurrUser().getUserVO().getYhmc());
					param.put(BCBDX,id[i]);
					param.put(BCBDXMC,BusinessUserHelper.getUser(id[i]).getUserVO().getYhmc());
					param.put(XZQHDMSHENG,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
					param.put(XZQHDMSHI,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
					param.put(XZQHDMXIAN,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
					param.put("CREATETIME",sdf.format(new Date()));
					param.put("MODIFYTIME",sdf.format(new Date()));	
					if(flag) {
						param.put("QYID",qyid[i]);
					}
					//信息提醒
					param.put("ID", noticeId);
					param.put("BT", params.get("CBZT"));
					param.put("NR", params.get("CBNR"));
					param.put("SENDER", this.getBusinessCurrUser().getUserExtendVO().getYhid());
					param.put("JB", 1);//级别
					param.put("FL", 1);//分类
					param.put("RECEIVER", id[i]);
					param.put("SENDTIME", sdf.format(new Date()));//
					param.put("STATUS", 0);//状态
					list_xx.add(param);
					list_cb.add(param);
				}
				dataReportNoticeDao.insertInformationReminderBatch(list_xx);
				return dataReportNoticeDao.insertDataReportNoticeBatch(list_cb);				
			}else{
				list_xx = new ArrayList<Map<String, Object>>();	
				String noticeId=Toolkit.getID();
				params.put(UUID,noticeId);
				params.put(FSCBDX,this.getBusinessCurrUser().getUserExtendVO().getYhid());
				params.put(FSCBDXMC,this.getBusinessCurrUser().getUserVO().getYhmc());
				params.put(BCBDXMC,BusinessUserHelper.getUser(params.get(BCBDX).toString()).getUserVO().getYhmc());
				params.put(XZQHDMSHENG,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng());
				params.put(XZQHDMSHI,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi());
				params.put(XZQHDMXIAN,this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian());
				params.put("CREATETIME",sdf.format(new Date()));
				params.put("MODIFYTIME",sdf.format(new Date()));
				//信息提醒
				params.put("ID", noticeId);
				params.put("BT", params.get("CBZT"));
				params.put("NR", params.get("CBNR"));
				params.put("SENDER", this.getBusinessCurrUser().getUserExtendVO().getYhid());
				params.put("JB", 1);//级别
				params.put("FL", 1);//分类
				params.put("RECEIVER", params.get(BCBDX));
				params.put("SENDTIME", sdf.format(new Date()));//
				params.put("STATUS", 0);//状态	
				list_xx.add(params);
				dataReportNoticeDao.insertInformationReminderBatch(list_xx);
				return dataReportNoticeDao.insertDataReportNotice(params);
			}
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "保存催报信息异常，params：" + params, e);
            throw new DataReportNoticeException("保存催报信息异常，params：" + params, e);
        }	
	}
	
	/**
	 * 查询可以催报的用户列表
	 * @param params
	 * @return
	 */
	@Override
	public List<Map<String, Object>> queryDataReportNoticeUser(Map<String, Object> params) {
		try {			
			String yhjb =JB_LIST.get(JB_LIST.indexOf(this.getBusinessCurrUser().getUserVO().getYhjb()+"")+1);
			params.put(YHJB, yhjb);
			params.put(XZQHDMSHENG,StringUtils.isEmpty(MapUtils.getString(params, XZQHDMSHENG))? this.getBusinessCurrUser().getUserExtendVO().getXzqhdmsheng():MapUtils.getString(params,XZQHDMSHENG));
			params.put(XZQHDMSHI,StringUtils.isEmpty(MapUtils.getString(params, XZQHDMSHI))? this.getBusinessCurrUser().getUserExtendVO().getXzqhdmshi():MapUtils.getString(params,XZQHDMSHI));
			params.put(XZQHDMXIAN,StringUtils.isEmpty(MapUtils.getString(params, XZQHDMXIAN))? this.getBusinessCurrUser().getUserExtendVO().getXzqhdmxian():MapUtils.getString(params,XZQHDMXIAN));				
			if(MapUtils.getString(params, "YHMC")!="") {
				SQLUtils.fillLike(params, "YHMC");
			}
			if(MapUtils.getString(params, "QYMC")!="") {
				SQLUtils.fillLike(params, "QYMC");
			}
			return dataReportNoticeDao.queryDataReportNoticeUser(params);
        } catch (Exception e) {
            LoggerUtil.error(this.getClass(), "查询可以催报的用户列表异常，params：" + params, e);
            throw new DataReportNoticeException("查询可以催报的用户列表异常，params：" + params, e);
        }			

	}

}
