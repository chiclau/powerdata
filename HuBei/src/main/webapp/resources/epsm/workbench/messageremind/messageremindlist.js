var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('CaseInformationListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.sender = [
	               { yhmc: "全部",yhid: ''}
	       	];
	Common.send($scope, $http ,{
		method: "POST",
        url: Common.webRoot()+"/both/epsm/workbench/messageremind/controller/messageremindcontroller/findUser",
        success: function(result){
        	var datas = result.data;
        	var dataArr = $scope.sender;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({yhmc: datas[i].yhmc, yhid: datas[i].yhid});
			}
			$scope.sender = dataArr;
        }
	});
	//默认类型
	$scope.sender.selected = $scope.sender[0];
	$scope.NotificationGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'NotificationGrid',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height() -40 -90);
				$(".NotificationGrid").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height() -40 -90);
				$(".NotificationGrid").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs: [
			  {name :'ROW_ID', displayName :'序号', width :'5%'},
			  {name :'id', visible: false},
			  {name : 'bt', displayName : '标题', width :'15%',cellClass : "grid-col-left"},
			  {name : 'nr', displayName : '内容', cellClass : "grid-col-left"},
			  {name : 'sender', displayName : '发布人', width :'10%'},
			  {name : 'sendtime', displayName : '发布时间', width :'10%'},
			  {name : 'cz', displayName : '操作',
				  cellTemplate : "<a style='height: 100%' title='删除' ng-click='grid.appScope.NotificationGrid.gridEvent.del(grid, row);'><span class='glyphicon glyphicon-trash'></span></a>",
					cellClass : "tdButtonStyle",width :'5%'}
		  ],
		  rowHeight : 45
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/both/epsm/workbench/messageremind/controller/messageremindcontroller/findmessageremind',
	      data:{
	          pageSize:10,
	          pageNum:1
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("NotificationGrid");
	        }
	      }
	  },
	  gridEvent: {
		  del: function (grid, row, $event) {
			  Common.dialog({
		    	type:"confirm",
		    	content:"确认删除？",
		    	callback:function(){
		    		var ids=[];
					ids.push(row.entity.id);
					Common.send($scope, $http ,{
						method: "POST",
			            url: Common.webRoot()+"/both/epsm/workbench/messageremind/controller/messageremindcontroller/delmessageremind",
			            data:JSON.stringify({
			                "ids":ids
			            }),
			            showTips : true,
			            success: function(data){
			            	Common.page.send($scope, $http, $scope.NotificationGrid);
			            }
					});
		            Common.dialog({type:'close',index:1});
		        }
		    });
		  }
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.NotificationGrid);

	$scope.toSearch = function(){
		$scope.NotificationGrid.dataConfig.data.bt = $scope.bt;
		$scope.NotificationGrid.dataConfig.data.nr = $scope.nr;
		$scope.NotificationGrid.dataConfig.data.sendtimestart = $scope.sendtimestart;
		$scope.NotificationGrid.dataConfig.data.sendtimeend = $scope.sendtimeend;
		$scope.NotificationGrid.dataConfig.data.sender = $scope.yhmc;
		Common.page.pageChanged($scope, $http, $scope.NotificationGrid);
	};

	$scope.toDelete = function(){
	  	var row=$scope.NotificationGrid.gridApi.selection.getSelectedRows();
		if(row.length==0){
			Common.dialog({
		    	type:"alert",
		    	content:"请选中要删除的案例信息！"
		    });
			return false;
		}
		Common.dialog({
	    	type:"confirm",
	    	content:"确认删除？",
	    	callback:function(index){
	    		var ids=[];
				for(var i=0;i<row.length;i++){
					ids.push(row[i].id);
				}
				Common.send($scope, $http ,{
					method: "POST",
		            url: Common.webRoot()+"/both/epsm/workbench/messageremind/controller/messageremindcontroller/delmessageremind",
		            data:JSON.stringify({
		                "ids":ids
		            }),
		            showTips : true,
		            success: function(data){
		            	Common.page.send($scope, $http, $scope.NotificationGrid);
		            }
				});
	            Common.dialog({type:'close',index:index});
	        }
	    });
    };
    
    $('body').on('keypress', function (e) {
        platform.enterSearch(e, function () {
            $scope.toSearch();
        })
     });
}]);