<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="standard = {openType:'${openType}',BZ_ID:'${BZ_ID}',BZMC:'${BZMC}',BZFL:'${BZFL}',BZLX:'${BZLX}',BZFL_NAME:'${BZFL_NAME}',BZLX_NAME:'${BZLX_NAME}',BZBH:'${BZBH}',YYFW:'${YYFW}',YYHYDM:'${YYHYDM}',YYHYDM_NAME:'${YYHYDM_NAME}',SSSJ:'${SSSJ}',FZSJ:'${FZSJ}',BZ:'${BZ}'};
bzfl_selected={label:'${BZFL_NAME}',value:'${BZFL}'};bzlx_selected={label:'${BZLX_NAME}',value:'${BZLX}'};yyhy_selected={label:'${YYHYDM_NAME}',value:'${YYHYDM}'};" >
	<div class="container-full" ng-controller="standardController">
		<div class="row">
			<div class="col-md-12 leftContain" >
				<div class="edit-header row">
					<div class="col-xs-4 edit-title">
						<i class="iocn"></i>
						<i>标准基本信息</i>
					</div>
					<div class="col-xs-8 edit-btn-box" style="padding-right: 24px;">
						<button ng-show="isAddShow" type="button" class="btn btn-default btn-sm" ng-click="saveStandard()">下&nbsp;一&nbsp;步</button>
						<button ng-show="isLookShow" type="button" class="btn btn-default btn-sm" ng-click="">标&nbsp;准&nbsp;条&nbsp;目</button>
						<button ng-show="isEditShow" type="button" class="btn btn-default btn-sm" ng-click="saveStandard()">提&nbsp;交</button>
						<button ng-show="isEditShow" type="button" class="btn btn-default btn-sm" ng-click="">标&nbsp;准&nbsp;条&nbsp;目&nbsp;信&nbsp;息</button>						
					</div>
				</div>
				<div class="edit-body row">
					<div style="height:20%"></div>
					<form id="myVaildate" class="form-horizontal" name="validateForm">
					<div class="col-md-12">
						<div class="form-group">
							<div class="col-md-6">
								<label class="col-sm-4 control-label"><i class='pd-required'>*</i>名称</label>
								<div class="col-sm-6">
									<input ng-model="standard.BZMC" type="text" class="form-control easyui-validatebox"
										 	data-options="required:true,validType:'length[1,50]',required:true,tipPosition:'bottom'" />
							 	</div>
							</div>
							<div class="col-md-6">
								<label class="col-sm-4 control-label">标准分类</label>
								<div class="col-sm-8">
									<div class="col-md-7">
											<ui-select ng-model="bzfl_selected" on-select="changeSelectedBzfl($item, $model)">
												<ui-select-match repeat="item in (BZFLS | filter: $standard.BZFL) track by item.value">
													<span ng-bind="$select.selected.label"></span>
												</ui-select-match>
												<ui-select-choices repeat="item in (BZFLS | filter: $select.search) track by item.value">
													<span ng-bind="item.label"></span>
												</ui-select-choices>
											</ui-select>
									</div>
							 	</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<label class="col-md-4">类型</label>
								<div class="col-sm-8">
									<div class="col-md-7">
											<ui-select ng-model="bzlx_selected" on-select="changeSelectedBzlx($item, $model)">
												<ui-select-match repeat="item in (BZLXS | filter: $standard.BZLX) track by item.value">
													<span ng-bind="$select.selected.label"></span>
												</ui-select-match>
												<ui-select-choices repeat="item in (BZLXS | filter: $select.search) track by item.value">
													<span ng-bind="item.label"></span>
												</ui-select-choices>
											</ui-select>
									</div>
							 	</div>
							</div>
							<div class="col-md-6">
								<label class="col-sm-4 control-label"><i class='pd-required'>*</i>编号</label>
								<div class="col-sm-6">
									<input ng-model="standard.BZBH" type="text" class="form-control easyui-validatebox"
										 	data-options="required:true,validType:'length[1,20]',tipPosition:'bottom'" />
							 	</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<label class="col-md-4">应用范围</label>
								<div class="col-sm-6">
									<input ng-model="standard.YYFW" type="text" class="form-control easyui-validatebox"
										 	data-options="validType:'length[1,100]',tipPosition:'bottom'" />
							 	</div>
							</div>
							<div class="col-md-6">
								<label class="col-sm-4 control-label">应用行业</label>
								<div class="col-sm-8">
									<div class="col-md-7">
											<ui-select ng-model="yyhy_selected" on-select="changeSelectedYyhy($item, $model)">
												<ui-select-match repeat="item in (YYHYS | filter: $standard.YYHYDM) track by item.value">
													<span ng-bind="$select.selected.label"></span>
												</ui-select-match>
												<ui-select-choices repeat="item in (YYHYS | filter: $select.search) track by item.value">
													<span ng-bind="item.label"></span>
												</ui-select-choices>
											</ui-select>
									</div>
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-6">
								<label class="col-md-4"><i class='pd-required'>*</i>实施时间</label>
								<div class="col-sm-5">
									<div date-picker divid='ssDate' ng-model="standard.SSSJ" data-width="col-xs-9" ng-type="date"
										ng-config="startDateConfig"
										validateoptions="validType:'date(\'yyyy-MM-dd\')',required:true,tipPosition:'bottom'" ng-write-able = "true"
										placeholder="" ng-format="yyyy-MM-dd">
									</div>
							 	</div>
							</div>
							<div class="col-md-6">
								<label class="col-sm-4 control-label"><i class='pd-required'>*</i>废止时间</label>
								<div class="col-sm-5">
									<div date-picker divid='fzDate' ng-model="standard.FZSJ" data-width="col-xs-9" ng-type="date"
										ng-config="startDateConfig"
										validateoptions="validType:'date(\'yyyy-MM-dd\')',required:true,tipPosition:'bottom'" ng-write-able = "true"
										placeholder="" ng-format="yyyy-MM-dd">
									</div>								
							 	</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-md-12">
								<label class="col-sm-2 control-label">备注</label>
								<div class="col-sm-8">
									<input type="text" ng-model="standard.BZ" class="form-control easyui-validatebox"
										   data-options="validType:'length[1,100]',tipPosition:'bottom'"
										   placeholder="">
							 	</div>
							</div>
							
						</div>

					</div>
					</form>
				</div>
			</div>
			
		</div>
	</div>
	<script type="text/javascript" src="<common:webRoot/>/resources/epsm/emissionstandard/levelstandardmanager/standardedit.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/footer.jsp" %>
