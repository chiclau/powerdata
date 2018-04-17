var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('areaMonitorAbilityListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {   
	//定义分析维度
	$scope.analysisType = {
			isDq : true
		};
    
	//定义表单
	$scope.columnDefs=[
						  {name : 'SBSL', displayName : '设备数量', width :'50%',enableSorting: false},
						  {name : 'RYSL', displayName : '人员数量', width :'50%',enableSorting: false}				  
						  ];
    //项目ID
    $scope.X_ID='';	
   
	// 初始化选择条件：行政区划
	$scope.systemUser = {};
	if ($scope.currUserProvince) {
		$scope.systemUser.SHENG = $scope.currUserProvince;
		$scope.isReadProvince = true;
	}
	if ($scope.currUserCity) {
		$scope.systemUser.SHI = $scope.currUserCity;
		$scope.isReadCity = true;
	}
	if ($scope.currUserArea) {
		$scope.systemUser.XIAN = $scope.currUserArea;
		$scope.isReadArea = true;
	}
	
    //-----------------------------------------------------------
	//污染源类型tab
	$scope.tabs = [];	
    Common.send($scope, $http, {
		method: 'POST',
		url: Common.webRoot() + '/public/epsm/common/commoncontroller/getdevice',
		success: function(result){
			var datas = result.data;
			var dataArr = $scope.tabs;
			for(var i = 0; i < datas.length; i++) {
					dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});						
			}
			$scope.tabs = dataArr;
		}
	});    
    //污染源类型默认
    $scope.JCJGRY_ZSLX='A';
    //点击tab后的回调函数
    $scope.setTab = function(value){
    	//污染源类型
    	$scope.JCJGRY_ZSLX = value;
    	$scope.toSearch();
    };
    //-----------------------------------------------------------
	//监测方法
    $scope.jcffs = [
        {label: '请选择' , value: ''}
    ];    
    //默认方法
    $scope.jcffs.selected = $scope.jcffs[0];    
    //监测方法
    $scope.ID='';    
	$scope.changeSelectedJcff = function (item, model) {
		$scope.ID=item.value		
		$scope.toSearch();
    }; 
    //------------------------------------------------------------
    //机构
    $scope.jgs = [
        {label: '请选择' , value: ''}
    ];   
    //默认机构
    $scope.jgs.selected = $scope.jgs[0];	    
    //机构
    $scope.JCJG_ID='';
	$scope.changeSelectedJg = function (item, model) {
		$scope.JCJG_ID=item.value
		$scope.toSearch();
    };     
    //-----------------------------------------------------------    
	$scope.areaMonitorAbilityGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'areaMonitorAbilityGrid',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height()-160);
				$(".areaMonitorAbilityGrid").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height()-160);
				$(".areaMonitorAbilityGrid").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs:$scope.columnDefs,
			//组合配置checkbox复选框
			enableSelectAll : false,          										//是否全选选择框按钮
			enableRowHeaderSelection : false  										//是否选择框按钮	
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/both/epsm/businessmanagement/organization/monitorabilitycontroller/getareamonitorability',
	      data:{
	          pageSize:10,
	          pageNum:1
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	//Common.grid.autoSelectedFirstRow("areaMonitorAbilityGrid");
	        }
	      }
	  },
	  gridEvent: {}
	};
	Common.grid.initConfig($scope,$http,$scope.areaMonitorAbilityGrid);

	$scope.toSearch = function(){
		$scope.areaMonitorAbilityGrid.dataConfig.data = $scope.systemUser;
		$scope.areaMonitorAbilityGrid.dataConfig.data.pageSize = Common.pageSize;
		$scope.areaMonitorAbilityGrid.dataConfig.data.pageNum = 1;		
		var num=2;
		var columnDefs=[];
		if($scope.analysisType.isDq){
			num++;			
			columnDefs.push({name : 'XZQH', displayName : '地区', width :'100%',enableSorting: false});
		}
		if($scope.analysisType.isJg){
			num++;
			columnDefs.push({name : 'JCJG_JGMC', displayName : '机构', width :'100%',enableSorting: false});
		}
		if($scope.analysisType.isJcxm){
			num++;
			columnDefs.push({name : 'JCXM', displayName : '监测项目', width :'100%',enableSorting: false});
		}
		if($scope.analysisType.isJcff){
			num++;
			columnDefs.push({name : 'FFMC', displayName : '监测方法', width :'100%',enableSorting: false});
		}
		columnDefs.push({name : 'SBSL', displayName : '设备数量', width :'100%',enableSorting: false});
		columnDefs.push({name : 'RYSL', displayName : '人员数量', width :'100%',enableSorting: false});
		for (var i = 0; i < columnDefs.length; i++) {
			columnDefs[i].width=(100/num)+"%";
		}
				
		$scope.areaMonitorAbilityGrid.gridOptions.columnDefs=columnDefs;		
		$scope.areaMonitorAbilityGrid.dataConfig.data.isArea=$scope.analysisType.isDq;
		$scope.areaMonitorAbilityGrid.dataConfig.data.isJg=$scope.analysisType.isJg;
		$scope.areaMonitorAbilityGrid.dataConfig.data.isJcxm=$scope.analysisType.isJcxm;	
		$scope.areaMonitorAbilityGrid.dataConfig.data.isJcff=$scope.analysisType.isJcff;		
		//污染源类型
		$scope.areaMonitorAbilityGrid.dataConfig.data.JCJGRY_ZSLX=$scope.JCJGRY_ZSLX;
		
		//监测项目
		$scope.areaMonitorAbilityGrid.dataConfig.data.X_ID=$scope.X_ID;
		
		//监测方法
		$scope.areaMonitorAbilityGrid.dataConfig.data.ID=$scope.ID;
		
		//监测机构
		$scope.areaMonitorAbilityGrid.dataConfig.data.JCJG_ID=$scope.JCJG_ID;

		Common.page.pageChanged($scope, $http, $scope.areaMonitorAbilityGrid);
	};

}]);