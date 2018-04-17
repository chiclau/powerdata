var app = Common.initApp();
app.controller('withoutMonitorListController', ['$scope', '$http', 'uiGridConstants','$templateCache',
       function ($scope, $http, uiGridConstants, $templateCache) {
        
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
    				{name : 'QYMC', displayName : '企业名称', width :'20%', cellClass : "grid-col-left"},
    				{name : 'BJCLX', displayName : '不监测类型', width :'10%', cellClass : "grid-col-left",
    					cellTemplate : '<div class="grid-vertical-middle">{{grid.appScope.baseGrid.gridEvent.baseGridBjclxShow(grid, row)}}</div>'
    				},
    				{name : 'BJCMC', displayName : '不监测名称', width :'10%', cellClass : "grid-col-center"},
    				{name : 'BJCKSSJ', displayName : '开始时间', width :'13%', cellClass : "grid-col-center",
    					cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
    				{name : 'BJCJZSJ', displayName : '结束时间', width :'13%', cellClass : "grid-col-center",
        					cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
        			{name : 'BJCYY', displayName : '不监测原因', width :'10%', cellClass : "grid-col-center"},
        			{name : 'SFTC', displayName : '是否停产', width :'10%', cellClass : "grid-col-center"},
        			{name : 'BJCYYPZ', displayName : '不监测凭证', width :'10%', cellClass : "grid-col-center"}
                ],
                //组合配置checkbox复选框
    			enableSelectAll : false,          										//是否全选选择框按钮
    			enableRowHeaderSelection : false,  										//是否选择框按钮

    			//组合配置单行无复选框的选中
    			enableRowSelection : true,
    			multiSelect : false
            },
            dataConfig : {
                url: Common.webRoot() + '/ep/epsm/emissionstandard/standardmanagement/standardmanagementController/querynotmonitored',
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
    			},
    			baseGridBjclxShow : function (grid, row) {
    				var bjclxmc ="";
            		if(row.entity.bjclx=="1"){
            			bjclxmc = "企业";
            		}else if(row.entity.bjclx=="2"){
            			bjclxmc = "排放设备";;
            		}if(row.entity.bjclx=="3"){
            			bjclxmc = "监测点";
            		}if(row.entity.bjclx=="4"){
            			bjclxmc = "监测项目";
            		}
    				return bjclxmc;
    			}
    		}
        };
        
        Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格
        
        // 查询消息列表
    	$scope.search = function() {
    		$scope.baseGrid.dataConfig.data = $scope.bjcjlb;
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