<%@ include file="/platform/common/head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='标准基本信息';id='${id }'">
<div class="container-fluid" ng-controller="standarDetailController">
	<input id="jbxxid" value="${id}" type='hidden'>
	<div class="row">
		<div class="edit-header row">
			<div class="col-xs-4 edit-title">
				<i class="icon"></i>
				<i>标准基本信息</i>
			</div>
		</div>
		<div class="edit-body" style="margin: 0">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">名称：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.BZMC" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly" /></div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">标准分类：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.BZFL" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly" /></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">类型：：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.BZLXMC" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly" /></div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">编号：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.BZBH" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly" /></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <label class="col-md-4 control-label oneline">应用范围：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.YYFW" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly" /></div>
                    </div>
                    <div class="col-xs-6">
                        <label class="col-md-4 control-label oneline">应用行业代码</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.YYHYDM" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly"/></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <label class="col-md-4 control-label oneline">实施时间：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.SSSJ" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly"/></div>
                    </div>
                    	<div class="col-xs-6">
                        <label class="col-md-4 control-label oneline">废止时间：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.FZSJ" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly"/></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-xs-6">
                        <label class="col-md-4 control-label">备注：</label>
                        <div class="col-md-8"><input id="XTZH" ng-model="jbxx.BZ" type="text" class="form-control easyui-validatebox"
                                                     readonly="readonly"/></div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-6"></label>
                        <div class="col-md-6">
                        	<button type="button" class="btn btn-default btn-sm" >标准条目</button>
                        </div>
                    </div>
                </div>
            </form>
        </div>
	</div>
</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/standardquery/standardetail.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/foot.jsp" %>