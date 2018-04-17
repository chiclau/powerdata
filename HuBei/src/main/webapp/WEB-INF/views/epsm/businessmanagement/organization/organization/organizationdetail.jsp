<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构信息维护';currentUserAccount='${SESSION_USER.xtzh}'">
<div class="container-fluid" ng-controller="OrganizationController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构信息维护</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>用户登录名</label>
                        <div class="col-md-10">
                        	<input ng-readonly="true" ng-model="currentUserAccount" type="text" class="form-control"/>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>机构名称</label>
                        <div class="col-md-10">
                        	<input ng-model="organization.JCJG_JGMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入机构名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>                
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">机构地址</label>
                        <div class="col-md-10">
                        	<input ng-model="organization.JCJG_JGSZD" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入机构地址" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">联系人</label>
                        <div class="col-md-10">
                        	<input ng-model="organization.JCJG_LXR" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系人" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">联系人电话</label>
                        <div class="col-md-10">
                        	<input ng-model="organization.JCJG_LXRDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系人电话" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">机构简介</label>
                        <div class="col-md-10">
                        	<textarea elastic style="max-height: 150px;min-height: 100px;resize: none"
									  ng-model="organization.JCJG_JGJJ" name="remarks" class="form-control easyui-validatebox autoHeight"
									  placeholder="请输入机构简介"
									  data-options="validType:'length[1,250]',tipPosition:'bottom'">
							</textarea>
						</div>
                    </div>
                </div>
                <br/>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/organization/organizationdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>