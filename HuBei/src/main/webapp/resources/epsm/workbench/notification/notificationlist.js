var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('CaseInformationListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {
	$scope.sender = [
		               { yhmc: "全部",yhid: ''}
		       	];
	Common.send($scope, $http ,{
		method: "POST",
        url: Common.webRoot()+"/both/epsm/workbench/notification/controller/notificationcontroller/findUser",
        success: function(result){
        	var datas = result.data;
        	var dataArr = $scope.sender;
			for(var i = 0; i < datas.length; i++) {
				dataArr.push({yhmc: datas[i].yhmc, yhid: datas[i].yhid});
			}
			$scope.sender = dataArr;
        }
	});

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
			  {name : 'title', displayName : '标题', cellClass : "grid-col-left"},
			  {name : 'yhmc', displayName : '发布人', width :'8%'},
			  {name : 'validdate', displayName : '生效时间', width :'10%'},
			  {name : 'invaliddate', displayName : '失效时间', width :'10%'},
			  {name : 'fbsj', displayName : '发布时间', width :'10%'},
			  {name : 'fbfw', displayName : '发布范围', width :'15%'},
			  {name : 'cz', displayName : '操作',
				  cellTemplate : "<a style='height: 100%' title='查看' ng-click='grid.appScope.NotificationGrid.gridEvent.check(grid, row);'><span class='glyphicon glyphicon-search'></span></a>",
					cellClass : "tdButtonStyle",width :'5%'}
		  ],
		  rowHeight : 45,
		  enableSelectAll : false,
		  enableRowHeaderSelection : false
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/ep/epsm/workbench/notification/controller/notificationcontroller/findnotification',
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
		  check: function (grid, row, $event) {
	          Common.dialog({
	              type : "open",
	              title : "详细信息",
	              width : '900px',
	              height : '420px',
	              url : Common.link("/both/epsm/workbench/notification/controller/notificationcontroller/pagedetail?id="+row.entity.id)
	          });
		  },
		  edit: function (grid, row, $event) {
	          Common.dialog({
	              type : "open",
	              title : "编辑",
	              width : '900px',
	              height : '420px',
	              url : Common.link("/epsm/knowledge/caseinformation/controller/caseinformationcontroller/pageedit?zbalid="+row.entity.zbalid)
	          });
		  },
		  del: function (grid, row, $event) {
			  Common.dialog({
		    	type:"confirm",
		    	content:"确认删除？",
		    	callback:function(){
		    		var ids=[];
		    		alert(row.entity.zbalid);
					ids.push(row.entity.zbalid);
					debugger;
					Common.send($scope, $http ,{
						method: "POST",
			            url: Common.webRoot()+"/epsm/knowledge/caseinformation/controller/caseinformationcontroller/delcaseinformation",
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
	
	$scope.changeSelected=function(item, model){
		$scope.fbr = item.yhid;
	};
	
	$scope.toSearch = function(){
		$scope.NotificationGrid.dataConfig.data.title = $scope.title;
		$scope.NotificationGrid.dataConfig.data.yhmc = $scope.yhmc;
		$scope.NotificationGrid.dataConfig.data.validdate = $scope.validdate;
		$scope.NotificationGrid.dataConfig.data.invaliddate = $scope.invaliddate;
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
					ids.push(row[i].zbalid);
				}
				Common.send($scope, $http ,{
					method: "POST",
		            url: Common.webRoot()+"/epsm/knowledge/caseinformation/controller/caseinformationcontroller/delcaseinformation",
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
    }

	$scope.addOpenPage = function () {
	    Common.dialog({
	        type : "open",
	        title : "新增案例信息",
	        width : '900px',
	        height : '500px',
	        url : Common.link("/ep/epsm/workbench/notification/controller/notificationcontroller/pageadd"),
	        end : function(){
            	Common.page.send($scope, $http, $scope.NotificationGrid);
            }
	    });
	  };
	  
	  $('body').on('keypress', function (e) {
        platform.enterSearch(e, function () {
            $scope.toSearch();
        })
      });
}]);