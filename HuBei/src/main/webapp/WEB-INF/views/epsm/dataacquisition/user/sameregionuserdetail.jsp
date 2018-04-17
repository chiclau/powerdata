<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='环保部门用户详情';operationType='${operationType }';sameRegionUser.XTZH='${XTZH }';sameRegionUser.XZQHDMSHENG='${SESSION_USER_EXTEND.xzqhdmsheng}';sameRegionUser.XZQHDMSHI='${SESSION_USER_EXTEND.xzqhdmshi}';sameRegionUser.XZQHDMXIAN='${SESSION_USER_EXTEND.xzqhdmxian}';sameRegionUser.ZHJGMC='${SESSION_USER_EXTEND.zhjgmc}';sameRegionUser.YHJB='${SESSION_USER.yhjb}';">
<div class="container-fluid" ng-controller="sameRegionUserDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>环保部门用户详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-12">
                    	<label class="col-md-2 control-label oneline"><i class="pd-required">*</i>环保账号</label>
                        <div class="col-md-10">
                        	<input ng-readonly="true" ng-model="sameRegionUser.XTZH" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>用户类别</label>
                        <div class="col-md-10">
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isEPEditUserId" ng-model="userType.isEPEditUser" ng-disabled="isRead">
	                            <label for="isEPEditUserId">环保填报用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isMonitorEditUserId" ng-model="userType.isMonitorEditUser" ng-disabled="isRead">
	                            <label for="isMonitorEditUserId">监测站填报用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isPlanCheckUserId" ng-model="userType.isPlanCheckUser" ng-disabled="isRead">
	                            <label for="isPlanCheckUserId">方案审核用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isMonitorCheckUserId" ng-model="userType.isMonitorCheckUser" ng-disabled="isRead">
	                            <label for="isMonitorCheckUserId">监督性监测审核用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isReadUserId" ng-model="userType.isReadUser" ng-disabled="isRead">
	                            <label for="isReadUserId">查看用户</label>
	                        </span>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>环保联系人</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="sameRegionUser.YHMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入用户名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>                
                <div class="form-group">
                	<div class="col-md-12">
                        <label class="col-md-2 control-label oneline">用户密码</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="sameRegionUser.YHMM" type="password" class="form-control easyui-validatebox"
                            	placeholder="请输入用户密码, 不填默认密码为8888" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/dataacquisition/user/sameregionuserdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>