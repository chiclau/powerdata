var app = Common.initApp(['ngAnimate', 'ui.bootstrap', 'angular.filter']);
Common.initDirective(app);
powerUtil.initService(app);
app.controller('standardListController', ['$scope', '$http', 'uiGridConstants','$timeout','$templateCache','commUtileService',
    function ($scope, $http, uiGridConstants,$timeout,$templateCache,commUtileService) {       
        $scope.BZGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'BZGrid',
            height : function () {
            	commUtileService.gridAutoHeight('BZGrid');
            },
            gridOptions : {
                columnDefs: [
                    { field : 'ROW_ID', name:"序号", width: '5%', enableSorting: false},
                    { field : 'BZMC',  name:"名称", width: '17%', enableSorting: false},
                    { field : 'BZBH',  name:"编号", width: '17%', enableSorting: false},
                    { field : 'BZLX_NAME',  name:"类型", width: '17%', enableSorting: false},
                    { field : 'BZFL_NAME',  name:"标准分类", width: '17%', enableSorting: false},
                    { field : 'SSSJ',  name:"实施时间", width: '17%', enableSorting: false,
                    	cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
                	{ field : 'U_OPERATE', width: '10%' , displayName: "操作", cellClass : 'grid-col-center',
      		        	  cellTemplate : '<a title="查看" class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.BZGrid.gridEvent.openSee(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-search"></span></a> &nbsp;&nbsp;' +
    		        	  '<a title="编辑"  class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.BZGrid.gridEvent.openEdit(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-edit"></span></a> &nbsp;&nbsp; '+
    		        	  '<a title="删除"  class="grid-vertical-middle" href="javascript:void(0);" ng-click="grid.appScope.BZGrid.gridEvent.remove(grid, row);"><span class="grid-vertical-middle glyphicon glyphicon-remove"></span></a> &nbsp;&nbsp;'}
                ],
                rowHeight:45,
                enableSelectAll : false,         //是否全选选择框按钮
                enableRowHeaderSelection : false //是否选择框按钮
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/querystandard',
                data:{
                    pageSize:Common.pageSize,
                    pageNum:1
                },
                appendSuccess : function (result) {
                    if (result.data.size > 0) {
                        //Common.grid.autoSelectedFirstRow("BZGrid");
                    }
                }
            },
	        gridEvent : {
				  //以下是Grid使用到的自定义函数
				  openSee: function(grid, row) {
						var openConfig = {
							title:row.entity.BZMC,
							type: 'open',
							width: '80%',
							height: '60%',
							url : Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/edit?standardID=' + row.entity.BZ_ID+'&openType=look',
							end: function () {
								$scope.gridQuery({searchTxt:''});
							}
						};
						Common.dialog(openConfig);
					  },
				  openEdit: function(grid, row) {
					var openConfig = {
						title:row.entity.BZMC,
						type: 'open',
						width: '80%',
						height: '60%',
						url : Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/edit?standardID=' + row.entity.BZ_ID+'&openType=edit',
						end: function () {
							$scope.gridQuery({searchTxt:''});
						}
					};
					Common.dialog(openConfig);
				  },
				  remove: function(grid, row) {
					  	$scope.DELETE_ID=row.entity.BZ_ID;
			    		Common.dialog({
			                type : 'confirm',
			                content : '确认删除"'+row.entity.BZMC+'"吗？',
			                title : '提示',
			                callback: function () {
					    		var deleteConfig = {
					        			url: Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/deletestandard',
					        			method: 'POST',
					        			showTips: true,
					        			data: {'standardID':$scope.DELETE_ID},
					        			success: function(repsonse){			
					        				$scope.gridQuery({searchTxt:''});
					        			},
					        			error: function(response){
					        				throw new Error('请求[' + deleteConfig.url + ']出错,[' + response.data + ']');
					        			}
					        		};			                				                	
				        		Common.send($scope, $http, deleteConfig);	
			                },
			                error: function () {
			                    return false
			                }
			            })
				  
				  }
            }
        };
        Common.grid.initConfig($scope,$http,$scope.BZGrid);
		
		/**
		 * 数据查询
		 */
		$scope.gridQuery = function(param){
			//高级查询参数
			if(param){
				param["searchTxt"] = param.searchTxt;
			}
			$scope.BZGrid.dataConfig.data = param;
			//$scope.BZGrid.dataConfig.data.pageNum = 1;//防止绑定数据的变化造成多一次的后台请求
			//$scope.BZGrid.dataConfig.data.pageSize = 10;
			Common.page.send($scope, $http,$scope.BZGrid);
			$scope.BZGrid.gridApi.grid.refresh(); 
		}
		/**
		 * 高级查询参数配置
		 */
		$scope.queryOption={
				autoComplete:false,//保留项
				advQueryDisplay:"block", //"none"||"block"
				seachInputTips:"请输入标准名称|标准编号|监测项目",
				buttons:[{
					name:"查询",
					iconClass:"fa fa-search",
					clickFun:function(param){
						//跳转到任务发起
						$scope.gridQuery(param);
					}
				},{
					name:"新增",
					iconClass:"fa fa-pencil-square-o",
					clickFun:function(param){
						Common.dialog({
		        			title:'新增标准',
		        			type: 'open',
		        			width: '80%',
		        			height: '60%',
		        			url : Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/edit',
		        			end: function (param) {
		        				$scope.gridQuery(param);
		        			}
		        		});
					}
				}],
				//事件处理
				events:{
					//点击子参数事件--组件方法
					clickSubItem:function(param){
						$scope.gridQuery(param);
					},
					//查询按钮事件--组件方法
					searchBtnClick:function(param){
						$scope.gridQuery(param);
					},
					//enter 按键事件
					enterKeyup:function(param){
						$scope.gridQuery(param);
					},
					//显示高级查询回调函数
					showSubQueryCallb:function(){
						//重新计算表格高度
						commUtileService.gridAutoHeight('BZGrid');
					}
				},
				paramData:[{
					type:"url", //"dataArr"||"code"||"URL"|| "date"
					url:Common.webRoot() + "/public/epsm/common/commoncontroller/getstandardclassify",
					title:"标准分类",
					textField:'C_NAME',
					urlParam:{field:"VALID",value:"1"},
					valueField:'PARAM0',
					name:"bzfl",
					multi:false//多选 为true
				}]
				
		}	
		
    	//新增标准
    	$scope.toAddStandard = function() {
    		var openConfig = {
    			title:'新增标准',
    			type: 'open',
    			width: '80%',
    			height: '60%',
    			url : Common.webRoot() + '/both/epsm/emissionstandard/levelstandardmanagercontroller/levelstandard/edit',
    			end: function () {
    				$scope.gridQuery({searchTxt:''}); 
    			}
    		};
    		Common.dialog(openConfig);
    	}
                
    }]);