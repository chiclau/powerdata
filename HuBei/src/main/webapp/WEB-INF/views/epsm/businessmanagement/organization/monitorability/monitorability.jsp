<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构监测能力查询'" style="font-size:1rem">
<!-- 基于 bootstrap 布局 -->
	<div class="container-full" ng-controller="monitorAbilityListController">
				<div class="edit-header row" style="overflow: hidden">
					<div class="col-xs-4 edit-title">
						<i class="icon"></i>本机构监测能力查询
					</div>
				</div>
				<div class="edit-body row" style="overflow: hidden">
					<div class="form-group">
					</div>	
					<div class="col-md-12" style="padding: 0;height: 100%">
	                	<div data-id="monitorAbilityGrid" data-classname="monitorAbilityGrid" ng-transclude grid-directive></div>
	           		</div>
           		</div>
	</div>
<script type="text/javascript" src="<common:webRoot />/resources//epsm/businessmanagement/organization/monitorability/monitorability.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>