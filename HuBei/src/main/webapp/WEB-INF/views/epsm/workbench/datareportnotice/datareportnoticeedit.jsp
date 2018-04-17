<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="dataReportNotice = {ids:'${ids }',qyids:'${qyids }',openType:'${openType}',UUID:'${UUID}',QYID:'${QYID}',CBNR:'${CBNR}',CBZT:'${CBZT}',BCBDX:'${BCBDX}'};" >
	<div class="container-full" ng-controller="dataReportNoticeController">
		<div class="row">
			<div class="col-md-12 leftContain" >
				<div class="edit-header row">
					<div class="col-xs-4 edit-title">
						<i class="iocn"></i>
						<i>催报信息</i>
					</div>
					<div class="col-xs-8 edit-btn-box" style="padding-right: 24px;">
						<button ng-show="isEditShow" type="button" class="btn btn-default btn-sm" ng-click="saveDataReportNotice()">保&nbsp;存</button>					
					</div>
				</div>
				<div class="edit-body">
					<div style="height:20%"></div>
					<form id="myVaildate" class="form-horizontal" name="validateForm">
						<div class="col-md-14">
							<div class="form-group">
								<div class="col-md-12">
									<label class="col-sm-4 control-label"><i class='pd-required'>*</i>催报主题</label>
									<div class="col-sm-6">
										<input ng-model="dataReportNotice.CBZT" type="text" class="form-control easyui-validatebox"
											 	data-options="required:true,validType:'length[1,100]',required:true,tipPosition:'bottom'" />
								 	</div>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-12">
									<label class="col-sm-4 control-label"><i class='pd-required'>*</i>催报内容</label>
									<div class="col-sm-6">
										<textarea rows="3" cols="5" ng-model="dataReportNotice.CBNR" type="text" class="form-control easyui-validatebox"
											 	data-options="required:true,validType:'length[1,200]',tipPosition:'bottom'" ></textarea>
										<!-- <input ng-model="dataReportNotice.CBNR" type="text" class="form-control easyui-validatebox"
											 	data-options="required:true,validType:'length[1,200]',tipPosition:'bottom'" /> -->
								 	</div>
								</div>
							</div>
						</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/workbench/datareportnotice/datareportnoticeedit.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>
