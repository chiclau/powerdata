var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('SelfMonitoringMethodDetailController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot()+'/both/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller/findselfmonitoringmethodtitle',
		data:{
			"id":$('#jbxxid').val()
		},
		success: function(result){
			$scope.jbxx = result.data[0];
		}
	});          	
}]);

app.controller('SelfMonitoringMethodGridDetailController', ['$scope', '$http','uiGridConstants','$templateCache',
                                             function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.SelfMonitoringMethodGridDetail = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'SelfMonitoringMethodGridDetail',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height() -182);
				$(".SelfMonitoringMethodGridDetail").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height() -182);
				$(".SelfMonitoringMethodGridDetail").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs: [
			  {name : 'cyfl', displayName :'采样分类', width :'12%', cellClass : "grid-col-left"},
			  {name : 'qyl', displayName : '取样量', width :'5%'},
			  {name : 'qydw', displayName : '企业单位', width :'7%'},
			  {name : 'drtj', displayName : '定容体积', width :'7%'},
			  {name : 'jyl', displayName : '进样量', width :'5%'},
			  {name : 'jydw', displayName : '进样单位', width :'7%'},
			  {name : 'jcnd', displayName : '监测浓度', width :'7%'},
			  {name : 'jcx', displayName : '检出限', width :'5%'},
			  {name : 'cdxx', displayName : '测定下限', width :'7%'},
			  {name : 'cdsx', displayName : '测定上限', width :'7%'},
			  {name : 'cdfw', displayName : '测定范围', width :'7%'},
			  {name : 'jcdw', displayName : '检出单位', width :'7%'},
			  {name : 'ffyl', displayName : '方法原理', cellClass : "grid-col-left"}
		  ],
	  	  enableSelectAll : false,
		  enableRowHeaderSelection : false
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/both/epsm/knowledge/selfmonitoringmethod/controller/selfmonitoringmethodcontroller/findselfmonitoringmethod',
	      data:{
	          pageSize:10,
	          pageNum:1,
	          ffmc:$scope.ffmc, 
	          ffbzmc:$scope.ffbzmc, 
	          ffbzbh:$scope.ffbzbh,
	          ffbzfl:$scope.ffbzfl,
	          jcxmmc:$scope.jcxmmc,
	          id:$('#jbxxid').val()
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("SelfMonitoringMethodGridDetail");
	        }
	      }
	  },
	  gridEvent: {
		  _sortChangeCallback:false
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.SelfMonitoringMethodGridDetail);
}]);