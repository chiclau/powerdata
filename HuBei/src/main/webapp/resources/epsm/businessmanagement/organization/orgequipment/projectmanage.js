var app = Common.initApp();
app.controller('projectManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	// 监测产品类别集合
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
				var roldGridHeight = ($('.edit-body').height() - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			});
			$(window).resize(function () {
				var roldGridHeight = ($('.edit-body').height() - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			})
		},
		gridOptions : {
			columnDefs: [                                                              //定义表格列内容       
				{name :'ROW_ID', displayName :'序号', width :'5%'},
				{name : 'JCJG_SBJCX_JCCPLB', displayName : '监测产品类别', width :'15%', cellClass : "grid-col-left",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.equipmentTypeShow(grid, row);}}</span>"},
				{name : 'JCJG_SBJCX_JCXM', displayName : '监测项目', width :'40%', cellClass : "grid-col-left"},
				{name : 'JCJG_SBJCX_FFYL', displayName : '方法原理', width :'12%', cellClass : "grid-col-center"},
				{name : 'JCJG_SBJCX_CZRY', displayName : '持证人员', width :'12%', cellClass : "grid-col-center"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readProject(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editProject(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delProject(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/findprojectpaging',
			data:{																	//配置数据过滤条件
				pageSize : Common.pageSize,
				pageNum:1,
				JCJG_SBJCX_SBID : $scope.project.JCJG_SBJCX_SBID
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
    				if (row.entity.JCJG_SBJCX_JCCPLB == $scope.equipmentTypes[i].PARAM0) {
    					equipmentType = $scope.equipmentTypes[i].C_NAME;
    					break;
    				}
    			}
				return equipmentType;
			},
			delProject : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除本机构设备监测项目吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/delproject',
    		        		showTips : true,
    		        		data: {
    		        			"JCJG_SBJCX_ID" : row.entity.JCJG_SBJCX_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readProject : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看本机构设备监测项目',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/projectdetail/read/' + $scope.project.JCJG_SBJCX_SBID + '/' + row.entity.JCJG_SBJCX_ID
		        });
			},
			editProject : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改本机构设备监测项目',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/projectdetail/edit/' + $scope.project.JCJG_SBJCX_SBID + '/' + row.entity.JCJG_SBJCX_ID,
		            end: function(){
		            	Common.page.send($scope, $http, $scope.baseGrid);
		            }
		        });
			}
		}
	};
	Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格
	
	// 跳转到新增环保部门用户页面
	$scope.add = function() {
		Common.dialog({
            type: 'open',
            title: '新增本机构设备监测项目',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/projectdetail/add/' + $scope.project.JCJG_SBJCX_SBID + '/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);