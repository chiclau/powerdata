<%@ include file="/platform/common/head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<body>
<div ng-controller="caseInformationAddController" style="height: 100%;overflow: auto;overflow-x: hidden">
	<input id="jbxxid" value="${zbalid}" type='hidden'/>
	<div class="edit-header">
		<div class="col-xs-4 edit-title">
			<i class="icon"></i>案例信息修改
		</div>
		<div class="col-xs-8 edit-btn-box" style="padding-right: 10px;">
			<button type="button" class="btn btn-default btn-sm" ng-click="toAdd()">提&nbsp;交</button>
		</div>
	</div>
	<div class="edit-body" style="margin: 0">
        <form id="myVaildate" class="form-horizontal class-for-readonly" name="validateForm">
            <div class="form-group">
            	 <div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">案例名称</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.almc"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">案例类别</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.allb"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">事故名称</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.sgmc"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">发生地点</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.fsdd"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">污染物</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.wrw"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">主要污染物</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.zywrw"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">影响范围</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.yxfw"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">企业名称</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.qymc"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">案例地点</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.aldd"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">监测过程描述</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.jcgcms"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-3" style="float:left;width:120px">事故发生时间</label></div>
	                <div class="col-md-3" style="float:left">
	                    <div date-picker="" divid="KSSJ" ng-model="searchGridData.sgfssj" style="width: 250px;margin-left: -18px;" ng-config="startDateConfig" validateclass="easyui-validatebox" ng-type="date" validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'" ng-write-able="true" placeholder="请选择开始时间" ng-format="yyyy-MM-dd" class="ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty"><div id="KSSJ" class="pt-date input-group date-select-for-disabled date date-picker-class"><input id="inputid_1506761290689" placeholder="请选择开始时间" class="form-control easyui-validatebox  easyui-validatebox ng-pristine ng-untouched ng-valid ng-empty validatebox-text" type="text" ng-model="_model" data-options="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"><span class="input-group-addon" ng-hide="_DateSelect._hideDateSelectIcon"><span class="glyphicon icon-datepicket glyphicon-calendar"></span></span><div class="datetimepicker datetimepicker-dropdown-bottom-left dropdown-menu" style="left: 105px; z-index: 1010;"><div class="datetimepicker-minutes" style="display: none;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">30 九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="minute">16:00</span><span class="minute">16:05</span><span class="minute">16:10</span><span class="minute">16:15</span><span class="minute">16:20</span><span class="minute">16:25</span><span class="minute">16:30</span><span class="minute">16:35</span><span class="minute">16:40</span><span class="minute active">16:45</span><span class="minute">16:50</span><span class="minute">16:55</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-hours" style="display: none;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">30 九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="hour">0:00</span><span class="hour">1:00</span><span class="hour">2:00</span><span class="hour">3:00</span><span class="hour">4:00</span><span class="hour">5:00</span><span class="hour">6:00</span><span class="hour">7:00</span><span class="hour">8:00</span><span class="hour">9:00</span><span class="hour">10:00</span><span class="hour">11:00</span><span class="hour">12:00</span><span class="hour">13:00</span><span class="hour">14:00</span><span class="hour">15:00</span><span class="hour active">16:00</span><span class="hour">17:00</span><span class="hour">18:00</span><span class="hour">19:00</span><span class="hour">20:00</span><span class="hour">21:00</span><span class="hour">22:00</span><span class="hour">23:00</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-days" style="display: block;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr><tr><th class="dow">一</th><th class="dow">二</th><th class="dow">三</th><th class="dow">四</th><th class="dow">五</th><th class="dow">六</th><th class="dow">日</th></tr></thead><tbody><tr><td class="day old">28</td><td class="day old">29</td><td class="day old">30</td><td class="day old">31</td><td class="day">1</td><td class="day">2</td><td class="day">3</td></tr><tr><td class="day">4</td><td class="day">5</td><td class="day">6</td><td class="day">7</td><td class="day">8</td><td class="day">9</td><td class="day">10</td></tr><tr><td class="day">11</td><td class="day">12</td><td class="day">13</td><td class="day">14</td><td class="day">15</td><td class="day">16</td><td class="day">17</td></tr><tr><td class="day">18</td><td class="day">19</td><td class="day">20</td><td class="day">21</td><td class="day">22</td><td class="day">23</td><td class="day">24</td></tr><tr><td class="day">25</td><td class="day">26</td><td class="day">27</td><td class="day">28</td><td class="day">29</td><td class="day today active">30</td><td class="day new">1</td></tr><tr><td class="day new">2</td><td class="day new">3</td><td class="day new">4</td><td class="day new">5</td><td class="day new">6</td><td class="day new">7</td><td class="day new">8</td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-months" style="display: none;"><table class="table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="month">一月</span><span class="month">二月</span><span class="month">三月</span><span class="month">四月</span><span class="month">五月</span><span class="month">六月</span><span class="month">七月</span><span class="month">八月</span><span class="month active">九月</span><span class="month">十月</span><span class="month">十一月</span><span class="month">十二月</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-years" style="display: none;"><table class="table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">2010-2019</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="year old">2009</span><span class="year">2010</span><span class="year">2011</span><span class="year">2012</span><span class="year">2013</span><span class="year">2014</span><span class="year">2015</span><span class="year">2016</span><span class="year active">2017</span><span class="year">2018</span><span class="year">2019</span><span class="year old">2020</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div></div></div></div>
	                </div>
                </div>
                <div style="width: 50%; float: left">
	                <div class="col-md-4"><label class="col-md-3" style="float:left;width:120px">案例上传时间</label></div>
	                <div class="col-md-3" style="float:left">
	                    <div date-picker="" divid="JSSJ" ng-model="searchGridData.alscsj" style="width: 250px;margin-left: -15px;" ng-config="endDateConfig" validateclass="easyui-validatebox" ng-type="date" validateoptions="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'" ng-write-able="true" placeholder="请选择结束时间" ng-format="yyyy-MM-dd" class="ng-pristine ng-untouched ng-valid ng-isolate-scope ng-empty"><div id="JSSJ" class="pt-date input-group date-select-for-disabled date date-picker-class"><input id="inputid_1506761290691" placeholder="请选择结束时间" class="form-control easyui-validatebox  easyui-validatebox ng-pristine ng-untouched ng-valid ng-empty validatebox-text" type="text" ng-model="_model" data-options="validType:'date[\'yyyy-MM-dd\']',required:false,tipPosition:'bottom'"><span class="input-group-addon" ng-hide="_DateSelect._hideDateSelectIcon"><span class="glyphicon icon-datepicket glyphicon-calendar"></span></span><div class="datetimepicker datetimepicker-dropdown-bottom-left dropdown-menu" style="left: 105px; z-index: 1020;"><div class="datetimepicker-minutes" style="display: none;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">30 九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="minute">16:00</span><span class="minute">16:05</span><span class="minute">16:10</span><span class="minute">16:15</span><span class="minute">16:20</span><span class="minute">16:25</span><span class="minute">16:30</span><span class="minute">16:35</span><span class="minute">16:40</span><span class="minute active">16:45</span><span class="minute">16:50</span><span class="minute">16:55</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-hours" style="display: none;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">30 九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="hour">0:00</span><span class="hour">1:00</span><span class="hour">2:00</span><span class="hour">3:00</span><span class="hour">4:00</span><span class="hour">5:00</span><span class="hour">6:00</span><span class="hour">7:00</span><span class="hour">8:00</span><span class="hour">9:00</span><span class="hour">10:00</span><span class="hour">11:00</span><span class="hour">12:00</span><span class="hour">13:00</span><span class="hour">14:00</span><span class="hour">15:00</span><span class="hour active">16:00</span><span class="hour">17:00</span><span class="hour">18:00</span><span class="hour">19:00</span><span class="hour">20:00</span><span class="hour">21:00</span><span class="hour">22:00</span><span class="hour">23:00</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-days" style="display: block;"><table class=" table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">九月 2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr><tr><th class="dow">一</th><th class="dow">二</th><th class="dow">三</th><th class="dow">四</th><th class="dow">五</th><th class="dow">六</th><th class="dow">日</th></tr></thead><tbody><tr><td class="day old">28</td><td class="day old">29</td><td class="day old">30</td><td class="day old">31</td><td class="day">1</td><td class="day">2</td><td class="day">3</td></tr><tr><td class="day">4</td><td class="day">5</td><td class="day">6</td><td class="day">7</td><td class="day">8</td><td class="day">9</td><td class="day">10</td></tr><tr><td class="day">11</td><td class="day">12</td><td class="day">13</td><td class="day">14</td><td class="day">15</td><td class="day">16</td><td class="day">17</td></tr><tr><td class="day">18</td><td class="day">19</td><td class="day">20</td><td class="day">21</td><td class="day">22</td><td class="day">23</td><td class="day">24</td></tr><tr><td class="day">25</td><td class="day">26</td><td class="day">27</td><td class="day">28</td><td class="day">29</td><td class="day today active">30</td><td class="day new">1</td></tr><tr><td class="day new">2</td><td class="day new">3</td><td class="day new">4</td><td class="day new">5</td><td class="day new">6</td><td class="day new">7</td><td class="day new">8</td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-months" style="display: none;"><table class="table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">2017</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="month">一月</span><span class="month">二月</span><span class="month">三月</span><span class="month">四月</span><span class="month">五月</span><span class="month">六月</span><span class="month">七月</span><span class="month">八月</span><span class="month active">九月</span><span class="month">十月</span><span class="month">十一月</span><span class="month">十二月</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div><div class="datetimepicker-years" style="display: none;"><table class="table-condensed"><thead><tr><th class="prev" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-left"></span> </th><th colspan="5" class="switch">2010-2019</th><th class="next" style="visibility: visible;"><span class="glyphicon glyphicon-arrow-right"></span> </th></tr></thead><tbody><tr><td colspan="7"><span class="year old">2009</span><span class="year">2010</span><span class="year">2011</span><span class="year">2012</span><span class="year">2013</span><span class="year">2014</span><span class="year">2015</span><span class="year">2016</span><span class="year active">2017</span><span class="year">2018</span><span class="year">2019</span><span class="year old">2020</span></td></tr></tbody><tfoot><tr><th colspan="4" class="today">今天</th><th colspan="3" class="clear">清空</th></tr></tfoot></table></div></div></div></div>
	                </div>
				</div>        
				
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">监测结果</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.jcjg"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">行业类别1</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.hylb1"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">行业类别2</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.hylb2"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">行业类别3</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.hylb3"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">行业类别4</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.hylb4"/>
				</div>
				<div style="width: 50%; float: left">
					<div class="col-md-4"><label class="col-md-4" style="float:left;width:120px">案例资料</label></div>
					<input type="text" style="float:left" class="searchFilter"  ng-model="searchGridData.alzl"/>
				</div>
            </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="<common:webRoot/>/resources/epsm/knowledge/caseinformation/caseinformationedit.js?v=${sysversion}"></script>
</body>
<%@ include file="/platform/common/foot.jsp" %>