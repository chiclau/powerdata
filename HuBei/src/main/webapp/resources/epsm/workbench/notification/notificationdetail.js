var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('caseInformationAddController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {

	 Common.send($scope, $http, {
		 method: 'POST',
		 url: Common.webRoot() + '/ep/epsm/workbench/notification/controller/notificationcontroller/findnotification',
		 data:{
			 "id":$('#jbxxid').val()
		},
		 success: function (result) {
			 $scope.detail = result.data.list[0];
		 }
	 });
	 
}]);