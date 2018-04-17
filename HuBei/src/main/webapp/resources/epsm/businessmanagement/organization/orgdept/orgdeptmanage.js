var app = Common.initApp();
app.controller('orgDeptManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	$scope.orgDept = {};
	
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
				{name : 'JCJGBM_BMMC', displayName : '部门名称', width :'20%', cellClass : "grid-col-left"},
				{name : 'JCJGBM_BMLXR', displayName : '部门联系人', width :'20%', cellClass : "grid-col-left"},
				{name : 'JCJGBM_BMLXRDH', displayName : '部门联系人电话', width :'20%', cellClass : "grid-col-right"},
				{name : 'JCJGBM_BMJJ', displayName : '部门简介', width :'25%', cellClass : "grid-col-left"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readOrgDept(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editOrgDept(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delOrgDept(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/findorgdeptpaging',
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
			delOrgDept : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除本机构部门吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/delorgdept',
    		        		showTips : true,
    		        		data: {
    		        			"JCJGBM_ID" : row.entity.JCJGBM_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readOrgDept : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看本机构部门',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/orgdeptdetail/read/' + row.entity.JCJGBM_ID
		        });
			},
			editOrgDept : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改本机构部门',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/orgdeptdetail/edit/' + row.entity.JCJGBM_ID,
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
		$scope.baseGrid.dataConfig.data = $scope.orgDept;
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
            title: '新增本机构部门',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/orgdeptdetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);