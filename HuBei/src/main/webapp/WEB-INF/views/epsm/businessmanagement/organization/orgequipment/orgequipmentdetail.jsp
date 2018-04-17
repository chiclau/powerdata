<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构设备详情';operationType='${operationType }';orgEquipment.JCJGSB_ID='${JCJGSB_ID }';">
<div class="container-fluid" ng-controller="orgEquipmentDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构设备详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>设备编号</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_SBBH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入设备编号" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>设备名称</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_SBMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入设备名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>监测设备型号</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_JCSBXH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入监测设备型号" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>生产厂家</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_SCCJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入生产厂家" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>固定资产编号</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_GDZCBH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入固定资产编号" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>设备类型</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="equipmentTypes.selected" on-select="changeSelectedEquipmentType($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (equipmentTypes | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>联系人</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_LXR" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系人" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>联系人电话</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgEquipment.JCJGSB_LXRDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系人电话" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/orgequipment/orgequipmentdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>