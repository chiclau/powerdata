<%@ include file="/platform/common/head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body jbxx.id='${id}'>
<div ng-controller="SelfMonitoringMethodDetailController" style="height: 100%;overflow: auto;overflow-x: hidden">
	<input id="jbxxid" value="${id}" type='hidden'>
	<div class="edit-body row" style="margin: 0">
        <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            <div class="form-group">
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">方法名称</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ffmc" type="text"/></div>
                </div>
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">方法标准名称</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ffbzmc" type="text"/></div>
                </div>
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">方法标准编号</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ffbzbh" type="text"/></div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">监测项目</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.jcxmmc" type="text"/></div>
                </div>
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">方法标准分类</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ffbzfl" type="text"/></div>
                </div>
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">方法标准代替</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ffbzdt" type="text"/></div>
                </div>
            </div>
            <div class="form-group">
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left">发布日期</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.fbrq" type="text"/></div>
                </div>
                <div class="col-md-3">
                        <label class="col-md-2 control-label oneline" style="float:left;width:110px">实施日期</label>
                        <div class="col-md-4" style="float:left;margin-left:-10px"><input ng-readonly="true" class="form-control" ng-model="jbxx.ssrq" type="text"/></div>
                </div>
            </div>
            
            <div class="col-xs-12" style="padding: 0;height:400px;" ng-controller="SelfMonitoringMethodGridDetailController">
				<div data-id="SelfMonitoringMethodGridDetail" data-classname="SelfMonitoringMethodGridDetail color-ext" grid-directive></div>
			</div>
        </form>
    </div>
</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/selfmonitoringmethod/selfmonitoringmethoddetail.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/foot.jsp" %>