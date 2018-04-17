<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='管理账号分配';currUserId='${SESSION_USER.yhid}';currUserProvince='${SESSION_USER_EXTEND.xzqhdmsheng}';currUserCity='${SESSION_USER_EXTEND.xzqhdmshi}';currUserArea='${SESSION_USER_EXTEND.xzqhdmxian}';">
<div class="container-full" ng-controller="systemUserManageController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>环保部门用户管理</i>
        </div>
        <div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
			<!-- <button type="button" class="btn btn-default btn-sm" ng-click="search()">查&nbsp;询</button> -->
			<button type="button" class="btn btn-default btn-sm" ng-click="add()">新&nbsp;增</button>
		</div>
    </div>

    <div class="edit-body row" style="overflow: hidden">

        <%--搜索栏过滤条件---------------------------------开始--%>

        <div class="col-md-12" style="padding: 0px; text-align: right;">
        
        	<div class="form-group">
                <div class="col-md-3">
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
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/dataacquisition/user/sameregionusermanage.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>