var app = Common.initApp();
Common.initDirective(app);
app.controller('orgUserDetailController', ['$scope', '$http', function($scope, $http) {
	
	// 性别集合
	$scope.sexes = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'SEX'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.sexes.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.sexes.selected = $scope.sexes[0];
		}
	});
	
	// 政治面貌集合
	$scope.politicalVisages = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'IDENTITY'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.politicalVisages.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.politicalVisages.selected = $scope.politicalVisages[0];
		}
	});
	
	// 最高学历集合
	$scope.educations = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'EDU_TYPE'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.educations.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.educations.selected = $scope.educations[0];
		}
	});
	
	// 是否三五人才集合
	$scope.personnels = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'SWRC'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.personnels.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.personnels.selected = $scope.personnels[0];
		}
	});
	
	// 职称集合
	$scope.titles = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'POST'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.titles.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.titles.selected = $scope.titles[0];
		}
	});
	
	// 所在部门集合
	$scope.orgDepts = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/ep/epsm/businessmanagement/organization/orgdept/orgdeptcontroller/findorgdept',
		data : {},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.orgDepts.push({label: datas[i].JCJGBM_BMMC, value: datas[i].JCJGBM_ID});						
			}
			$scope.orgDepts.selected = $scope.orgDepts[0];
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
    		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/getorguserbyid",
    		data : {
    			"JCJGRY_ID" : $scope.orgUser.JCJGRY_ID
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.orgUser = result.data;
    			
    			// 初始化性别
    			for (var i = 0; i < $scope.sexes.length; i++) {
    				if ($scope.orgUser.JCJGRY_SEX == $scope.sexes[i].value) {
    					$scope.sexes.selected = $scope.sexes[i];
    					break;
    				}
    			}
    			
    			// 初始化政治面貌
    			for (var i = 0; i < $scope.politicalVisages.length; i++) {
    				if ($scope.orgUser.JCJGRY_ZZMM == $scope.politicalVisages[i].value) {
    					$scope.politicalVisages.selected = $scope.politicalVisages[i];
    					break;
    				}
    			}
    			
    			// 初始化最高学历
    			for (var i = 0; i < $scope.educations.length; i++) {
    				if ($scope.orgUser.JCJGRY_ZGXL == $scope.educations[i].value) {
    					$scope.educations.selected = $scope.educations[i];
    					break;
    				}
    			}
    			
    			// 初始化是否三五人才
    			for (var i = 0; i < $scope.personnels.length; i++) {
    				if ($scope.orgUser.JCJGRY_SFSWRC == $scope.personnels[i].value) {
    					$scope.personnels.selected = $scope.personnels[i];
    					break;
    				}
    			}
    			
    			// 初始化职称
    			for (var i = 0; i < $scope.titles.length; i++) {
    				if ($scope.orgUser.JCJGRY_ZC == $scope.titles[i].value) {
    					$scope.titles.selected = $scope.titles[i];
    					break;
    				}
    			}
    			
    			// 初始化所在部门
    			for (var i = 0; i < $scope.orgDepts.length; i++) {
    				if ($scope.orgUser.JCJGRY_JCJGBMBH == $scope.orgDepts[i].value) {
    					$scope.orgDepts.selected = $scope.orgDepts[i];
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
		
		$scope.orgUser.JCJGRY_SEX = $scope.sexes.selected.value;
		$scope.orgUser.JCJGRY_ZZMM = $scope.politicalVisages.selected.value;
		$scope.orgUser.JCJGRY_ZGXL = $scope.educations.selected.value;
		$scope.orgUser.JCJGRY_SFSWRC = $scope.personnels.selected.value;
		$scope.orgUser.JCJGRY_ZC = $scope.titles.selected.value;
		$scope.orgUser.JCJGRY_JCJGBMBH = $scope.orgDepts.selected.value;
		$scope.orgUser.JCJGRY_SZBM = $scope.orgDepts.selected.label;
		
		var url = Common.webRoot() + "/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/";
		if ($scope.operationType == 'add') {
			url += "addorguser";
		} else if ($scope.operationType == 'edit') {
			url += "updateorguser";
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.orgUser,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);