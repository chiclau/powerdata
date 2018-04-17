<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='监测点所属标准';currUserProvince='${SESSION_USER_EXTEND.xzqhdmsheng}';currUserCity='${SESSION_USER_EXTEND.xzqhdmshi}';currUserArea='${SESSION_USER_EXTEND.xzqhdmxian}';">
<!-- 基于 bootstrap 布局 -->
	<div class="container-full" ng-controller="pointStandardController">
		<div class="edit-header row" style="overflow: hidden">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i> <i>监测点所属标准</i>
			</div>
			<div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="search()">查&nbsp;询</button>
			</div>
		</div>
		
		<div class="edit-body row" style="overflow: hidden">
			<div class="col-md-12" style="padding: 0px; text-align: right;">
	        
	        	<div class="form-group">
	                <div class="col-md-6">
	                    <label class="col-md-4">标准名称：</label>
	                    <div class="col-md-8">
	                        <div class="searchContent">
	                            <input class="searchInput" type="text" ng-model="piont.bzmc"
	                                   placeholder="输入标准名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <label class="col-md-4">标准分类：</label>
	                    <div class="col-md-8">
							<ui-select ng-model="BZFL.selected" on-select="changeSelectedBZFL($item, $model)">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (BZFL | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="form-group">
	                <div class="col-md-12">
	                    <label class="col-md-2">所在地区：</label>
	                    <div class="col-md-6">
	                    	<div class="searchContent">
                        	<region-select>
								<!--
									ng-disabled : 三种类型值，true false AutoToView .其中true与false可用变量在js中定义
								-->
								<div class="col-md-4" style="padding: 0px;" id="province">
									<province ng-disabled="AutoViewPage" ng-model="piont.sheng"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city">
									<city ng-disabled="AutoViewPage"  ng-model="piont.shi"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area">
									<district ng-disabled="AutoViewPage" ng-model="piont.xian"></district>
								</div>
							</region-select>
                            <!-- <input class="searchInput" type="text" ng-model="message.xxbt"
                                   placeholder="按消息标题查找,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
                            <i class="glyphicon glyphicon-search platform-search-icon"></i> -->
                        	</div>
	                    </div>
		             </div>
	            </div>
	        </div>
	        
	        <div class="col-md-12" style="padding: 0px;margin-top: 10px;">
            	<div data-id="baseGrid" data-classname="baseGrid color-ext" ng-transclude grid-directive></div>
        	</div>
		</div>
	</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/emissionstandard/standardmanagement/pointstandard.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>