var app = Common.initApp();
Common.initDirective(app);
app.controller('reportemplateDetailController', ['$scope', '$http', function($scope, $http) {
	
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
	} else {
		$scope.isRead = false;
	}
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		$scope.isTemplateType = true;
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/queryreportemplatebyid",
    		data : {
    			"ID" : $scope.template.ID
    		},
    		success : function(result){
    			$scope.template = result.data;
    		}
    	});
	}
	
	$scope.templateType = [
        { label: "请选择",value: ''},
		{ label: "废气",value: 'A'},
		{ label: "废水",value: 'B'},
		{ label: "无组织",value: 'C'},
		{ label: "周边环境",value: 'D'},
		{ label: "厂界噪声",value: 'E'}
    ];
	
	$scope.save = function() {
		// 校验表单信息
		// flag ： 验证结果标志
		var flag = Common.validate();

		// 验证不通过，不再往下执行
		if (!flag) {
			return;
		}
		
		$scope.template.M_LB = $scope.templateType.selected.value;
		
		if ($scope.operationType == 'add') {
			$scope.templateType.selected = $scope.templateType[0];
			
			Common.send($scope, $http, {
	    		method : "POST",
	    		url : Common.webRoot()+"/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/addreportemplate",
	    		data : $scope.template,
	    		success : function(result){
	    			Common.RBTips('操作成功', 1);
	    			parent.layer.close(parent.layer.getFrameIndex(window.name));
	    		}
	    	});
		}else if ($scope.operationType == 'edit') {
			Common.send($scope, $http, {
	    		method : "POST",
	    		url : Common.webRoot()+"/ep/epsm/decisionsupport/reportmanager/reportmanagercontroller/updatereportemplate",
	    		data : $scope.template,
	    		success : function(result){
	    			Common.RBTips('操作成功', 1);
	    			parent.layer.close(parent.layer.getFrameIndex(window.name));
	    		}
	    	});
		}
	};
} ]);