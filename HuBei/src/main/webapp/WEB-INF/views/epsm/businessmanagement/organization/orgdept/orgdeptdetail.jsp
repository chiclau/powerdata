<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构部门详情';operationType='${operationType }';orgDept.JCJGBM_ID='${JCJGBM_ID }';">
<div class="container-fluid" ng-controller="orgDeptDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构部门详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline"><i class="pd-required">*</i>部门名称</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="orgDept.JCJGBM_BMMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入部门名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>                
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">部门联系人</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="orgDept.JCJGBM_BMLXR" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入部门联系人" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">部门联系人电话</label>
                        <div class="col-md-10">
                        	<input ng-readonly="isRead" ng-model="orgDept.JCJGBM_BMLXRDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入部门联系人电话" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">部门简介</label>
                        <div class="col-md-10">
                        	<textarea ng-readonly="isRead" elastic style="max-height: 150px;min-height: 100px;resize: none"
									  ng-model="orgDept.JCJGBM_BMJJ" name="remarks" class="form-control easyui-validatebox autoHeight"
									  placeholder="请输入部门简介"
									  data-options="validType:'length[1,250]',tipPosition:'bottom'">
							</textarea>
						</div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/orgdept/orgdeptdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>