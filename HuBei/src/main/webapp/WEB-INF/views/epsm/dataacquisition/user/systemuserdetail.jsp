<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='环保部门用户详情';operationType='${operationType }';systemUser.XTZH='${XTZH }';currUserProvince='${SESSION_USER_EXTEND.xzqhdmsheng}';currUserCity='${SESSION_USER_EXTEND.xzqhdmshi}';currUserArea='${SESSION_USER_EXTEND.xzqhdmxian}';">
<div class="container-fluid" ng-controller="systemUserDetailController">
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
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>账号类型</label>
                        <div class="col-md-10">
                        	<span>{{systemUser.XTZH}}</span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isSystemUserId" ng-model="userType.isSystemUser" ng-disabled="true">
	                            <label for="isSystemUserId">管理用户</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="userType" id="isEPEditUserId" ng-model="userType.isEPEditUser" ng-disabled="isRead">
	                            <label for="isEPEditUserId">环保填报用户</label>
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
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>用户名称</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.YHMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入用户名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>所属行政区</label>
                        <div class="col-md-8">
                        	<region-select>
								<!--
									ng-disabled : 三种类型值，true false AutoToView .其中true与false可用变量在js中定义
								-->
								<div class="col-md-4" style="padding: 0px;" id="province">
									<province ng-disabled="isReadProvince" ng-model="systemUser.XZQHDMSHENG"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city">
									<city ng-disabled="isReadCity"  ng-model="systemUser.XZQHDMSHI"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area">
									<district ng-disabled="isReadArea" ng-model="systemUser.XZQHDMXIAN"></district>
								</div>
							</region-select>
						</div>
                    </div>
                </div>                
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">用户密码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.YHMM" type="password" class="form-control easyui-validatebox"
                            	placeholder="请输入用户密码, 不填默认密码为8888" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>综合机构名称</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.ZHJGMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入综合机构名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">环保联系人手机</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.YHSJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入环保联系人手机" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">环保联系人电话</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.BGDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入环保联系人电话" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">环保联系人传真</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.YHCZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入环保联系人传真" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">邮政编码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.YZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入邮政编码" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">电子邮箱</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.DZYX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入电子邮箱" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">详细地址</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="systemUser.BGDZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入详细地址" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <br/>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/dataacquisition/user/systemuserdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>