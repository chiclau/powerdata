var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('CaseInformationListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.rdbt = {};
	$scope.ckbt = {};
	$scope.systemUser={};
	$scope.startTime = '2017';
	$scope.endTime = '2017';
	$scope.condition=[{"label":"地区","value":"1","sfxz":"true"},{"label":"年度","value":"2","sfxz":"true"}];
	$scope.showDjsj = 'djsjContent';
	$scope.showWtjg = 'wtjgContent';
	$scope.checkItem = function(item){
		if(item.check&&item.value==1){
			$scope.showWtjg = 'wtjgContent'
		}else if(item.check&&item.value==2){
			$scope.showDjsj = 'djsjContent'
		}else if(item.check&&item.value==3){
			$scope.showSblx = 'sblxContent'
		}
	};
	
	$scope.changeSelectedType = function(){
		if($scope.rdbt.type==3){
			$scope.condition.push({"label":"设备类型","value":"3","sfxz":""});
		}else{
			$scope.condition=[{"label":"地区","value":"1","sfxz":"true"},{"label":"年度","value":"2","sfxz":"true"}];
		}
	};
	//初始化表格
	$scope.NotificationGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'NotificationGrid',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height() -40 -90);
				$(".NotificationGrid").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height() -40 -90);
				$(".NotificationGrid").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs: [
			  {name :'ROW_ID', displayName :'序号', width :'5%'},
			  {name : 'xzqh', displayName : '地区', width :'15%' },
			  {name : 'nian', displayName : '年度' },
			  {name : 'sl', displayName : '机构数量', width :'10%'},
			  {name : 'zs', displayName : '技术人员数量', width :'10%'},
		  ],
		  enableSorting : false,
		  rowHeight : 45,
		  enableSelectAll : false,
		  enableRowHeaderSelection : false
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/ep/epsm/businessmanagement/entrust/controller/entrustcontroller/findentrust',
	      data:{
	          pageSize:10,
	          pageNum:1,
	          startTime:$scope.startTime,
	          endTime:$scope.endTime
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("NotificationGrid");
	        }
	      }
	  },
	  gridEvent: {
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.NotificationGrid);
	
	$scope.toSearch = function(){
		$scope.NotificationGrid.dataConfig.data.startTime = $scope.startTime;
		$scope.NotificationGrid.dataConfig.data.endTime = $scope.endTime;
		$scope.NotificationGrid.dataConfig.data.sheng = $scope.systemUser.XZQHDMSHENG;
		$scope.NotificationGrid.dataConfig.data.shi = $scope.systemUser.XZQHDMSHI;
		$scope.NotificationGrid.dataConfig.data.xian = $scope.systemUser.XZQHDMXIAN;
		$scope.NotificationGrid.dataConfig.data.type = $scope.rdbt.type;
		Common.page.pageChanged($scope, $http, $scope.NotificationGrid);
	};
}]);