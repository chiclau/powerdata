var app = Common.initApp([]);
app.controller('monitorAbilityListController', ['$scope', '$http', 'uiGridConstants','$timeout','$templateCache',
    function ($scope, $http, uiGridConstants,$timeout,$templateCache) {
	      
        //搜索按钮点击
        $scope.toSearchData = function () {
            //将搜索条件复制给列表数据条件对象中
        };
        
        $scope.monitorAbilityGrid = {
            dependence:{uiGridConstants:uiGridConstants,$templateCache:$templateCache},
            gridID:'monitorAbilityGrid',
            height : function () {
    			//计算表格高度 注意使用必选是boostrap布局   		
                $(function () {
                    var gridHeight = ($('.edit-body').height() - 45  ) + "px";
                    angular.element($(".monitorAbilityGrid")).css('height', gridHeight);
                });
                $(window).resize(function () {
                    var gridHeight = ($('.edit-body').height() - 45  ) + "px";
                    angular.element($(".monitorAbilityGrid")).css('height', gridHeight);
                })
            },
            gridOptions : {
                columnDefs: [
                    { field : 'ROW_ID', name:"序号", width: '5%', enableSorting: false},
                    { field : 'C_NAME',  name:"类别", width: '19%', enableSorting: false},
                    { field : 'JCJG_SBJCX_JCXM', name:"监测项目", width: '19%', enableSorting: false},
                    { field : 'JCJG_SBJCX_FFYL',  name:"分析方法", width: '19%', enableSorting: false},
                    { field : 'JCJGSB_SBMC',  name:"所用设备", width: '19%', enableSorting: false},
                    { field : 'JCJGRY_NAME',  name:"持证人员", width: '19%', enableSorting: false},
                    ],
                enableSelectAll : false,         //是否全选选择框按钮
                enableRowHeaderSelection : false //是否选择框按钮
            },
            dataConfig : {
                url: Common.webRoot() + '/both/epsm/businessmanagement/organization/monitorabilitycontroller/getmonitorability',
                data:{
                    pageSize:Common.pageSize,
                    pageNum:1
                },
                appendSuccess : function (result) {
                    if (result.data.size > 0) {
                        Common.grid.autoSelectedFirstRow("monitorAbilityGrid");
                    }
                }
            }
        };
        Common.grid.initConfig($scope,$http,$scope.monitorAbilityGrid);
    }]);