<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='本机构设备管理';">
<div class="container-full" ng-controller="orgEquipmentManageController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>本机构设备管理</i>
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
	                <label class="col-md-4">设备编号：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="orgEquipment.JCJGSB_SBBH"
	                               placeholder="按设备编号查找,按回车键搜索" style="float: left;text-indent: 10px;width: 240px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">设备名称：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="orgEquipment.JCJGSB_SBMC"
	                               placeholder="按设备名称查找,按回车键搜索" style="float: left;text-indent: 10px;width: 240px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
	                    </div>
	                </div>
	            </div>
            </div>
            <div class="form-group">
        		<div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">监测设备型号：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="orgEquipment.JCJGSB_JCSBXH"
	                               placeholder="按监测设备型号查找,按回车键搜索" style="float: left;text-indent: 10px;width: 240px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
	                    </div>
	                </div>
	            </div>
	            <div class="col-md-6" style="padding-right: 15%;">
	                <label class="col-md-4">生产厂家：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="orgEquipment.JCJGSB_SCCJ"
	                               placeholder="按生产厂家查找,按回车键搜索" style="float: left;text-indent: 10px;width: 240px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
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
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/businessmanagement/organization/orgequipment/orgequipmentmanage.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>