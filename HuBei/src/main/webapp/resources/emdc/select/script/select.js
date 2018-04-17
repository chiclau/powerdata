/**
 * 定义通用选择对象
 */
var select = {
	/**
	 *兼容平台3.0，4.0
	 */
	ctx : top.window.WEB_ROOT || ctx,
	
	/**
	 * 选择功能
	 * fn		   	回调函数
	 * 					返回数据：[数据序号串,点位或对象名称串,对象序号串或点位代码串,父级名称串]
	 * 					各数据均为按“,”分割的数据串
	 * initIds    	需选中的值	
	 * 					为对象或者测点的id值串，用‘,’分割，即该方法回调方法返回结果数组的第一个值returnValue[0]
	 * params     	条件参数对象，默认包含以下数据key
	 * 					params.dataType		数据类型,支持多种数据类型，用“,”分割，默认只允许地表水测点、对象允许组合
	 * 					params.lander		操作人：必填字段。用以控制是否有权限编辑、查看、删除、新增模板
	 * 					params.year			年份：选填字段，用以获取指定年份点位或对象数据，为空则查全部
	 * 					params.groupParams	分组字段：选填字段，用以规定界面数据分组规则，不填系统会按照默认规则分组，支持自定义。前提是分组参数中该的字段值在数据查询中已查询并组装
	 * 										分组数据由三个数据组成，分组值字段,按XX分组名称,分组后组名称展示字段支持多个分组，用#连接如 : CSDM,按城市,CSMC#LYDM,按流域,LYMC
	 * 											上面例子自定义了两个分组：
	 * 												1、以CSDM作为分组字段，分组的名称在页面显示“按城市”，各小组展示的名称是以LYDM字段数据
	 * 												2、以LYDM作为分组字段，分组的名称在页面显示“按流域”，各小组展示的名称是以LYMC字段数据
	 * 				
	 * 
	 */
	selectCommon : function(fn, initIds, params,type) {
		params = $.isPlainObject(params) ? params : {};
		if(!params || !params.dataType || !params.lander){
			layer.msg('缺失数据类型、操作人必填数据！', {time: 2000, icon:7});
			return;
		}
		var url = this.ctx 
				+ '/resources/emdc/select/index.jsp' 
				+ "?params=" + escape(JSON.stringify(params))
				+ "&initIds=" + initIds
				+ "&type="+type;
		var index = Layer.openDialogWithCallBack("选择功能",url,"1075px","600px",function(){
			var data = top.window.returnValue;
			//清空returnValue
			top.window.returnValue = null;
			if($.isFunction(fn)){
				fn(data);
			}
		})
		top.window.index = index;
	}
};