var app = Common.initApp();
Common.initDirective(app);
app.controller('companyUserDetailController', ['$scope', '$http', function($scope, $http) {
	
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/co/epsm/dataacquisition/companyuser/companyusercontroller/getcompanyuserbyaccount",
    		data : {
    			"XTZH" : $scope.companyUser.XTZH
    		},
    		success : function(result){
    			$scope.companyUser = result.data;
    			$scope.userType = {};
    			if ($scope.companyUser.YHJS.indexOf("QYYH_TBJS") > -1) {
    				$scope.userType.isEditUser = true;
    			}
    			if ($scope.companyUser.YHJS.indexOf("QYYH_SHJS") > -1) {
    				$scope.userType.isCheckUser = true;
    			}
    			
    			// 初始化用户密码
    			$scope.companyUser.YHMM = '########';
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
		
		if (!($scope.userType.isEditUser || $scope.userType.isCheckUser)) {
			Common.dialog({
				content : '至少选择一个用户类别!'
			});
			return;
		}
		
		// 设置用户角色
		var addRoles = [];
		var userRole = '';
		
		// 管理员用户保留管理角色
		if ($scope.operationType == 'edit' && $scope.companyUser.YHJS.indexOf("QYYH_GLJS") > -1) {
			userRole += 'QYYH_GLJS,';
		}
		
		if ($scope.userType.isEditUser) {
			addRoles.push({'JSBH' : 'QYYH_TBJS'});
			userRole += 'QYYH_TBJS,';
		}
		if ($scope.userType.isCheckUser) {
			addRoles.push({'JSBH' : 'QYYH_SHJS'});
			userRole += 'QYYH_SHJS,';
		}
		$scope.companyUser.addRoles = addRoles;
		$scope.companyUser.YHJS = userRole.substring(0, userRole.length - 1);
		
		var url = Common.webRoot()+"/co/epsm/dataacquisition/companyuser/companyusercontroller/";
		if ($scope.operationType == 'add') {
			url += "addcompanyuser";
		} else if ($scope.operationType == 'edit') {
			url += "updatecompanyuser";
			
			if ($scope.companyUser.YHMM == '########') {
				// 密码不变则不作为更新条件
				$scope.companyUser.YHMM = '';
			} else if (!$scope.companyUser.YHMM) {
				// 不填使用默认密码：8888
				$scope.companyUser.YHMM = '8888';
			}
		}
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.companyUser,
    		showTips : true,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);