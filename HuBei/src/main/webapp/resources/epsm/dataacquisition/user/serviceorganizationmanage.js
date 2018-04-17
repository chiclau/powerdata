var app = Common.initApp();
app.controller('serviceOrganizationManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	$scope.organizationType = {};
	$scope.serviceOrganization = {};
	
	$scope.baseGrid = {
		dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},    //添加注入的依赖
		gridID:'baseGrid',                                                             //grid表格ID，与HTML中的data-id一致
		height : function () { 														   //根据页面具体内容设置表格高度。
			$(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 -35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			});
			$(window).resize(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 -35 - 29 - 10) + "px";
				angular.element($(".baseGrid")).css('height', roldGridHeight);
			})
		},
		gridOptions : {
			columnDefs: [                                                              //定义表格列内容       
				{name :'ROW_ID', displayName :'序号', width :'5%'},
				{name : 'JBXX_NAME', displayName : '机构名称', width :'10%', cellClass : "grid-col-left"},
				{name : 'JBXX_TYPE', displayName : '机构类型', width :'30%', cellClass : "grid-col-left",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.serviceOrganizationTypeShow(grid, row);}}</span>"},
				{name : 'JBXX_GSSZD', displayName : '公司所在地', width :'20%', cellClass : "grid-col-left"},
				{name : 'JCJG_SFBA', displayName : '是否备案', width :'5%', cellClass : "grid-col-center",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.isRecordShow(grid, row);}}</span>"},
				{name : 'JBXX_LXR', displayName : '联系人', width :'6%', cellClass : "grid-col-left"},
				{name : 'JBXX_LXDH', displayName : '联系电话', width :'6%', cellClass : "grid-col-right"},
				{name : 'USERDLMC', displayName : '登录号', width :'6%', cellClass : "grid-col-center"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='密码重置' ng-click='grid.appScope.baseGrid.gridEvent.resetPassword(grid, row);'><span class='glyphicon glyphicon-lock'></span></a>" + 
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readServiceOrganization(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editServiceOrganization(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delServiceOrganization(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/both/epsm/dataacquisition/user/serviceorganizationcontroller/findserviceorganization',
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
			serviceOrganizationTypeShow : function (grid, row) {
				var serviceOrganizationType = '';
				if ('1' == row.entity.JBXX_JCJG) {
					serviceOrganizationType += '检测机构 ';
				}
				
				if ('1' == row.entity.JBXX_YYJG) {
					serviceOrganizationType += '运营机构 ';
				}
				
				if ('1' == row.entity.JBXX_JCSB) {
					serviceOrganizationType += '在线监测设备供应商 ';
				}
				
				if ('1' == row.entity.JBXX_JCS) {
					serviceOrganizationType += '集成商 ';
				}
				
				if (serviceOrganizationType) {
					serviceOrganizationType = serviceOrganizationType.substring(0, serviceOrganizationType.length - 1);
				}
				
				return serviceOrganizationType;
			},
			isRecordShow : function (grid, row) {
				var isRecord = '';
				if ('1' == row.entity.JCJG_SFBA) {
					isRecord = '是 ';
				} else if ('0' == row.entity.JCJG_SFBA) {
					isRecord = '否 ';
				}
				
				return isRecord;
			},
			resetPassword : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认重置密码吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/platform/rms/usercontroller/updateuser',
    		        		showTips : true,
    		        		data: {
    		        			"YHID" : row.entity.YHID,
    		        			"YHMM" : 8888
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			delServiceOrganization : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除委托机构吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/both/epsm/dataacquisition/user/serviceorganizationcontroller/delserviceorganization',
    		        		showTips : true,
    		        		data: {
    		        			"JBXX_ID" : row.entity.JBXX_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readServiceOrganization : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看委托机构',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/both/epsm/dataacquisition/user/serviceorganizationcontroller/serviceorganizationdetail/read/' + row.entity.USERDLMC
		        });
			},
			editServiceOrganization : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改委托机构',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/both/epsm/dataacquisition/user/serviceorganizationcontroller/serviceorganizationdetail/edit/' + row.entity.USERDLMC,
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
		$scope.baseGrid.dataConfig.data = $scope.serviceOrganization;
		$scope.baseGrid.dataConfig.data.pageSize = Common.pageSize;
		$scope.baseGrid.dataConfig.data.pageNum = 1;
		
		if ($scope.organizationType.isDetector) {
			$scope.baseGrid.dataConfig.data.JBXX_JCJG = '1';
		} else {
			$scope.baseGrid.dataConfig.data.JBXX_JCJG = '';
		}
		
		if ($scope.organizationType.isOperator) {
			$scope.baseGrid.dataConfig.data.JBXX_YYJG = '1';
		} else {
			$scope.baseGrid.dataConfig.data.JBXX_YYJG = '';
		}
		
		if ($scope.organizationType.isSupplier) {
			$scope.baseGrid.dataConfig.data.JBXX_JCSB = '1';
		} else {
			$scope.baseGrid.dataConfig.data.JBXX_JCSB = '';
		}
		
		if ($scope.organizationType.isIntegrator) {
			$scope.baseGrid.dataConfig.data.JBXX_JCS = '1';
		} else {
			$scope.baseGrid.dataConfig.data.JBXX_JCS = '';
		}
		
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
            title: '新增委托机构',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/both/epsm/dataacquisition/user/serviceorganizationcontroller/serviceorganizationdetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);