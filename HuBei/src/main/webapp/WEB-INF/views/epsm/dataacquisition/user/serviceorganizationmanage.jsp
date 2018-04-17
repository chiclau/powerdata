<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='委托机构管理';currUserId='${SESSION_USER.yhid}';currUserLevel='${SESSION_USER.yhjb}';currUserProvince='${SESSION_USER_EXTEND.xzqhdmsheng}';currUserCity='${SESSION_USER_EXTEND.xzqhdmshi}';currUserArea='${SESSION_USER_EXTEND.xzqhdmxian}';">
<div class="container-full" ng-controller="serviceOrganizationManageController">
    <div class="edit-header row" style="overflow: hidden">
        <div class="col-xs-4 edit-title">
            <i class="icon"></i>
            <i>委托机构管理</i>
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
        		<div class="col-md-6" style="padding-right: 12%;">
	                <label class="col-md-4">机构名称：</label>
	                <div class="col-md-8">
	                    <div class="searchContent">
	                        <input class="searchInput" type="text" ng-model="serviceOrganization.JBXX_NAME"
	                               placeholder="按机构名称查找,按回车键搜索" style="float: left;text-indent: 10px;width: 410px;"/>
	                        <i class="glyphicon glyphicon-search platform-search-icon"></i>
	                    </div>
	                </div>
	            </div>
                <div class="col-md-6">
                    <label class="col-md-4">公司所在地：</label>
                    <div class="col-md-8">
                        <div class="searchContent">
                        	<region-select>
								<!--
									ng-disabled : 三种类型值，true false AutoToView .其中true与false可用变量在js中定义
								-->
								<div class="col-md-4" style="padding: 0px;" id="province">
									<province ng-model="serviceOrganization.JBXX_SHENG"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city">
									<city ng-model="serviceOrganization.JBXX_SHI"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area">
									<district ng-model="serviceOrganization.JBXX_XIAN"></district>
								</div>
							</region-select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group">
            	<div class="col-md-7">
                    <label class="col-md-3" style="margin-left: -3%;">机构类型：</label>
                    <div class="col-md-9" style="margin-left: -11%;">
						<span>
                            <input class="magic-checkbox" type="checkbox" name="organizationType" id="isDetectorId" ng-model="organizationType.isDetector">
                            <label for="isDetectorId">检测机构</label>
                        </span>
                        <span>
                            <input class="magic-checkbox" type="checkbox" name="organizationType" id="isOperatorId" ng-model="organizationType.isOperator">
                            <label for="isOperatorId">运营机构</label>
                        </span>
                        <span>
                            <input class="magic-checkbox" type="checkbox" name="organizationType" id="isSupplierId" ng-model="organizationType.isSupplier">
                            <label for="isSupplierId">在线监测设备供应商</label>
                        </span>
                        <span>
                            <input class="magic-checkbox" type="checkbox" name="organizationType" id="isIntegratorId" ng-model="organizationType.isIntegrator">
                            <label for="isIntegratorId">集成商</label>
                        </span>
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
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/dataacquisition/user/serviceorganizationmanage.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>