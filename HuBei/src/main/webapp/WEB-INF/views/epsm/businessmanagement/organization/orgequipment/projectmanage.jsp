<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='本机构设备监测项目管理';project.JCJG_SBJCX_SBID='${JCJG_SBJCX_SBID }';">
<div class="container-full" ng-controller="projectManageController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>本机构设备监测项目管理</i>
        </div>
        <div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
			<button type="button" class="btn btn-default btn-sm" ng-click="add()">新&nbsp;增</button>
		</div>
    </div>

    <div class="edit-body row" style="overflow: hidden">

        <%--生成基础表格组件HTML-----------------开始--%>
        <div class="col-md-12" style="padding: 0px;margin-top: 10px;">
            <div data-id="baseGrid" data-classname="baseGrid color-ext" ng-transclude grid-directive></div>
        </div>
        <%--生成基础表格组件HTML-----------------结束--%>

    </div>

</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/businessmanagement/organization/orgequipment/projectmanage.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>