			    	 
/**
 * 表格数据选择
 */
Platform_Directive_APP.directive('gridDataPick',function(){
	
	return {
		restrict:"EA",
		template:function(tElement,tAttrs){
			return "表格数据";
		},
		scope:{
			testCallback:"&"
		},
		transclude:true,
		controller:['$scope', '$http' ,'uiGridConstants','$templateCache','$timeout','$window','$location',function($scope, $http, uiGridConstants,$templateCache,$timeout,$window,$location){
			/*$scope.testCallback();
			console.log($scope.testCallback());*/
		}]
		
	}
});

/**
 * 工作流过程文书
 */
Platform_Directive_APP.directive('workflowHistoryForm',function(){
	return {
		restrict:"EA",
		scope:{
			lcslbh:"=",
			bzbh:"=",
			activeFun:"&"
//			activeFun:"&active" 外部使用 属性active='myfunction()'
		},
		template:function(tElement,tAttrs){
			return  " <div class='process-history-form'>												"+
					"	<ul>																			"+
					"		<div ng-repeat='step in historyActionForms'>								"+
					"			<li class='process-parent-li' ng-class=\"{'on':formData.parentIndex == $index }\" ng-click='collapse($index)'>									"+
					"				<div class='li-tag parent' ng-class=\"{'on':formData.parentIndex == $index }\"></div>									"+
					"				<div class='process-form-item'>" +
					"					<span>{{$index+1}}.{{step.BZMC}}</span>" +
					"					<span style='float: right;padding-right:30px;'><i id='arrow{{$index}}' class='fa fa-caret-down' aria-hidden='true'></i></span>" +
					"				</div>		" +
					"			</li>" +
					"			<div id=\"child{{$index}}\">																	"+
					"				<li class='process-child-li' ng-class=\"{'on':formData.index==($parent.$index+'&'+$index)}\" ng-repeat = 'form in step.actionForms' ng-click='active($parent.$index,$index,form)'>	"+
					"					<div class='li-tag' ng-class=\"{'on':formData.index==($parent.$index+'&'+$index)}\"></div>																						"+
					"					<div class='process-form-item'>{{$parent.$index+1}}.{{$index+1}} {{form.DZMC}} </div>			"+
					"				</li>	" +
					"			</div>																"+
					"		</div>																		"+
					"	</ul>"+
					"</div>";
		},
		transclude:true,
		controller:['$scope', '$http' ,'$timeout','$window','$location',function($scope, $http,$timeout,$window,$location){
			 //获取历史动作表单；
		     Common.send($scope, $http, {
					method:"POST",
					url:Common.webRoot()+"/workflow/workflowcontroller/getHistoryActions",
					data:{lcslbh:$scope.lcslbh},
					showTips : false,
					success: function(res){
						$scope.historyActionForms = res.data;
					}
			 });
			
		}],
		link: function($scope, elem, iAttrs) { 
			$scope.formData={
				index:0,
				parentIndex:0
			};
			//详细内容点击事件
			$scope.active = function($parentIndex,$index,formObj){
				$scope.formData.parentIndex = $parentIndex;
				$scope.formData.index = $parentIndex+'&'+$index;
				//执行调用者事件函数
				$scope.activeFun({parentIndex:$parentIndex,index:$index,value:formObj});
			}
			
			//点击父节点收还在节点
			$scope.caretDown = true;
			$scope.collapse = function(id){
				/*$scope.formData.parentIndex = id;*/
				
				if($("#child"+id).css("display")=="none"){
					$("#child"+id+" .li-tag").show();
					$("#arrow"+id).removeClass("fa-caret-up");
				}else{
					$("#child"+id+" .li-tag").hide();
					$("#arrow"+id).addClass("fa-caret-up");
				}
				$("#child"+id).slideToggle("fast");
			}
		}
		
	}
});


/**
 * 表格数据选择
 */
