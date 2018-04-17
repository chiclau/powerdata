var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('CaseInformationListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {    
	$scope.searchGridData={};
	$scope.zts = [{label:"请选择",value:""},
	              {label:"未提交",value:"1"},
	              {label:"审核中",value:"2"},
	              {label:"审核失败",value:"3"},
	              {label:"审核通过(当前在用)",value:"4"},
	              {label:"审核通过",value:"5"}];
	$scope.searchGridData.zt = $scope.zts[0];
	$scope.CaseInformationGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'CaseInformationGrid',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height() -40 -60 -20);
				$(".CaseInformationGrid").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height() -40 -60 -20);
				$(".CaseInformationGrid").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs: [
			  {name :'ROW_ID', displayName :'序号', width :'5%'},
			  {name :'id', visible: false},
			  {name : 'famc', displayName : '监测方案名称', width :'15%'},
			  {name : 'bb', displayName : '版本', width :'8%'},
			  {name : 'qymc', displayName : '企业名称', width :'30%'},
			  {name : 'cjsj', displayName : '创建时间', width :'10%'},
			  {name : 'fakssj', displayName : '方案生效时间', width :'10%'},
			  {name : 'zt', displayName : '状态', width :'10%'},
			  {name : 'cz', displayName : '操作',
				  cellTemplate : "<a class='grid-vertical-middle' href='javascript:void(0);' ng-click='grid.appScope.CaseInformationGrid.gridEvent.openTemplate(grid, row);'><span class='grid-vertical-middle glyphicon glyphicon-cloud-upload'></span></a>&nbsp;&nbsp;\
				  		 <a style='height: 100%' title='修改' ng-click='grid.appScope.CaseInformationGrid.gridEvent.edit(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>&nbsp;&nbsp;\
						 <a style='height: 100%' title='查看' ng-click='grid.appScope.CaseInformationGrid.gridEvent.check(grid, row);'><span class='glyphicon glyphicon-search'></span></a>&nbsp;&nbsp;\
						 <a style='height: 100%' title='删除' ng-click='grid.appScope.CaseInformationGrid.gridEvent.del(grid, row);'><span class='glyphicon glyphicon-trash'></span></a>",
					cellClass : "tdButtonStyle"}
		  ],
		  rowHeight : 45,
		  enableSelectAll : false,
		  enableRowHeaderSelection : false  
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/co/epsm/dataacquisition/schememanage/controller/schememanagecontroller/findschememanage',
	      data:{
	          pageSize:10,
	          pageNum:1
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("CaseInformationGrid");
	        }
	      }
	  },
	  gridEvent: {
		  check: function (grid, row, $event) {
	          Common.dialog({
	              type : "open",
	              title : "详细信息",
	              width : '1300px',
	              height : '600px',
	              url : Common.link("/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/pagedetail?zbalid="+row.entity.zbalid)
	          });
		  },
		  edit: function (grid, row, $event) {
			  Common.send($scope, $http ,{
				 method: "POST",
	             url: Common.webRoot()+"/co/epsm/dataacquisition/schememanage/controller/schememanagecontroller/updateschememanage",
	             data:JSON.stringify({
	                "id":row.entity.id
	             }),
	             showTips : true,
	             success: function(data){
	            	Common.page.send($scope, $http, $scope.CaseInformationGrid);
	             }
			  });
		  },
		  del: function (grid, row, $event) {
			  Common.dialog({
		    	type:"confirm",
		    	content:"确认删除？",
		    	callback:function(){
		    		var ids=[];
					ids.push(row.entity.id);
					Common.send($scope, $http ,{
						method: "POST",
			            url: Common.webRoot()+"/co/epsm/dataacquisition/schememanage/controller/schememanagecontroller/delschememanage",
			            data:JSON.stringify({
			                "ids":ids
			            }),
			            showTips : true,
			            success: function(data){
			            	Common.page.send($scope, $http, $scope.CaseInformationGrid);
			            }
					});
		            Common.dialog({type:'close',index:1});
		        }
		    });
		  }
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.CaseInformationGrid);

	$scope.changeSelected = function(item, $model){
		$scope.zt = item.value;
	};
	
	$scope.toSearch = function(){
		$scope.CaseInformationGrid.dataConfig.data.famc = $scope.searchGridData.famc;
		$scope.CaseInformationGrid.dataConfig.data.zt = $scope.zt;
		$scope.CaseInformationGrid.dataConfig.data.bb = $scope.searchGridData.bb;
		$scope.CaseInformationGrid.dataConfig.data.cjsj = $scope.searchGridData.cjsj;
		Common.page.pageChanged($scope, $http, $scope.CaseInformationGrid);
	};

	$scope.clean = function(){
		$scope.searchGridData = {};
	}
	
     $('body').on('keypress', function (e) {
        platform.enterSearch(e, function () {
            $scope.toSearch();
        })
     });
}]);