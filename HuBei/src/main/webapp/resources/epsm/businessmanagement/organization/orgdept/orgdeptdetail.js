var app = Common.initApp();
Common.initDirective(app);
app.controller('orgDeptDetailController', ['$scope', '$http', function($scope, $http) {
	
	// 查看操作则设置为只读
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/getorgdeptbyid",
    		data : {
    			"JCJGBM_ID" : $scope.orgDept.JCJGBM_ID
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.orgDept = result.data;
    		}
    	});
	}
	
	// 保存
	$scope.save = function() {
		
		// 校验表单信息
		// flag ： 验证结果标志
		var flag = Common.validate();

		// 验证不通过，不再往下执行
		if (!flag) {
			return;
		}
		
		var url = Common.webRoot() + "/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/";
		if ($scope.operationType == 'add') {
			url += "addorgdept";
		} else if ($scope.operationType == 'edit') {
			url += "updateorgdept";
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.orgDept,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);