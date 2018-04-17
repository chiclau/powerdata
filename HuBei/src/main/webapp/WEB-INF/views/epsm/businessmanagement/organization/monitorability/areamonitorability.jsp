<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<body ng-init="pageTitle='辖区监测能力查询';currUserProvince='${currUserProvince}';currUserCity='${currUserCity}';currUserArea='${currUserArea}';">
	<div class="container-full" ng-controller="areaMonitorAbilityListController">
		<div class="edit-header">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>辖区监测能力查询
			</div>
			<div class="col-xs-8 edit-btn-box" style="padding-right: 10px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="toSearch()">查&nbsp;询</button>
			</div>
		</div>
		<div class="edit-body row" style="overflow: hidden">
			<div class="form-group">
		        <div class="col-md-12">
		            <label class="col-md-1 control-label oneline">分析维度:</label>
		            <div class="col-md-10">
		             <span>
		                 <input class="magic-checkbox" type="checkbox" name="analysisType" id="isDq" ng-model="analysisType.isDq" >
		                 <label for="isDq">地区</label>
		             </span>
		             <span>
		                 <input class="magic-checkbox" type="checkbox" name="analysisType" id="isJg" ng-model="analysisType.isJg" >
		                 <label for="isJg">机构</label>
		             </span>
		             <span>
		                 <input class="magic-checkbox" type="checkbox" name="analysisType" id="isJcxm" ng-model="analysisType.isJcxm" >
		                 <label for="isJcxm">监测项目</label>
		             </span>
		             <span>
		                 <input class="magic-checkbox" type="checkbox" name="analysisType" id="isJcff" ng-model="analysisType.isJcff">
		                 <label for="isJcff">监测方法</label>
		             </span>
		            </div>
		        </div>		
			</div>
			<div class="form-group">
				<div id="tab-header" class="tab-div" style="height: 42px">
					<uib-tabset active="active">
					    <uib-tab ng-repeat="tab in tabs track by $index" heading={{tab.label}} index="$index"  disable="tab.disabled" select="setTab(tab.value)">
					    </uib-tab>
					</uib-tabset>
				</div>		
			</div>
					
	        <div class="col-md-12" style="padding: 0px; text-align: right;margin-top: 10px">
	        
	        	<div class="form-group">
	        	
					<div class="col-md-4">
						<label class="col-md-5">监测方法:</label>
						<div class="col-md-7">
							<ui-select ng-model="jcffs.selected" on-select="changeSelectedJcff($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (jcffs | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>	
					</div>		        	
	        	
	                <div class="col-md-5">
	                    <label class="col-md-3">行政区划：</label>
	                    <div class="col-md-9">
	                        <div class="searchContent">
	                        	<region-select>
									<!--
										ng-disabled : 三种类型值，true false AutoToView .其中true与false可用变量在js中定义
									-->
									<div class="col-md-4" style="padding: 0px;" id="province">
										<province ng-disabled="isReadProvince" ng-model="systemUser.SHENG"></province>
									</div>
									<div class="col-md-4" style="padding: 0px;" id="city">
										<city ng-disabled="isReadCity"  ng-model="systemUser.SHI"></city>
									</div>
									<div class="col-md-4" style="padding: 0px;" id="area">
										<district ng-disabled="isReadArea" ng-model="systemUser.XIAN"></district>
									</div>
								</region-select>
	                        </div>
	                    </div>
	                </div>
	                	                
		            <div class="col-md-3">
						<label class="col-md-5">机构:</label>
						<div class="col-md-7">
							<ui-select ng-model="jgs.selected" on-select="changeSelectedJg($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (jgs | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>	
					</div>	
	            </div>
	        </div>		
	        
	     
			<div class="col-xs-12" style="padding: 0;margin-top: 10px">
				<div data-id="areaMonitorAbilityGrid" data-classname="areaMonitorAbilityGrid color-ext" ng-transclude grid-directive></div>
			</div>
		</div>
	</div>
</body>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/businessmanagement/organization/monitorability/areamonitorability.js?v=${sysversion}"></script>
<%@ include file="/platform/common/footer.jsp"%>