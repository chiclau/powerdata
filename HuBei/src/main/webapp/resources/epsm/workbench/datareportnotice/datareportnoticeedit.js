//JavaScript Doccument
var app = Common.initApp(['ngAnimate', 'ui.bootstrap', 'angular.filter']);
Common.initDirective(app);//初始化自定义指令
app.controller('dataReportNoticeController', ['$scope', '$http', 
     function ($scope, $http) {
	
		//按钮显示 设置
		$scope.isEditShow=false;
  	    
		if($scope.dataReportNotice.openType=='look'){//查看
			$scope.isLookShow=true;
			Common.setFormReadOnly($scope,$("#myVaildate"),true);//设置jsp页面是否只读模式,如果打开就显示只读模式 
		}else if($scope.dataReportNotice.openType=='edit'){//修改
			$scope.isEditShow=true;
		}else{
			$scope.isAddShow=true;
		}
		//保存按钮绑定事件方法
		$scope.saveDataReportNotice = function() {
				
			//验证"*"的必须填写，否则无法提交
			if(!Common.validate()) {
				return;
			}
			//添加处理逻辑
			var saveConfig = {
				url : Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/savedatareportnotice',
				method : 'POST',
				showTips : true,
				data : JSON.stringify($scope.dataReportNotice),
				success : function(response) {
					//关闭弹框
					parent.layer.close(parent.layer.getFrameIndex(window.name));
				},
				error: function(response) {
					throw new Error('请求[' + saveConfig.url + ']出错,[' + response + ']')
				}
			};
			Common.send($scope, $http, saveConfig);
		};
	
}]);
