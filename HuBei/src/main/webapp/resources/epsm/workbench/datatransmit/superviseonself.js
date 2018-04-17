var app = Common.initApp(['ngAnimate', 'ui.bootstrap','angular.filter','angularFileUpload']);

app.controller('SuperviseOnselfController', ['$scope', '$http','uiGridConstants','$templateCache',
                                 function ($scope, $http, uiGridConstants,$templateCache) {
	setTimeout(function () {
        $scope.types = [
            {label: '组织主要领导' , value: 'ORG_LEADER'},
            {label: '组织一般领导' , value: 'ORG_LEADERS'},
            {label: '部门主要领导' , value: 'DEPT_LEADER'},
            {label: '部门一般领导' , value: 'DEPT_LEADERS'},
            {label: '部门对外接口人' , value: 'DEPT_SPEAKER'},
            {label: '部门工作人员' , value: 'WORKER'}
        ];


        $scope.types.selected = $scope.types[0];


//        $scope.deptRoles = [
//            {label: '组织主要领导' , value: 'ORG_LEADER'},
//            {label: '组织一般领导' , value: 'ORG_LEADERS'},
//            {label: '部门主要领导' , value: 'DEPT_LEADER'},
//            {label: '部门一般领导' , value: 'DEPT_LEADERS'},
//            {label: '部门对外接口人' , value: 'DEPT_SPEAKER'},
//            {label: '部门工作人员' , value: 'WORKER'}
//        ];
//        $scope.deptRoles.selected = [];
//        $scope.deptRoles.selected.push($scope.deptRoles[0]);

//        $scope.$apply();
    },5000);
}]);


app.controller('SuperviseOnselfGridController', ['$scope', '$http','uiGridConstants','$templateCache','$compile',
                                             function ($scope, $http, uiGridConstants,$templateCache,$compile) {
	$scope.getTestData = function () {
		var temp = [];
		for(var i=0;i<6;i++){
			temp.push({
				"wrwdm":'二氧化硫',
				"xsjpfnd": "123",
				"xyxpfnd": "21",
				"xsjpfzl":'123',
				"xhdpfzl":'123',
				"bycpfnd":'123',
				"byxpfnd":'123',
				"bcsl":'123',
				"bzsxjl":'123',
				"bycpfzl":'123',
				"bhdpfzl":'123',
				"zyxdlxjl":'123',
				"zqyphtdl":'123',
				"zycpfl":'123',
				"zhdpfzl":'123',
				"zpfzjl":'33'
			})
		}
		return temp;
	};

	$scope.SuperviseOnselfGrid = {
		dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
		gridID:'SuperviseOnselfGrid',
		height : function () {
			$(function () {
				angular.element($(".basicGridDemo")).css('height', "400px");
			});
			$(window).resize(function () {
				angular.element($(".basicGridDemo")).css('height', "400px");
			})
		},
		gridOptions : {
			headerTemplate:"<span></span>",     //使用空标签完全替换表头，无排序功能。若使用合并原表头此项应默认值（可保留排序功能）
//			enableHorizontalScrollbar:1,
//			enableVerticalScrollbar:1,
			onRegisterApi: function(gridApi){
				$scope.SuperviseOnselfGrid.gridApi = gridApi;
			},
			columnDefs : [
				{ field : 'wrwdm',width:'180'},
				{ field : 'xsjpfnd', width:'137' },
				{ field : 'xyxpfnd', width:'137'},
				{ field : 'xsjpfzl', width:'137'},
				{ field : 'xhdpfzl', width:'137'},
				{ field : 'bycpfnd',  width:'137'},
				{ field : 'byxpfnd',width:'137'},
				{ field : 'bcsl', width:'137'},
				{ field : 'bzsxjl',width:'137' },
				{ field : 'bycpfzl', width:'137'},
				{ field : 'bhdpfzl',  width:'137' },
				{ field : 'zyxdlxjl', width:'137' },
				{ field : 'zqyphtdl', width:'167' },
				{ field : 'zycpfl', width:'137' },
				{ field : 'zhdpfzl', width:'137'},
				{ field : 'zpfzjl'}
			]
		},
		gridHeader:{
			html:$compile(
				'<tr>\
	                <td width="35" rowspan="2"><input class="select-all" type="checkbox" title="全选" ng-click="SuperviseOnselfGrid.gridEvent._checkAll($event)"></td>\
	                <td width="180" rowspan="2">地区</td>\
	                <td colspan="4" style="border-right: 1px solid #D4D4D4;border-left: 1px solid #D4D4D4;border-bottom: 1px solid #D4D4D4">未发布企业数</td>\
					<td width="137" rowspan="2">企业总数</td>\
				</tr>\
	            <tr>\
	                <td width="137">未填报</td>\
	                <td width="137" style="border-right: 1px solid #D4D4D4">在填报</td>\
	                <td width="137">关停企业数</td>\
	                <td width="137">已发布企业数</td>\
	            </tr>')($scope)
		},
		gridEvent : {
			_rowClick : function (row, $event) {

			},
			_sortChange : function () {
				console.log($scope.confgGrid)
			},
			_checkAll:function ($event) {
				if($($event.target).attr('checked')){
					$($event.target).attr("checked",false);
					$scope.SuperviseOnselfGrid.gridApi.selection.clearSelectedRows();
				}else {
					$($event.target).attr("checked",true);
					$scope.SuperviseOnselfGrid.gridApi.selection.selectAllRows();
				}
			}
		}
	};
	Common.grid.initConfig($scope,$http,$scope.SuperviseOnselfGrid);

	//异步请求获取数据
	$scope.testData = $scope.getTestData();
	//将数据赋值给列表
	$scope.SuperviseOnselfGrid.gridOptions.data = $scope.testData;
	$scope.bigTotalItems = $scope.SuperviseOnselfGrid.gridOptions.data ? $scope.SuperviseOnselfGrid.gridOptions.data.length : 0;
}]);