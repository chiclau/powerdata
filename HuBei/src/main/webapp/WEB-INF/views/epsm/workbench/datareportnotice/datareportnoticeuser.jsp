<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='数据催报';queryType='${queryType}';" style="font-size:1rem">
<!-- 基于 bootstrap 布局 -->
	<div class="container-full" ng-controller="dataReportNoticeUserListController">
		<div class="edit-header row" style="overflow: hidden">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>数据催报
			</div>
			<div class="col-xs-8 edit-btn-box" style="padding-right: 24px;">					
				<button type="button" class="btn btn-default btn-sm"
					ng-click="toSearchData()">查&nbsp;询</button>
				<button type="button" class="btn btn-default btn-sm"
					ng-click="batchUrgeReport()">批&nbsp;量&nbsp;催&nbsp;报</button>
			</div>
		</div>
		<div class="edit-body row" style="overflow: hidden">		
			<div class="form-group">
				<div class="col-md-4" ng-show="isQYShow">
					<label class="col-md-4">企业名称</label>
					<div class="col-md-8">
						<div class="searchContent">
							<input class="searchInput" type="text" ng-model="searchTerm.qymc" ng-keyup="keyupSearch($event);" placeholder="输入企业名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
						</div>
					</div>
				</div>
				<div class="col-md-4" ng-show="isQYShow">
					<label class="col-md-4">联系人</label>
					<div class="col-md-8">
						<div class="searchContent">
							<input class="searchInput" type="text" ng-model="searchTerm.lxr" ng-keyup="keyupSearch($event);" placeholder="输入联系人,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
						</div>
					</div>
				</div>
					
				<div class="col-md-4" ng-show="isSHIShow">
					<label class="col-md-4 control-label oneline">市</label>
					<div class="col-md-8">
						<ui-select ng-model="SHIS.selected" on-select="changeSelectedXZQH($item, $model)">
							<ui-select-match>
								<span ng-bind="$select.selected.label"></span>
							</ui-select-match>
							<ui-select-choices repeat="item in (SHIS | filter: $select.search) track by item.value">
								<span ng-bind="item.label"></span>
							</ui-select-choices>
						</ui-select>
					</div>	
				</div>
				
				<div class="col-md-4" ng-show="isXIANShow">
					<label class="col-md-5 control-label oneline">区县</label>
					<div class="col-md-4">
						<ui-select ng-model="XIANS.selected" on-select="changeSelectedXZQH($item, $model)">
							<ui-select-match>
								<span ng-bind="$select.selected.label"></span>
							</ui-select-match>
							<ui-select-choices repeat="item in (XIANS | filter: $select.search) track by item.value">
								<span ng-bind="item.label"></span>
							</ui-select-choices>
						</ui-select>
					</div>				
				</div>	
				
				<div class="col-md-4" ng-show="isCOMShow">
					<label class="col-md-4">用户名称</label>
					<div class="col-md-8">
						<div class="searchContent">
							<input class="searchInput" type="text" ng-model="searchTerm.yhmc" ng-keyup="keyupSearch($event);" placeholder="输入用户名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
						</div>
					</div>
				</div>								                    
			</div>			
			<div class="col-md-12" style="padding: 0;margin-top: 10px;">
	        	<div data-id="CBGrid"  data-classname="CBGrid color-ext" ng-transclude grid-directive></div>
	        </div>
        </div>
	</div>
<script type="text/javascript" src="<common:webRoot />/resources/epsm/workbench/datareportnotice/datareportnoticeuser.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>