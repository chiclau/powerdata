var app = Common.initApp();
app.controller('systemUserManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	// 初始化选择条件：行政区划
	$scope.systemUser = {};
	if ($scope.currUserProvince) {
		$scope.systemUser.XZQHDMSHENG = $scope.currUserProvince;
		$scope.isReadProvince = true;
	}
	if ($scope.currUserCity) {
		$scope.systemUser.XZQHDMSHI = $scope.currUserCity;
		$scope.isReadCity = true;
	}
	if ($scope.currUserArea) {
		$scope.systemUser.XZQHDMXIAN = $scope.currUserArea;
		$scope.isReadArea = true;
	}
	
	// 用户类型集合
	$scope.userTypes = [
        { label: "全部类别",value: ''},
		{ label: "管理用户",value: 'HBYH_GLJS'},
		{ label: "环保填报用户",value: 'HBYH_HBTBJS'},
		{ label: "方案审核用户",value: 'HBYH_FASHJS'},
		{ label: "监督性监测审核用户",value: 'HBYH_JDXJCSHJS'},
		{ label: "查看用户",value: 'HBYH_CKJS'}
    ];
    //初始化用户类型下拉列表，默认选择全部类别
	$scope.userTypes.selected = $scope.userTypes[0];

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
				{name : 'XZQHMCSHENG', displayName : '所在行政区省', width :'11%', cellClass : "grid-col-center"},
				{name : 'XZQHMCSHI', displayName : '所在行政区市', width :'11%', cellClass : "grid-col-center"},
				{name : 'XZQHMCXIAN', displayName : '所在行政区县', width :'11%', cellClass : "grid-col-center"},
				{name : 'YHMC', displayName : '用户名称', width :'13%', cellClass : "grid-col-left"},
				{name : 'XTZH', displayName : '登录号', width :'13%', cellClass : "grid-col-center"},
				{name : 'ZHJGMC', displayName : '综合机构名称', width :'13%', cellClass : "grid-col-left"},
				{name : 'BGDH', displayName : '环保联系人电话', width :'12%', cellClass : "grid-col-left"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='密码重置' ng-click='grid.appScope.baseGrid.gridEvent.resetPassword(grid, row);'><span class='glyphicon glyphicon-lock'></span></a>" + 
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readUser(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editUser(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delUser(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/dataacquisition/user/businessusercontroller/findsystemuser',
			data:{																	//配置数据过滤条件
				pageSize : Common.pageSize,
				pageNum:1,
				XZQHDMSHENG : $scope.currUserProvince,
				XZQHDMSHI : $scope.currUserCity,
				XZQHDMXIAN : $scope.currUserArea
			}
		},
		gridEvent : {                                                               //表格事件方法
			/*以下部分的函数名称是自定义的*/
			_rowClickCallback: function () {
			},
			_dbClickRow : function (row,$event) {									//数据行双击事件，单击事件为：_rowClick(),默认有单击行事件，执行回调方式为：_rowClickCallback
				
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
			delUser : function (grid, row) {
				if (row.entity.YHID == $scope.currUserId) {
					Common.dialog({
	    				content : '不能删除当前用户!'
	    			});
					return;
				}
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除用户吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/dataacquisition/user/businessusercontroller/deluser',
    		        		showTips : true,
    		        		data: {
    		        			"YHID" : row.entity.YHID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readUser : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看环保部门用户',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/dataacquisition/user/businessusercontroller/systemuserdetail/read/' + row.entity.XTZH
		        });
			},
			editUser : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改环保部门用户',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/dataacquisition/user/businessusercontroller/systemuserdetail/edit/' + row.entity.XTZH,
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
		$scope.baseGrid.dataConfig.data = $scope.systemUser;
		$scope.baseGrid.dataConfig.data.YHJS = $scope.userTypes.selected.value;
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
            title: '新增环保部门用户',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/dataacquisition/user/businessusercontroller/systemuserdetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);