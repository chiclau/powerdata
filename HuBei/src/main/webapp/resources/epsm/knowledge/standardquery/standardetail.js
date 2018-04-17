var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('standarDetailController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot()+'/both/epsm/knowledge/standardquery/standardquerycontroller/querystandardetail',
		data:{
			"id":$('#jbxxid').val()
		},
		success: function(result){
			var bzflmc="";
    		if(result.data.BZFL=="A"){
    			bzflmc = bzflmc + " 废气";
    		}
    		if(result.data.BZFL1=="B"){
    			bzflmc = bzflmc + " 废水";
    		}
    		if(result.data.BZFL2=="C"){
    			bzflmc = bzflmc + " 无组织";
    		}
    		if(result.data.BZFL3=="E"){
    			bzflmc = bzflmc + " 厂界噪声";
    		}
    		if(result.data.BZFL4=="D"){
    			bzflmc = bzflmc + " 周边环境";
    		}
    		result.data.BZFL=bzflmc;
			$scope.jbxx = result.data;
		}
	});          	
}])