var app = Common.initApp();
app.controller('orgUserManageController', ['$scope', '$http', 'uiGridConstants','$templateCache',
	function ($scope, $http, uiGridConstants, $templateCache) {
	
	$scope.orgUser = {};
	
	// 性别集合
	$scope.sexes = [{label: '请选择', value: ''}];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'SEX'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.sexes.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.sexes.selected = $scope.sexes[0];
		}
	});
	
	// 最高学历集合
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'EDU_TYPE'
		},
		success: function(result) {
			$scope.educations = result.data;
		}
	});
	
	// 职称集合
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'POST'
		},
		success: function(result) {
			$scope.titles = result.data;
		}
	});
	
	// 所在部门集合
	$scope.orgDepts = [{label: '请选择', value: ''}];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/findorgdept',
		data : {},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.orgDepts.push({label: datas[i].JCJGBM_BMMC, value: datas[i].JCJGBM_ID});						
			}
			$scope.orgDepts.selected = $scope.orgDepts[0];
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
				{name : 'JCJGRY_NAME', displayName : '姓名', width :'8%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_SEX', displayName : '性别', width :'6%', cellClass : "grid-col-center",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.sexShow(grid, row);}}</span>"},
				{name : 'JCJGRY_CSNY', displayName : '出生年月', width :'7%', cellClass : "grid-col-center"},
				{name : 'JCJGRY_ZGXL', displayName : '最高学历', width :'8%', cellClass : "grid-col-center",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.educationShow(grid, row);}}</span>"},
				{name : 'JCJGRY_ZY', displayName : '专业', width :'8%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_ZC', displayName : '职称', width :'8%', cellClass : "grid-col-center",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.titleShow(grid, row);}}</span>"},
				{name : 'JCJGRY_SZBM', displayName : '所在部门', width :'8%', cellClass : "grid-col-left",
					cellTemplate : "<span class='grid-vertical-middle'>{{grid.appScope.baseGrid.gridEvent.orgDeptShow(grid, row);}}</span>"},
				{name : 'JCJGRY_CSJSLYNX', displayName : '从事技术领域年限', width :'8%', cellClass : "grid-col-right"},
				{name : 'JCJGRY_ZW', displayName : '职务', width :'8%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_CSJSLY', displayName : '从事技术领域', width :'8%', cellClass : "grid-col-left"},
				{name : 'JCJGRY_BGWGZNX', displayName : '本岗位工作年限', width :'8%', cellClass : "grid-col-right"},
				{name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
					cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.baseGrid.gridEvent.readOrgUser(grid, row);'><span class='glyphicon glyphicon-search'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='证书' ng-click='grid.appScope.baseGrid.gridEvent.certificate(grid, row);'><span class='glyphicon glyphicon-book'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='编辑' ng-click='grid.appScope.baseGrid.gridEvent.editOrgUser(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>" +
						"&nbsp;&nbsp;&nbsp;<a style='height: 100%' title='删除' ng-click='grid.appScope.baseGrid.gridEvent.delOrgUser(grid, row);'><span class='glyphicon glyphicon-remove-circle'></span></a>"
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
			url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/findorguserpaging',
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
			sexShow : function (grid, row) {
				var sex = "";
				
				for (var i = 0; i < $scope.sexes.length; i++) {
    				if (row.entity.JCJGRY_SEX == $scope.sexes[i].value) {
    					sex = $scope.sexes[i].label;
    					break;
    				}
    			}
				return sex;
			},
			educationShow : function (grid, row) {
				var education = "";
				
				for (var i = 0; i < $scope.educations.length; i++) {
    				if (row.entity.JCJGRY_ZGXL == $scope.educations[i].PARAM0) {
    					education = $scope.educations[i].C_NAME;
    					break;
    				}
    			}
				return education;
			},
			titleShow : function (grid, row) {
				var title = "";
				
				for (var i = 0; i < $scope.titles.length; i++) {
    				if (row.entity.JCJGRY_ZC == $scope.titles[i].PARAM0) {
    					title = $scope.titles[i].C_NAME;
    					break;
    				}
    			}
				return title;
			},
			orgDeptShow : function (grid, row) {
				var orgDept = "";
				
				for (var i = 0; i < $scope.orgDepts.length; i++) {
    				if (row.entity.JCJGRY_JCJGBMBH == $scope.orgDepts[i].value) {
    					orgDept = $scope.orgDepts[i].label;
    					break;
    				}
    			}
				return orgDept;
			},
			certificate : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '本机构人员证书管理',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/certificatemanage/' + row.entity.JCJGRY_ID
		        });
			},
			delOrgUser : function (grid, row) {
				Common.dialog({
    				type : 'confirm',
    				content : '确认删除本机构人员吗？',
    				callback : function(index){
    					Common.send($scope, $http, {
    		        		method: 'POST',
    		        		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/delorguser',
    		        		showTips : true,
    		        		data: {
    		        			"JCJGRY_ID" : row.entity.JCJGRY_ID
		        			},
    		    			success: function(result){
    		    				Common.page.send($scope, $http, $scope.baseGrid);
    		    			}
    		        	});
    				}
    			});
			},
			readOrgUser : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '查看本机构人员',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/orguserdetail/read/' + row.entity.JCJGRY_ID
		        });
			},
			editOrgUser : function (grid, row) {
				Common.dialog({
		            type: 'open',
		            title: '修改本机构人员',
		            width: '95%',
		            height: '95%',
		            noScroll : 'true',
		            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/orguserdetail/edit/' + row.entity.JCJGRY_ID,
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
		
		$scope.orgUser.JCJGRY_SEX = $scope.sexes.selected.value;
		$scope.orgUser.JCJGRY_JCJGBMBH = $scope.orgDepts.selected.value;
		
		$scope.baseGrid.dataConfig.data = $scope.orgUser;
		
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
            title: '新增本机构人员',
            width: '95%',
            height: '95%',
            noScroll : 'true',
            url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/orguserdetail/add/no',
            end: function(){
            	Common.page.send($scope, $http, $scope.baseGrid);
            }
        });
	};
}]);