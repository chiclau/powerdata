var app = Common.initApp();
app.controller('pointStandardController', ['$scope', '$http', 'uiGridConstants','$templateCache',
       function ($scope, $http, uiGridConstants, $templateCache) {
        
	    //标准分类
    	$scope.BZFL = [
    	   { label: "全部",value: ''}
	    ];
	    //默认类型
	    $scope.BZFL.selected = $scope.BZFL[0];
	    
	    Common.send($scope, $http, {
			method: 'POST',
			url: Common.webRoot() + '/public/epsm/common/commoncontroller/getstandardclassify',
			success: function(result){
				var datas = result.data;
				var dataArr = $scope.BZFL;
				for(var i = 0; i < datas.length; i++) {
					if(datas[i].VALID =='1'){
						dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
					}
				}
				$scope.BZFL = dataArr;
			}
		});
	    
	    // 初始化选择条件：行政区划
		$scope.piont = {};
		if ($scope.currUserProvince) {
			$scope.piont.sheng = $scope.currUserProvince;
			$scope.isReadProvince = true;
		}
		if ($scope.currUserCity) {
			$scope.piont.shi = $scope.currUserCity;
			$scope.isReadCity = true;
		}
		if ($scope.currUserArea) {
			$scope.piont.xian = $scope.currUserArea;
			$scope.isReadArea = true;
		}
	    
        $scope.baseGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'baseGrid',
            height : function () { 														   //根据页面具体内容设置表格高度。
    			$(function () {
    				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 29 - 10) + "px";
    				angular.element($(".baseGrid")).css('height', roldGridHeight);
    			});
    			$(window).resize(function () {
    				var roldGridHeight = ($('.edit-body').height() - 35 - 35 - 29 - 10) + "px";
    				angular.element($(".baseGrid")).css('height', roldGridHeight);
    			})
    		},
            gridOptions : {
                columnDefs: [
                    {name :'ROW_ID', displayName :'序号', width :'5%'},
    				{name : 'BZMC', displayName : '标准名称', width :'15%', cellClass : "grid-col-left"},
    				{name : 'WRYLB', displayName : '标准分类', width :'10%', cellClass : "grid-col-left"},
    				{name : 'JCDMC', displayName : '监测点名称', width :'10%', cellClass : "grid-col-center"},
    				{name : 'QYMC', displayName : '企业名称', width :'10%', cellClass : "grid-col-center"},
        			{name : 'SHENG', displayName : '省', width :'8%', cellClass : "grid-col-center"},
        			{name : 'SHI', displayName : '市', width :'8%', cellClass : "grid-col-center"},
        			{name : 'XIAN', displayName : '县', width :'8%', cellClass : "grid-col-center"},
    				{name : 'QSSJ', displayName : '开始时间', width :'13%', cellClass : "grid-col-center",
    					cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
    				{name : 'JSSJ', displayName : '结束时间', width :'13%', cellClass : "grid-col-center",
        					cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
        			
                ],
                //组合配置checkbox复选框
    			enableSelectAll : false,          										//是否全选选择框按钮
    			enableRowHeaderSelection : false,  										//是否选择框按钮

    			//组合配置单行无复选框的选中
    			enableRowSelection : true,
    			multiSelect : false
            },
            dataConfig : {
                url: Common.webRoot() + '/ep/epsm/emissionstandard/standardmanagement/standardmanagementController/querypointstandard',
                data:{					
                    pageSize:Common.pageSize,
                    pageNum:1
                }
            },
            gridEvent : {                                                               //表格事件方法
            	/*以下部分的函数名称是自定义的*/
    			_rowClickCallback: function () {
    			},
    			_dbClickRow : function (row,$event) {									//数据行双击事件，单击事件为：_rowClick(),默认有单击行事件，执行回调方式为：_rowClickCallback
    			}
    		}
        };
        
        Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格
        
        // 查询列表
        $scope.changeSelectedBZFL = function (item, model) {
    		$scope.search();
        };
        
    	$scope.search = function() {
    		$scope.baseGrid.dataConfig.data = $scope.piont;
    		$scope.baseGrid.dataConfig.data.bzfl = $scope.BZFL.selected.value;
    		$scope.baseGrid.dataConfig.data.pageSize = Common.pageSize;
    		$scope.baseGrid.dataConfig.data.pageNum = 1;
    		
    		Common.page.send($scope, $http, $scope.baseGrid);
    	};
    	
    	$('body').on('keypress', function (e) {
           platform.enterSearch(e, function () {
               $scope.search();
           })
        });
    	
    }]);