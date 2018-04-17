<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body ng-init="pageTitle='自行监测方法库';">
	<div class="container-full" ng-controller="SelfMonitoringMethodGridController">
		<div class="edit-header">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>自行监测方法库
			</div>
			<div class="col-xs-8 edit-btn-box" style="padding-right: 30px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="toSearch()">查&nbsp;询</button>
			</div>
		</div>
		<div class="edit-body row" style="overflow: hidden">
			<div class="col-xs-12" style="padding: 0px; text-align: left;">
				<div class="form-group">
					<div class="col-md-4">
						<label class="col-md-4">方法名称</label>
						<input type="text" class="searchFilter" placeholder="输入方法名称,按回车搜索" ng-model="ffmc">
					</div>
					<div class="col-md-4">
						<label class="col-md-5">方法标准名称</label>
						<input type="text" class="searchFilter" placeholder="输入方法标准名称,按回车搜索" ng-model="ffbzmc">
					</div>
					<div class="col-md-4">
						<label class="col-md-5">方法标准编号</label>
						<input type="text" class="searchFilter" placeholder="输入方法标准编号,按回车搜索" ng-model="ffbzbh">
					</div>
				</div>
				<div class="form-group">
					<div class="col-md-4">
						<label class="col-md-4">监测项目</label>
						<input type="text" class="searchFilter" placeholder="输入监测项目,按回车搜索" ng-model="jcxmmc">
					</div>
					<div class="col-md-4">
						<label class="col-md-5">方法标准分类</label>
						<ui-select ng-model="ffbzfl" style="width:200px;float:left;padding-left:25px" on-select="changeSelectedYear($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (years | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
						</ui-select>
					</div>
				</div>
			</div>
			<div class="col-md-12" style="padding: 0px;margin-top: 10px;">
				<div data-id="SelfMonitoringMethodGrid" data-classname="SelfMonitoringMethodGrid color-ext grid-ext" ng-transclude grid-directive></div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/selfmonitoringmethod/selfmonitoringmethod.js?v=${sysversion}"></script>
<%@ include file="/platform/common/footer.jsp"%>