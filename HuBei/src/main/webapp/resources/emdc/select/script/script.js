var allData = null;          //用于保存当前所有数据列表
var allTemplateData = null;  //用于保存当前所有模板内容

var isSelectData = [];       //已选择的所有数据
var isSelectDataIds = [];    //已选择的所有数据的id字符串数组
var NotTemplateAllSelectedIds = []; //用于缓存在 所有断面 tab下已经选择的数据数组
var flag = false;			//用于限制某些函数只能调用
//获取url参数
var urlParams = "";			//json串参数
var initIds = '';			//初始化选中值id
//单选还是多选  radio/checkbox
var inputType = "radio";//默认单选

$(function () {
	//初始化页面
	//需要配置请求数据地址以及参数，然后执行 dataSelect.queryData 方法；
	//需要配置另存为模板时的请求参数 (dataSelect.confirmAddNewTemplate())
	//需要配置保存编辑模板时的请求参数 (dataSelect.confirmEditTemplate())
	//需要配置删除模板的请求参数 (dataSelect.deleteTemplate())
	dataSelect.initUrlParams();
	dataSelect.queryData();
	dataSelect.initSelectDatas();
});

/**
 * 定义通用选择对象
 */
var dataSelect = {
	/**
	 * 选中url参数中需选中的数据
	 */
	initSelectDatas : function(){
		var initSelectData = [];
		//组装对象格式
		if(initIds){
			var arrIds = initIds.split(',');
			for(var index in arrIds){
				for (var i = 0;i < allData.dataList.length; i++){
					if(allData.dataList[i].code == arrIds[index]){
						initSelectData.push(allData.dataList[i]);
						break;
					}
				}
			}
		}
		if(initSelectData && initSelectData.length > 0 ){
			dataSelect.appendSelectedList(initSelectData, true);
			dataSelect.checkedBoxByisSelected(isSelectDataIds,allData);
			dataSelect.checkOpenOrCloseGroup();
		}
	},
	/**
	 * 页面初始化所需要执行的函数
	 */
	init: function () {
		this.allSelectClearEvent(); //清空所有已选择函数
		this.radioChangeEvent();    //筛选方式改变事件
		this.searchChangeEvent();   //输入框过滤查询
		Util.setHeight();
		dataSelect.initTemplate(allTemplateData);
		dataSelect.commonPageEvent(); //初始化一些通用固定的点击事件
	},
	/**
	 * 循环遍历模板数据,并渲染到选择框中
	 */
	initTemplate: function (templateLists) {
		var templateListsLength = templateLists.length;
		var showListArray = [
				'<li class="on" id="allTemplate">所有</li>'
			];
		for (var i = 0;i < templateListsLength;i++){
			showListArray.push(
				'<li data-name="'+templateLists[i].name+'" id="'+templateLists[i].id+'" isPublic="'+templateLists[i].isPublic+'" createUser="'+templateLists[i].createUser+'">'+templateLists[i].name+'</li>'
			)
		}
		document.getElementById('templateLists').innerHTML = showListArray.join("");
		this.initTemplateEvent();
	},
	/**
	 * 给左侧模板列表数据增加事件
	 */
	initTemplateEvent: function () {

		$('#templateLists li').unbind('click').on("click", function(event) {
			$(this).addClass('on').siblings().removeClass('on');

			var currentTemplateId = $(this).attr("id");
			var currentTempalteCode = [];
			var templateSelectDatasId = [];
			var templateSelectData = [];

			if ($(this).attr("id") === "allTemplate"){
				$(".save").show();
				$(".edit").hide();
				for (var j = 0;j < NotTemplateAllSelectedIds.length; j++){
					for (var k = 0;k < allData.dataList.length; k++){
						if(allData.dataList[k].id == NotTemplateAllSelectedIds[j]){
							templateSelectDatasId.push(allData.dataList[k].id);
							templateSelectData.push(allData.dataList[k]);
							break;
						}
					}
				}
				isSelectDataIds = [];
				dataSelect.appendSelectedList(templateSelectData, true);
			}else {
				//只允许模板创建人修改模板
				var lander = $.parseJSON(urlParams).lander;
				if($(this).attr("createUser") != lander){
					$(".edit").hide();
				}else{
					$(".edit").show();
				}
				$(".save").hide();
				// NotTemplateAllSelectedIds = isSelectDataIds;

				for (var i = 0;i < allTemplateData.length; i++){
					if (currentTemplateId == allTemplateData[i].id){
						currentTempalteCode = allTemplateData[i].selectedCode;
						break;
					}
				}

				for (var j = 0;j < currentTempalteCode.length; j++){
					for (var k = 0;k < allData.dataList.length; k++){
						if(allData.dataList[k].code == currentTempalteCode[j]){
							templateSelectDatasId.push(allData.dataList[k].id);
							templateSelectData.push(allData.dataList[k]);
							break;
						}
					}
				}
				isSelectDataIds = [];
				dataSelect.appendSelectedList(templateSelectData, true);
			}
			$('.modEdit').hide();


			dataSelect.checkedBoxByisSelected(isSelectDataIds,allData);


			dataSelect.checkOpenOrCloseGroup();
			Util.stopPropagation(event);
		});

	},
	/**
	 * 判断所有组内是否有数据被选中，如果有的话，就展开当前组，如果没有，则关闭当前组
	 */
	checkOpenOrCloseGroup: function () {
		$(".cities .city").each(function () {
			var self = $(this);
			if (($(this).find(".groupSelectedNum").html() != 0) && $(this).find(".city-name .toggle").hasClass("on")){
				$(this).find(".city-name .toggle").click();
			}else if (($(this).find(".groupSelectedNum").html() == 0) && !$(this).find(".city-name .toggle").hasClass("on")){
				$(this).find(".city-name .toggle").click();
			}
		})
	},


	/**
	 * 渲染已选测点的数据到列表中
	 * @param dataList 需要渲染数据，数组或者对象
	 * @param boolean  是否需要清空原有列表数据
	 */
	appendSelectedList: function (dataList, boolean) {
		if (boolean){
			$('.addr-list').html('');
		}

		var dataTempArray = [];
		if ($.isArray(dataList)){
			for (var i = 0;i < dataList.length;i++){
				if ($.inArray(dataList[i].id, isSelectDataIds) === -1){
					dataTempArray.push('\
					<li id="addr-list-li-'+dataList[i].id+'" data-value="'+dataList[i].id+'">\
						<span>'+dataSelect.joinName(dataList[i])+'</span>\
						<i data-value="'+dataList[i].id+'"></i>\
					</li>'
					)
					isSelectDataIds.push(dataList[i].id);
				}

			}
		}else {
			if ($.inArray(dataList.id, isSelectDataIds) === -1){
				dataTempArray.push('\
					<li id="addr-list-li-'+dataList.id+'">\
						<span>'+dataSelect.joinName(dataList)+'</span>\
						<i data-value="'+dataList.id+'"></i>\
					</li>'
				);
				isSelectDataIds.push(dataList.id);
			}
			$(".allSelectedDataLength").html(isSelectDataIds.length);
		}

		if ($("#templateLists").find(".on").attr("id") === 'allTemplate'){
			NotTemplateAllSelectedIds = isSelectDataIds;
		}

		//初始化渲染列表：
		$('.addr-list').append($(dataTempArray.join('')));
		//初始化渲染已选择的数量
		$(".allSelectedDataLength").html(isSelectDataIds.length);
		this.deleteSelectedListEvent(dataList);

		if (isSelectDataIds.length === allData.dataList.length){
			$('.allSelectedDataLength').parent().parent().find('input[type="checkbox"]').attr("checked", true).siblings().addClass("on")
		}
		$(".addr-list-scroll-div").mCustomScrollbar("update");
	},
	removeSelectedList: function (dataList) {
		//右侧列表数据删除
		if ($.isArray(dataList)){
			for (var i = 0;i < dataList.length;i++){
				$("#addr-list-li-"+ dataList[i].id).remove()
				isSelectDataIds.splice($.inArray(dataList[i].id, isSelectDataIds),1);
			}
		}else {
			$("#addr-list-li-"+ dataList.id).remove();
			isSelectDataIds.splice($.inArray(dataList.id, isSelectDataIds),1);
		}
		$(".allSelectedDataLength").html(isSelectDataIds.length);

	},
	/**
	 * 给右侧已选中的数据列表增加点击删除事件
	 */
	deleteSelectedListEvent: function () {
		$('.addr-list li i').unbind('click').on('click', function(event) {
			//需要遍历已选择的数据数组，并删除当前数组中的数据，及时更新已选择数据条数。
			for (var i = 0;i < isSelectDataIds.length; i++){
				if (isSelectDataIds[i] == $(this).attr('data-value')){
					isSelectDataIds.splice(i, 1);
					break;
				}
			}
			//重新渲染已选择的数量
			$(".allSelectedDataLength").html(isSelectDataIds.length).parent().parent().find('input[type="checkbox"]').removeAttr("checked").siblings().removeClass("on");
			var itemId = $(this).attr("data-value");
			//根据ID移除中间部分中的checkbox与样式
			$('#item-' + itemId).removeAttr("checked").siblings().removeClass("on");
			var groupSelectNumber = 0;
			$('#item-' + itemId).parent().parent().siblings().each(function (index, object) {
				if ($(this).find('input[type="checkbox"]').attr("checked")){
					groupSelectNumber ++;
				}
			});
			$('#item-' + itemId).parent().parent().parent().prev().find('input[type="checkbox"]').removeClass("checked").siblings().removeClass("on");
			$('#item-' + itemId).parent().parent().parent().prev().find(".groupSelectedNum").html(groupSelectNumber);

			$('.allSelectedDataLength').parent().parent().find('input[type="checkbox"]').removeAttr("checked", true).siblings().removeClass("on")

			$(this).parent().remove();


			Util.stopPropagation(event);
		});
	},
	/**
	 * 清空所有已选择数据事件
	 */
	allSelectClearEvent: function () {
		$("#clearAllData").unbind("click").on('click',function (event) {
			layer.confirm('是否清空所有数据？', {icon: 3, title:'提示'}, function(index){
				$('.addr-list li').remove();
				for (var i=0;i<isSelectDataIds.length;i++){
					$("#item-" + isSelectDataIds[i]).removeAttr("checked").siblings().removeClass("on")
				}
				isSelectDataIds = [];
				//更新已选择条数：
				$(".allSelectedDataLength").html(isSelectDataIds.length);
				$(".city-name").find('input[type="checkbox"]').removeAttr("checked").siblings().removeClass("on");
				$(".groupSelectedNum").html(0);
				layer.close(index);
			});
			Util.stopPropagation(event);
		})
	},
	/**
	 * 筛选方式更新事件
	 */
	radioChangeEvent: function () {
		$('.radioBox').unbind("click").on('click', function(event) {
			Util.radioCheck(this);
			Util.stopPropagation(event);
			var currentRadio = $(this).find('input[type="radio"]').val();
			dataSelect.searchDataByFilter(currentRadio,isSelectDataIds, allData);

			dataSelect.checkOpenOrCloseGroup()
		});
	},
	/**
	 * 根据右侧已选测点中，将中间部分的CheckBox选中
	 */
	checkedBoxByisSelected: function (isSelectDataIds,allData) {
		$(".cities .check-box").find("input[type='checkbox']").removeAttr("checked").siblings().removeClass("on");
		$(".groupSelectedNum").html(0);
		if (isSelectDataIds.length){
			for (var i = 0;i < isSelectDataIds.length;i++ ){
				$("#item-" + isSelectDataIds[i]).attr("checked", true).siblings().addClass("on");
				if (this.handleLinkageEvent($("#item-" + isSelectDataIds[i]).parent()[0])){
					$("#item-" + isSelectDataIds[i]).parent().parent().parent().prev().find("input[type='checkbox']").attr("checked", true).siblings().addClass("on");
				}
			}
			for (var i = 0;i < isSelectDataIds.length;i++ ){
				var groupSelectedNumber = 0;
				$("#item-" + isSelectDataIds[i]).parent().parent().parent().find(".checkBox").each(function () {
					if ($(this).find('input[type="checkbox"]').attr("checked")){
						groupSelectedNumber++;
					}
					$("#item-" + isSelectDataIds[i]).parent().parent().parent().prev().find(".groupSelectedNum").html(groupSelectedNumber);
				})
			}
		}
		if (isSelectDataIds.length === allData.dataList.length){
			$('.allSelectedDataLength').parent().parent().find('input[type="checkbox"]').attr("checked", true).siblings().addClass("on")
		}else {
			$('.allSelectedDataLength').parent().parent().find('input[type="checkbox"]').removeAttr("checked", true).siblings().removeClass("on")
		}
	},
	/**
	 * 处理联动问题
	 */
	handleLinkageEvent: function (childObj) {
		var isCheckAll = true;
		$(childObj).parent().parent().find(".checkBox").each(function () {
			if (!$(this).find('input[type="checkbox"]').attr("checked")){
				isCheckAll = false;
				return false;
			}
		})
		return isCheckAll;
	},
	/**
	 * 搜索输入框 搜索事件
	 */
	searchChangeEvent: function () {
		var currentRadio = $(".srh-area .radio-box .on").parent().find('input[type="radio"]').val();
		dataSelect.searchDataByFilter(currentRadio,isSelectDataIds, allData, true);
	},
	/**
	 * 根据过滤条件查询数据
	 */
	searchDataByFilter: function (currentRadio, isSelectDataIds, allData, boolean) {
		for (var i=0;i<allData.groupList.length;i++){
			if (currentRadio === allData.groupList[i].key){
				var groupList = this.loopHandle(allData.groupList[i],allData);
				this.renderData(groupList, isSelectDataIds);
				break;
			}
		}
		this.checkedBoxByisSelected(isSelectDataIds,allData);
		if (boolean){
			$(".city00 .toggle.all").click();
		}
	},
	/**
	 * 按照数据格式渲染过滤条件
	 */
	initGroupList: function (groupList) {
		var groupTempArray = [];
		for (var i = 0;i < groupList.length; i++){
			groupTempArray.push('\
				<span class="radioBox">\
					<span class="radio-icon"></span>\
					<input type="radio" name="option" data-showName="'+groupList[i].showName+'" value="'+groupList[i].key+'" style="display:none;" />\
					<span class="addrType">'+groupList[i].name+'</span>\
				</span>\
			');
		}
		$(".radio-box")[0].innerHTML = groupTempArray.join("");

	},
	/**
	 * 根据当前的选中的分类进行数据分组
	 */
	loopHandle: function (classify, allData) {
		//首先遍历分组方式，并保存数据
		var key    = classify.key,      //展示分组key的字段
			showName = classify.showName, //展示分组名称的字段
			dataList = allData.dataList;  //所有列表数据

		//增加待选数据列表进行输入框值过滤操作
		dataList = Util.filterData(dataList, $("#search-input").val());
		var groupList = [];
		var groupKeyTemp = [];

		for (var i = 0;i < dataList.length; i++){
			if ($.inArray(dataList[i][key],groupKeyTemp) != -1){
				for (var j = 0;j < groupList.length; j++){
					if (dataList[i][key] === groupList[j].key){
						groupList[j].itemList.push(dataList[i])
						break;
					}
				}
			}else {
				groupKeyTemp.push(dataList[i][key]);
				groupList.push({
					key: dataList[i][key],
					name: dataList[i][showName] ? dataList[i][showName] : "其他",
					itemList: [
						dataList[i]
					]
				})
			}
		}

		return groupList;
	},
	/**
	 * 中间部分可选择部分内容数据渲染
	 */
	renderData: function (groupList, isSelectDataIds) {
		var checkAllTemplate = '\
			<i class="toggle all on"></i>\
			<div class="check-box" style="display: inline-block;">\
				<span class="checkBox chkA">'+
					((inputType=="checkbox")?'<span class="checkBox-icon"></span><input type="checkbox" style="display:none;">':'')
					+ '<span class="check-txt boldtxt">全部(<b class="allSelectedDataLength">'+ isSelectDataIds.length +'</b>/<b class="itemLength"></b>)</span>\
				</span>\
			</div>'
		$(".cities").prev()[0].innerHTML = checkAllTemplate;


		var renderTempArray = [];

		for (var i=0;i<groupList.length;i++){
			var itemTempLeft = '';
			var itemTempRight = '';
			for (var j = 0;j<groupList[i].itemList.length;j++){
				if (j % 2){
					itemTempLeft += '\
						<span class="checkBox item">\
							<span class="checkBox-icon"></span>\
							<input type="checkbox" id="item-'+groupList[i].itemList[j].id+'" data-name="'+groupList[i].itemList[j].name+'" value="'+groupList[i].itemList[j].id+'" style="display:none;">\
							<span class="check-txt" >'+dataSelect.joinName(groupList[i].itemList[j])+'</span>\
						</span>'
				}else {
					itemTempRight += '\
						<span class="checkBox item">\
							<span class="checkBox-icon"></span>\
							<input type="checkbox" id="item-'+groupList[i].itemList[j].id+'"  data-name="'+groupList[i].itemList[j].name+'" value="'+groupList[i].itemList[j].id+'" style="display:none;">\
							<span class="check-txt" >'+dataSelect.joinName(groupList[i].itemList[j])+'</span>\
						</span>'
				}
			}
			var LeftItemList = '';
			var rightItemList = '';
			if (itemTempLeft){
				LeftItemList += '<div class="check-box">'+itemTempLeft+'</div>'
			}
			if (itemTempRight){
				rightItemList += '<div class="check-box">'+itemTempRight+'</div>'
			}

			renderTempArray.push('\
				<div class="city city'+groupList[i].key+'">\
					<div class="city-name">\
						<i class="toggle on"></i>\
						<div class="check-box" style="display: inline-block;">\
							<span class="checkBox">'+
							((inputType=="checkbox")?'<span class="checkBox-icon"></span><input type="checkbox" value="'+groupList[i].key+'" style="display:none;">':'')
							+'<span class="check-txt boldtxt">'+groupList[i].name+'（<b class="groupSelectedNum">0</b>/'+groupList[i].itemList.length+'）</span>\
							</span>\
						</div>\
					</div>\
					<div class="city-addr" style="display: none;">\
						'+rightItemList+'\
						'+LeftItemList+'\
					</div>\
				</div>')
		};
		$(".cities")[0].innerHTML = renderTempArray.length ? renderTempArray.join("") : "<p class='no-data-tips'>未找到匹配项</p>";
		if (renderTempArray.length){
			$(".city00 .city-name").show();
		}else {
			$(".city00 .city-name").hide();
		}
		$(".itemLength")[0].innerHTML = allData.dataList.length;
		this.middleListEvent();
		$(".city-content").mCustomScrollbar("update");
	},
	middleListEvent: function () {
		$('i.toggle').on('click', function() {
			if ($(this).hasClass('on')) {
				$(this).removeClass('on');
			} else {
				$(this).addClass('on');
			}
			$(this).parent().siblings('.city-addr').toggle();

			$(".city-content").mCustomScrollbar("update")
		});

		$('i.toggle.all').on('click', function() {
			if ($(this).hasClass('on')) {
				$(this).parent().siblings().find('.city-addr').hide();
				$(this).parent().siblings().find('i.toggle').addClass('on');
			} else {
				$(this).parent().siblings().find('.city-addr').show();
				$(this).parent().siblings().find('i.toggle').removeClass('on');
			}
			$(".city-content").mCustomScrollbar("update")
		});

		$('.city-addr .checkBox').on('click', function() {
			if(inputType=="radio"){ //单选
				$('.addr-list li').remove();
				for (var i=0;i<isSelectDataIds.length;i++){
					$("#item-" + isSelectDataIds[i]).removeAttr("checked").siblings().removeClass("on")
				}
				isSelectDataIds = [];
				//更新已选择条数：
				$(".allSelectedDataLength").html(isSelectDataIds.length);
				$(".city-name").find('input[type="checkbox"]').removeAttr("checked").siblings().removeClass("on");
				$(".groupSelectedNum").html(0);
			}	
			
			Util.myCheckbox(this);
			if ($(this).find('input[type="checkbox"]').attr("checked") && dataSelect.handleLinkageEvent(this)){
				$(this).parent().parent().prev().find("input[type='checkbox']").attr("checked", true).siblings().addClass("on");
			}else {
				$(this).parent().parent().prev().find("input[type='checkbox']").removeAttr("checked", true).siblings().removeClass("on");
			}
		});
		$('.city-name .checkBox').on('click', function() {
				Util.myCheckbox(this,true);
		});
	},
	/**
	 * 获取url参数数据
	 */
	initUrlParams : function(){
		urlParams = Util.getParam("params");
		initIds = Util.getParam("initIds");
		var type = Util.getParam("type");
		if(type=='checkbox'||type=='radio'){
			inputType=type;
		}
	},
	/**
	 * 查询所有数据
	 */
	queryData: function () {
		//模拟ajax异步请求数据
		var self = this;
		if(!urlParams){
			layer.msg('参数为空！', {time: 2000, icon:7});
			return;
		}
		//查询点位信息及分组信息
		Util.ajaxRequest({
			type: 'GET',
			//url: 'json/test.json',
			url: ctx + '/selectservice',
			data:{
				method :'queryPointsInfo',
				urlParams : urlParams
			},
			async: false,
			success: function (result) {
				allData = result;
			}
		});
		//查询模板信息
		Util.ajaxRequest({
			type: 'GET',
			//url: 'json/template.json',
			url: ctx + '/selectservice',
			data:{
				method :'queryTemplates',
				urlParams : urlParams
			},
			async: false,
			success: function (result) {
				allTemplateData = result;
				self.initGroupList(allData.groupList);
				self.init();
				if(!flag){
					Util.changeScrollBar();
					flag = true
				}else{
					$(".city-content").mCustomScrollbar("update");
					$(".modu-list").mCustomScrollbar("update");
				}
				$($(".radioBox")[0]).click();
			}
		});
	},
	/**
	 * 点击确认事件，确认当前选中数据
	 */
	confirmSelect: function () {
		if(isSelectDataIds.length > 0){
			var names = [],codes = [],parentNames = [];
			for (var i = 0;i < isSelectDataIds.length; i++){
				for (var j = 0;j < allData.dataList.length; j++){
					if (isSelectDataIds[i] === allData.dataList[j].id){
						names.push(allData.dataList[j].name);
						codes.push(allData.dataList[j].code);
						parentNames.push(allData.dataList[j].parentName);
					}
				}
			}
			debugger;
			//回调返回数据：[数据序号,点位或对象名称，对象序号或点位代码,父级名称]
			top.window.returnValue = [isSelectDataIds.join(","),names.join(","),codes.join(","),parentNames.join(",")];
		}
		if (top.window.index) {
			Layer.closeLayer(top.window.index);
			top.window.index = null;
		}
		
	},
	getAllSelectData: function () {
		var resultData = [];
		for (var i = 0;i < isSelectDataIds.length; i++){
			for (var j = 0;j < allData.dataList.length; j++){
				if (isSelectDataIds[i] === allData.dataList[j].id){
					resultData.push(allData.dataList[j])
				}
			}
		}
		return resultData;
	},
	confirmAddNewTemplate: function () {
		var name = $(".modInp").val();
		var isPublic = $(".saveBox span.on").next().val();
		var selectedCode = [],selectedName = [];
		for (var i = 0;i < isSelectDataIds.length; i++){
			for (var j = 0;j < allData.dataList.length; j++){
				if (isSelectDataIds[i] === allData.dataList[j].id){
					selectedCode.push(allData.dataList[j].code);
					selectedName.push(allData.dataList[j].name);
				}
			}
		}
		if(selectedCode.length < 1 || selectedName.length < 1){
			layer.msg('模板必须包含数据！', {time: 2000, icon:7});
			return;
		}
		if(!name){
			layer.msg('请输入模板名称！', {time: 2000, icon:7});
			return;
		}
		
		var uuid = Util.UUID();
		Util.ajaxRequest({
			type: 'GET',
			url: ctx + '/selectservice',
			data:{
				method : 'addTemplate',
				XH : uuid,
				MBMC : name,
				QXJB : isPublic,
				NRBH : selectedCode.join(","),
				NRMC : selectedName.join(","),
				urlParams : urlParams
			},
			success: function (result) {
				if(result == true){
					dataSelect.queryData();
					$("#" + uuid).click();
					layer.msg('新增成功', {time: 2000, icon:1});
					//清空模板名称，防止下次进来依然是上次的名称
					$(".modInp").val("");
				}else{
					layer.msg('新增失败', {time: 2000, icon:2});
				}
			}
		});
	},
	confirmEditTemplate: function () {
		var name = $("#currentTemplateName").val();
		var currentTemplateId = $(".modEdit").attr("data-templateid");
		var isPublic = $(".modEdit span.on").next().val();
		var selectedCode = [],selectedName = [];
		for (var i = 0;i < isSelectDataIds.length; i++){
			for (var j = 0;j < allData.dataList.length; j++){
				if (isSelectDataIds[i] === allData.dataList[j].id){
					selectedCode.push(allData.dataList[j].code);
					selectedName.push(allData.dataList[j].name);
				}
			}
		}
		if(selectedCode.length < 1){
			layer.msg('模板必须包含数据！', {time: 2000, icon:7});
			return;
		}
		
		Util.ajaxRequest({
			type: 'GET',
			url: ctx + '/selectservice',
			data:{
				method : 'updateTemplate',
				XH : currentTemplateId,
				MBMC : name,
				QXJB : isPublic,
				NRBH : selectedCode.join(","),
				NRMC : selectedName.join(","),
				urlParams : urlParams
			},
			success: function (result) {
				if(result == true){
					dataSelect.queryData();
					$("#" + currentTemplateId).click();
					layer.msg('更新成功', {time: 2000, icon:1});
				}else{
					layer.msg('更新失败', {time: 2000, icon:2});
				}
			}
		});
		
	},
	deleteTemplate: function () {
		var currentTemplateId = $(".modEdit").attr("data-templateid");
		Util.ajaxRequest({
			type: 'GET',
			url: ctx + '/selectservice',
			data:{
				method : 'deleteTemplate',
				XH : currentTemplateId
			},
			success: function (result) {
				if(result == true){
					dataSelect.queryData();
					$("#allTemplate").click();
					layer.msg('删除成功', {time: 2000, icon:1});
				}else{
					layer.msg('删除失败', {time: 2000, icon:2});
				}
			}
		});
	},
	/**
	 * 页面的一些固定点击事件
	 */
	commonPageEvent: function () {

		// 点击另存为模板
		$('.save .saveSpan').unbind("click").on('click',function() {
			$(this).siblings('.saveBox').toggle();
		});

		$('.cancel, .confirm').unbind("click").on('click',function() {
			$(this).parent().hide();
		});

		// 点击编辑模板
		$('.editSpan').unbind("click").on('click',function(){
			$('.modEdit').show();
			var currentTemplateId = $("#templateLists").find("li.on").attr("id");
			var currentTemplateName = $("#templateLists").find("li.on").attr("data-name");
			var templateIsPublic = $("#templateLists").find("li.on").attr("isPublic");
			$("#currentTemplateName").val(currentTemplateName);
			$(".modEdit").attr('data-templateID', currentTemplateId);
			$(".modEdit .radio-box .radioBox").each(function() {
				if ($(this).find('input[type="radio"]').prop('name') == 'isPublic') {
					$(this).find('input[type="radio"]').removeAttr('checked');
					$(this).find('.radio-icon').removeClass('on');
					$(this).find('span.addrType').removeClass('on');
				}
			});
			$(".modEdit .radio-box :radio[name='isPublic'][value='" + templateIsPublic + "']").attr("checked", "checked");
			$(".modEdit .radio-box :radio[name='isPublic'][value='" + templateIsPublic + "']").prev().addClass("on");
		});

		$('.modEditBtn span').unbind("click").on('click',function(){
			$('.modEdit').hide();
		});
	},
	joinName: function (object) {
		if (object.parentName){
			return object.parentName + "-" + object.name
		}else {
			return object.name
		}
	}
};
var Util = {
	/**
	 *组织点击事件阻止冒泡行为
	 */
	stopPropagation: function(event) {
		var e = arguments.callee.caller.arguments[0] || event; //若省略此句，下面的e改为event，IE运行可以，但是其他浏览器就不兼容
		if (e && e.stopPropagation) {
			// this code is for Mozilla and Opera
			e.stopPropagation();
		} else if (window.event) {
			// this code is for IE
			window.event.cancelBubble = true;
		}
	},
	/**
	 * checkbox 选择框改变后的处理
	 * @param obj
	 * @param boolean
	 */
	myCheckbox: function(obj, boolean) {
		var checkicon = $(obj).find('.checkBox-icon');
		var checkbox = $(obj).find('input[type="checkbox"]');
		var checktxt = $(obj).find('.check-txt');
		//点击全部
		if ($(obj).parent().parent().next().hasClass("cities")){
			if (checkbox.attr('checked')) {
				checkicon.removeClass("on");
				checkbox.removeAttr('checked');
				checktxt.removeClass("on");
				$(obj).parent().parent().next().find(".checkBox").each(function (index, object) {
					$(this).find(".checkBox-icon").removeClass("on");
					$(this).find(".check-txt").removeClass("on");
					$(this).find('input[type="checkbox"]').removeAttr('checked');
				});
				$('.addr-list li').remove();
				isSelectDataIds = [];
				isSelectData = [];
				//更新已选择条数：
				$(".allSelectedDataLength").html(isSelectData.length);
				//取消全选时：
				$(obj).parent().parent().next().find(".groupSelectedNum").html(0);
			}else {
				checkicon.addClass("on");
				checkbox.attr('checked', true);
				checktxt.addClass("on");
				var dataSelectArray = [];

				$(obj).parent().parent().next().find(".checkBox").each(function (index, object) {
					$(this).find(".checkBox-icon").addClass("on");
					$(this).find(".check-txt").addClass("on");
					$(this).find('input[type="checkbox"]').attr('checked',true);
					if ($(this).hasClass('item')){
						dataSelectArray.push({
							id: $(this).find('input[type="checkbox"]').val(),
							name: $(this).find('input[type="checkbox"]').attr('data-name')
						})
					}
				});
				isSelectDataIds = [];
				isSelectData = [];
				dataSelect.appendSelectedList(dataSelectArray, true);

				//全选时：遍历数组
				$(obj).parent().parent().next().find(".city").each(function (index, object) {
					$(this).find(".city-name .groupSelectedNum").html($(this).find(".city-addr .item").length);
				})
			}
		}else {
			if (checkbox.attr('checked')) {
				checkicon.removeClass("on");
				checkbox.removeAttr('checked');
				checktxt.removeClass("on");
				if(boolean){
					var dataSelectArray = [];
					$(obj).parent().parent().siblings().find('.checkBox').each(function (index, object) {
						$(this).find(".checkBox-icon").removeClass("on");
						$(this).find(".check-txt").removeClass("on");
						$(this).find('input[type="checkbox"]').removeAttr('checked');
						dataSelectArray.push({
							id: $(this).find('input[type="checkbox"]').val(),
							name: $(this).find('input[type="checkbox"]').attr('data-name')
						})
					});
					dataSelect.removeSelectedList(dataSelectArray);
					//点击当前组名时，取消全选，当前组中的已选择数量为0
					checktxt.find(".groupSelectedNum").html(0)
				}else {
					dataSelect.removeSelectedList({
						id: $(checkbox).val(),
						name: $(checkbox).attr('data-name')
					});
					//点击当前组，取消当前数据，当前组中的已选择数量为遍历当前组的所有数据，包含checked的数量之和；
					var groupSelectedNumber = 0;
					$(obj).parent().parent().find('.checkBox').each(function (index, object) {
						if ($(this).find('input[type="checkbox"]').attr("checked")){
							groupSelectedNumber ++;
						}
					});
					$(obj).parent().parent().prev().find(".checkBox .groupSelectedNum").html(groupSelectedNumber)
				}

				$('.allSelectedDataLength').parent().parent().find('input[type="checkbox"]').removeAttr("checked", true).siblings().removeClass("on")
			} else {
				checkicon.addClass("on");
				checkbox.attr('checked', true);
				checktxt.addClass("on");
				if(boolean){
					var dataSelectArray = [];
					$(obj).parent().parent().siblings().find('.checkBox').each(function (index, object) {
						$(this).find(".checkBox-icon").addClass("on");
						$(this).find(".check-txt").addClass("on");
						$(this).find('input[type="checkbox"]').attr('checked', true);
						dataSelectArray.push({
							id: $(this).find('input[type="checkbox"]').val(),
							name: $(this).find('input[type="checkbox"]').attr('data-name')
						})
					});
					dataSelect.appendSelectedList(dataSelectArray);
					//点击当前组名时，全选，当前组中的已选择数量为当前组的总数据
					checktxt.find(".groupSelectedNum").html(dataSelectArray.length)
				}else {

					dataSelect.appendSelectedList({
						id: $(checkbox).val(),
						name: $(checkbox).attr('data-name')
					});
					//点击当前组，取消当前数据，当前组中的已选择数量为遍历当前组的所有数据，包含checked的数量之和；
					var groupSelectedNumber = 0;
					$(obj).parent().parent().find('.checkBox').each(function (index, object) {
						if ($(this).find('input[type="checkbox"]').attr("checked")){
							groupSelectedNumber ++;
						}
					});
					$(obj).parent().parent().prev().find(".checkBox .groupSelectedNum").html(groupSelectedNumber)
				}
			}
		}
	},
	/**
	 * radio 选择框点击改变后的样式处理
	 * @param obj
	 */
	radioCheck: function(obj) {
		var obj = $(obj);
		var name = obj.find('input[type="radio"]').attr('name');
		obj.find('input[type="radio"]').attr('checked', 'checked');
		obj.find('.radio-icon').addClass('on');
		obj.find('span.addrType').addClass('on');
		obj.siblings('.radioBox').each(function() {
			if ($(this).find('input[type="radio"]').prop('name') == name) {
				$(this).find('input[type="radio"]').removeAttr('checked');
				$(this).find('.radio-icon').removeClass('on');
				$(this).find('span.addrType').removeClass('on');
			}
		});
	},
	/**
	 * 搜索输入框过滤数据操作
	 * @param dataList, filterString
	 * */
	filterData: function (dataList, filterString) {
		var resultData = [];
		var searchArray = filterString.split("-");
		if (searchArray.length === 1){
			var RegExpObject = new RegExp(searchArray[0]);
			for (var i = 0;i < dataList.length; i++){
				if (RegExpObject.test(dataList[i].name) || RegExpObject.test(dataList[i].parentName)){
					resultData.push(dataList[i]);
				}
			}
		}else if (searchArray.length === 2){
			var RegExpObjectParentName = new RegExp(searchArray[0]);
			var RegExpObjectName = new RegExp(searchArray[1]);
			for (var i = 0;i < dataList.length; i++){
				if (RegExpObjectParentName.test(dataList[i].parentName) && RegExpObjectName.test(dataList[i].name)){
					resultData.push(dataList[i]);
				}
			}
		}
		return resultData;
	},

	ajaxRequest: function (config) {
		//通用ajax封装
		$.ajax({
			type: config.type ? config.type : 'GET',
			url: config.url,
			data: config.data,
			async: config.async == false ? false : true,
			contentType:'application/json',
			success: config.success,
			error: config.error
		});
	},
	setHeight: function () {
		$('.dlg-con-rt .addr-list-scroll-div').height($(window).height() - $('.panel-title').height() - $('.conf-wrap').height());
		$('.modu-list').height($(window).height() - $('.panel-title').height());
		$('.dlg-con-mid .city-content').height($(window).height() - $('.panel-tit').height());
	},
	changeScrollBar: function () {
		$(".scroll-content").mCustomScrollbar({
			autoHideScrollbar:true,
			scrollInertia: 50,
			theme:"dark"
		});
	},
	/** 
	 * 获取指定的URL参数值 
	 * 参数：paramName URL参数名称 
	 */ 
	getParam : function(paramName) { 
	    paramValue = "", isFound = !1; 
	    if (window.location.search.indexOf("?") == 0 && window.location.search.indexOf("=") > 1) { 
	        arrSource = unescape(window.location.search).substring(1, window.location.search.length).split("&"), i = 0; 
	        while (i < arrSource.length && !isFound) arrSource[i].indexOf("=") > 0 && arrSource[i].split("=")[0].toLowerCase() == paramName.toLowerCase() && (paramValue = arrSource[i].split("=")[1], isFound = !0), i++ 
	    } 
	    return paramValue == "" && (paramValue = null), paramValue 
	} ,
	/**
	 * @private
	 * 生成随机数
	 * @returns 随机数
	 */
	random4:function() {
		return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
	},
	
	/**
	 * 生成唯一的UUID
	 * @returns UUID
	 */
	UUID:function() {
		return (this.random4() + this.random4() + this.random4() + this.random4() + this.random4() +  this.random4() + this.random4() + this.random4());
	}
};