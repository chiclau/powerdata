var app = Common.initApp([]);
app.controller('dataReportNoticeUserListController', ['$scope', '$http', 'uiGridConstants','$timeout','$templateCache',
    function ($scope, $http, uiGridConstants,$timeout,$templateCache) {
		//搜索条件
		$scope.isQYShow=false;
		$scope.isSHIShow=false;
		$scope.isXIANShow=false;
		$scope.isCOMShow=false;
		
        //初始化区县
        $scope.XIANS = [
            {label: '请选择' , value: ''}
        ];
        //默认区县
        $scope.XIANS.selected = $scope.XIANS[0];	
        //初始化市
        $scope.SHIS = [
            {label: '请选择' , value: ''}
        ];
        //默认市
        $scope.SHIS.selected = $scope.SHIS[0];	
        //选中的行政区划值
        $scope.xzqhdm='';
		if($scope.queryType=="1"){//省级
			$scope.columnDefs=[
                { field : 'ROW_ID', name:"序号", width: '5%', enableSorting: false },
                { field : 'SHI',  name:"市", width: '19%', enableSorting: false},
                { field : 'YHMC',  name:"用户名称", width: '19%', enableSorting: false},
                { field : 'YHSJ',  name:"手机号码", width: '19%', enableSorting: false },
                { field : 'DZYX',  name:"电子邮箱", width: '19%', enableSorting: false },
                { field : 'U_OPERATE', width: '19%' , displayName: "操作", cellClass : 'grid-col-center',
		        	  cellTemplate : '<a title="催报" class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.CBGrid.gridEvent.openEdit(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-envelope"></span></a> &nbsp;&nbsp;'}
            ];
			$scope.isSHIShow=true;
			$scope.isCOMShow=true;
	        Common.send($scope, $http, {
				method: 'POST',
				url: Common.webRoot() + '/public/epsm/common/commoncontroller/getxzqh',
				success: function(result){
					var datas = result.data;
					var dataArr = $scope.SHIS;
					for(var i = 0; i < datas.length; i++) {
							dataArr.push({label: datas[i].XZQH, value: datas[i].XZQHDM});						
					}
					$scope.SHIS = dataArr;
				}
			});

		}else if($scope.queryType=="2"){//市
			$scope.columnDefs=[
                { field : 'ROW_ID', name:"序号", width: '5%', enableSorting: false },
                { field : 'XIAN',  name:"区县", width: '19%', enableSorting: false},
                { field : 'YHMC',  name:"用户名称", width: '19%', enableSorting: false},
                { field : 'YHSJ',  name:"手机号码", width: '19%', enableSorting: false },
                { field : 'DZYX',  name:"电子邮箱", width: '19%', enableSorting: false },
                { field : 'U_OPERATE', width: '19%' , displayName: "操作", cellClass : 'grid-col-center',
		        	  cellTemplate : '<a title="催报" class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.CBGrid.gridEvent.openEdit(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-envelope"></span></a> &nbsp;&nbsp;'}
            ];
			$scope.isXIANShow=true;
			$scope.isCOMShow=true;
	        Common.send($scope, $http, {
				method: 'POST',
				url: Common.webRoot() + '/public/epsm/common/commoncontroller/getxzqh',
				success: function(result){
					var datas = result.data;
					var dataArr = $scope.XIANS;
					for(var i = 0; i < datas.length; i++) {
							dataArr.push({label: datas[i].XZQH, value: datas[i].XZQHDM});						
					}
					$scope.XIANS = dataArr;
				}
			});
		}else if($scope.queryType=="3"){//县
			$scope.columnDefs=[
                { field : 'ROW_ID', name:"序号", width: '5%', enableSorting: false },
                { field : 'QYMC',  name:"企业名称", width: '19%', enableSorting: false},                  
                { field : 'YHMC',  name:"联系人", width: '19%', enableSorting: false},             
                { field : 'YHSJ',  name:"手机号码", width: '19%', enableSorting: false },
                { field : 'DZYX',  name:"电子邮箱", width: '19%', enableSorting: false },
                { field : 'U_OPERATE', width: '19%' , displayName: "操作", cellClass : 'grid-col-center',
		        	  cellTemplate : '<a title="催报" class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.CBGrid.gridEvent.openEdit(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-envelope"></span></a> &nbsp;&nbsp;'}
            ]
			$scope.isQYShow=true;
		}
		//回车事件
		$scope.keyupSearch = function(e){
			var keycode = window.event ? e.keyCode:e.which;
			if(keycode == 13){
				$scope.toSearchData();
			}
		}
        //查询条件
    	$scope.changeSelectedXZQH = function (item, model) {
    		$scope.xzqhdm=item.value;
    		$scope.toSearchData();
        }; 
        
        //搜索按钮点击
        $scope.toSearchData = function () {
            //将搜索条件复制给列表数据条件对象中
        	$scope.CBGrid.dataConfig.data={};
        	if($scope.searchTerm){
        		$scope.CBGrid.dataConfig.data.YHMC= $scope.searchTerm.yhmc;
        	}
        	if($scope.queryType=="1"){
        		$scope.CBGrid.dataConfig.data.XZQHDMSHI=$scope.xzqhdm;
        	}else if($scope.queryType=="2"){
        		$scope.CBGrid.dataConfig.data.XZQHDMXIAN=$scope.xzqhdm;
        	}else if($scope.queryType=="3"){
            	if($scope.searchTerm.qymc){
                	$scope.CBGrid.dataConfig.data.QYMC = $scope.searchTerm.qymc;        		
            	}
            	if($scope.searchTerm.lxr){
            		$scope.CBGrid.dataConfig.data.YHMC= $scope.searchTerm.lxr;
            	}
        	}
        	$scope.CBGrid.dataConfig.data.queryType=$scope.queryType;
            Common.page.pageChanged($scope,$http,$scope.CBGrid);
        };
        
        $scope.CBGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'CBGrid',
            height : function () {
    			//计算表格高度 注意使用必选是boostrap布局    		
                $(function () {
                    var gridHeight = ($('.edit-body').height() - 75) + "px";
                    angular.element($(".CBGrid")).css('height', gridHeight);
                });
                $(window).resize(function () {
                    var gridHeight = ($('.edit-body').height() - 75 ) + "px";
                    angular.element($(".CBGrid")).css('height', gridHeight);
                })
            },
            gridOptions : {
                columnDefs:$scope.columnDefs
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/getdatareportnoticeuser',
                data:{
                    pageSize:Common.pageSize,
                    pageNum:1,
                    queryType:$scope.queryType
                },
                appendSuccess : function (result) {
                    if (result.data.size > 0) {
                        Common.grid.autoSelectedFirstRow("CBGrid");
                    }
                }
            },
            gridEvent : {
				  //以下是Grid使用到的自定义函数
            	openEdit: function(grid, row) {
						var openConfig = {
							title:"催报信息",
							type: 'open',
							width: '60%',
							height: '60%',
							url : Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/edit?openType=edit&BCBDX='+row.entity.YHID+(row.entity.QYID?("&QYID="+row.entity.QYID):""),
							end: function () {
								 $scope.toSearchData();
							}
						};
						Common.dialog(openConfig);
					  }
          }
        };
        Common.grid.initConfig($scope,$http,$scope.CBGrid);
        
        //批量 
        $scope.batchUrgeReport=function(){
        	var row=$scope.CBGrid.gridApi.selection.getSelectedRows();
    		if(row.length==0){
    			Common.dialog({
    		    	type:"alert",
    		    	content:"请选中要催报的"+($scope.queryType=="3"?"企业":"用户")+"！"
    		    });
    			return false;
    		}
    		//var addList=[];
    		var ids='';
    		var qyids='';
			for(var i=0;i<row.length;i++){
				if(i>0){
					ids+=',';
					if(row[i].QYID){
						qyids+=',';
					}
				}
				ids+=row[i].YHID;
				if(row[i].QYID){
					qyids+=row[i].QYID;
				}

			}
			var openConfig = {
					title:"催报信息",
					type: 'open',
					width: '60%',
					height: '60%',
					url : Common.webRoot() + '/both/epsm/workbench/datareportnoticecontroller/datareportnotice/edit?openType=edit&ids='+ids+'&qyids='+qyids,
					end: function () {
						 $scope.toSearchData();
					}
				};
			Common.dialog(openConfig);
        };
    }]);