var app = Common.initApp();
Common.initDirective(app);
app.controller('contactDetailController', ['$scope', '$http', function($scope, $http) {
	
	if ($scope.operationType == 'read') {
		$scope.isRead = true;
		$scope.isUserTypes = true;
	} else {
		$scope.isRead = false;
		$scope.isUserTypes = false;
	}
	
	// 用户类型集合
	$scope.userTypes = [
        { label: "请选择",value: ''},
		{ label: "管理用户",value: 'HBYH_GLJS'},
		{ label: "审核用户",value: 'HBYH_SHJS'},
		{ label: "认证用户",value: 'HBYH_RZJS'}
    ];
    //初始化用户类型下拉列表，默认选择全部类别
	
	
	if ($scope.operationType == 'read' || $scope.operationType == 'edit') {
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/both/epsm/workbench/contactmanager/contactmanagercontroller/querycontactsbyid",
    		data : {
    			"ID" : $scope.contact.ID
    		},
    		success : function(result){
    			$scope.contact = result.data;
    			
    			//匹配重新赋值 分类类型
		    	$scope.contact.YHLB = function(){
		    		for(var i=0;i<$scope.userTypes.length;i++){
		    			if(result.data.YHLB == $scope.userTypes[i].value){
		    				return $scope.userTypes[i];
		    			}
		    		}
		    		return "";
		    	}();
    		}
    	});
	}
	
	// 用户类型集合
	$scope.userTypes = [
        { label: "全部类别",value: ''},
		{ label: "管理用户",value: 'HBYH_GLJS'},
		{ label: "审核用户",value: 'HBYH_SHJS'},
		{ label: "认证用户",value: 'HBYH_RZJS'}
    ];
	
	$scope.save = function() {
		// 校验表单信息
		// flag ： 验证结果标志
		var flag = Common.validate();

		// 验证不通过，不再往下执行
		if (!flag) {
			return;
		}
		
		$scope.contact.YHLB = $scope.userTypes.selected.value;
		
		if ($scope.operationType == 'add') {
			$scope.userTypes.selected = $scope.userTypes[0];
			
			Common.send($scope, $http, {
	    		method : "POST",
	    		url : Common.webRoot()+"/both/epsm/workbench/contactmanager/contactmanagercontroller/addcontacts",
	    		data : $scope.contact,
	    		success : function(result){
	    			Common.RBTips('操作成功', 1);
	    			parent.layer.close(parent.layer.getFrameIndex(window.name));
	    		}
	    	});
		}else if ($scope.operationType == 'edit') {
			Common.send($scope, $http, {
	    		method : "POST",
	    		url : Common.webRoot()+"/both/epsm/workbench/contactmanager/contactmanagercontroller/updatecontacts",
	    		data : $scope.contact,
	    		success : function(result){
	    			Common.RBTips('操作成功', 1);
	    			parent.layer.close(parent.layer.getFrameIndex(window.name));
	    		}
	    	});
		}
	};
} ]);