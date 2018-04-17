<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='已发催报'" style="font-size:1rem">
<!-- 基于 bootstrap 布局 -->
	<div class="container-full" ng-controller="dataReportNoticeListController">
		<div class="edit-header row" style="overflow: hidden">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>已发催报
			</div>
			<div class="col-xs-8 edit-btn-box" style="padding-right: 24px;">
				<button type="button" class="btn btn-default btn-sm"
					ng-click="toSearchData()">查&nbsp;询</button>
			</div>
		</div>
		<div class="edit-body row" style="overflow: hidden">
			<div class="form-group">
				<div class="col-md-4">
					<label class="col-md-4">催报标题</label>
					<div class="col-md-8">
						<div class="searchContent">
							<input class="searchInput" type="text" ng-model="cbzt" ng-keyup="keyupSearch($event);" placeholder="输入催报标题,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<label class="col-md-4">催报内容</label>
					<div class="col-md-8">
						<div class="searchContent">
							<input class="searchInput" type="text" ng-model="cbnr" ng-keyup="keyupSearch($event);" placeholder="输入催报内容,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
						</div>
					</div>
				</div>	                    
			</div>		
			<div class="col-md-12" style="padding: 0;margin-top: 10px;">
               	<div data-id="CBGrid"  data-classname="CBGrid color-ext" ng-transclude grid-directive></div>
          	</div>
		</div>
	</div>
<script type="text/javascript" src="<common:webRoot />/resources/epsm/workbench/datareportnotice/seeddatareportnotice.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>