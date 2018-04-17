var app = Common.initApp();
Common.initDirective(app);
app.controller('sameRegionUserDetailController', ['$scope', '$http', function($scope, $http) {
	
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/ep/epsm/dataacquisition/user/businessusercontroller/getuserbyaccount",
    		data : {
    			"XTZH" : $scope.sameRegionUser.XTZH
    		},
    		success : function(result){
    			$scope.sameRegionUser = result.data;
    			// 初始化用户类别
    			$scope.userType = {};
    			if ($scope.sameRegionUser.YHJS.indexOf("HBYH_HBTBJS") > -1) {
    				$scope.userType.isEPEditUser = true;
    			}
    			if ($scope.sameRegionUser.YHJS.indexOf("HBYH_JCZTBJS") > -1) {
    				$scope.userType.isMonitorEditUser = true;
    			}
    			if ($scope.sameRegionUser.YHJS.indexOf("HBYH_FASHJS") > -1) {
    				$scope.userType.isPlanCheckUser = true;
    			}
    			if ($scope.sameRegionUser.YHJS.indexOf("HBYH_JDXJCSHJS") > -1) {
    				$scope.userType.isMonitorCheckUser = true;
    			}
    			if ($scope.sameRegionUser.YHJS.indexOf("HBYH_CKJS") > -1) {
    				$scope.userType.isReadUser = true;
    			}
    			// 初始化用户密码
    			$scope.sameRegionUser.YHMM = '########';
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
		
		if (!($scope.userType.isEPEditUser || $scope.userType.isMonitorEditUser || $scope.userType.isPlanCheckUser || $scope.userType.isMonitorCheckUser || $scope.userType.isReadUser)) {
			Common.dialog({
				content : '至少选择一个用户类别!'
			});
			return;
		}
		
		// 设置用户角色
		var addRoles = [];
		var userRole = '';
		
		// 管理员用户保留管理角色
		if ($scope.operationType == 'edit' && $scope.sameRegionUser.YHJS.indexOf("HBYH_GLJS") > -1) {
			userRole += 'HBYH_GLJS,';
		}
		
		if ($scope.userType.isEPEditUser) {
			addRoles.push({'JSBH' : 'HBYH_HBTBJS'});
			userRole += 'HBYH_HBTBJS,';
		}
		if ($scope.userType.isMonitorEditUser) {
			addRoles.push({'JSBH' : 'HBYH_JCZTBJS'});
			userRole += 'HBYH_JCZTBJS,';
		}
		if ($scope.userType.isPlanCheckUser) {
			addRoles.push({'JSBH' : 'HBYH_FASHJS'});
			userRole += 'HBYH_FASHJS,';
		}
		if ($scope.userType.isMonitorCheckUser) {
			addRoles.push({'JSBH' : 'HBYH_JDXJCSHJS'});
			userRole += 'HBYH_JDXJCSHJS,';
		}
		if ($scope.userType.isReadUser) {
			addRoles.push({'JSBH' : 'HBYH_CKJS'});
			userRole += 'HBYH_CKJS,';
		}
		$scope.sameRegionUser.addRoles = addRoles;
		$scope.sameRegionUser.YHJS = userRole.substring(0, userRole.length - 1);
		
		var url = Common.webRoot()+"/ep/epsm/dataacquisition/user/businessusercontroller/";
		if ($scope.operationType == 'add') {
			url += "addsameregionuser";
		} else if ($scope.operationType == 'edit') {
			if ($scope.sameRegionUser.YHMM == '########') {
				// 密码不变则不作为更新条件
				$scope.sameRegionUser.YHMM = '';
			} else if (!$scope.sameRegionUser.YHMM) {
				// 不填使用默认密码：8888
				$scope.sameRegionUser.YHMM = '8888';
			}
			url += "updateuser";
		}
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.sameRegionUser,
    		showTips : true,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);