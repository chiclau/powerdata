<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构设备监测项目详情';operationType='${operationType }';project.JCJG_SBJCX_SBID='${JCJG_SBJCX_SBID }';project.JCJG_SBJCX_ID='${JCJG_SBJCX_ID }';">
<div class="container-fluid" ng-controller="projectDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构设备监测项目详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>监测产品类别</label>
                        <div class="col-md-10">
                        	<input ng-readonly="true" ng-model="project.JCJG_SBJCX_JCCPLBMC" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>监测项目</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="project.JCJG_SBJCX_JCXM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入监测项目" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>方法原理</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="project.JCJG_SBJCX_FFYL" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入方法原理" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>持证人员</label>
                        <div class="col-md-10">
                        	<span ng-repeat="orgUser in orgUsers">
	                            <input class="magic-checkbox" type="checkbox" name="orgUser" id="{{orgUser.JCJGRY_ID}}" 
	                            	ng-model="orgUser.check" ng-disabled="isRead" ng-change="checkOrgUser(orgUser)">
	                            <label for="{{orgUser.JCJGRY_ID}}">{{orgUser.JCJGRY_NAME}}</label>
	                        </span>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/orgequipment/projectdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>