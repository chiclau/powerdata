<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='报告模版详情';operationType='${operationType }';template.ID='${ID}';">
<div class="container-fluid" ng-controller="reportemplateDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>报告模版详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>模版名称：</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="template.M_NAME" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入姓名" data-options="validType:'length[1,20]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>创建时间：</label>
                        <div class="col-md-8">
	                        <div date-picker divid='createtime' ng-model="template.M_CREATE_TIME"  ng-config="createtime"
	                             validateclass="easyui-validatebox" ng-type="date"
	                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
	                             ng-write-able="true" placeholder="请选择创建时间" ng-format="yyyy-MM-dd"></div>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">模版类型：：</label>
                        <div class="col-md-8">
                        	<ui-select ng-disabled="isTemplateType" ng-model="templateType.selected">
							<ui-select-match>
								<span ng-disabled="isTemplateType" ng-bind="$select.selected.label"></span>
							</ui-select-match>
							<ui-select-choices repeat="item in (templateType | filter: $select.search) track by item.value">
								<span ng-disabled="isTemplateType" ng-bind="item.label"></span>
							</ui-select-choices>
							</ui-select>
						</div>
						
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>文件：</label>
                        <div class="col-md-8">
                        	<div class="container" ng-controller="UploadController">
						        <div upload-file-list ng-config="uploadConfig" ng-uploader="uploader"></div>
						    </div>
						</div>
                    </div>
                </div>            
                <br/>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/decisionsupport/reportmanager/reportemplatedetail.js?v=${sysversion}"></script>
<!-- 文件上传依赖 -->
<script type="text/javascript" src="<common:webRoot />/resources/platform/common/component/file/ng-slide-down.js?v=${sysversion}"></script>
<script type="text/javascript" src="<common:webRoot />/resources/platform/common/component/file/directive.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>