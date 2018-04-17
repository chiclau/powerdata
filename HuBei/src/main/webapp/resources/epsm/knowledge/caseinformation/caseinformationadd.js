var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('caseInformationAddController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.toAdd = function(){
		$scope.searchGridData;
		if(!Common.validate()) {
			return;
		}
		Common.send($scope, $http, {
		  method: 'POST',
		  url: Common.webRoot() + '/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/addcaseinformation',
		  data:$scope.searchGridData,
		  success: function (result) {
			  Common.RBTips("操作成功", 1);
		  }
	  })
	};         	
}]);