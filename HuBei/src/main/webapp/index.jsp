<%@ include file="/platform/common/head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<title>湖北省土壤污染源数据库与联网平台建设项目</title>
<link rel="stylesheet" type="text/css"
	href="<common:webRoot />/resources/epsm/login/css/login.css">
</head>
<body style="overflow: hidden;">
	<div class="wrap" ng-controller="loginController">
		<img src="resources/epsm/login/images/login_bg.jpg" width="100%"
			height="100%" class="abs" style="z-index: -1;" />
		<div class="login-logo"></div>
		<form action="" id="login-form" style="width: 90%; margin: auto"
			autocomplete="off" autocorrect="off" autocapitalize="off"
			spellcheck="false">
			<div class="login-box">
				<div class="login-inner auto">
					<div class="log-box">
						<h1>
							登录系统
							<a href="">服务机构账号注册</a>
						</h1>
						<ul>
							<li><input type="text" id="XTZH" ng-model="user.XTZH"
								name="XTZH" class="login-text input-txt-user" placeholder="用户名" style="margin-bottom:10px;"/></li>
							<li style="margin-bottom: 10px;"><input type="password" id="YHPD" ng-model="user.YHPD"
								name="YHPD" class="login-text input-txt-pw" placeholder="密码" /></li>
							<li style="height: 15px;"><span class="err" id="showError" style="display: none;" >{{error}}</span></li>
							<li>
								<p class="fl">
									<span class="checkboxLog" ><span class="checkbox-icon"></span>
									<input type="checkbox" style="display:none;" name="remember" id="remember" ng-model="user.remember" /><span>记住用户名</span></span>
								</p>
								<p class="fr"><span>忘记密码?</span></p>
							</li>
							<li><input type="button" class="btn" ng-click="login()"
								id="login" /></li>
						</ul>
						<i class="log-br"></i>
					</div>
				</div>
			</div>
		</form>
		<div class="login-fot">
			<p>技术支持：深圳市博安达信息技术股份有限公司</p>
		</div>
	</div>

	<!-- flash -->
	<div class="feiniao">
		<object id="flash-show" type="application/x-shockwave-flash"
			data="resources/epsm/login/images/feiniao.swf" width="100%"
			height="100%">
			<param name="movie" value="resources/epsm/login/images/feiniao.swf" />
			<param name="wmode" value="transparent" />
		</object>
	</div>
	<div class="yun">
		<object id="flash-show" type="application/x-shockwave-flash"
			data="resources/epsm/login/images/yun.swf" width="100%" height="100%">
			<param name="movie" value="resources/epsm/login/images/yun.swf" />
			<param name="wmode" value="transparent" />
		</object>
	</div>
	<script type="text/javascript"
		src="<common:webRoot />/resources/component/slide/jquery.SuperSlide.2.1.1.js?v=${sysversion}"></script>
	<script type="text/javascript"
		src="<common:webRoot />/resources/login/login.js?v=${sysversion}"></script>
	<script type="text/javascript">
		// 复选框
		$('.checkboxLog').click(function() {
			myCheckbox($(this));
		});

		function myCheckbox(obj) {
			var checkicon = $(obj).find('.checkbox-icon');
			var checkbox = $(obj).find('input[type="checkbox"]');
			if (checkbox.prop('checked')) {
				checkicon.removeClass("on");
				checkbox.prop('checked', false);
			} else {
				checkicon.addClass("on");
				checkbox.prop('checked', true);
			}
		};

		/**
		 * 设置垂直居中
		 */
		window.onload = window.onresize = function() {
			fVericalAlignBody()
		};
		function fVericalAlignBody() {
			var nBodyHeight = 575;
			var nClientHeight = $(window).height();
			if (nClientHeight >= nBodyHeight + 1) {
				var nDis = (nClientHeight - nBodyHeight) / 2;
				$('.login-box').css('padding-top', nDis);
			} else {
				$('.login-box').css('padding-top', 0);
			}
		}
	</script>
</body>
<%@ include file="/platform/common/footer.jsp"%>