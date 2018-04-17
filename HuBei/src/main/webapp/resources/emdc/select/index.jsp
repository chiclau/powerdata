<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String ctx = request.getContextPath();
	if ("/".equals(ctx)) {
	    ctx = "";
	}
	session.setAttribute("ctx", ctx);
%>
<script type="text/javascript">
	var ctx='${ctx}';
<!--

//-->
</script>
<head>
<title>选择功能</title>
<link href="${ctx}/resources/emdc/select/css/jquery.mCustomScrollbar.css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/emdc/select/css/reset.css" />
<link rel="stylesheet" type="text/css" href="${ctx}/resources/emdc/select/css/style.css" />
</head>
<body style="background-color: #f1f4f8;">
	<div class="dlg-box">
		<div class="dlg-con">
			<div class="dlg-con-lf">
				<div class="panel-title">
					<span>模板列表</span>
				</div>
				<div class="modu-list scroll-content">
				    <ul id="templateLists">
				    
				    </ul>
				</div>
			</div>
			<div class="dlg-con-mid">
				<div class="panel-tit">
					<span class="mid-panel-tit">项目列表</span>
					<div class="srh-area">
						<div class="radio-box">

						</div>
						<div class="srh-wrap">
							<input type="text" id="search-input" onchange="dataSelect.searchChangeEvent()" class="srh" placeholder="请输入名称搜索" value="">
							<i class="srh_icon"></i>
						</div>
					</div>	
				</div>
				<div class="city-content  scroll-content">
					<div class="city city00">
						<div class="city-name"></div>
						<div class="cities">
						</div>
					</div>
				</div>
			</div>
			<div class="dlg-con-rt">
				<div class="panel-title">
					<span>已选项目(<b class="allSelectedDataLength"></b>)</span>
					<i id="clearAllData">全部清除</i>
				</div>
				<div class="addr-list-scroll-div scroll-content">
					<ul class="addr-list">
					</ul>
				</div>
				<div class="conf-wrap">
					<div class="conf-btn" onclick="dataSelect.confirmSelect()">确定</div>
					<div class="edit" style="display: none">
						<span class="editSpan">编辑模板</span>
					</div>

					<div class="save">
						<span class="saveSpan">另存为模板</span>
						<div class="saveBox" style="display: none;">
							<input class="modInp" type="text" placeholder="请输入项目名称" value="">
							<div class="radio-box">
								<span class="radioBox">
									<span class="radio-icon on"></span>
									<input type="radio" name="isPublic" checked="checked" value="public" style="display:none;" />
									<span>公有模板</span>
								</span>
								<span class="radioBox">
									<span class="radio-icon"></span>
									<input type="radio" name="isPublic" value="private" style="display:none;" />
									<span>私有模板</span>
								</span>
							</div>
							<div class="cancel">取消</div>
							<div class="confirm" onclick="dataSelect.confirmAddNewTemplate()">确定</div>
						</div>
					</div>
				</div>		
			</div>
		</div>
		<div class="modEdit" style="display: none;">
			<div class="modName">
				<label>模板名称</label>
				<input id="currentTemplateName" class="modInp" type="text" value="">
			</div>
			<div class="radio-box">
                <span class="radioBox">
                    <span class="radio-icon on"></span>
                    <input type="radio" name="isPublic" checked="checked" value="public" style="display:none;" />
                    <span>公有模板</span>
                </span>
                <span class="radioBox">
                    <span class="radio-icon"></span>
                    <input type="radio" name="isPublic" value="private" style="display:none;" />
                    <span>私有模板</span>
                </span>
            </div>
			<div class="modEditBtn">
				<span class="editBtn01" onclick="dataSelect.confirmEditTemplate()">确定</span>
				<span class="editBtn02">取消</span>
				<span class="editBtn03" onclick="dataSelect.deleteTemplate()">删除模板</span>
			</div>
		</div>
	</div>
	<script type="text/javascript" src="${ctx}/resources/emdc/select/script/jquery-1.11.1.min.js"></script>
	<script type="text/javascript" src="${ctx}/resources/emdc/select/script/layer/layer.js"></script>
	<script src="${ctx}/resources/emdc/select/script/jquery.mCustomScrollbar.concat.min.js"></script>
    <script type="text/javascript" src="${ctx}/resources/emdc/select/script/layerTool.js"></script>
	<script src="${ctx}/resources/emdc/select/script/script.js"></script>
    <script type="text/javascript">
    <!--
        
    //-->
    </script>
</body>
