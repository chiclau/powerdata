<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='未监测情况查询'">
<!-- 基于 bootstrap 布局 -->
	<div class="container-full" ng-controller="withoutMonitorListController">
		<div class="edit-header row" style="overflow: hidden">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i> <i>未监测情况查询</i>
			</div>
			<div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="search()">查&nbsp;询</button>
			</div>
		</div>
		
		<div class="edit-body row" style="overflow: hidden">
			<div class="col-md-12" style="padding: 0px; text-align: right;">
	        
	        	<div class="form-group">
	                <div class="col-md-6">
	                    <label class="col-md-4">企业名称：</label>
	                    <div class="col-md-8">
	                        <div class="searchContent">
	                            <input class="searchInput" type="text" ng-model="bjcjlb.qymc"
	                                   placeholder="输入企业名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="col-md-6">
	                    <label class="col-md-4">监测项目名称：</label>
	                    <div class="col-md-8">
	                        <div class="searchContent">
	                            <input class="searchInput" type="text" ng-model="bjcjlb.bjcmc"
	                                   placeholder="输入监测项目,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
	                        </div>
	                    </div>
	                </div>
	            </div>
	            
	            <div class="form-group">
	                <div class="col-md-12">
	                    <label class="col-md-2">不监测起止时间：</label>
	                    <div class="col-md-4">
	                        <div date-picker divid='KSSJ' ng-model="bjcjlb.bjckssj" style="width: 280px;margin-left: -18px;"  ng-config="startDateConfig"
	                             validateclass="easyui-validatebox" ng-type="date"
	                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
	                             ng-write-able="true" placeholder="请选择开始时间" ng-format="yyyy-MM-dd"></div>
	                    </div>
	                    <label class="col-md-2">
	                    	<span style="margin-right: 45px;">至</span>
	                    </label>
	                    <div class="col-md-4">
	                        <div date-picker divid='JSSJ' ng-model="bjcjlb.bjcjzsj" style="width: 280px;margin-left: -11px;"  ng-config="endDateConfig"
	                             validateclass="easyui-validatebox" ng-type="date"
	                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
	                             ng-write-able="true" placeholder="请选择结束时间" ng-format="yyyy-MM-dd"></div>
	                    </div>
		             </div>
	            </div>
	        </div>
	        
	        <div class="col-md-12" style="padding: 0px;margin-top: 10px;">
            	<div data-id="baseGrid" data-classname="baseGrid color-ext" ng-transclude grid-directive></div>
        	</div>
		</div>
	</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/emissionstandard/standardmanagement/querywithoutmonitor.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>