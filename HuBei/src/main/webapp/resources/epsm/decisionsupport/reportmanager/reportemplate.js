var app = Common.initApp();
app.controller('reportemplateController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	//模版类型
    $scope.MBLX = [
        { label: "请选择",value: ''}
	];
    //默认类型
    $scope.MBLX.selected = $scope.MBLX[0];
    
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getstandardclassify',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.MBLX;
			for(var i = 0; i < datas.length; i++) {
				if(datas[i].VALID =='1'){
					dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
				}
			}
			$scope.MBLX = dataArr;
		}
	});

	$scope.baseGrid = {
		dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},    //添加注入的依赖
		gridID:'baseGrid',                                                             //grid表格ID，与HTML中的data-id一致
		height : function () { 														   //根据页面具体内容设置表格高度。
			$(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			});
			$(window).resize(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			})
		},
		gridOptions : {
			columnDefs: [                                                              //定义表格列内容       
				{name :'ROW_ID', displayName :'序号', width :'5%'},
				{name : 'M_NAME', displayName : '模版名称', width :'30%', cellClass : "grid-col-center"},
				{name : 'M_LB', displayName : '模版类型', width :'20%', cellClass : "grid-col-center",
					cellTemplate : '<div class="grid-vertical-middle">{{grid.appScope.baseGrid.gridEvent.baseGridLBShow(grid, row)}}</div>'},
				{name : 'M_CREATE_TIME', displayName : '创建时间', width :'20%', cellClass : "grid-col-center"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : 
						"<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readReportemplate(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editReportemplate(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delReportemplate(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
				}
			],
			//组合配置checkbox复选框
			enableSelectAll : false,          										//是否全选选择框按钮
			enableRowHeaderSelection : false,  										//是否选择框按钮

			//组合配置单行无复选框的选中
			enableRowSelection : true,
			multiSelect : false
		},
		dataConfig : {																//配置数据接口
			url: Common.webRoot() + '/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/queryreportemplate',
			data:{																	//配置数据过滤条件
				pageSize : Common.pageSize,
				pageNum:1
			}
		},
		gridEvent : {                                                               //表格事件方法
			/*以下部分的函数名称是自定义的*/
			_rowClickCallback: function () {
			},
			_dbClickRow : function (row,$event) {									//数据行双击事件，单击事件为：_rowClick(),默认有单击行事件，执行回调方式为：_rowClickCallback
			},
			baseGridLBShow : function (grid, row) {
				if(row.entity.M_LB=="A"){
					return " 废气";
        		}else if(row.entity.M_LB=="B"){
        			return " 废水";
        		}else if(row.entity.M_LB=="C"){
        			return " 无组织";
        		}else if(row.entity.M_LB=="E"){
        			return " 厂界噪声";
        		}else if(row.entity.M_LB=="D"){
        			return " 周边环境";
        		}
			},
			delReportemplate : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除报告模版吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/delreportemplate',
    		        		showTips : true,
    		        		data: {
    		        			"ID" : row.entity.M_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readReportemplate : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看联系人',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/pagedetail/read/' + row.entity.M_ID
		        });
			},
			editReportemplate : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改报告模版',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/reportemplatedetail/edit/' + row.entity.M_ID,
		            end: function(){
		            	Common.page.send($scope, $http, $scope.baseGrid);
		            }
		        });
			}
		}
	};
	Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格

	//查询条件
	$scope.changeSelectedMBLX = function (item, model) {
		$scope.search();
    };
	
	$scope.search = function() {
		$scope.baseGrid.dataConfig.data = $scope.template;
		$scope.baseGrid.dataConfig.data.lb = $scope.MBLX.selected.value;
		$scope.baseGrid.dataConfig.data.pageSize = Common.pageSize;
		$scope.baseGrid.dataConfig.data.pageNum = 1;
		
		Common.page.send($scope, $http, $scope.baseGrid);
	};
	
	$('body').on('keypress', function (e) {
       platform.enterSearch(e, function () {
           $scope.search();
       })
    });
	
	// 跳转到新增环保部门用户页面
	$scope.add = function() {
		Common.dialog({
            type: 'open',
            title: '新增报告模版',
            width: '90%',
            height: '90%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/reportemplatedetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);