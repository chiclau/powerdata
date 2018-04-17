var app = Common.initApp([]);
Common.initDirective(app);
powerUtil.initService(app);
app.controller('quotaListController', ['$scope', '$http', 'uiGridConstants','$timeout','$templateCache','commUtileService',
    function ($scope, $http, uiGridConstants,$timeout,$templateCache,commUtileService) {
	   
        $scope.ZBGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'ZBGrid',
            height : function () {
            	commUtileService.gridAutoHeight('ZBGrid');
            },
            gridOptions : {
                columnDefs: [
                    { field : 'ROW_ID', name:"序号", width: '4%',enableFiltering : false, enableSorting: false},
                    { field : 'ZBMC',  name:"名称", width: '24%',enableFiltering : false, enableSorting: false},
                    { field : 'ZBBH',  name:"编号", width: '24%',enableFiltering : false,enableSorting: false},
                    { field : 'ZBLX_NAME',  name:"类型", width: '24%',enableFiltering : false, enableSorting: false},
                    { field : 'QZDW_NAME',  name:"取值单位", width: '24%',enableFiltering : false, enableSorting: false}
                ],
                rowHeight:45,
                enableSelectAll : false,         //是否全选选择框按钮
                enableRowHeaderSelection : false
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/emissionstandard/quotaquerycontroller/quotaquery/queryquota',
                data:{
                    pageSize:Common.pageSize,
                    pageNum:1
                },
                appendSuccess : function (result) {
                    if (result.data.size > 0) {
                        //Common.grid.autoSelectedFirstRow("ZBGrid");
                    }
                }
            }
        };
        
        Common.grid.initConfig($scope,$http,$scope.ZBGrid);
		/**
		 * 查询
		 */
		/*$scope.keyupSearch = function(e){
			var keycode = window.event ? e.keyCode:e.which;
			if(keycode == 13){
				$scope.doSearch();
			}
		}*/
		
		/**
		 * 数据查询
		 */
		$scope.gridQuery = function(param){
			//高级查询参数
			if(param){
				param["searchTxt"] = param.searchTxt;
			}else{
				param["searchTxt"] = '';
			}
			$scope.ZBGrid.dataConfig.data = param;
			$scope.ZBGrid.dataConfig.data.pageNum = 1;//防止绑定数据的变化造成多一次的后台请求
			$scope.ZBGrid.dataConfig.data.pageSize = 10;
			Common.page.send($scope, $http,$scope.ZBGrid);
			$scope.ZBGrid.gridApi.grid.refresh(); 
		}
		/**
		 * 高级查询参数配置
		 */
		$scope.queryOption={
				autoComplete:false,//保留项
				advQueryDisplay:"block", //"none"||"block"
				seachInputTips:"请输入项目名称|项目编号",
				buttons:[{
					name:"查询",
					iconClass:"fa fa-search",
					clickFun:function(param){
						//跳转到任务发起
						$scope.gridQuery(param);
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
						commUtileService.gridAutoHeight('ZBGrid');
					}
				},
				paramData:[{
					type:"url", //"dataArr"||"code"||"URL"|| "date"
					url:Common.webRoot() + "/public/epsm/common/commoncontroller/getdevice",
					title:"项目类型",
					textField:'C_NAME',
					urlParam:{},
					valueField:'PARAM0',
					name:"zblx",
					multi:false//多选 为true
				}]
				
		}		

    }]);