<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body ng-init="pageTitle='自行监测方法库';">
	<div class="container-full" ng-controller="CaseInformationListController">
		<div class="edit-header">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>案例信息库
			</div>
			<div class="col-xs-8 edit-btn-box" style="padding-right: 10px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="toSearch()">查&nbsp;询</button>
				<button type="button" class="btn btn-default btn-sm" ng-click="addOpenPage()">新&nbsp;增</button>
			</div>
		</div>
		<div id="tab-header" class="tab-div" style="height: 42px">
			<uib-tabset active="active">
			    <uib-tab ng-repeat="tab in tabs track by $index" heading={{tab.label}} index="$index"  disable="tab.disabled" select="setTab(tab.value)">
			    </uib-tab>
			</uib-tabset>
		</div>
		<div class="edit-body row" style="overflow: hidden">
			<div class="col-xs-12 grid-serachbar"
				style="padding-top:0; text-align: right;">
				<div class="form-group">
					<div class="searchContent">
						<div style="width: 50%; float: left">
							<label class="col-md-5">案例名称</label>
							<input type="text" style="float:left" class="searchFilter" placeholder="输入案例名称,按回车搜索"  ng-model="searchGridData.almc">
						</div>
						<div style="width: 50%; float: left">
							<label class="col-md-5">案例类别</label>
							<input type="text" style="float:left" class="searchFilter" placeholder="输入案例类别,按回车搜索" ng-model="searchGridData.allb">
						</div>
						<div style="width: 50%; float: left">
							<label class="col-md-5">事故名称</label>
							<input type="text" style="float:left" class="searchFilter" placeholder="输入事故名称,按回车搜索" ng-model="searchGridData.sgmc">
						</div>
						<div style="width: 50%; float: left">
							<label class="col-md-5">发生地点</label>
							<input type="text" style="float:left" class="searchFilter" placeholder="输入发生地点,按回车搜索" ng-model="searchGridData.fsdd">
						</div>
					</div>
				</div>
			</div>
			<div class="col-xs-12" style="padding: 0px;margin-top:0px;">
				<div data-id="CaseInformationGrid" data-classname="CaseInformationGrid color-ext grid-ext" grid-directive></div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/caseinformation/caseinformationlist.js?v=${sysversion}"></script>
<%@ include file="/platform/common/footer.jsp"%>