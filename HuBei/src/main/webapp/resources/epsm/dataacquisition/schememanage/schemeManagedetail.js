var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('caseInformationAddController', ['$scope', '$http','uiGridConstants','$templateCache',
                                                  function ($scope, $http, uiGridConstants,$templateCache) {

	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/findcaseinformation',
		data: {
			"zbalid":$('#jbxxid').val()
		},
		success: function (result) {
			$scope.searchGridData = result.data.list[0];
		}   
	})
}]);