var app = Common.initApp();
app.controller('orgEquipmentManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	$scope.orgEquipment = {};
	
	// 设备类型集合
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'DEVICE'
		},
		success: function(result) {
			$scope.equipmentTypes = result.data;
		}
	});
	
	$scope.baseGrid = {
		dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},    //添加注入的依赖
		gridID:'baseGrid',                                                             //grid表格ID，与HTML中的data-id一致
		height : function () { 														   //根据页面具体内容设置表格高度。
			$(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			});
			$(window).resize(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			})
		},
		gridOptions : {
			columnDefs: [                                                              //定义表格列内容       
				{name :'ROW_ID', displayName :'序号', width :'5%'},
				{name : 'JCJGSB_SBBH', displayName : '设备编号', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_SBMC', displayName : '设备名称', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_JCSBXH', displayName : '设备型号', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_TYPE', displayName : '设备类型', width :'10%', cellClass : "grid-col-left",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.equipmentTypeShow(grid, row);}}</span>"},
				{name : 'JCJGSB_SCCJ', displayName : '生产厂家', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_GDZCBH', displayName : '固定资产编号', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_LXR', displayName : '联系人', width :'10%', cellClass : "grid-col-left"},
				{name : 'JCJGSB_LXRDH', displayName : '联系人电话', width :'10%', cellClass : "grid-col-right"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readOrgEquipment(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='监测项目' ng-click='grid.appScope.baseGrid.gridEvent.project(grid, row);'><span class='glyphicon glyphicon-tasks'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editOrgEquipment(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delOrgEquipment(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/findorgequipmentpaging',
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
			equipmentTypeShow : function (grid, row) {
				var equipmentType = "";
				
				for (var i = 0; i < $scope.equipmentTypes.length; i++) {
    				if (row.entity.JCJGSB_TYPE == $scope.equipmentTypes[i].PARAM0) {
    					equipmentType = $scope.equipmentTypes[i].C_NAME;
    					break;
    				}
    			}
				return equipmentType;
			},
			project : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '本机构设备监测项目管理',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/projectmanage/' + row.entity.JCJGSB_ID
		        });
			},
			delOrgEquipment : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除本机构设备吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/delorgequipment',
    		        		showTips : true,
    		        		data: {
    		        			"JCJGSB_ID" : row.entity.JCJGSB_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readOrgEquipment : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看本机构设备',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/orgequipmentdetail/read/' + row.entity.JCJGSB_ID
		        });
			},
			editOrgEquipment : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改本机构设备',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/orgequipmentdetail/edit/' + row.entity.JCJGSB_ID,
		            end: function(){
		            	Common.page.send($scope, $http, $scope.baseGrid);
		            }
		        });
			}
		}
	};
	Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格
	
	// 查询环保用户管理员
	$scope.search = function() {
		$scope.baseGrid.dataConfig.data = $scope.orgEquipment;
		
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
            title: '新增本机构设备',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/orgequipmentdetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);