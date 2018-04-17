<%@ include file="/platform/common/header.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body ng-init="pageTitle='详细信息填报';accessType = '${accessType}'" >
<div class="container-fluid" ng-controller="dataInfoDetailController">
	<div class="row">
        <div class="edit-header">
            <div class="col-xs-4 edit-title" style="padding-left: 0">
            	<i class="icon"></i>
            	<i ng-show="accessType == 'basicinfo'">基本信息录入</i>
            	<i ng-show="accessType == 'datainfo'">详细信息填报 </i>
            </div>
            <div class="col-md-8 edit-btn-box" >
				<button type="button" class="btn btn-default btn-sm" ng-click="save()">保&nbsp;存</button>
			</div>
        </div>
        <div class="edit-body" style="margin-left: -100px">
            <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            	<div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>企业名称</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.QYMC" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入企业名称" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">曾用名</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.CYM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入曾用名" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">社会信用代码</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.SHFWM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入社会信用代码" data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">组织机构代码</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.ZZJGDM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入组织机构代码" data-options="validType:'length[1,500]',required:true,tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业登录号</label>
                        <div class="col-md-4">
                        	<input ng-readonly="true" ng-model="datainfo.YHID" type="text" class="form-control easyui-validatebox"
                            	 data-options="validType:'length[1,500]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业规模</label>
                        <div class="col-md-8">
	                         <ui-select ng-model="qygm.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (qygm | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-12">
                        <label class="col-md-2 control-label oneline">企业类别</label>
                        <div class="col-md-10">
	                        <span>
	                            <input class="magic-radio" type="radio" name="qylb" id="qylb_1" ng-model="datainfo.QYLB" value="A">
	                            <label for="qylb_1">工业企业</label>
	                        </span>
	                        <span>
	                            <input class="magic-radio" type="radio" name="qylb" id="qylb_2" ng-model="datainfo.QYLB" value="B">
	                            <label for="qylb_2">污水处理厂</label>
	                        </span>
	                        <span>
	                            <input class="magic-radio" type="radio" name="qylb" id="qylb_3" ng-model="datainfo.QYLB" value="C">
	                            <label for="qylb_3">固废处理厂</label>
	                        </span>
	                        <span>
	                            <input class="magic-radio" type="radio" name="qylb" id="qylb_4" ng-model="datainfo.QYLB" value="D">
	                            <label for="qylb_4">畜禽养殖场</label>
	                        </span>
                        </div>
                    </div>
                </div>
                <div class="form-group" ng-show = "datainfo.QYLB == 'B'">
                	<div class="col-md-12">
                		<label class="col-md-2 control-label oneline">污水处理厂类别</label>
						<div class="col-md-4">
							 <ui-select ng-model="wsclclb.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (wsclclb | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                	</div>
				</div>
				<div class="form-group" ng-show = "datainfo.QYLB == 'C'">
					<div class="col-md-12">
	                	<label class="col-md-2 control-label oneline">固废处置厂类别</label>
						<div class="col-md-6">
							 <span>
	                            <input class="magic-checkbox" type="checkbox" name="ljclc" id="ljclcId" ng-model="datainfo.LJCLC" >
	                            <label for="ljclcId">垃圾处理厂</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxfwclc" id="wxfwclcId" ng-model="datainfo.WXFWCLC" >
	                            <label for="wxfwclcId">危险废物处置厂</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="ylfwczc" id="ylfwczcId" ng-model="datainfo.YLFWCZC">
	                            <label for="ylfwczcId">医疗废物处置厂 </label>
	                        </span>
						</div>
	                </div>
				</div>
				<!-- 垃圾处理厂 -->
				<div class="form-group" ng-if = "datainfo.LJCLC">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">垃圾处置方式</label>
                        <div class="col-md-8">
	                         <ui-select ng-model="LJCZFS.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (LJCZFS | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                    </div>
                    <div class="col-md-6">
                    	<label class="col-md-2 control-label oneline">垃圾类型:</label>
						<div class="col-md-6">
							 <span>
	                            <input class="magic-checkbox" type="checkbox" name="shlj" id="shljId" ng-model="datainfo.SHLJ" >
	                            <label for="shljId">生活垃圾</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="gylj" id="gyljId" ng-model="datainfo.GYLJ" >
	                            <label for="gyljId">工业垃圾</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="jzlj" id="jzljId" ng-model="datainfo.JZLJ">
	                            <label for="jzljId">建筑垃圾   </label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="qtlj" id="qtljId" ng-model="datainfo.QTLJ">
	                            <label for="qtljId">其他 </label>
	                        </span>
						</div>
                    </div>
				</div>
				<!-- 危险废物处置厂 -->
				<div class="form-group" ng-if = "datainfo.WXFWCLC">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">危险废物处置方式</label>
                        <div class="col-md-8">
	                         <ui-select ng-model="WXFWCZFS.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (WXFWCZFS | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                    </div>
                    <div class="col-md-6">
                    	<label class="col-md-2 control-label oneline">危险废物类型:</label>
						<div class="col-md-6">
							 <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxyl" id="wxylId" ng-model="datainfo.WXYL" >
	                            <label for="wxylId">易燃</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxyb" id="wxybId" ng-model="datainfo.WXYB" >
	                            <label for="wxybId">易爆</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxts" id="wxtsId" ng-model="datainfo.WXTS">
	                            <label for="wxtsId">毒素 </label>
	                        </span>
	                         <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxfs" id="wxfsId" ng-model="datainfo.WXFS">
	                            <label for="wxfsId">辐射 </label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="wxqt" id="wxqtId" ng-model="datainfo.WXQT">
	                            <label for="wxqtId">其他 </label>
	                        </span>
						</div>
                    </div>
				</div>
				<!-- 医疗废物处置厂 -->
				<div class="form-group" ng-if = "datainfo.YLFWCZC">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">医疗废物处置方式</label>
                        <div class="col-md-8">
	                         <ui-select ng-model="YLFWCZFS.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (YLFWCZFS | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                    </div>
                    <div class="col-md-6">
                    	<label class="col-md-2 control-label oneline">医疗废物类型: </label>
						<div class="col-md-6">
							 <span>
	                            <input class="magic-checkbox" type="checkbox" name="qxfp" id="qxfpId" ng-model="datainfo.QXFP" >
	                            <label for="qxfpId">器械废品 </label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="ylhc" id="ylhcId" ng-model="datainfo.YLHC">
	                            <label for="ylhcId">医疗耗材</label>
	                        </span>
	                        <span>
	                            <input class="magic-checkbox" type="checkbox" name="qtfp" id="qtfpId" ng-model="datainfo.QTFP">
	                            <label for="qtfpId">其他 </label>
	                        </span>
						</div>
                    </div>
				</div>
                 <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">注册类型</label>
                        <div class="col-md-8">
                        	 <ui-select ng-model="qyzclx.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (qyzclx | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select> 
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline"><i class="pd-required">*</i>行业类别</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="hylb.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (hylb | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-12">
                        <label class="col-md-2 control-label oneline">企业注册地址</label>
                        <div class="col-md-3">
                        	<region-select>
								<div class="col-md-4" style="padding: 0px;" id="province">
									<province  ng-model="datainfo.QYZCDZSHENG"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city">
									<city   ng-model="datainfo.QYZCDZSHI"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area">
									<district  ng-model="datainfo.QYZCDZXIAN"></district>
								</div>
							</region-select>
						</div>
						<div class="col-md-2">
                        	<input  ng-model="datainfo.QYZCDZXZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入乡（镇)" data-options="tipPosition:'bottom'" />
                        </div>
						<label class="col-md-1 control-label oneline">乡（镇)</label>
						<div class="col-md-2">
                        	<input  ng-model="datainfo.QYZCDZ" type="text" class="form-control easyui-validatebox"
                                placeholder="请输入详细地址" data-options="tipPosition:'bottom'" />
                        </div>
                        <label class="col-md-1 control-label oneline">邮编</label>
						<div class="col-md-1">
                        	<input  ng-model="datainfo.QYZCDZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入邮编" data-options="tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-12">
                        <label class="col-md-2 control-label oneline">企业生产地址</label>
                        <div class="col-md-3">
                        	<region-select>
								<div class="col-md-4" style="padding: 0px;" id="province2">
									<province  ng-model="datainfo.XZQHDMSHENG"></province>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="city2">
									<city   ng-model="datainfo.XZQHDMSHI"></city>
								</div>
								<div class="col-md-4" style="padding: 0px;" id="area2">
									<district  ng-model="datainfo.XZQHDMXIAN"></district>
								</div>
							</region-select>
						</div>
						<div class="col-md-2">
                        	<input  ng-model="datainfo.QYXXDZXZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入乡（镇)" data-options="tipPosition:'bottom'" />
                        </div>
						<label class="col-md-1 control-label oneline">乡（镇)</label>
						<div class="col-md-2">
                        	<input  ng-model="datainfo.QYXXDZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入详细地址" data-options="tipPosition:'bottom'" />
                        </div>
                        <label class="col-md-1 control-label oneline">邮编</label>
						<div class="col-md-1">
                        	<input  ng-model="datainfo.QYYZBM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入邮编" data-options="tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-12">
                        <label class="col-md-2 control-label oneline">企业地理位置  </label>
                        <label class="col-md-1 control-label oneline">中心经度:</label>
						<div class="col-md-1">
                        	<input  ng-model="datainfo.QYZXJDDU" type="text" class="form-control easyui-validatebox"
                            	 data-options="tipPosition:'bottom'" />
                        </div>
						<div class="col-md-1">
                        	<input  ng-model="datainfo.QYZXJDFEN" type="text" class="form-control easyui-validatebox"
                            	 data-options="tipPosition:'bottom'" />
                        </div>
						<div class="col-md-1">
                        	<input ng-model="datainfo.QYZXJDMIAO" type="text" class="form-control easyui-validatebox"
                            	 data-options="tipPosition:'bottom'" />
                        </div>
                        <label class="col-md-1 control-label oneline">中心纬度:</label>
                        <div class="col-md-1">
                        	<input ng-model="datainfo.QYZXWDDU" type="text" class="form-control easyui-validatebox"
                            	 data-options="tipPosition:'bottom'" />
                        </div>
						<div class="col-md-1">
                        	<input ng-model="datainfo.QYZXWDFEN" type="text" class="form-control easyui-validatebox"
                            	 data-options="tipPosition:'bottom'" />
                        </div>
						<div class="col-md-1">
                        	<input  ng-model="datainfo.QYZXWDMIAO" type="text" class="form-control easyui-validatebox"
                            	data-options="tipPosition:'bottom'" />
                        </div>
                    </div>
                </div>             
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">环保联系人</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.HBLXRXM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入环保联系人"  data-options="tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                    	<label class="col-md-4 control-label oneline">联系电话</label>
                        <div class="col-md-3">
                        	<input ng-model="datainfo.HBLXRDH" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入联系电话" data-options="tipPosition:'bottom'" />
						</div>
                        <label class="col-md-2 control-label oneline">传真</label>
                        <div class="col-md-3">
                        	<input  ng-model="datainfo.HBLXRCZ" type="text" class="form-control easyui-validatebox"
                            	 placeholder="请输入传真" data-options="tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">联系人手机</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.HBLXRSJH" type="text" class="form-control easyui-validatebox"
                             placeholder="请输入联系人手机" data-options="tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">电子邮箱</label>
                        <div class="col-md-8">
                        	<input ng-model="datainfo.HBLXRDZYX" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入电子邮箱"  data-options="tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                	<div class="col-md-6">
                        <label class="col-md-4 control-label oneline">单位平面图</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.DWPMSYTCFWZ" type="text" class="form-control easyui-validatebox"
                            	 data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业网址</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.QYWZ" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入企业网址" data-options="tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">法定代表人</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.FRDBXM" type="text" class="form-control easyui-validatebox"
                            	placeholder="请输入法定代表人" data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">企业类型</label>
                        <div class="col-md-8">
	                        <ui-select ng-model="qylx.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (qylx | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>
                    </div>
                </div>
                <div class="form-group" ng-show = "accessType == 'basicinfo'">
                	<div class="col-md-6">
                		<label class="col-md-4 control-label oneline">所属集团</label>
	                     <div class="col-md-8">
	                         <ui-select ng-model="zdysx.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (zdysx | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>
                	</div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">自定义属性</label>
                        <div class="col-md-8">
                        	<ui-select ng-model="zdysx.selected">
								<ui-select-match>
									<span ng-bind="$select.selected.label"></span>
								</ui-select-match>
								<ui-select-choices repeat="item in (zdysx | filter: $select.search) track by item.value">
									<span ng-bind="item.label"></span>
								</ui-select-choices>
							</ui-select>
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">建成投产年月</label>
						<div class="col-md-8">
                            <div date-picker divid='JCTCNY' ng-model="datainfo.JCTCNY" ng-type="month"
		                     class="col-xs-6" ng-config="JCTCNY"
		                     validateoptions="validType:'date[\'yyyy-MM\']',required:true,tipPosition:'bottom'"
		                     placeholder="请选择日期,如:2016-09" ng-format="yyyy-MM"></div>
						</div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">排污许可证</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.PWXKZBH" type="text" class="form-control easyui-validatebox"
                                   data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">排污许可证发证日期</label>
                        <div class="col-md-8">
                            <div date-picker divid='PWXKZFZRQ' ng-model="datainfo.PWXKZFZRQ" ng-type="date"
		                     class="col-xs-6" ng-config="PWXKZFZRQ"
		                     validateoptions="validType:'date[\'yyyy-MM-dd\']',required:true,tipPosition:'bottom'"
		                     placeholder="请选择日期,如:2016-09-03" ng-format="yyyy-MM-dd"></div>
						</div>
                    </div>
                </div>
                <div class="form-group" ng-show = "accessType == 'basicinfo'">
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">检测机构</label>
                        <div class="col-md-8">
                        	<input  ng-model="datainfo.JCJG" type="text" class="form-control easyui-validatebox"
                                   data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                    <div class="col-md-6">
                        <label class="col-md-4 control-label oneline">运营机构</label>
                        <div class="col-md-8">
                            <input  ng-model="datainfo.YYJG" type="text" class="form-control easyui-validatebox"
                                   data-options="validType:'length[1,25]',tipPosition:'bottom'" />
						</div>
                    </div>
                </div>
                <br/>
            </form>
        </div>
    </div>
</div>
	
<script type="text/javascript" src="<common:webRoot />/resources/epsm/workbench/datamanagement/datainfodetail.js?v=${sysversion}"></script>
</body>
<%@include file="/platform/common/footer.jsp" %>