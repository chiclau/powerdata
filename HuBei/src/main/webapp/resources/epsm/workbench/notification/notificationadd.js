var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('caseInformationAddController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {

	 Common.send($scope, $http, {
		 method: 'POST',
		 url: Common.webRoot() + '/both/epsm/workbench/notification/controller/notificationcontroller/findArea',
		 success: function (result) {
			 $scope.area = result.data;
		 }
	 });
	
	 var qhdm = [];
	 $scope.checkItemUser = function(item){
		if(item.check){
			qhdm.push(item.xzqhdm);
		}else{
			var index = qhdm.indexOf(item.xzqhdm);
			qhdm.splice(index, 1);
		}
	 };
		
	$scope.toAdd = function(){
		if(!Common.validate()) {
			return;
		}
		Common.send($scope, $http, {
		  method: 'POST',
		  url: Common.webRoot() + '/ep/epsm/workbench/notification/controller/notificationcontroller/addnotification',
		  data: {
			  "title":$scope.title,
			  "content":$scope.content,
			  "validdate":$scope.validdate,
			  "invaliddate":$scope.invaliddate,
			  "fbsj":$scope.fbsj,
			  "fbfw":qhdm
		  },
		  success: function (result) {
			  Common.RBTips("操作成功", 1);
		  }
	  })
	};
	
	
}]);