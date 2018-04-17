<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='企业用户详情';operationType='${operationType }';companyUser.XTZH='${XTZH }';companyUser.YHJB='${SESSION_USER.yhjb}';companyUser.QYID='${SESSION_USER_EXTEND.qyid}';">
<div class="container-fluid" ng-controller="companyUserDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>企业用户详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-12">
                    	<label class="col-md-2 control-label oneline"><i class="pd-required">*</i>企业账号</label>
                        <div class="col-md-10">
                        	<input ng-readonly="true" ng-model="companyUser.XTZH" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>用户类别</label>
                        <div class="col-md-10">
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isEditUserId" ng-model="userType.isEditUser" ng-disabled="isRead">
	                            <label for="isEditUserId">填报用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isCheckUserId" ng-model="userType.isCheckUser" ng-disabled="isRead">
	                            <label for="isCheckUserId">审核用户</label>
	                        </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-12">
                        <label class="col-md-2 control-label oneline">用户密码</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="companyUser.YHMM" type="password" class="form-control easyui-validatebox"
                            	placeholder="请输入用户密码, 不填默认密码为8888" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/dataacquisition/companyuser/companyuserdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>