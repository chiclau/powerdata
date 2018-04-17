var app = Common.initApp();
app.controller('certificateManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
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
				{name : 'JCJGRY_ZSMC', displayName : '证书名称', width :'15%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_ZSDYJCXM', displayName : '证书对应监测项目', width :'40%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_ZSFZSJ', displayName : '发证时间', width :'12%', cellClass : "grid-col-center"},
				{name : 'JCJGRY_ZSDQSJ', displayName : '证书到期时间', width :'12%', cellClass : "grid-col-center"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readCertificate(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editCertificate(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delCertificate(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/findcertificatepaging',
			data:{																	//配置数据过滤条件
				pageSize : Common.pageSize,
				pageNum:1,
				JCJGRY_RYBH : $scope.certificate.JCJGRY_RYBH
			}
		},
		gridEvent : {                                                               //表格事件方法
			/*以下部分的函数名称是自定义的*/
			_rowClickCallback: function () {
			},
			_dbClickRow : function (row,$event) {									//数据行双击事件，单击事件为：_rowClick(),默认有单击行事件，执行回调方式为：_rowClickCallback
				
			},
			delCertificate : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除本机构人员证书吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/delcertificate',
    		        		showTips : true,
    		        		data: {
    		        			"JCJGRY_ZSBH" : row.entity.JCJGRY_ZSBH
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readCertificate : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看本机构人员证书',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/certificatedetail/read/' + $scope.certificate.JCJGRY_RYBH + '/' + row.entity.JCJGRY_ZSBH
		        });
			},
			editCertificate : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改本机构人员证书',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/certificatedetail/edit/' + $scope.certificate.JCJGRY_RYBH + '/' + row.entity.JCJGRY_ZSBH,
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
            title: '新增本机构人员证书',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/certificatedetail/add/' + $scope.certificate.JCJGRY_RYBH + '/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);