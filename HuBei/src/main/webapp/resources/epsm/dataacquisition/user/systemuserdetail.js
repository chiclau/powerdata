var app = Common.initApp();
Common.initDirective(app);
app.controller('systemUserDetailController', ['$scope', '$http', function($scope, $http) {
	
	// 查看操作则设置为只读
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
		$scope.isReadProvince = true;
		$scope.isReadCity = true;
		$scope.isReadArea = true;
	} else {
		$scope.isRead = false;
	}
	
	// 设置账号类型选中管理用户
	$scope.userType = {
		isSystemUser : true
	};
	
	// 初始化所属行政区
	if ($scope.currUserProvince) {
		$scope.systemUser.XZQHDMSHENG = $scope.currUserProvince;
		$scope.isReadProvince = true;
	}
	if ($scope.currUserCity) {
		$scope.systemUser.XZQHDMSHI = $scope.currUserCity;
		$scope.isReadCity = true;
	}
	if ($scope.currUserArea) {
		$scope.systemUser.XZQHDMXIAN = $scope.currUserArea;
		$scope.isReadArea = true;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/ep/epsm/dataacquisition/user/businessusercontroller/getuserbyaccount",
    		data : {
    			"XTZH" : $scope.systemUser.XTZH
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.systemUser = result.data;
    			// 初始化用户角色
    			if ($scope.systemUser.YHJS.indexOf("HBYH_HBTBJS") > -1) {
    				$scope.userType.isEPEditUser = true;
    			}
    			if ($scope.systemUser.YHJS.indexOf("HBYH_FASHJS") > -1) {
    				$scope.userType.isPlanCheckUser = true;
    			}
    			if ($scope.systemUser.YHJS.indexOf("HBYH_JDXJCSHJS") > -1) {
    				$scope.userType.isMonitorCheckUser = true;
    			}
    			if ($scope.systemUser.YHJS.indexOf("HBYH_CKJS") > -1) {
    				$scope.userType.isReadUser = true;
    			}
    			
    			// 用户的原始行政区划
    			$scope.OriginalRegion = {
    				XZQHDMSHENG : $scope.systemUser.XZQHDMSHENG,
    				XZQHDMSHI : $scope.systemUser.XZQHDMSHI,
    				XZQHDMXIAN : $scope.systemUser.XZQHDMXIAN
    			};
    			
    			// 初始化用户密码
    			$scope.systemUser.YHMM = '########';
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
		
		// 设置用户角色
		var addRoles = [];
		if ($scope.operationType == 'add') {
			addRoles.push({'JSBH' : 'HBYH_GLJS'});
		}
		var userRole = 'HBYH_GLJS,';
		if ($scope.userType.isEPEditUser) {
			addRoles.push({'JSBH' : 'HBYH_HBTBJS'});
			userRole += 'HBYH_HBTBJS,';
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
		$scope.systemUser.addRoles = addRoles;
		$scope.systemUser.YHJS = userRole.substring(0, userRole.length - 1);
		
		// 设置用户级别
		// 县级用户
		if ($scope.systemUser.XZQHDMXIAN) {
			$scope.systemUser.YHJB = '3';
		// 市级用户
		} else if ($scope.systemUser.XZQHDMSHI) {
			$scope.systemUser.YHJB = '2';
		// 省级用户
		} else if ($scope.systemUser.XZQHDMSHENG) {
			$scope.systemUser.YHJB = '1';
		}
		
		var url = Common.webRoot()+"/ep/epsm/dataacquisition/user/businessusercontroller/";
		if ($scope.operationType == 'add') {
			url += "addsystemuser";
		} else if ($scope.operationType == 'edit') {
			url += "updateuser";
			if ($scope.OriginalRegion.XZQHDMSHENG != $scope.systemUser.XZQHDMSHENG || $scope.OriginalRegion.XZQHDMSHI != $scope.systemUser.XZQHDMSHI || $scope.OriginalRegion.XZQHDMXIAN != $scope.systemUser.XZQHDMXIAN) {
				$scope.systemUser.isCheckRegion = "true";
			} else {
				$scope.systemUser.isCheckRegion = "false";
			}
			
			if ($scope.systemUser.YHMM == '########') {
				// 密码不变则不作为更新条件
				$scope.systemUser.YHMM = '';
			} else if (!$scope.systemUser.YHMM) {
				// 不填使用默认密码：8888
				$scope.systemUser.YHMM = '8888';
			}
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.systemUser,
    		success : function(result){
    			if (result.data.existRegion) {
    				Common.dialog({
	    				content : '已有相同行政区划的管理账号, 请重新选择行政区划!'
	    			});
					return;
    			}
    			Common.RBTips('操作成功', 1);
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);