Platform_Directive_APP.directive('powerTab',function(){
	return {
		restrict:"EA",
		scope:{
			tabsConfig:"="
		},
		templateUrl:function(tElement,tAttrs){
			if(tAttrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/tab/"+tAttrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/tab/default-tab.html";
		},
		transclude:true,
		controller:['$scope', '$http' ,'$timeout','$window','$location',function($scope, $http,$timeout,$window,$location){
			/***
			 * 初始化参数
			 */
			$scope._initConfig = function(){
				var tabsConfig = $scope.tabsConfig;
				if(!$scope.tabsConfig){tabsConfig = $scope.tabsConfig = {}};
				if(typeof tabsConfig.closeMsg == 'undefined'){tabsConfig.closeMsg=true};
				if(!tabsConfig.height){tabsConfig.height="500px"};
				//初始化数据
				if(!tabsConfig.data){tabsConfig.data=[]};
				var optFun = function(){ 
						return {
							 addTab:function(obj){
								 var me = this;
								 var index = me.checkHasExsit(obj.url);
								 if(index !=-1 ){
									 me.activeTab(index);
									 return;
								 }
								 if(typeof obj.isActive == "undefined"){obj.isActive=true};
								 if(typeof obj.isIframe == "undefined"){obj.isIframe=true};
								 if(typeof obj.immediately == "undefined"){obj.immediately=true};
								 if(typeof obj.isCanClose == "undefined"){obj.isCanClose=true};
						    	 $scope.tabsConfig.data.push(obj);
						     },
						     addTabAndActive:function(obj){
						    	 this.addTab(obj);
						    	 this.activeTab($scope.tabsConfig.data.length-1); 
						     },
						     removeTab:function($index){
						    	 /*扩展删除属性*/
						    	 var me = this;
						    	 Array.prototype.remove = function(from, to) {
						    		 var rest = this.slice((to || from) + 1 || this.length);
						    		 this.length = from < 0 ? this.length + from : from;
						    		 return this.push.apply(this, rest);
						    	 };
						    	 //删除之后激动；当前位置是大于0的情况激活下一个，否则激活上一个选项卡
						    	 if($scope.tabsConfig.data[$index].isActive){
						    		 if($index>0){
						    			 //$scope.tabsConfig.data[$index-1].isActive = true;
						    			 me.activeTab($index-1);
						    		 }else if($index==0&&$scope.tabsConfig.data.length!=1){
						    			 //$scope.tabsConfig.data[$index+1].isActive = true;
						    			 me.activeTab($index+1);
						    		 }
						    	 }
						    	 //移除tab
						    	 $scope.tabsConfig.data.remove($index);
						     },
						     //清楚所以的选中
						     clearActiveAll:function(){
						    	//清除所有的active 
								var tabsDatas = $scope.tabsConfig.data
								for(var index in tabsDatas){
									tabsDatas[index].isActive = false;
								}
						     },
						     //激动当前选项卡
						     activeTab : function($index){
						    	 try{
						    		 $scope.tabsConfig.clearActiveAll();
						    		 $scope.tabsConfig.data[$index].isActive = true;
						    		 $scope.tabsConfig.data[$index].immediately = true;
						    	 }catch (e) {
						    		 if(console){
						    			 console.log("激活选项卡异常"+e);
						    		 }
								}
						    	 //tabIframe_{{$index}}
						    	 //计算高度自动撑开；有问题；实在不想要iframe了；load page页面追加进入应该就不会出现这个问题
						    	/* $timeout(function(){
						    		 var iframe = $window.document.getElementById("tabIframe_"+$index);
							    	 iframe.onload = function(){
							    		 console.log(iframe.contentDocument.body.scrollHeight);
							    		 console.log(iframe.contentWindow.document.documentElement.scrollHeight);
							             iframe.height = 900;
							         };
						    	 },300);*/
						     },
						     checkHasExsit : function(url){
						    	 var data = tabsConfig.data;
						    	 for(var i=0;i<data.length;i++){
						    		 if(data[i].url.indexOf(url)!=-1){
						    			 return i;
						    		 }
						    	 }
						    	 return -1;
						     }
					}
				};
				$scope.tabsConfig = $.extend(tabsConfig,optFun());
			};
			$scope._initConfig();
		}],
		link: function(scope, elem, iAttrs) {//
			//
			//console.log(scope.tabsConfig);
			/*scope.$watch("tabsConfig.height",function(n,o){
				//console.log("n "+n);
				//$(".power-tab-contain iframe").height(n+"px");
			});*/
		}
	}
});

/**
 * power 高级查询组件 by wwupower
 */
Platform_Directive_APP.directive('powerAdvQuery',function(){
	return {
		restrict:"EA",
		templateUrl:function(tElement,tAttrs){
			if(tAttrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/adv-query/"+tAttrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/adv-query/adv-query.html";
		},
		scope:{
			queryOption:"="
		},
		transclude:true,
		controller:['$scope', '$http' ,'uiGridConstants','$templateCache','$timeout','$window','$location',function($scope, $http, uiGridConstants,$templateCache,$timeout,$window,$location){
			$scope.windowWidth = $($window).width();
			function setQueryWidth(){
				$("#mainQuery").width($scope.windowWidth*0.5+135+$scope.queryOption.buttons.length*110+"px");
				$("#queryInput").width($scope.windowWidth*0.5+"px");
			}
			setQueryWidth();
			//浏览器窗口大小更改
			$(window).resize(function(){
				$scope.windowWidth = $($window).width();
				setQueryWidth();
			});
		}],
		link:function(scope, element, attrs){
			//初始化参数配置
			scope._initConfig = function(){
				if(!scope.queryOption){scope.queryOption={}};
				var q = scope.queryOption;
				//默认关闭自动查询补全
				if(!q.autoComplete) q.autoComplete = true;
				//默认关闭高级查询参数
				if(!q.advQueryDisplay) q.advQueryDisplay = 'none';
				//高级查询参数没有默认 [] 数组
				if(!q.paramData) q.paramData = [];
				if(!q.seachInputTips) q.seachInputTips = '请输入关键字查询';
				//详细参数信息
				initParamData(q.paramData);
				//初始化监听事件
				if(!q.events){q.events={};}
				initEvents(q.events);
				
				/**
				 * 初始化详细查询参数
				 * @param  paramData 详细参数信息
				 */
				function initParamData(paramData){
					for(var i in paramData){
						var subp = paramData[i];
						if(!subp.type){console.log("error:没有设置查询类型.."); return;};
						//默认参数
						if(!subp.textField){subp.textField="text"};
						//显示文本参数
						if(!subp.valueField){subp.valueField="id"};
						//默认单选
						if(typeof(subp.isShowAll)=='undefined'&&!subp.multi){subp.multi=false};
						//默认显示全部
						if(typeof(subp.isShowAll)=='undefined'&&!subp.isShowAll){subp.isShowAll=true;};
						//远程访问的方式
						if(subp.type=="code"){
							subp.data=[];
							$.ajax({
									url : Common.webRoot() + '/platform/system/commoncodecontroller/getCommonCodesFromCache',
									type : "POST",
									dataType : 'json',
									async : false,
									contentType : 'application/json',
									data : JSON.stringify({
										"DM" : subp.dm,
										"DMJBH" : subp.dmjbh,
										"FDM" : subp.fdm
									}),
									success : function (codes){
										for(var j = 0;j < codes.length;j++){
											var obj = {};
											obj[subp.textField] = codes[j].DMMC;
											obj[subp.valueField] = codes[j].DM;
											subp.data.push(obj);
										}
										//切换成普通数组模式就可以
										subp.type="dataArr";
									}
								});
						}else if(subp.type=="url"){
							subp.data=[];
							if(!subp.urlParam){subp.urlParam={}};
							$.ajax({
									url :subp.url ,
									type : "POST",
									dataType : 'json',
									async : false,
									contentType : 'application/json',
									data : JSON.stringify(subp.urlParam),
									success : function (res){
										if(powerUtil.isArray(res)){
											subp.data=res;
										//兼容ajaxjson对象响应
										}else if(powerUtil.isObject(res)){
											if(res.code==0){
												subp.data=res.result;
											}else{
												subp.data=[];
											}
										}else{
											subp.data=[];
										}
										//最后切换成普通数组模式
										subp.type="dataArr";
									}
							});
						}else if(subp.type=="date"){
							//时间数据
							subp.data=[];
							//时间状态默认单选
							subp.multi=false;
							//自定义实现格式
							subp.beginFild = subp.name+"_beginTime";
							subp.endFild = subp.name+"_endTime";
							//时间显示格式
							if(!subp.dateFormat){
								subp.dateFormat = "yyyy-MM-dd HH:mm:ss";
							}
							for(var index in subp.dateParams){
								var dateConfig = subp.dateParams[index];
								for(var i in dateConfig.dateIntvs){
									//区间参数
									var dateIntv = dateConfig.dateIntvs[i];
									//工具获取的时间区间信息
									var dateIntvInfo;
									if(dateConfig.type =="day"){
										dateIntvInfo= MyDateUtil.getDayIntvs(new Date(),dateIntv.intv);
									}else if(dateConfig.type =="week"){
										dateIntvInfo= MyDateUtil.getWeekIntvs(new Date(),dateIntv.intv);
									}else if(dateConfig.type =="quarter"){
										dateIntvInfo= MyDateUtil.getQuarterIntvs(new Date(),dateIntv.intv);
									}else if(dateConfig.type =="year"){
										dateIntvInfo= MyDateUtil.getYearIntvs(new Date(),dateIntv.intv);
									}
									dateIntv[subp.valueField] =MyDateUtil.formatDateToStr(dateIntvInfo.beginTime,subp.dateFormat)
									+","+MyDateUtil.formatDateToStr(dateIntvInfo.endTime,subp.dateFormat);
									subp.data.push(dateIntv);
								}
							}
						}else if(subp.type=="user"){
							//办理人
							if(subp.defaultVal){
								scope.QueryComp.queryParam[subp.name] = subp.defaultVal;
							}
						}
						//显示全部
						if(subp.data&&subp.isShowAll){
							var allDt = {};
							allDt[subp.textField] = "全部";
							allDt[subp.valueField] = "";
							if(subp.textField==subp.valueField){
								allDt.allText = "全部";
							}
							subp.data.unshift(allDt);
						}
					}
				}
				
				
				/**
				 * 初始化事件类型
				 */
				function initEvents(events){
					//点击子菜单类型
					if(!events.clickSubItem){
						events.clickSubItem = function(queryParam){};
					}
					//enter 事件 待续
					if(!events.enterKey){
						events.enterKey = function(queryParam){};
					}
				}
				
				/**
				 * update-begin by wwupower date 2017-8-22 for 添加一些扩充方法 比如获取当前查询的参数
				 */
				/**
				 * 获取查询参数
				 */
				scope.queryOption.getQueryParam = function(){
					return scope.QueryComp.getSearchParams();
				}
				/**
				 * update-end by wwupower date 2017-8-22 for 添加一些扩充方法 比如获取当前查询的参数
				 */
			}
			
			/**
			 * 高级查询组件
			 */
			scope.QueryComp = {
					queryParam:{},
					initParam:function(paramData){
						var me = this;
						for(var i in paramData){
							var subp = paramData[i];
							if(subp.type=="dataArr"){
								if(subp.multi){
									me.queryParam[subp.name] = [];
									me.queryParam[subp.name].push("");
								}else{
									me.queryParam[subp.name] = "";
								}
							}else if(subp.type=="date"){
								scope.QueryComp.queryParam[subp.name] = "";
								scope.QueryComp.queryParam[subp.name+"_beginTime"] = ""; 
								scope.QueryComp.queryParam[subp.name+"_endTime"] ="";
							}
						}
					},
					getSearchParams:function(){
						var me = scope.QueryComp;
						var paramObj = $.extend({},me.queryParam);
						for(var index in paramObj){
							if(me.util.isArray(paramObj[index])){
								paramObj[index] = paramObj[index].join(",");
							}
						}
						return paramObj;
					},
					/**
					 * 事件管理
					 */
					events:{
						//点击子菜单选项
						clickSubItem:function(obj,value){
							var me = scope.QueryComp;
							//组织添加查询参数
							me.queryParamAdd(obj,value);
							scope.queryOption.events.clickSubItem(me.getSearchParams());
						},
						searchBtnClick:function(param){
							var me = scope.QueryComp;
							scope.queryOption.events.searchBtnClick(me.getSearchParams());
						},
						keyup:function($event){
							var me = scope.QueryComp;
							var keycode = window.event?$event.keyCode:$event.which;
							if(keycode==13){           
								scope.queryOption.events.enterKeyup(me.getSearchParams());        
							}
						},
						//显示详细查询参数
						showSubQuery : function(){
							var advQueryDisplay = $("#subQuery").css("display");
							if("none"==advQueryDisplay){
								scope.advQueryDisplay = true;
							}else{
								scope.advQueryDisplay = false;
							}
							$("#subQuery").slideToggle(300,function(){
								if(typeof (scope.queryOption.events.showSubQueryCallb)=='function'){
									scope.queryOption.events.showSubQueryCallb()
								}
							});
						},
						datePickChange:function(paramInfo){
							var me = scope.QueryComp;
							me.queryParam[paramInfo.name] = me.queryParam[paramInfo.beginFild]+","+me.queryParam[paramInfo.endFild];
						}
					},
					//是否选中子查询选项
					isCheckSubParam:function(obj,currValue){
						var me = scope.QueryComp;
						//获取参数信息
						var thisParam = me.queryParam[obj.name];
						if(thisParam!=""&&!thisParam){
							return false;
						}
						//多选参数数组的情况
						if(me.util.isArray(thisParam)){
							for(var i in thisParam){
								if(thisParam[i] == currValue){
									return true;
								};
							}
							return false;
						}
						//单选
						if( thisParam == currValue){
							return true;
						}
						return false;
					},
					//参数添加,有的则移除；没有则添加到容器参数里
					queryParamAdd:function(paramInfo,value){
						//通常数数据参数获取参数操作
						if(paramInfo.type!="date"){
							var me = scope.QueryComp;
							//
							var thisParam = me.queryParam[paramInfo.name];
							if(paramInfo.multi){
								//选择全部的情况下
								if(value==""||value=="全部"){
									thisParam = me.queryParam[paramInfo.name]=[];
									thisParam.push(value);
									return;
								}
								//到这里则先取消全部按钮的样式
								for(index in thisParam){
									if(thisParam[index]==""||thisParam[index]=="全部"){
										me.util.removeArray(thisParam,0);
									}
								}
								//已存在数据的情况有则移除；无则添加；
								var isExist = false;
								for(var index in thisParam){
									if(thisParam[index]==value){
										me.util.removeArray(thisParam,index);
										return;
									}
								}
								//添加请求参数
								thisParam.push(value);
								return;
							}
							//单选的情况下
							me.queryParam[paramInfo.name] = value;
						}else{//时间参数处理
							this.dateParamAdd(paramInfo,value);
						}
					},
					dateParamAdd:function(paramInfo,value){
						//开始截止时间
						var valueArr = value.split(",");
						scope.QueryComp.queryParam[paramInfo.beginFild] = valueArr[0];
						scope.QueryComp.queryParam[paramInfo.endFild] = valueArr[1];
						//对象的形式就克隆出新的对象；这样参数获取到新的参数；再操作这些参数时候不会改变原来的参数
						this.queryParam[paramInfo.name] = value;
						
					},
					util:{
						isObject:function(obj){
							if(typeof obj === 'object'){
								return true;
							}
							return false;
						},
						isArray:function(obj){
							if(typeof obj === "object" && Array == obj.constructor){
								return true;
							}
							return false;
						},
						removeArray:function(data,from, to) {
				      		  var rest = data.slice((to || from) + 1 || data.length);
				      		  data.length = from < 0 ? data.length + from : from;
				      		  return data.push.apply(data, rest);
				        }
					}
			}
			//初始化配置
			scope._initConfig();
			//初始化查询参数信息
			scope.QueryComp.initParam(scope.queryOption.paramData);
		}
	}
});

/**
 * 时间控件 my97 时间 by wwupower
 * my97Datepicker日期控在你选取日期后只是赋予了dom的value。
 * 所有我们应该根据my97Datepicker oncleared 动态给ng-model赋值；
 */
Platform_Directive_APP.directive('powerDatePicker', function () {
    return {
        restrict: 'A',
        require: 'ngModel',
        scope:{
        	ngModel:"=",
        	dateConfig:"@",
        	onpickedCall:"&",
        	onclearedCall:"&"
        },
        controller:['$scope', '$http' ,'$timeout','$window','$location','$filter',function($scope, $http,$timeout,$window,$location,$filter){
        	//时间格式化过滤
        	$scope.$watch("ngModel",function (newValue, oldValue) {
//                if(newValue&&newValue != oldValue){
                	$scope.ngModel = $filter('date')(newValue,$scope.dateFmt);
//                }
            });
		}],
        link: function (scope, element, attr, ngModel) {
        	scope.dateFmt = (attr.datefmt || 'yyyy-MM-dd HH');
            element.val(ngModel.$viewValue);
            function onpicking(dp) {
                var date = dp.cal.getNewDateStr();
                ngModel.$setViewValue(date);
                if(typeof scope.onpickedCall=='function'){
                	scope.onpickedCall();
                }
            }
            function oncleared(){
                ngModel.$setViewValue("");
                if(typeof scope.oncleared=='function'){
                	scope.oncleared();
                }
            }
            element.bind('click', function () {
            	init97Date();
            });
            
            function init97Date(){
            	try{
            		//my97 基本数据配置
            		var wdateConfig = {
            				onpicking: onpicking,
            				oncleared: oncleared,
            				dateFmt: scope.dateFmt
            		};
            		if(scope.dateConfig){
            			var config = "{"+scope.dateConfig+"}";
            			var configObj = (new Function("return "+config))();
            			if(configObj['onpicking']){
            				delete configObj['onpicking'];
            			}
            			if(configObj['onpicking']){
            				delete configObj['oncleared'];
            			}
            			wdateConfig = $.extend(wdateConfig,configObj);
            		}
            		//扩展my97属性
            		WdatePicker(wdateConfig)
            	}catch (e) {
					if(console){
						console.log(e);
					}
				}
            }
        }
    };
});


/**
 * CheckGroup 组件
 * 目前支持单选多选
 */
Platform_Directive_APP.directive('powerCheckGroup',function(){
	return {
		restrict:"EA",
		templateUrl:Common.webRoot()+"/resources/epsm/common/plug-directive/template/checkbox/checkboxGroup.html",
		scope:{
			ngModel:"=",
			disabled:"=ngDisabled",
			ngData:"=ngData",
			ngCall:"&"
		},
		transclude:true,
		controller:['$scope', '$http' ,'$templateCache','$timeout','$window','$location',function($scope,$http,$templateCache,$timeout,$window,$location){
			//选项数据
			this.checkType = "checkbox";
			this.checkboxs = [];
			this.power_radio = powerUtil.UUID();
			$scope.ngModelArr="";
			//使用ng-swich之后；ng-model失效问题；因为ng-swich会单独尝试新的scope作用域  ;可以通过在外面的scope上挂一个对象然后从里面访问来解决//https://github.com/angular/angular.js/wiki/Understanding-Scopes
			$scope.compRadio={value:""};
			//缺省值
			this.checkConfig = {
				registId:powerUtil.UUID(),//注册ID 唯一的
				valueField:"value",
				textField:"label",
				isCheck:false,
				validate:false,//默认关闭验证
				checkType:"checkbox"
			};
			//添加check
			this.addCheck = function addCheck(check) {
                this.checkboxs.push(check);
            };
		}],
		controllerAs: 'powerCheckGroupCtrl',
		link:function(scope, element, attr,$ctrl){
			//添加验证样式
			scope.isValidClass = function (validInputId) {
				if($(element).find("#"+validInputId).hasClass("validatebox-invalid")){
					if(!scope.ngModel || !scope.ngModel.length){
						return 'power-invalid';
					}
				}
				return ''
			};
			
			
			/**
			 * 初始化组件
			 */
			$ctrl.initComp = function(){
				scope.ngModelArr = [];
				return {
					/**
					 * 初始化绑定的数据
					 */
					initModelData:function(){
						if(scope.ngModel){
							scope.ngModelArr = (scope.ngModel+"").split(",");
						}else{
							scope.ngModelArr = [];
						}
					},
					
					
					/**
					 * option 属性参数初始化
					 */
					initOption:function(){
						//读取配置数据并解析
						if(!attr.option){
							console.log("没有配置数据项 option 属性 undefined..");
							return;
						};
						var checkbox = "{"+attr.option+"}";
						checkbox = (new Function("return "+checkbox))();
						$ctrl.checkConfig = $.extend($ctrl.checkConfig,checkbox);
						//类型赋值 checkbox | radio
						if($ctrl.checkConfig.checkType && $ctrl.checkConfig.checkType == "checkbox" || $ctrl.checkConfig.checkType == "radio"){
							$ctrl.checkType = $ctrl.checkConfig.checkType;
						}
						//简单字符data对象
						if(checkbox.data){
							this.initData($ctrl.checkConfig);
							return;
						}
						//js对象赋值
						if(scope.ngData){
							$ctrl.checkConfig.data = scope.ngData;
							this.initData($ctrl.checkConfig);
							return;
						}
						//远程请求数据的方式
						if(checkbox.url){
							this.getUrlData($ctrl.checkConfig);
							return;
						}
						//公共代码值得情况
						if(checkbox.code){
							this.getCode($ctrl.checkConfig);
							return;
						}
					},
					getUrlData:function(checkbox){
						if(!checkbox.params){checkbox.params={}};
						var me = this;
						$.ajax({
								url :checkbox.url ,
								type : "POST",
								dataType : 'json',
								async : false,
								contentType : 'application/json',
								data : JSON.stringify(checkbox.params),
								success : function (res){
									checkbox.data = res;
									me.initData(checkbox);
								},
								error:function(e){
									console.log("单选多选组件远程请求数据异常.."+e);
								}
						});
					},
					getCode:function(checkbox){
						if(checkbox.data&&checkbox.data.length>0){
							me.initData(checkbox);
							return;
						}
						var me = this;
						$.ajax({
							url : Common.webRoot() + '/platform/system/commoncodecontroller/getCommonCodesFromCache',
							type : "POST",
							dataType : 'json',
							async : false,
							contentType : 'application/json',
							data : JSON.stringify({
								"DM" : checkbox.dm ? checkbox.dm : '',
								"DMJBH" : checkbox.code,
								"FDM" : checkbox.fdm ? checkbox.fdm :''
							}),
							success : function (codes){
								$ctrl.checkConfig.valueField = "DM";
								$ctrl.checkConfig.textField = "DMMC";
								scope.ngData = codes;
								checkbox.data = codes;
								me.initData(checkbox);
							}
						});
					},
					/**
					 * 初始化所需渲染的数据
					 */
					initData:function(checkbox){
						try{
							$ctrl.checkboxs = [];
							$.each(checkbox.data, function(i,ch) {
								//初始化默认缺省值
								ch.checkId_const = powerUtil.UUID();
								$.each(scope.ngModelArr,function(i,model){
									if($ctrl.checkType == "checkbox"){
										if(model == ch[$ctrl.checkConfig.valueField] || model[i] == ch[$ctrl.checkConfig.valueField]){
											ch.isCheck = true;
										}
									}else{
										if(model == ch[$ctrl.checkConfig.valueField] || model[i] == ch[$ctrl.checkConfig.valueField]){
											scope.compRadio.value = ch[$ctrl.checkConfig.valueField];
										}
									}
								});
								$ctrl.addCheck(ch);
							});
						}catch(e){
							console.log("初始化单选多选组件数据initData异常："+e);
						}
					}
					
				}
			}
			
			/**点击改变数据 checkbox稍微麻烦一些的处理*/
			scope.checkModelChange = function(checkObj){
				var valueField = $ctrl.checkConfig.valueField;
				if(checkObj.isCheck){
					scope.ngModelArr.push(checkObj[valueField]);
					scope.ngModel = scope.ngModelArr.join(",");
				}else{
					for(var i=0 ; i < scope.ngModelArr.length ; i++){
						if(checkObj[valueField] == scope.ngModelArr[i]){
							scope.ngModelArr.splice(i,1);
							i--;
						}
					}
					if(scope.ngModelArr.length ==0 ){
						scope.ngModel = "" ; 
					}else{
						scope.ngModel = scope.ngModelArr.join(",");
					}
				}
				if(typeof scope.ngCall == 'function'){
					scope.ngCall({data:scope.ngModel});
				}
				
			}
			//赋值给外部对象
			scope.$watch("compRadio.value",function(val){
	            if (val) scope.ngModel  = val;
			});
			
			/**
			 * 绑定数据更新时、动态刷新选择状态
			 */
			scope.$watch("ngModel",function(n,o){
				if(n){
					initc.initModelData();
					var checkData = [];
					//遍历获取更新选中的数据
					$.each($ctrl.checkConfig.data, function(i,ch) {
						//初始化默认缺省值
						$.each(scope.ngModelArr,function(i,model){
							if($ctrl.checkType == "checkbox"){
								if(model == ch[$ctrl.checkConfig.valueField] || model[i] == ch[$ctrl.checkConfig.valueField]){
									checkData.push(ch);
								}
							}else{
								if(model[i] == ch[$ctrl.checkConfig.valueField]){
									scope.compRadio.value = ch[$ctrl.checkConfig.valueField];
								}
							}
						});
					});
					//刷新勾选状态
					for(var index in $ctrl.checkConfig.data){
						var f = false;
						for(var ii in checkData){
							if($ctrl.checkConfig.data[index][$ctrl.checkConfig.valueField] == checkData[ii][$ctrl.checkConfig.valueField]){
								f = true;
							}
						}
						$ctrl.checkConfig.data[index].isCheck=f;
					}
				}
			});
			
			//执行初始化函数
			var initc = $ctrl.initComp();
			initc.initModelData();
			initc.initOption();
			
			/**
			 * 模型数据列更新之后动态刷新渲染
			 */
			scope.$watch("ngData",function(n,o){
            	if(n&&n!==o){
            		//执行初始化函数
            		var initc = $ctrl.initComp();
            		initc.initModelData();
            		initc.initOption();
            	}
            });
		}
	}
});

/**
 * 动态数据选择
 */
Platform_Directive_APP.directive('powerDataPick',function(){
	return {
		restrict:"EA",
		templateUrl:function(element, attrs){
			if(attrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-table/"+attrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-table/pick-table.html";
		},
		scope:{
			btnText:'@btnText',
			openTitle:"@openTitle",
			selectId:"@selectId",
			onSelect:"&onSelect",
			model:"=ngModel",
			validate:"=validate",
			dataOptions:"@dataOptions",
			readonly:"=ngReadonly",
			disabled:"=ngDisabled",
			ngInitQueryParam:"="
		},
		controller:['$scope','$window','$location',function($scope,$window,$location){
			
		}],
		link:function(scope,ele,attr){
			//初始化
			if(attr.disabled==""||attr.disabled){
				scope.disabled = true;
			}
			if(typeof scope.validate == "undefined"){
				scope.validate = false;
			}
			if(scope.validate&&!scope.dataOptions){
				scope.dataOptions = "required:true,tipPosition:'bottom'";
			}
			//弹出框
			scope.btnClick = function(){
				top.window.TMPDTSJ = "temp";
				var param = "";
				if(scope.ngInitQueryParam){
					param = JSON.stringify(scope.ngInitQueryParam);
				}else{
					param = JSON.stringify({});
				}
				//初始化查询参数
				top.window.DataPickInitQParam = param;
				Common.dialog({
					type:"open",
					title:scope.openTitle,
					width: '85%',
		    		height: '70%',
					url:Common.webRoot()+"/tableopt/datapickgrid?id="+scope.selectId,
					end:function(){
						var data = top.window.TMPDTSJ;
						if(data!="temp"){
							data = JSON.parse(data);
							scope.onSelect({data:data});
							scope.$apply();
						}
					}
				});
			}
		}
	}
});

/**
 * 动态数据按钮选择
 */
Platform_Directive_APP.directive('powerDataBtnPick',function(){
	return {
		restrict:"EA",
		templateUrl:function(element, attrs){
			if(attrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-table/"+attrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-table/pick-btn.html";
		},
		scope:{
			btnText:'@btnText',
			openTitle:"@openTitle",
			selectId:"@selectId",
			onSelect:"&onSelect",
			validate:"=validate",
			readonly:"=ngReadonly",
			disabled:"=ngDisabled",
			ngInitQueryParam:"="
		},
		controller:['$scope','$window','$location',function($scope,$window,$location){
			
		}],
		link:function(scope,ele,attr){
			//初始化
			if(attr.disabled==""||attr.disabled){
				scope.disabled = true;
			}
			if(typeof scope.validate == "undefined"){
				scope.validate = false;
			}
			if(scope.validate&&!scope.dataOptions){
				scope.dataOptions = "required:true,tipPosition:'bottom'";
			}
			//弹出框
			scope.btnClick = function(){
				top.window.TMPDTSJ = "temp";
				var param = "";
				if(scope.ngInitQueryParam){
					param = JSON.stringify(scope.ngInitQueryParam);
				}else{
					param = JSON.stringify({});
				}
				//初始化查询参数
				top.window.DataPickInitQParam = param;
				Common.dialog({
					type:"open",
					title:scope.openTitle,
					width: '85%',
		    		height: '70%',
					url:Common.webRoot()+"/tableopt/datapickgrid?id="+scope.selectId,
					end:function(){
						var data = top.window.TMPDTSJ;
						if(data!="temp"){
							data = JSON.parse(data);
							scope.onSelect({data:data});
							scope.$apply();
						}
					}
				});
			}
		}
	}
});

/**
 * 用户签名
 * 
 */
Platform_Directive_APP.directive('powerSignature',function(){
	
	return {
		restrict:"EA",
		templateUrl:function(tElement,tAttrs){
			if(tAttrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/signature/"+tAttrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/signature/signature.html";
		},
		scope:{
			model:"=ngModel",
			validate:"=",
			disabled:"=ngDisabled",
			multi:"=ngMulti",
			splitStr:"@"
		},
		transclude:true,
		controller:['$scope', '$http' ,'$timeout','$window','$location',function($scope, $http,$timeout,$window,$location){
			$scope.webRoot = Common.webRoot();
			$scope.comfirmPanelId = powerUtil.UUID();
			/**
			 * 弹出确认选择框 
			 */
			var  layerIndex;
			//密码验证对象
			$scope.confirmFrom = {
				confirmPassw : ""
			};
			//当前登录用户
			$scope.currUser = {};
			//签名数据
			$scope.comp = {};
			$scope.comp.sigts = [];
			//绑定对象模型数据
			$scope.modelArr = [];
			//defaultConfig 默认参数
			var defaultConfig = {
					multi:true,
					validate : false,
					disabled : false
			}
			
			//是否可以多人签名 默认可多人签名
			if(typeof $scope.multi == "undefined"){
				$scope.multi = defaultConfig.multi;
			}
			//是否验证
			if(typeof $scope.validate == "undefined"){
				$scope.validate =  defaultConfig.validate;
			}
			//是否禁用 默认启用
			if(typeof $scope.disabled == "undefined"){
				$scope.disabled = defaultConfig.disabled;
			}
			
			/**
			 * 确认密码回调
			 */
			$scope.confirmPass = function(){
				if(!$scope.confirmFrom.confirmPassw){
					Common.RBTips("请输入您的密码进行校验！",2);
					return;
				}
				//验证用户密码
				Common.send($scope, $http,{
					method: "POST",
					url: Common.webRoot() + "/config/yhqmxxcontroller/validpassword",
					data:JSON.stringify({yhmm:$scope.confirmFrom.confirmPassw}),
					showTips : false,
					success: function(data){
						//请求相应的对象
						var resData = data.data;
						if(resData.code==0){
							//Common.RBTips(resData.msg,1);
							$timeout(function(){
								layer.closeAll();
							},1000);
							//清楚密码数据、并且把表单改写为text 类型 ，这样清除浏览器记住密码的恶心问题
							$scope.confirmFrom.confirmPassw = "";
							$("#confirmPasswInput-"+$scope.comfirmPanelId).attr("type","text");
							//签名数据赋值
							var isAdd = true;//是否能添加；有则不添加；无则可添加
							for(var i=0;i<$scope.comp.sigts.length;i++ ){
								if($scope.comp.sigts[i].YHID == $scope.currUser.YHID){
									isAdd = false;
									break;
								}
							}
							if(isAdd){
								$scope.comp.sigts.push($scope.currUser);
								$scope.modelArr = [];
								for(var i=0;i<$scope.comp.sigts.length;i++ ){
									$scope.modelArr.push($scope.comp.sigts[i].YHID);
								}
								//重新赋值
								$scope.model = $scope.modelArr.join($scope.splitStr);
							}
							
						}else{
							Common.RBTips(resData.msg,2);
						}
					}
				});
			}
			//enter
			$scope.keyup =　function($event){
				var keycode = window.event?$event.keyCode:$event.which;
				if(keycode==13){           
					$scope.confirmPass();   
				}
			}
			
			/**
			 * 打开验证面板
			 */
			$scope.openConfirm = function(){
				//存在用户不弹出
				for(var i=0;i<$scope.modelArr.length;i++){
					if($scope.modelArr[i] == $scope.currUser.YHID){
						return;
					}
				}
				/**打开用户密码验证**/
				layer.open({
					type:"1",
					title:"请输入密码确认",
		    		area: ['500px', '260px'],
		    		shade:0.1,
		    		content : $("#confirmPasswPanle-"+$scope.comfirmPanelId),
		    		cancel: function(){
						layer.close(layerIndex);
						$scope.confirmFrom.confirmPassw = "";
						$("#confirmPasswInput-"+$scope.comfirmPanelId).attr("type","text");
					}
				});
				
				/*Common.dialog({
					type:"dom",
					width:"500px",
					height:"260px",
					content:$("#confirmPasswPanle-"+$scope.comfirmPanelId)
				});*/
			}
			
			//获取用户签名信息
			Common.send($scope, $http,{
				method: "POST",
				url: Common.webRoot() + "/config/yhqmxxcontroller/getqmbyyhid",
				data:JSON.stringify({}),
				showTips : false,
				success: function(data){
					//请求相应的对象
					var resData = data.data;
					if(resData.code==0){
						if(resData.result){
							$scope.QMDZ = resData.result.QMDZ;
							$scope.QMTP = resData.result.QMTP;
							$scope.currUserId = resData.result.YHID;
							$scope.currUser = resData.result;
						}
					}
				}
			});
			
			
			$scope.$watch("model",function(n,o){
				if(n){
					$scope.modelArr = $scope.model.split($scope.splitStr);
					//获取用户签名信息
					$.ajax({
						url :Common.webRoot() + "/config/yhqmxxcontroller/getqmsbyyhids" ,
						type : "POST",
						dataType : 'json',
//						async : false,
						contentType : 'application/json',
						data : JSON.stringify({splitStr:$scope.splitStr,yhids:$scope.model}),
						success : function (res){
							if(res.code==0){
								$scope.comp.sigts = res.result;
								$scope.$apply();
								//是否可以多人签名 默认可多人签名
							}
						},
						error:function(e){
							if(console){
								console.log(e);
							}
						}
					});
				}
			});
			//初始化参数；
			(function init($scope,$http){
				try{
					//绑定对象模型数据
					$scope.modelArr = [];
					if(!$scope.splitStr){
						$scope.splitStr = ",";
					}
				}catch (e) {
					if(console){
						console.log(e);
					}else{
						alert(e);
					}
				}
			})($scope,$http);
		}],
		link:function(scope,ele,attr){
			
			//是否可以多人签名 默认可多人签名
			//添加验证样式
			scope.isValidClass = function (validInputId) {
				if($(ele).find("#"+validInputId).hasClass("validatebox-invalid")){
					if(!scope.model || !scope.model.length){
						return 'power-sigts-invalid';
					}
				}
				return ''
			};
			
			//删除签名
			scope.delQm = function(yhid){
				//删除当前
				for(var i=0;i<scope.comp.sigts.length;i++){
					if(scope.comp.sigts[i].YHID == yhid){
						//删除数据
						powerUtil.removeArray(scope.comp.sigts,i);
					}
				}
				scope.modelArr = [];
				for(var i=0;i<scope.comp.sigts.length;i++ ){
					scope.modelArr.push(scope.comp.sigts[i].YHID);
				}
				//重新赋值
				scope.model = scope.modelArr.join(scope.splitStr);
			}
			
			/**
			 * 用户签名完成之后，按钮变灰色，不可签名；
			 */
			scope.isCanSigt = function(){
				for(var i=0;i<scope.modelArr.length;i++){
					
					if(scope.modelArr[i] == scope.currUser.YHID){
						//删除数据
						return true;
					}
				}
				return false;
			}
		}
		
	}
});

/**
 * 违法行为选择
 */
Platform_Directive_APP.directive('powerIllegalDataPick',function(){
	return {
		restrict:"EA",
		templateUrl:function(element, attrs){
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-wfxw/pick-wfxw.html";
		},
		scope:{
			btnText:'@btnText',
			openTitle:"@openTitle",
			onSelect:"&onSelect",
			disabled:"=ngDisabled"
		},
		controller:['$scope','$window','$location',function($scope,$window,$location){

		}],
		link:function(scope,ele,attr){
			//初始化
			
			if(scope.disabled == "" || typeof scope.disabled =='undefinded' ){
				scope.disabled = false;
			}
			if(!scope.btnText){
				scope.btnText = "...";
			}
			if(!scope.openTitle){
				scope.openTitle = "信息选择";
			}
			
			//弹出框数据选择
			scope.btnClick = function(){
				top.window.TMPWFXW = "temp";
				Common.dialog({
					type:"open",
					title:scope.openTitle,
					width: '85%',
		    		height: '70%',
					url:Common.webRoot()+"/wfxw/wfxwclcontroller/wflxselect",
					end:function(){
						//数据选择回调
						var data = top.window.TMPWFXW;
						if(data!="temp"){
							data = JSON.parse(data);
							scope.onSelect({data:data});
							scope.$apply();
						}
					}
				});
			}
			
		}
	}
});

/**
 * 公共代码值选择可维护的
 */
Platform_Directive_APP.directive('powerCodePick',function(){
	return {
		restrict:"EA",
		templateUrl:function(element, attrs){
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/pick-wfxw/pick-wfxw.html";
		},
		scope:{
			ngDmmcModel:"=", //绑定显示代码名称多个使用“,”分割
			ngDmModel:"=",	//绑定显示代码 多个使用“,”分割
			btnText:'@btnText',
			openTitle:"@openTitle",
			callback:"&callback",
			disabled:"=ngDisabled",
			code:"@",
			isCanDel:"=",
			isCanAdd:"=",
			isCanUpdate:"=",
			multi:"=",
			isMultiXh:"=" //多选时候是否自动补充序号  如 1、aaa  2、bb
		},
		controller:['$scope','$window','$location',function($scope,$window,$location){

		}],
		link:function(scope,ele,attr){
			//初始化
			
			if(scope.disabled == "" || typeof scope.disabled =='undefinded' ){
				scope.disabled = false;
			}
			if(!scope.btnText){
				scope.btnText = "...";
			}
			if(!scope.openTitle){
				scope.openTitle = "信息选择";
			}
			//请求参数
			var param = "?DMJBH="+scope.code;
			//是否了删除
			if(typeof scope.isCanDel == 'undefined'){
				scope.isCanDel = false;
			}
			if(typeof scope.isCanAdd == 'undefined'){
				scope.isCanAdd = false;
			}
			if(typeof scope.isCanUpdate == 'undefined'){
				scope.isCanUpdate = false;
			}
			if(typeof scope.multi == 'undefined'){
				scope.multi = true;
			}
			param+="&isCanDel="+scope.isCanDel+"&isCanAdd="+scope.isCanAdd+"&isCanUpdate="+scope.isCanUpdate+"&multi="+scope.multi+"";
			//弹出框数据选择
			scope.btnClick = function(){
				//初始化临时变量
				top.window.TMPGGDMZ = "temp";
				top.layer.open({
					type:2,
					title:scope.openTitle,
		    		area:['60%','450px'],
					content:Common.webRoot()+"/commcode/ggdmzcontroller/ggdmzlist"+param,
					shift:0,
					isOutAnim:true,
					end:function(){
						//数据选择回调
						var data = top.window.TMPGGDMZ;
						if(data!="temp"){
							data = JSON.parse(data);
							var dmmc = "";
							var dms = [];
							for(var i in data){
								//自动补全序号
								if(scope.isMultiXh){
									dmmc += (Number(i)+1)+"、"+data[i].DMMC+"\n";
								}else{
									dmmc += data[i].DMMC+"\n";
								}
								dms.push(data[i].DM);
							}
							scope.ngDmmcModel = dmmc;
							scope.ngDmModel = dms.join(",");
						}
						if(typeof scope.callback =='function'){
							scope.callback({data:data});
						}
						scope.$apply();
					}
				});
			}
		}
	}
});


/**
 * 行政处罚文书属性展示
 * 
 */
Platform_Directive_APP.directive('powerWsTree',function(){
		return {
			restrict:"EA",
			scope:{
				ywzbxh:"=",//业务主表序号
				ngSelectData:"=",
				isShowProcess:"=",
				activeFun:"&",
				ngIsOpenAll:"=",
				ngDefaultClickFirstEle:"="
			},
			template:function(tElement,tAttrs){
				return  " <div class='process-history-form' {{ngSelectData}}>"+
						"	<ul>"+
						"		<div ng-repeat='step in ngSelectData'>"+
						"			<li class='process-parent-li' ng-class=\"{'process-no-data':isChildHasData(step),'on':formData.parentIndex == $index }\" ng-click='collapse($index,step)'>									"+
						"				<div class='li-tag parent' ng-class=\"{'on':formData.parentIndex == $index }\"></div>									"+
						"				<div class='process-form-item'>" +
						"					<span>{{$index+1}}.{{step.WSMC}}</span>" +
						"					<span style='float: right;padding-right:30px;' ng-if='false&&step.children.length>0'><i id='arrow{{$index}}' class='fa fa-caret-down' aria-hidden='true'></i></span>" +
						"				</div>		" +
						"			</li>" +
						"			<div id=\"child{{$index}}\" class='process-children power-hide' ng-class=\"{'power-show':ngIsOpenAll}\">																	"+
						"				<li class='process-child-li' ng-class=\"{'on':formData.index==($parent.$index+'&'+$index),'process-no-data':!form.isHasData}\" ng-repeat = 'form in step.children' ng-click='active($parent.$index,$index,form)'>	"+
						"					<div class='li-tag' ng-class=\"{'on':formData.index==($parent.$index+'&'+$index)}\"></div>																						"+
						"					<div class='process-form-item ellipsis'> <span ng-if='form.WSCJSJ'>{{form.WSCJSJ}}</span><span ng-class=\"{'process-ws-no-data':!isChildHasData(step)&&!form.isHasData}\"> 《{{form.WSMC}}》{{formData.isHasData}}</sapn> </div>			"+
						"				</li>	" +
						"			</div>																"+
						"		</div>																		"+
						"	</ul>"+
						"</div>";
			},
			transclude:true,
			controller:['$scope', '$http' ,'$timeout','$window','$location',function($scope, $http,$timeout,$window,$location){
				//获取历史动作表单；
				Common.send($scope, $http, {
						method:"POST",
						url:Common.webRoot()+"/tzxx/tzxxcontroller/getwslist/"+$scope.ywzbxh,
						data:JSON.stringify({}),
						showTips : false,
						success: function(res){
							if($scope.isShowProcess){
								$scope.ngSelectData = res.data.result;
							}else{
								/**
								 * 办理过程删除、待改善..
								 */
							    powerUtil.removeArray(res.data.result,res.data.result.length-1);
								$scope.ngSelectData = res.data.result;
							}
							if($scope.ngDefaultClickFirstEle){
								$timeout(function(){
									if($scope.ngSelectData[0].children.length>0){
										$scope.formData.parentIndex = 0;
										$scope.formData.index = '0&0';
										$scope.activeFun({value:$scope.ngSelectData[0].children[0]});
									}else{
										$scope.formData.parentIndex = 0;
										$scope.formData.index = null;
										$scope.activeFun({value:$scope.ngSelectData[0]});
									}},500);
							}
							
						}
				 });	
			}],
			link: function($scope, elem, iAttrs) { 
				$scope.formData={
						index:-1,
						parentIndex:-1
					};
				//详细内容点击事件
				$scope.active = function($parentIndex,$index,formObj){
					if(!formObj.isHasData){
						return;
					}
					$scope.formData.parentIndex = $parentIndex;
					$scope.formData.index = $parentIndex+'&'+$index;
					//执行调用者事件函数
					$scope.activeFun({value:formObj});
				}
				
				//点击父节点收还在节点
				$scope.caretDown = true;
				$scope.collapse = function(id,formObj){
					/*if(formObj.children.length>0){
						$scope.formData.parentIndex = id;
						$scope.formData.index = id+'&0';
						$scope.activeFun({value:formObj.children[0]});
					}else{
						$scope.formData.parentIndex = id;
						$scope.formData.index = null;
						$scope.activeFun({value:formObj});
					}*/
					if(formObj.children.length==0 && !formObj.isHasData){
						return;
					}
					$scope.formData.parentIndex = id;
					$scope.formData.index = null;
					$scope.activeFun({value:formObj});
					//只打开一个
					if(!$scope.ngIsOpenAll){
						$(".process-children").hide();
					}
					if($("#child"+id).css("display")=="none"){
						$("#child"+id+" .li-tag").show();
						$("#arrow"+id).removeClass("fa-caret-up");
					}else{
						$("#child"+id+" .li-tag").hide();
						$("#arrow"+id).addClass("fa-caret-up");
					}
					$("#child"+id).slideToggle("fast");
					
				}
				
				/*
				 * 判断当前目录下的孩子是否有数据
				 */
				$scope.isChildHasData = function(data){
					/**
					 * 有孩子则判断只有一个事有数据就显示，否则就是无数据样式
					 */
					var chDatas = data.children;
					if(chDatas.length>0){
						for(var index in chDatas){
							if(chDatas[index].isHasData){
								return false;
							}
						}
						return true;
					}else {
						//目录类型没有孩子显示为没数据
						if(chDatas.length==0 && data.PZLX==3){
							return true;
						}
						return !data.isHasData;
					}
				}
				
			}
			
		}
});

/**
 * input 表单渲染
 */
Platform_Directive_APP.directive('powerInput',function(){
	return {
		restrict:"EA",
		template:function(tElement,tAttrs){
			return '<div style="position: relative;">\
						<input ng-model="ngModel" ng-disabled="ngDisabled"\
							type="text" class="power-from-control"\
				data-options="{{ngDataOptions}}" />\
				<span ng-bind="unit" style="position: absolute;right:5px;top:8px;font-weight: 600;"></span>\
			</div>';
		},
		require: 'ngModel',
	    scope:{
	        ngModel:"=",
	        defaultVal:"@",
	        ngDataOptions:"@ngDataOptions",
	        ngDisabled:"=",
	        unit:"@ngUnit"
	    },
		transclude:true,
		controller:['$scope',function($scope){
			
		}],
		link:function(scope,elem, iAttrs){
			if(scope.defaultVal){
				scope.ngModel = scope.defaultVal;
			}
			if(scope.unit){
				scope.unit = "";
			}
		}
	}
});


/**
 * 领导审批组件
 */
Platform_Directive_APP.directive('powerApproval',function(){
	return {
		restrict:"EA",
		templateUrl:function(tElement,tAttrs){
			if(tAttrs.templateUrl){
				return Common.webRoot()+"/resources/epsm/common/plug-directive/template/approval/"+tAttrs.templateUrl;
			}
			return Common.webRoot()+"/resources/epsm/common/plug-directive/template/approval/approval.html";
		},
	    scope:{
	    	splxdm:"@",//审批类型代码
	    	ngModelDate:"=",//审批绑定时间
	    	ngModelSign:"=",//审批签名绑定
	    	ngModelOpinion:"=", //审批意见绑定
	    	opinionCode:"@", //审批公共代码值选择
	    	ngDisabled:"="
	    },
		transclude:true,
		controller:['$scope','$http',function($scope,$http){
			$scope.wirteAuth = false;
			//默认调查时间为当前时间
			Common.send($scope, $http, {
				method: "POST",
				url: Common.webRoot() + "/config/spyjlxcontroller/getsplxgxbyuserauth",
				data:JSON.stringify({lxdm:$scope.splxdm}),
				showTips : false,
				success: function(data){
					//请求相应的对象
					
					if(data.data.code==0){
						$scope.approval = data.data.result;
						//获取当前用户的操作权限
						if($scope.approval.CZQX){
							$scope.wirteAuth = true;
							$scope.$watch("ngModelDate",function(n,o){
								if(typeof n == 'undefined'){
									$scope.ngModelDate = new Date().getTime();
								}
							});
							//$scope.ngModelDate = new Date().getTime();
						}else{
							$scope.wirteAuth = false;
						}
					}else{
						$scope.approval = {};
					}
				}
		  });
		}],
		link:function(scope,elem, iAttrs){
			 
		}
	}
});



