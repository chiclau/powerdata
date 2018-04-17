<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<body ng-init="pageTitle='标准查询'">
<!-- 基于 bootstrap 布局 -->
    <div class="container-full" ng-controller="standardListController">
        <div class="edit-header row" style="overflow: hidden">
            <div class="col-xs-4 edit-title">
                <i class="icon"></i> <i>标准查询</i>
            </div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
                <button type="button" class="btn btn-default btn-sm" ng-click="search()">查&nbsp;询</button>
            </div>
        </div>
        
        <div class="edit-body row" style="overflow: hidden">
            <div class="col-md-12" style="padding: 0px; text-align: right;">
            
                <div class="form-group">
                    <div class="col-md-4">
                        <label class="col-md-4">标准名称：</label>
                        <div class="col-md-8">
                            <div class="searchContent">
                                <input class="searchInput" type="text" ng-model="standard.bzmc"
                                       placeholder="输入标准名称,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-md-4">标准编号：</label>
                        <div class="col-md-8">
                            <div class="searchContent">
                                <input class="searchInput" type="text" ng-model="standard.bzbh"
                                       placeholder="输入标准编号,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <label class="col-md-4">标准类型：</label>
                        <div class="col-md-8">
                            <ui-select ng-model="BZLX.selected" on-select="changeSelectedBZLX($item, $model)"> 
                                <ui-select-match>
                                    <span ng-bind="$select.selected.label"></span> 
                                </ui-select-match>
                                 <ui-select-choices repeat="item in (BZLX | filter: $select.search) track by item.value">
                                    <span ng-bind="item.label"></span> 
                                </ui-select-choices> 
                            </ui-select>
                        </div>
                    </div>
                </div>
                
                <div class="form-group">
                    <div class="col-md-4" style="float: none;">
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
                    <div class="col-md-4">
                        <label class="col-md-4">监测项目：</label>
                        <div class="col-md-8">
                            <div class="searchContent">
                                <input class="searchInput" type="text" ng-model="standard.jcxm"
                                       placeholder="输入监测项目,按回车键搜索" style="float: left;text-indent: 10px;width: 250px;"/>
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
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/standardquery/querystandard.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp"%>