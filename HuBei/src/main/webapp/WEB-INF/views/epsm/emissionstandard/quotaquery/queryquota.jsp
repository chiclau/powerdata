<%@ include file="/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="searchObj={}">
	<div class="container-full" ng-controller="quotaListController">
		<div class="row" style="margin-bottom: 10px;">
			<div class="col-xs-12">
				<power-adv-query query-option="queryOption" template-url="adv-query-normal.html">
				</power-adv-query>
			</div>
		</div>
		<div class="row">
			<div class="col-xs-12">
				<div data-id="ZBGrid" data-classname="ZBGrid grid-ext"  ng-transclude grid-directive></div>
			</div>
		</div>
	</div>
<script type="text/javascript" src="<common:webRoot />/resources/epsm/emissionstandard/quotaquery/queryquota.js?v=${sysversion}"></script>
</body>
<%@ include file="/common/footer.jsp"%>