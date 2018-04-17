var app = Common.initApp();
Common.initDirective(app);
app.controller('orgEquipmentDetailController', ['$scope', '$http', function($scope, $http) {
	
	// 设备类型集合
	$scope.equipmentTypes = [];
	Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcommoncode',
		data : {
			PARENT : 'DEVICE'
		},
		success: function(result) {
			var datas = result.data;
			for(var i = 0; i < datas.length; i++) {
				$scope.equipmentTypes.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.equipmentTypes.selected = $scope.equipmentTypes[0];
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
    		url : Common.webRoot()+"/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/getorgequipmentbyid",
    		data : {
    			"JCJGSB_ID" : $scope.orgEquipment.JCJGSB_ID
    		},
    		success : function(result){
    			// 初始化数据
    			$scope.orgEquipment = result.data;
    			
    			// 初始化设备类型
    			for (var i = 0; i < $scope.equipmentTypes.length; i++) {
    				if ($scope.orgEquipment.JCJGSB_TYPE == $scope.equipmentTypes[i].value) {
    					$scope.equipmentTypes.selected = $scope.equipmentTypes[i];
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
		
		$scope.orgEquipment.JCJGSB_TYPE = $scope.equipmentTypes.selected.value;
		
		var url = Common.webRoot() + "/ep/epsm/businessmanagement/organization/orgequipment/orgequipmentcontroller/";
		if ($scope.operationType == 'add') {
			url += "addorgequipment";
		} else if ($scope.operationType == 'edit') {
			url += "updateorgequipment";
		}
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : url,
    		data : $scope.orgEquipment,
    		success : function(result){
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);