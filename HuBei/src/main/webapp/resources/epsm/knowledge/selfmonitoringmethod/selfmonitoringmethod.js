var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

//app.controller('SelfMonitoringMethodController', ['$scope', '$http','uiGridConstants','$templateCache',
//                                 function ($scope, $http, uiGridConstants,$templateCache) {
//	$scope.toSearch = function(){
//		debugger;
//		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffmc = $scope.ffmc;
//		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzmc = $scope.ffbzmc;
//		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzbh = $scope.ffbzbh;
//		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzfl = $scope.ffbzfl;
//		$scope.SelfMonitoringMethodGrid.dataConfig.data.jcxmmc = $scope.jcxmmc;
//		Common.page.pageChanged($scope, $http, $scope.SelfMonitoringMethodGrid);
//	}
//}]);

app.controller('SelfMonitoringMethodGridController', ['$scope', '$http','uiGridConstants','$templateCache',
                                             function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.years = [
	                { label: "请选择",value: ''},
	                { label: "废气",value: 'A'},
					{ label: "废水",value: 'B'},
					{ label: "无组织",value: 'C'},
					{ label: "周边环境",value: 'D'},
					{ label: "厂界噪声",value: 'E'}
	             ];
	$scope.ffbzfl = $scope.years[0];
	$scope.SelfMonitoringMethodGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'SelfMonitoringMethodGrid',
	  height : function () { 														   //根据页面具体内容设置表格高度。
			$(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 30) + "px";
				angular.element($(".SelfMonitoringMethodGrid")).css('height', roldGridHeight);
			});
			$(window).resize(function () {
				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 30) + "px";
				angular.element($(".SelfMonitoringMethodGrid")).css('height', roldGridHeight);
			})
		},
	  gridOptions : {
		  columnDefs: [
			  {name :'ROW_ID', displayName :'序号', width :'5%'},
			  {name :'id', visible: false},
			  {name : 'ffmc', displayName : '方法名称', width :'10%', cellClass : "grid-col-left"},
			  {name : 'ffbzmc', displayName : '方法标准名称', width :'15%', cellClass : "grid-col-left"},
			  {name : 'ffbzbh', displayName : '方法标准编号', width :'10%', cellClass : "grid-col-left"},
			  {name : 'ffbzfl', displayName : '方法标准分类', width :'10%'},
			  {name : 'ffbzdt', displayName : '方法标准代替', width :'10%'},
			  {name : 'fbrq', displayName : '发布日期', width :'10%'},
			  {name : 'ssrq', displayName : '实施日期', width :'10%'},
			  {name : 'jcxmmc', displayName : '监测项目', width :'10%', cellClass : "grid-col-left"},
			  {name : 'cz', displayName : '操作', width :'10%',
					cellTemplate : "<a style='height: 100%' title='查看' href='javascript: void(0);' ng-click='grid.appScope.SelfMonitoringMethodGrid.gridEvent.check(grid, row);'><span class='glyphicon glyphicon-search'></span></a>",
					cellClass : "tdButtonStyle"}
		  ],
		  rowHeight : 45,
		  enableSelectAll : false,
		  enableRowHeaderSelection : false
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/both/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller/findselfmonitoringmethod',
	      data:{
	          pageSize:10,
	          pageNum:1
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("SelfMonitoringMethodGrid");
	        }
	      }
	  },
	  gridEvent: {
		  check: function (grid, row, $event) {
			  console.log(row.entity.id);
	          Common.dialog({
	              type : "open",
	              title : "详细信息",
	              width : '950px',
	              height : '600px',
	              url : Common.link("/both/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller/pagedetail?id="+row.entity.id)
	          });
//			  Common.send($scope, $http, {
//				  method: 'POST',
//				  url: Common.webRoot() + '/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller/findselfmonitoringmethod',
//				  data: {
//					  "zt": zt, 
//					  "bh": row.entity.bh
//				  },
//				  success: function (result) {
//					  Common.page.pageChanged($scope, $http, $scope.userGrid);
//				  }
//			  });
		  }
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.SelfMonitoringMethodGrid);
	
	$scope.toSearch = function(){
		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffmc = $scope.ffmc;
		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzmc = $scope.ffbzmc;
		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzbh = $scope.ffbzbh;
		$scope.SelfMonitoringMethodGrid.dataConfig.data.ffbzfl = $scope.ffbzfl.value;
		$scope.SelfMonitoringMethodGrid.dataConfig.data.jcxmmc = $scope.jcxmmc;
		Common.page.pageChanged($scope, $http, $scope.SelfMonitoringMethodGrid);
	}
	
	$('body').on('keypress', function (e) {
        platform.enterSearch(e, function () {
            $scope.toSearch();
        })
     });
}]);