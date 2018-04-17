//JavaScript Doccument
var app = Common.initApp(['ngAnimate', 'ui.bootstrap', 'angular.filter']);
Common.initDirective(app);//初始化自定义指令
app.controller('standardController', ['$scope', '$http', 
     function ($scope, $http) {
	
		//按钮显示 设置
		$scope.isAddShow=false;
		$scope.isLookShow=false;
		$scope.isEditShow=false;
	    //初始化标准分类
	    $scope.BZFLS = [];
 
	    Common.send($scope, $http, {
			method: 'POST',
			url: Common.webRoot() + '/public/epsm/common/commoncontroller/getstandardclassify',
			success: function(result){
				var datas = result.data;
				var dataArr = $scope.BZFLS;
				for(var i = 0; i < datas.length; i++) {
					if(datas[i].VALID=="1"){
						dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
					}
				}
				if (!$scope.standard.BZMC){
					$scope.bzfl_selected=dataArr[0];
				}
				$scope.BZFLS = dataArr;
			}
		});	    
	    
	    //初始化标准类型
	    $scope.BZLXS = [];
 
	    Common.send($scope, $http, {
			method: 'POST',
			url: Common.webRoot() + '/public/epsm/common/commoncontroller/getstandardtype',
			success: function(result){
				var datas = result.data;
				var dataArr = $scope.BZLXS;
				for(var i = 0; i < datas.length; i++) {
						dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
				}
				if (!$scope.standard.BZMC){
					dataArr=[{label:'地方标准', value: 'B'}];
					$scope.bzlx_selected=dataArr[0];
				}
				$scope.BZLXS = dataArr;
			}
		});	

	    //初始化标准类型
	    $scope.YYHYS = [];
 
	    Common.send($scope, $http, {
			method: 'POST',
			url: Common.webRoot() + '/public/epsm/common/commoncontroller/getcodeclass',
			success: function(result){
				var datas = result.data;
				var dataArr = $scope.YYHYS;
				for(var i = 0; i < datas.length; i++) {
						if(datas[i].PARAM0=='2'&&(datas[i].PARENT=='A'||datas[i].PARENT=='B'||datas[i].PARENT=='C'||datas[i].PARENT=='D')){
							dataArr.push({label: datas[i].C_NAME, value: datas[i].C_ID});	
						}					
				}
				if (!$scope.standard.BZMC){
					$scope.yyhy_selected=dataArr[0];
				}
				$scope.YYHYS = dataArr;
			}
		});
	    
    	$scope.changeSelectedBzfl = function (item, model) {
    		$scope.bzfl_selected =item;
        }; 
    	$scope.changeSelectedBzlx = function (item, model) {
    		$scope.bzlx_selected=item;
        }; 
    	$scope.changeSelectedYyhy = function (item, model) {
    		$scope.yyhy_selected=item;
        }; 
		if($scope.standard.openType=='look'){//查看
			$scope.isLookShow=true;
			Common.setFormReadOnly($scope,$("#myVaildate"),true);//设置jsp页面是否只读模式,如果打开就显示只读模式 
		}else if($scope.standard.openType=='edit'){//修改
			$scope.isEditShow=true;
		}else{
			$scope.isAddShow=true;
		}
					
		//保存按钮绑定事件方法
		$scope.saveStandard = function() {
				
			//验证"*"的必须填写，否则无法提交
			if(!Common.validate()) {
				return;
			}
			$scope.standard.BZFL=$scope.bzfl_selected.value;
			$scope.standard.BZLX=$scope.bzlx_selected.value;
			$scope.standard.YYHYDM=$scope.yyhy_selected.value;
			//添加处理逻辑
			var saveConfig = {
				url : Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/savestandard',
				method : 'POST',
				showTips : true,
				data : JSON.stringify($scope.standard),
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
