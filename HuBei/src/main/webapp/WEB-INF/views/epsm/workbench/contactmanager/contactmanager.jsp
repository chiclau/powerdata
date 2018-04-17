<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='联系人管理';">
<div class="container-full" ng-controller="contactManagerController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>联系人管理</i>
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
                <div class="col-md-4">
                    <label class="col-md-3">姓名：</label>
                    <div class="col-md-9">
                        <div class="searchContent">
                            <input class="searchInput" type="text" ng-model="contact.lxrxm"
                                   placeholder="输入姓名,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
                            <i class="glyphicon glyphicon-search platform-search-icon"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-4" style="padding-right: 12%;">
                    <label class="col-md-5">单位名称：</label>
                    <div class="col-md-7">
                        <div class="searchContent">
                            <input class="searchInput" type="text" ng-model="contact.lxrmc"
                                   placeholder="输入单位名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
                            <i class="glyphicon glyphicon-search platform-search-icon"></i>
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <label class="col-md-5">用户类别：</label>
                    <div class="col-md-7">
                        <ui-select ng-model="userTypes.selected" on-select="changeSelectedUserType($item, $model)">
							<ui-select-match>
								<span ng-bind="$select.selected.label"></span>
							</ui-select-match>
							<ui-select-choices repeat="item in (userTypes | filter: $select.search) track by item.value">
								<span ng-bind="item.label"></span>
							</ui-select-choices>
						</ui-select>
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
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/workbench/contactmanager/contactmanager.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>