<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='本机构人员详情';operationType='${operationType }';orgUser.JCJGRY_ID='${JCJGRY_ID }';">
<div class="container-fluid" ng-controller="orgUserDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>本机构人员详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>姓名</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_NAME" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入姓名" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>性别</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="sexes.selected" on-select="changeSelectedSex($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (sexes | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>出生年月</label>
                        <div class="col-md-8">
                        	<div date-picker divid='JCJGRY_CSNY' ng-model="orgUser.JCJGRY_CSNY" style="width: 107%;margin-left: -3%;"
                             validateclass="easyui-validatebox" ng-type="date"
                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
                             ng-write-able="true" placeholder="请选择出生年月" ng-format="yyyy-MM-dd" ng-disabled="isRead"></div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>政治面貌</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="politicalVisages.selected" on-select="changeSelectedPoliticalVisage($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (politicalVisages | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>民族</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_MZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入民族" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>最高学历</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="educations.selected" on-select="changeSelectedEducation($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (educations | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>是否三五人才</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="personnels.selected" on-select="changeSelectedPersonnel($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (personnels | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>专业</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_ZY" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入专业" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>职称</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="titles.selected" on-select="changeSelectedTitle($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (titles | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>所在部门</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="orgDepts.selected" on-select="changeSelectedOrgDept($item, $model)" ng-disabled="isRead">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (orgDepts | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>从事技术领域年限</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_CSJSLYNX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入从事技术领域年限" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>职务</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_ZW" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入职务" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>从事技术领域</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_CSJSLY" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入从事技术领域" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>本岗位工作年限</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="orgUser.JCJGRY_BGWGZNX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入本岗位工作年限" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>                
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/businessmanagement/organization/orguser/orguserdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>