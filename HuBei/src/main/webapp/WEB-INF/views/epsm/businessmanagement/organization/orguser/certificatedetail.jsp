<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构人员证书详情';operationType='${operationType }';certificate.JCJGRY_RYBH='${JCJGRY_RYBH }';certificate.JCJGRY_ZSBH='${JCJGRY_ZSBH }';">
<div class="container-fluid" ng-controller="certificateDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构人员证书详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>证书名称</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="certificate.JCJGRY_ZSMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入证书名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>证书类型</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="certificateTypes.selected" on-select="changeSelectedCertificateType($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (certificateTypes | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>发证时间</label>
                        <div class="col-md-8">
                        	<div date-picker divid='JCJGRY_ZSFZSJ' ng-model="certificate.JCJGRY_ZSFZSJ" style="width: 107%;margin-left: -3%;"
                             validateclass="easyui-validatebox" ng-type="date"
                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
                             ng-write-able="true" placeholder="请选择发证时间" ng-format="yyyy-MM-dd" ng-disabled="isRead"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>证书到期时间</label>
                        <div class="col-md-8">
                        	<div date-picker divid='JCJGRY_ZSDQSJ' ng-model="certificate.JCJGRY_ZSDQSJ" style="width: 107%;margin-left: -3%;"
                             validateclass="easyui-validatebox" ng-type="date"
                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
                             ng-write-able="true" placeholder="请选择证书到期时间" ng-format="yyyy-MM-dd" ng-disabled="isRead"></div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>证书对应监测项目</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="certificate.JCJGRY_ZSDYJCXM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入证书对应监测项目" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/orguser/certificatedetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>