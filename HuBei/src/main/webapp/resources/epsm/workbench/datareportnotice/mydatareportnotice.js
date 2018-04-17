var app = Common.initApp([]);
app.controller('dataReportNoticeListController', ['$scope', '$http', 'uiGridConstants','$timeout','$templateCache',
    function ($scope, $http, uiGridConstants,$timeout,$templateCache) {
	      
        //搜索按钮点击
        $scope.toSearchData = function () {
            //将搜索条件复制给列表数据条件对象中
        	$scope.CBGrid.dataConfig.data={};
        	if($scope.cbzt){
            	$scope.CBGrid.dataConfig.data.CBZT = $scope.cbzt;        		
        	}
        	if($scope.cbnr){
        		$scope.CBGrid.dataConfig.data.CBNR= $scope.cbnr;
        	}
            Common.page.pageChanged($scope,$http,$scope.CBGrid);
        };
		//回车事件
		$scope.keyupSearch = function(e){
			var keycode = window.event ? e.keyCode:e.which;
			if(keycode == 13){
				$scope.toSearchData();
			}
		}
        $scope.CBGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'CBGrid',
            height : function () {
    			//计算表格高度 注意使用必选是boostrap布局    		
    			$(function () {
    				var roldGridHeight = ($('.edit-body').height()-75) + "px";
    				angular.element($(".CBGrid")).css('height', roldGridHeight);
    			});
    			$(window).resize(function () {
    				var roldGridHeight = ($('.edit-body').height()-75) + "px";
    				angular.element($(".CBGrid")).css('height', roldGridHeight);
    			})
            },
            gridOptions : {
                columnDefs: [
                    { field : 'ROW_ID', name:"序号", width: '4%', enableSorting: false},
                    { field : 'CBZT',  name:"催报标题", width: '24%', enableSorting: false},
                    { field : 'CBNR',  name:"催报内容", width: '24%', enableSorting: false},
                    { field : 'CREATETIME',  name:"催报日期", width: '24%', enableSorting: false ,cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
                    {field : 'U_OPERATE', width: '24%' , displayName: "操作", cellClass : 'grid-col-center',
    		        	  cellTemplate : '<a title="查看" class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.CBGrid.gridEvent.openSee(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-search"></span></a> &nbsp;&nbsp;'}
                ],
                enableSelectAll : false,         //是否全选选择框按钮
                enableRowHeaderSelection : false //是否选择框按钮
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/getmydatareportnotice',
                data:{
                    pageSize:Common.pageSize,
                    pageNum:1
                },
                appendSuccess : function (result) {
                    if (result.data.size > 0) {
                        Common.grid.autoSelectedFirstRow("CBGrid");
                    }
                }
            },
            gridEvent : {
				  //以下是Grid使用到的自定义函数
				  openSee: function(grid, row) {
						var openConfig = {
							title:row.entity.CBZT,
							type: 'open',
							width: '60%',
							height: '60%',
							url : Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/edit?UUID='+row.entity.UUID +'&openType=look',
							end: function () {
								toSearchData();
							}
						};
						Common.dialog(openConfig);
					  }
          }
        };
        Common.grid.initConfig($scope,$http,$scope.CBGrid);
    }]);