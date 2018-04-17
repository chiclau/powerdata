var app = Common.initApp();
Common.initDirective(app);
app.controller('projectDetailController', ['$scope', '$http', function($scope, $http) {
	// 查看操作则设置为只读
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	var orgUserIds = [];
	var orgUserNames = [];
	// 持证人员复选框选择事件
	$scope.checkOrgUser = function(orgUser) {
		if(orgUser.check){
			orgUserIds.push(orgUser.JCJGRY_ID);
			orgUserNames.push(orgUser.JCJGRY_NAME);
		}else{
			var userIdIndex = orgUserIds.indexOf(orgUser.JCJGRY_ID);
			orgUserIds.splice(userIdIndex, 1);
			
			var userNameIndex = orgUserNames.indexOf(orgUser.JCJGRY_NAME);
			orgUserNames.splice(userNameIndex, 1);
		}
	}
	
	// 初始化持证人员
	Common.send($scope, $http, {
		method : "POST",
		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orguser/orgusercontroller/findorguser",
		data : {},
		success : function(result){
			$scope.orgUsers = result.data;
			if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
				Common.send($scope, $http, {
		    		method : "POST",
		    		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/getprojectbyid",
		    		data : {
		    			"JCJG_SBJCX_ID" : $scope.project.JCJG_SBJCX_ID
		    		},
		    		success : function(result){
		    			// 初始化数据
		    			$scope.project = result.data;
		    			
		    			// 初始化持证人员
		    			if ($scope.project.JCJG_SBJCX_CZRYID) {
		    				orgUserIds = $scope.project.JCJG_SBJCX_CZRYID.split(',');
			    			orgUserNames = $scope.project.JCJG_SBJCX_CZRY.split(',');
			    			
			    			for (var i = 0; i < orgUserIds.length; i++) {
			    				for (var j = 0; j < $scope.orgUsers.length; j++) {
			    					if (orgUserIds[i] == $scope.orgUsers[j].JCJGRY_ID) {
			    						$scope.orgUsers[j].check = true;
			    						break;
			    					}
			    				}
			    			}
		    			}
		    		}
		    	});
			}
		}
	});
	
	// 初始化监测产品类别
	Common.send($scope, $http, {
		method : "POST",
		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/getorgequipmentbyid",
		data : {
			"JCJGSB_ID" : $scope.project.JCJG_SBJCX_SBID
		},
		success : function(result){
			var equipmentType = result.data.JCJGSB_TYPE;
			$scope.project.JCJG_SBJCX_SBMC = result.data.JCJGSB_SBMC;
			Common.send($scope, $http, {
				method: 'POST',
				url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
				data : {
					PARENT : 'DEVICE'
				},
				success: function(result) {
					var datas = result.data;
					for(var i = 0; i < datas.length; i++) {
						if (equipmentType == datas[i].PARAM0) {
							$scope.project.JCJG_SBJCX_JCCPLB = equipmentType;
							$scope.project.JCJG_SBJCX_JCCPLBMC = datas[i].C_NAME;
							break;
						}
					}
				}
			});
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
		
		if (orgUserIds.length == 0) {
			Common.dialog({
				content : '请至少选择一个持证人员!'
			});
			return;
		}
		
		$scope.project.JCJG_SBJCX_CZRYID = orgUserIds.join(",");
		$scope.project.JCJG_SBJCX_CZRY = orgUserNames.join(",");
		
		var url = Common.webRoot() + "/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/";
		if ($scope.operationType == 'add') {
			url += "addproject";
		} else if ($scope.operationType == 'edit') {
			url += "updateproject";
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.project,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);