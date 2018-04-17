var app = Common.initApp();
app.controller('standardListController', ['$scope', '$http', 'uiGridConstants','$templateCache',
       function ($scope, $http, uiGridConstants, $templateCache) {
        
		//标准类型
	    $scope.BZLX = [
	        {label: '全部' , value: ''}
	    ];
	    //默认类型
	    $scope.BZLX.selected = $scope.BZLX[0];
	    
	    Common.send($scope, $http, {
			method: 'POST',
			url: Common.webRoot() + '/public/epsm/common/commoncontroller/getstandardtype',
			success: function(result){
				var datas = result.data;
				var dataArr = $scope.BZLX;
				for(var i = 0; i < datas.length; i++) {
					dataArr.push({label: datas[i].C_NAME, value: datas[i].PARAM0});
				}
				$scope.BZLX = dataArr;
			}
		});
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
    				{name : 'BZMC', displayName : '名称', width :'36%', cellClass : "grid-col-left",
    					cellTemplate : "<a class='grid-vertical-middle' href='javascript:void(0);' ng-click='grid.appScope.baseGrid.gridEvent.baseGridShow(grid, row);'>{{COL_FIELD}}</a>"},
    				{name : 'BZBH', displayName : '编号', width :'13%', cellClass : "grid-col-left"},
    				{name : 'BZLXMC', displayName : '类型', width :'13%', cellClass : "grid-col-center"},
    				{name : 'BZFL', displayName : '标准分类', width :'13%', cellClass : "grid-col-center",
    					cellTemplate : '<div class="grid-vertical-middle">{{grid.appScope.baseGrid.gridEvent.baseGridBzflShow(grid, row)}}</div>'},
    				{name : 'SSSJ', displayName : '实施时间', width :'13%', cellClass : "grid-col-center",
    					cellTemplate : "<span class='grid-vertical-middle'>{{COL_FIELD | date : 'yyyy-MM-dd'}}</span>"},
                    {name : "U_OPERATE", displayName: "操作", enableSorting: false,          //自定义各种操作按钮，使用模板替换的方式
    					cellTemplate : "<a style='height: 100%' title='详情' ng-click='grid.appScope.baseGrid.gridEvent.baseGridShow(grid, row);'><span class='glyphicon glyphicon-search'></span></a>"
    				}
                ],
                //组合配置checkbox复选框
    			enableSelectAll : false,          										//是否全选选择框按钮
    			enableRowHeaderSelection : false,  										//是否选择框按钮

    			//组合配置单行无复选框的选中
    			enableRowSelection : true,
    			multiSelect : false
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/knowledge/standardquery/standardquerycontroller/querystandard',
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
            	baseGridBzflShow : function (grid, row) {
            		var bzflmc="";
            		if(row.entity.BZFL=="A"){
            			bzflmc = bzflmc + " 废气";
            		}
            		if(row.entity.BZFL1=="B"){
            			bzflmc = bzflmc + " 废水";
            		}
            		if(row.entity.BZFL2=="C"){
            			bzflmc = bzflmc + " 无组织";
            		}
            		if(row.entity.BZFL3=="E"){
            			bzflmc = bzflmc + " 厂界噪声";
            		}
            		if(row.entity.BZFL4=="D"){
            			bzflmc = bzflmc + " 周边环境";
            		}
    				return bzflmc;
    			},
    			baseGridShow : function (grid, row) {
    				Common.dialog({
    					type : "open",
    					url : Common.webRoot() + "/both/epsm/knowledge/standardquery/standardquerycontroller/pagedetail?id=" + row.entity.BZ_ID,
    					width : "80%",
    					height : "80%"
    				});
    			}
    		}
        };
        
        Common.grid.initConfig($scope,$http,$scope.baseGrid);                            //调用平台方法初始化表格
        
      //查询条件
        $scope.standard = {
        		bzmc: '', 
        		bzbh: '',   
            	bzlx: '',  
            	bzfl: '', 
            	jcxm: ''
        };
        
    	$scope.changeSelectedBZLX = function (item, model) {
    		$scope.search('',item.value);
        };
        $scope.changeSelectedBZFL = function (item, model) {
    		$scope.search('',item.value);
        };
        
    	$scope.search = function(bzlx,bzfl) {
    		$scope.baseGrid.dataConfig.data = $scope.standard;
    		if(bzlx){
    			$scope.baseGrid.dataConfig.data.bzlx = bzlx;        		
        	}
        	if(bzfl){
        		$scope.baseGrid.dataConfig.data.bzfl = bzfl;
        	}
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