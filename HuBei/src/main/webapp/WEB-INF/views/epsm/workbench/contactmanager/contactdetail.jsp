<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='联系人详情';operationType='${operationType }';contact.ID='${ID}';">
<div class="container-fluid" ng-controller="contactDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>联系人详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>姓名：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.LXRXM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入姓名" data-options="validType:'length[1,20]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>单位名称：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.LXRMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入单位名称" data-options="validType:'length[1,50]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>                
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">座机：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.ZJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入座机" data-options="validType:'phone',tipPosition:'bottom'"/>
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">手机：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.SJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入手机" data-options="validType:'mobile',tipPosition:'bottom'"/>
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">传真：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.CZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入传真" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">电子邮箱：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="contact.DZYX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入电子邮箱" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>用户类型：</label>
                        <div class="col-md-8">
                        	<!-- <div ng-validate="false" pt-select style="padding-left: 15px" ng-disabled="isUserTypes" 
								ng-model="contact.YHLB" ng-model-select="userTypes"></div> -->
								<div ng-validate="true" pt-select ng-model-value=""  ng-disabled="isUserTypes" 
	                                ng-model="contact.YHLB" ng-datafield="userTypes"></div>
						</div>
                    </div>
                </div>
                <br/>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/workbench/contactmanager/contactdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>