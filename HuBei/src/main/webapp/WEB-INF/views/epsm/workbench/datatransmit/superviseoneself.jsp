<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body ng-init="pageTitle='企业自行监测信息';">
	<div class="container-full" ng-controller="SuperviseOnselfController">
		<div id="user-direct-config" class="user-direct-config">
			<div class="col-xs-12 grid-serachbar"
				style="padding: 0px; text-align: right;">
				<div class="col-xs-10">
					<div class="searchContent">
						<div style="width: 30%; float: left">
							<label class="col-md-6" style="text-align: left;">所属行政区划</label>
						</div>
						<div style="width: 70%; float: left">
							<label class="col-md-3" style="text-align: right">所属行业</label>
			                <div class="col-xs-9">
			                    <div pt-select  style="padding-left: 0px;float:left" ng-disabled="AutoViewPage"  ng-model="types.selected" ng-model-select="types"></div>
			                </div>
						</div>
					</div>
				</div>
				<div class="col-xs-2" style="height: 35px; line-height: 35px">
					<button type="button" class="btn btn-default btn-sm"
						ng-click="toSearch()">查&nbsp;询</button>
				</div>
			</div>
		</div>
		<div class="col-xs-12" style="padding: 0;height:650px;" ng-controller="SuperviseOnselfGridController">
			<!-- <div data-id="SuperviseOnselfGrid" data-classname="userGrid color-ext" grid-directive></div> -->
			<div data-id="SuperviseOnselfGrid" data-classname="customHeadGrid" ng-transclude grid-directive
                 options='{showButtomTips : "false" , showEditTips : "false",isCustomHeader : "true",isMergeHeader: "false"}'></div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/workbench/datatransmit/superviseonself.js?v=${sysversion}"></script>
<%@ include file="/platform/common/footer.jsp"%>