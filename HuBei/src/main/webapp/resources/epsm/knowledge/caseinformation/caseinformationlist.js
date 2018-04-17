var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('CaseInformationListController', ['$scope', '$http','uiGridConstants','$templateCache',
      function ($scope, $http, uiGridConstants,$templateCache) {    

	$scope.tabs = [
       {label:"废气",value:"1"},
       {label:"废水",value:"2"},
       {label:"无组织",value:"3"},
       {label:"周边环境",value:"4"}
	];

	
    //点击tab后的回调函数
    $scope.setTab = function(fwdm){
    	$scope.xxlb=fwdm;
    	$scope.CaseInformationGrid.dataConfig.data.xxlb = fwdm;
    	Common.page.send($scope, $http, $scope.CaseInformationGrid);
    };
    
	$scope.CaseInformationGrid = {
	  dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
	  gridID:'CaseInformationGrid',
	  height : function () {
		  	window.onload = function() {
				var roldGridHeight = ($('.edit-body').height() -40 -60 -61);
				$(".CaseInformationGrid").height(roldGridHeight);
		  	};
			window.onresize = function () {
				var roldGridHeight = ($('.edit-body').height() -40 -60 -61);
				$(".CaseInformationGrid").height(roldGridHeight)
			};
	  },
	  gridOptions : {
		  columnDefs: [
			  {name :'ROW_ID', displayName :'序号', width :'5%'},
			  {name :'zbalid', visible: false},
			  {name : 'almc', displayName : '案例名称', width :'15%'},
			  {name : 'allb', displayName : '案例类别', width :'8%'},
			  {name : 'sgmc', displayName : '事故名称', width :'10%'},
			  {name : 'fsdd', displayName : '发生地点', width :'10%'},
			  {name : 'wrw', displayName : '污染物', width :'10%'},
			  {name : 'qymc', displayName : '企业名称', width :'10%'},
			  {name : 'sgfssj', displayName : '事故发生时间', width :'8%'},
			  {name : 'jcjg', displayName : '监测结果', width :'15%'},
			  {name : 'cz', displayName : '操作',
				  cellTemplate : "<a style='height: 100%' title='修改' ng-click='grid.appScope.CaseInformationGrid.gridEvent.edit(grid, row);'><span class='glyphicon glyphicon-edit'></span></a>&nbsp;&nbsp;\
						 <a style='height: 100%' title='查看' ng-click='grid.appScope.CaseInformationGrid.gridEvent.check(grid, row);'><span class='glyphicon glyphicon-search'></span></a>&nbsp;&nbsp;\
						 <a style='height: 100%' title='删除' ng-click='grid.appScope.CaseInformationGrid.gridEvent.del(grid, row);'><span class='glyphicon glyphicon-trash'></span></a>",
					cellClass : "tdButtonStyle"}
		  ],
		  rowHeight : 45,
		  enableSelectAll : false,
		  enableRowHeaderSelection : false 
	  },
	  dataConfig : {
		  url: Common.webRoot()+'/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/findcaseinformation',
	      data:{
	          pageSize:10,
	          pageNum:1
	      },
	      appendSuccess : function (result) {
	        if (result.data.size > 0) {
	        	Common.grid.autoSelectedFirstRow("CaseInformationGrid");
	        }
	      }
	  },
	  gridEvent: {
		  check: function (grid, row, $event) {
	          Common.dialog({
	              type : "open",
	              title : "详细信息",
	              width : '1300px',
	              height : '600px',
	              url : Common.link("/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/pagedetail?zbalid="+row.entity.zbalid)
	          });
		  },
		  edit: function (grid, row, $event) {
	          Common.dialog({
	              type : "open",
	              title : "编辑",
	              width : '1300px',
	              height : '600px',
	              url : Common.link("/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/pageedit?zbalid="+row.entity.zbalid),
	              end : function(){
	              	Common.page.send($scope, $http, $scope.CaseInformationGrid);
	              }
	          });
		  },
		  del: function (grid, row, $event) {
			  Common.dialog({
		    	type:"confirm",
		    	content:"确认删除？",
		    	callback:function(){
		    		var ids=[];
					ids.push(row.entity.zbalid);
					Common.send($scope, $http ,{
						method: "POST",
			            url: Common.webRoot()+"/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/delcaseinformation",
			            data:JSON.stringify({
			                "ids":ids
			            }),
			            showTips : true,
			            success: function(data){
			            	Common.page.send($scope, $http, $scope.CaseInformationGrid);
			            }
					});
		            Common.dialog({type:'close',index:1});
		        }
		    });
		  }
	  }
	};
	Common.grid.initConfig($scope,$http,$scope.CaseInformationGrid);

	$scope.toSearch = function(){
		$scope.CaseInformationGrid.dataConfig.data.almc = $scope.searchGridData.almc;
		$scope.CaseInformationGrid.dataConfig.data.allb = $scope.searchGridData.allb;
		$scope.CaseInformationGrid.dataConfig.data.sgmc = $scope.searchGridData.sgmc;
		$scope.CaseInformationGrid.dataConfig.data.fsdd = $scope.searchGridData.fsdd;
		Common.page.pageChanged($scope, $http, $scope.CaseInformationGrid);
	};

	$scope.toDelete = function(){
	  	var row=$scope.CaseInformationGrid.gridApi.selection.getSelectedRows();
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
		            url: Common.webRoot()+"/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/delcaseinformation",
		            data:JSON.stringify({
		                "ids":ids
		            }),
		            showTips : true,
		            success: function(data){
		            	Common.page.send($scope, $http, $scope.CaseInformationGrid);
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
	        height : '600px',
	        url : Common.link("/ep/epsm/knowledge/caseinformation/controller/caseinformationcontroller/pageadd?xxlb="+$scope.xxlb),
	        end : function(){
            	Common.page.send($scope, $http, $scope.CaseInformationGrid);
            }
	    });
	  };
	 
     $('body').on('keypress', function (e) {
        platform.enterSearch(e, function () {
            $scope.toSearch();
        })
     });
}]);