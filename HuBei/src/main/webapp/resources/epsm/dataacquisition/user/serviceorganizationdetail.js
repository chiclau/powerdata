var app = Common.initApp();
Common.initDirective(app);
app.controller('serviceOrganizationDetailController', ['$scope', '$http', function($scope, $http) {
	$scope.region = {};
	$scope.organizationType = {};
	$scope.superiorCompany = {};
	$scope.record = {};
	
	// 查看操作则设置为只读
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/both/epsm/dataacquisition/user/serviceorganizationcontroller/getserviceorganizationbyaccount",
    		data : {
    			"USERDLMC" : $scope.serviceOrganization.XTZH
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.serviceOrganization = result.data;
    			
    			$scope.serviceOrganization.XTZH = $scope.serviceOrganization.USERDLMC;
    			
    			// 初始化用户密码
    			$scope.serviceOrganization.YHMM = '########';
    			
    			// 初始化是否有上级公司
    			if ('1' == $scope.serviceOrganization.JBXX_SFYSJGS) {
    				$scope.superiorCompany.hasSuperiorCompany = true;
    			}
    			
    			// 初始化机构类型
    			if ('1' == $scope.serviceOrganization.JBXX_JCJG) {
    				$scope.organizationType.isDetector = true;
    				if ('1' == $scope.serviceOrganization.JCJG_SFBA) {
    					// 初始化是否备案
        				$scope.record.isRecord = true;
    				}
    			}
    			
    			if ('1' == $scope.serviceOrganization.JBXX_YYJG) {
    				$scope.organizationType.isOperator = true;
    			}
    			
    			if ('1' == $scope.serviceOrganization.JBXX_JCSB) {
    				$scope.organizationType.isSupplier = true;
    			}
    			
    			if ('1' == $scope.serviceOrganization.JBXX_JCS) {
    				$scope.organizationType.isIntegrator = true;
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
		
		// 设置机构类型
		if ($scope.organizationType.isDetector) {
			$scope.serviceOrganization.JBXX_JCJG = '1';
		} else {
			$scope.serviceOrganization.JBXX_JCJG = '0';
		}
		
		if ($scope.organizationType.isOperator) {
			$scope.serviceOrganization.JBXX_YYJG = '1';
		} else {
			$scope.serviceOrganization.JBXX_YYJG = '0';
		}
		
		if ($scope.organizationType.isSupplier) {
			$scope.serviceOrganization.JBXX_JCSB = '1';
		} else {
			$scope.serviceOrganization.JBXX_JCSB = '0';
		}
		
		if ($scope.organizationType.isIntegrator) {
			$scope.serviceOrganization.JBXX_JCS = '1';
		} else {
			$scope.serviceOrganization.JBXX_JCS = '0';
		}
		
		// 设置是否有上级公司
		if ($scope.superiorCompany.hasSuperiorCompany) {
			$scope.serviceOrganization.JBXX_SFYSJGS = '1';
		} else {
			$scope.serviceOrganization.JBXX_SFYSJGS = '0';
			$scope.serviceOrganization.SJGSMC = '';
		}
		
		// 设置公司所在地
		if ($scope.region.provinceObj && $scope.region.provinceObj.value != '') {
			$scope.serviceOrganization.JBXX_GSSZD = $scope.region.provinceObj.label;
			if ($scope.region.cityObj && $scope.region.cityObj.value != '') {
				$scope.serviceOrganization.JBXX_GSSZD += " " + $scope.region.cityObj.label;
				if ($scope.region.districtObj && $scope.region.districtObj.value != '') {
					$scope.serviceOrganization.JBXX_GSSZD += " " + $scope.region.districtObj.label;
				}
			}
		}
		
		// 是否备案
		if ($scope.organizationType.isDetector && $scope.record.isRecord) {
			$scope.serviceOrganization.JCJG_SFBA = '1';
		} else {
			$scope.serviceOrganization.JCJG_SFBA = '0';
			$scope.serviceOrganization.JCJG_BAH = '';
		}
		
		var url = Common.webRoot() + "/both/epsm/dataacquisition/user/serviceorganizationcontroller/";
		if ($scope.operationType == 'add') {
			url += "addserviceorganization";
		} else if ($scope.operationType == 'edit') {
			url += "updateserviceorganization";
			
			// 设置是否有效
			$scope.serviceOrganization.JBXX_SFYX = '1';
			
			if ($scope.serviceOrganization.YHMM == '########') {
				// 密码不变则不作为更新条件
				$scope.serviceOrganization.YHMM = '';
			} else if (!$scope.serviceOrganization.YHMM) {
				// 不填使用默认密码：8888
				$scope.serviceOrganization.YHMM = '8888';
			}
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.serviceOrganization,
    		showTips : true,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);