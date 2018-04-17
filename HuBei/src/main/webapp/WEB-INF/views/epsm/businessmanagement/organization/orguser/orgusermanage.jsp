<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='本机构人员管理';">
<div class="container-full" ng-controller="orgUserManageController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>本机构人员管理</i>
        </div>
        <div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
			<button type="button" class="btn btn-default btn-sm" ng-click="search()">查&nbsp;询</button>
			<button type="button" class="btn btn-default btn-sm" ng-click="add()">新&nbsp;增</button>
		</div>
    </div>

    <div class="edit-body row" style="overflow: hidden">

        <%--搜索栏过滤条件---------------------------------开始--%>

        <div class="col-md-12" style="padding: 0px; text-align: right;">
        	<div class="form-group">
        		<div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">姓名：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="orgUser.JCJGRY_NAME"
	                               placeholder="按姓名查找,按回车键搜索" style="float: left;text-indent: 10px;width: 240px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">性别：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <ui-select ng-model="sexes.selected" on-select="changeSelectedSex($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (sexes | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
	                    </div>
	                </div>
	            </div>
            </div>
            <div class="form-group">
        		<div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">所在部门：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <ui-select ng-model="orgDepts.selected" on-select="changeSelectedOrgDept($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (orgDepts | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">出生年月：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <div date-picker divid='JCJGRY_CSNY' ng-model="orgUser.JCJGRY_CSNY" style="width: 107%;margin-left: -3%;"
                             validateclass="easyui-validatebox" ng-type="date"
                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
                             ng-write-able="true" placeholder="请选择出生年月" ng-format="yyyy-MM-dd"></div>
	                    </div>
	                </div>
	            </div>
            </div>
        </div>

        <%--搜索栏过滤条件---------------------------------结束--%>



        <%--生成基础表格组件HTML-----------------开始--%>
        <div class="col-md-12" style="padding: 0px;margin-top: 10px;">
            <div data-id="baseGrid" data-classname="baseGrid color-ext" ng-transclude grid-directive></div>
        </div>
        <%--生成基础表格组件HTML-----------------结束--%>

    </div>

</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/businessmanagement/organization/orguser/orgusermanage.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>