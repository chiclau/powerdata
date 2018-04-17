<%@ include file="/platform/common/header.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='委托机构详情';operationType='${operationType }';serviceOrganization.XTZH=serviceOrganization.USERDLMC='${XTZH }';currUserProvince='${SESSION_USER_EXTEND.xzqhdmsheng}';currUserCity='${SESSION_USER_EXTEND.xzqhdmshi}';currUserArea='${SESSION_USER_EXTEND.xzqhdmxian}';">
<div class="container-fluid" ng-controller="serviceOrganizationDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0"><i class="icon"></i>委托机构详情</div>
            <div class="col-md-8 edit-btn-box" style="padding-right: 30px;" ng-hide="isRead">
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
                <div class="form-group">
                    <div class="col-md-6">
                    	<label class="col-md-4 control-label oneline"><i class="pd-required">*</i>登录名</label>
                        <div class="col-md-8">
                        	<input ng-readonly="true" ng-model="serviceOrganization.XTZH" type="text" class="form-control"/>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">登录密码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.YHMM" type="password" class="form-control easyui-validatebox"
                            	placeholder="请输入登录密码, 不填默认密码为8888" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
	                	<label class="col-md-4 control-label oneline"><i class="pd-required">*</i>机构类型</label>
						<div class="col-md-8">
						  <span>
						      <input class="magic-checkbox" type="checkbox" name="organizationType" id="isDetectorId" ng-model="organizationType.isDetector" ng-disabled="isRead">
						      <label for="isDetectorId">检测机构</label>
						  </span>
						  <span>
						      <input class="magic-checkbox" type="checkbox" name="organizationType" id="isOperatorId" ng-model="organizationType.isOperator" ng-disabled="isRead">
						      <label for="isOperatorId">运营机构</label>
						  </span>
						  <span>
						      <input class="magic-checkbox" type="checkbox" name="organizationType" id="isSupplierId" ng-model="organizationType.isSupplier" ng-disabled="isRead">
						      <label for="isSupplierId">在线监测设备供应商</label>
						  </span>
						  <span>
						      <input class="magic-checkbox" type="checkbox" name="organizationType" id="isIntegratorId" ng-model="organizationType.isIntegrator" ng-disabled="isRead">
						      <label for="isIntegratorId">集成商</label>
						  </span>
						</div>
					</div>
					<div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>机构名称</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_NAME" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入机构名称" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group" ng-show="organizationType.isDetector">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>是否备案</label>
                        <div class="col-md-8">
                        	<span>
						    	<input class="magic-checkbox" type="checkbox" name="record" id="isRecordId" ng-model="record.isRecord" ng-disabled="isRead">
						      	<label for="isRecordId">是</label>
						  	</span>
                        </div>
                    </div>
                	<div class="col-md-6" ng-show = "record.isRecord">
                        <label class="col-md-4 control-label oneline">备案号</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JCJG_BAH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入备案号" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>企业法人代码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYFRDM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入企业法人代码" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
                        </div>
                    </div>
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业营业执照</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYYYZZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入企业营业执照" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
						</div>
                    </div>
                </div>                
                <div class="form-group">
                	<div class="col-md-6">
                       	<label class="col-md-4 control-label oneline"><i class="pd-required">*</i>公司成立时间</label>
                        <div class="col-md-8">
                        	<div date-picker divid='JBXX_GSCLSJ' ng-model="serviceOrganization.JBXX_GSCLSJ" style="width: 107%;margin-left: -3%;"
                             validateclass="easyui-validatebox" ng-type="date"
                             validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"
                             ng-write-able="true" placeholder="请选择公司成立时间" ng-format="yyyy-MM-dd" ng-disabled="isRead"></div>
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>注册资金（万元）</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_ZCZJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="注册资金（万元）" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">是否有上级公司</label>
                        <div class="col-md-8">
                        	<span>
						    	<input class="magic-checkbox" type="checkbox" name="superiorCompany" id="hasSuperiorCompanyId" ng-model="superiorCompany.hasSuperiorCompany" ng-disabled="isRead">
						      	<label for="hasSuperiorCompanyId">是</label>
						  	</span>
						</div>
                    </div>
                    <div class="col-md-6" ng-show="superiorCompany.hasSuperiorCompany">
                        <label class="col-md-4 control-label oneline">上级公司</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.SJGSMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入上级公司" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">法定代表人</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_FDDBR" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入法定代表人" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">技术人员数</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_JSRYS" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入技术人员数" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">公司所在地</label>
                        <div class="col-md-8">
                        	<region-select>
								<!--
									ng-disabled : 三种类型值，true false AutoToView .其中true与false可用变量在js中定义
								-->
								<div class="col-md-4" style="padding: 0px;" id="province">
									<province ng-disabled="isRead" ng-model="serviceOrganization.JBXX_SHENG" ng-getobject="region.provinceObj"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city">
									<city ng-disabled="isRead" ng-model="serviceOrganization.JBXX_SHI" ng-getobject="region.cityObj"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area">
									<district ng-disabled="isRead" ng-model="serviceOrganization.JBXX_XIAN" ng-getobject="region.districtObj"></district>
								</div>
							</region-select>
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">机构地址</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_JGDZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入机构地址" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">联系人</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_LXR" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系人" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">联系电话</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_LXDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系电话" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">传真号码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_CZHM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入传真号码" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">电子邮箱</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_DZYX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入电子邮件" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">办公电话</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_BGDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入办公电话" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">单位简介</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_DWJJ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入单位简介" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">社会服务码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_SHFWM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入社会服务码" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业邮政编码</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYYZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入企业邮政编码" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <!-- <div class="form-group" ng-show="organizationType.isDetector">
                	<div class="edit-header">
			            <div class="col-md-12 edit-title" style="padding-left: 7.1%;"><i class="icon"></i>检测机构检测范围及监测项目</div>
			        </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">水和废水</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_SHFWM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入水和废水的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">气和废气</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYYZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入气和废气的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group" ng-show="organizationType.isDetector">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">海水</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_SHFWM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入海水的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">生物</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYYZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入生物的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group" ng-show="organizationType.isOperator">
                	<div class="edit-header">
			            <div class="col-md-12 edit-title" style="padding-left: 7.1%;"><i class="icon"></i>运营机构检测范围及监测项目</div>
			        </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">水和废水</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_SHFWM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入水和废水的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">气和废气</label>
                        <div class="col-md-8">
                        	<input ng-readonly="isRead" ng-model="serviceOrganization.JBXX_QYYZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入气和废气的监测项目" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div> -->
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/dataacquisition/user/serviceorganizationdetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>