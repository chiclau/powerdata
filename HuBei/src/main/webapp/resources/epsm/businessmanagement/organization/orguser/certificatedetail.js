var app = Common.initApp();
Common.initDirective(app);
app.controller('certificateDetailController', ['$scope', '$http', function($scope, $http) {
	
	// 性别集合
	$scope.certificateTypes = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'DEVICE'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.certificateTypes.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.certificateTypes.selected = $scope.certificateTypes[0];
		}
	});
	
	// 查看操作则设置为只读
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/getcertificatebyid",
    		data : {
    			"JCJGRY_ZSBH" : $scope.certificate.JCJGRY_ZSBH
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.certificate = result.data;
    			
    			// 初始化证书类型
    			for (var i = 0; i < $scope.certificateTypes.length; i++) {
    				if ($scope.certificate.JCJGRY_ZSLX == $scope.certificateTypes[i].value) {
    					$scope.certificateTypes.selected = $scope.certificateTypes[i];
    					break;
    				}
    			}
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
		
		$scope.certificate.JCJGRY_ZSLX = $scope.certificateTypes.selected.value;
		
		var url = Common.webRoot() + "/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/";
		if ($scope.operationType == 'add') {
			url += "addcertificate";
		} else if ($scope.operationType == 'edit') {
			url += "updatecertificate";
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.certificate,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);