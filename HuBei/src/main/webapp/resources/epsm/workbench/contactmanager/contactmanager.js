var app = Common.initApp();
app.controller('contactManagerController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	// 用户类型集合
	$scope.userTypes = [
        { label: "请选择",value: ''},
		{ label: "管理用户",value: 'HBYH_GLJS'},
		{ label: "审核用户",value: 'HBYH_SHJS'},
		{ label: "认证用户",value: 'HBYH_RZJS'}
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
				{name : 'LXRXM', displayName : '姓名', width :'11%', cellClass : "grid-col-center"},
				{name : 'LXRMC', displayName : '单位名称', width :'11%', cellClass : "grid-col-center"},
				{name : 'ZJ', displayName : '座机', width :'11%', cellClass : "grid-col-center"},
				{name : 'SJ', displayName : '手机', width :'13%', cellClass : "grid-col-center"},
				{name : 'CZ', displayName : '传真', width :'13%', cellClass : "grid-col-center"},
				{name : 'DZYX', displayName : '电子邮箱', width :'13%', cellClass : "grid-col-center"},
				{name : 'YHLB', displayName : '用户类别', width :'12%', cellClass : "grid-col-center",
					cellTemplate : '<div class="grid-vertical-middle">{{grid.appScope.baseGrid.gridEvent.baseGridYHLBShow(grid, row)}}</div>'},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : 
						"<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readContact(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editContact(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delContact(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/querycontacts',
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
			baseGridYHLBShow : function (grid, row) {
				if(row.entity.YHLB=="HBYH_GLJS"){
					return "管理用户";
        		}else if(row.entity.YHLB=="HBYH_SHJS"){
        			return "审核用户";
        		}else if(row.entity.YHLB=="HBYH_RZJS"){
        			return "认证用户";
        		}
			},
			delContact : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除联系人吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/delcontacts',
    		        		showTips : true,
    		        		data: {
    		        			"ID" : row.entity.ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readContact : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看联系人',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/pagedetail/read/' + row.entity.ID
		        });
			},
			editContact : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改联系人',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/pagedetail/edit/' + row.entity.ID,
		            end: function(){
		            	Common.page.send($scope, $http, $scope.baseGrid);
		            }
		        });
			}
		}
	};
	Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格

	//查询条件
	$scope.changeSelectedUserType = function (item, model) {
		$scope.search();
    };
	
	$scope.search = function() {
		$scope.baseGrid.dataConfig.data = $scope.contact;
		$scope.baseGrid.dataConfig.data.yhlb = $scope.userTypes.selected.value;
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
            title: '新增联系人',
            width: '90%',
            height: '90%',
            noScroll : 'true',
            url: Common.webRoot() + '/both/epsm/workbench/contactmanager/contactmanagercontroller/pagedetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);