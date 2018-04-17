var app = Common.initApp();
Common.initDirective(app);
app.controller('OrganizationController', ['$scope', '$http', function($scope, $http) {
	
	// 操作标识
	var operation = 'edit';
	
	// 获取初始化数据
	Common.send($scope, $http, {
		method : "POST",
		url : Common.webRoot() + '/ep/epsm/businessmanagement/organization/organization/organizationcontroller/getorganizationbyregion',
		success : function(result) {
			if (result.data) {
				$scope.organization = result.data;
			} else {
				operation = 'add';
			}
		}
	});
	
	// 保存
	$scope.save = function() {
		// 校验表单信息
		// flag ： 验证结果标志
		var flag = Common.validate();

		// 验证不通过，不再往下执行
		if (!flag) {
			return;
		}
		
		var url = Common.webRoot() + '/ep/epsm/businessmanagement/organization/organization/organizationcontroller/updateorganization';
		if (operation == 'add') {
			url = Common.webRoot() + '/ep/epsm/businessmanagement/organization/organization/organizationcontroller/addorganization';
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.organization,
    		showTips : true,
    		success : function(result){
    			if (operation == 'add') {
    				$scope.organization.JCJG_ID = result.data.JCJG_ID;
    				operation = 'edit';
    			}
    		}
    	});
	};
} ]);