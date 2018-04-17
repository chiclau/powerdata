var app = Common.initApp();
Common.initDirective(app);
app.controller('dataInfoDetailController', ['$scope', '$http', function($scope, $http) {
	
	/**********************下拉框 start*************************/
    $scope.qygm = [{label: '请选择' , value: ''}];
    $scope.qygm.selected = $scope.qygm[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getqygmdm',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.qygm;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].PARAM0+"|"+datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.qygm = dataArr;
		}
	});
    $scope.qyzclx = [{label: '请选择' , value: ''}];
    $scope.qyzclx.selected = $scope.qyzclx[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getqyzclx',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.qyzclx;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.qyzclx = dataArr;
		}
	});
    
    $scope.hylb = [{label: '请选择' , value: ''}];
    $scope.hylb.selected = $scope.hylb[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getqyhylb',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.hylb;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].PARAM0+"|"+datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.hylb = dataArr;
		}
	});
    
    $scope.qylx = [{label: '请选择' , value: ''}];
    $scope.qylx.selected = $scope.qylx[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getqylx',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.qylx;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].PARAM0+"|"+datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.qylx = dataArr;
		}
	});
    
    $scope.zdysx = [{label: '请选择' , value: ''}];
    $scope.zdysx.selected = $scope.zdysx[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getqyzdysx',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.zdysx;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].PARAM0+"|"+datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.zdysx = dataArr;
		}
	});
    
    $scope.wsclclb = [{label: '请选择' , value: ''}];
    $scope.wsclclb.selected = $scope.wsclclb[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getwsclclb',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.wsclclb;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.wsclclb = dataArr;
		}
	});
    
    $scope.wsclclb = [{label: '请选择' , value: ''}];
    $scope.wsclclb.selected = $scope.wsclclb[0];
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getwsclclb',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.wsclclb;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
			}
			$scope.wsclclb = dataArr;
		}
	});
    
    //固废处置厂处置方式
    $scope.czfs = [
		{ label: "请选择",value: ''},
		{ label: "填埋",value: '1'},
		{ label: "焚烧",value: '2'},
		{ label: "堆肥",value: '3'},
		{ label: "其他",value: '4'}
    ];
    $scope.LJCZFS = $scope.czfs;
    $scope.WXFWCZFS = $scope.czfs;
    $scope.YLFWCZFS = $scope.czfs;
	
	/**********************下拉框 end *************************/
   //设置form业务表单信息
	$scope.fillDataInfo = function(data) {			
		if (data) { //设置json格式数据到form表单中
	    	$scope.datainfo = data;
	    	//企业规模
	    	$scope.qygm.selected = function(){
				for(var i=0;i<$scope.qygm.length;i++){
					if(data.QYGMDM == $scope.qygm[i].value){
						return $scope.qygm[i];
					}
				}
				return "";
	    	}();
	    	//污水处理厂类别
	    	$scope.wsclclb.selected = function(){
				for(var i=0;i<$scope.wsclclb.length;i++){
					if(data.WSCLCLB == $scope.wsclclb[i].value){
						return $scope.wsclclb[i];
					}
				}
				return "";
	    	}();
	    	//企业注册类型
	    	$scope.qyzclx.selected = function(){
				for(var i=0;i<$scope.qyzclx.length;i++){
					if(data.ZCLXDM == $scope.qyzclx[i].value){
						return $scope.qyzclx[i];
					}
				}
				return "";
	    	}();
	    	//行业类别
	    	$scope.hylb.selected = function(){
				for(var i=0;i<$scope.hylb.length;i++){
					if(data.HYLB == $scope.hylb[i].value){
						return $scope.hylb[i];
					}
				}
				return "";
	    	}();
	    	//企业类型
	    	$scope.qylx.selected = function(){
				for(var i=0;i<$scope.qylx.length;i++){
					if(data.QYLX == $scope.qylx[i].value){
						return $scope.qylx[i];
					}
				}
				return "";
	    	}();
	    	//企业类型
	    	$scope.zdysx.selected = function(){
				for(var i=0;i<$scope.zdysx.length;i++){
					if(data.ZDYSX == $scope.zdysx[i].value){
						return $scope.zdysx[i];
					}
				}
				return "";
	    	}();
	    	
		 } else { //判断查询结果是否为空,为空表示新增,重新初始化页面信息
			 
		 }
	};
    
	Common.send($scope, $http, {
		method : "POST",
		url : Common.webRoot()+"/co/epsm/workbench/datamanagement/datamanagementController/querydatainfobyid",
		success : function(result){
			$scope.fillDataInfo(result.data);//返回查询结果集,渲染填充数据到form表单
		}
	});
	
	// 保存
	$scope.save = function() {
		// 校验表单信息
		var flag = Common.validate(); // flag ： 验证结果标志

		// 验证不通过，不再往下执行
		if (!flag) {
			return;
		}
		
		//重新组装 标准类型 数据
		$scope.datainfo.QYGMDM = $scope.qygm.selected.value;
		if($scope.datainfo.QYLB == 'B'){
			$scope.datainfo.WSCLCLB =  $scope.wsclclb.selected.value;
		}else if($scope.datainfo.QYLB == 'C'){
			$scope.datainfo.GTFWCZC =  true;
		}else{
			$scope.datainfo.WSCLCLB =  "";
			$scope.datainfo.GTFWCZC =  false;
		}
		$scope.datainfo.ZCLXDM =  $scope.qyzclx.selected.value;
		$scope.datainfo.HYLB = $scope.hylb.selected.value;
		$scope.datainfo.QYLX =  $scope.qylx.selected.value;
		$scope.datainfo.ZDYSX =  $scope.zdysx.selected.value;
		
		Common.send($scope, $http, {
    		method : "POST",
    		url : Common.webRoot()+"/co/epsm/workbench/datamanagement/datamanagementController/updatedatainfo",
    		data : $scope.datainfo,
    		success : function(result){
    			Common.RBTips('操作成功', 1);
    			parent.layer.close(parent.layer.getFrameIndex(window.name));
    		}
    	});
	};
} ]);