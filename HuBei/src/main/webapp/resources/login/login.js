var app = Common.initApp(['ngCookies']);
var self = this.window;
$(function () {
	jQuery(".fullSlide").slide({ titCell:".hd ul", mainCell:".bd ul", effect:"fold",delayTime:'1000', autoPlay:true,interTime:6000,
		mouseOverStop:false,autoPage:true,pnLoop:false});
	setMainHeight();
	setSlideTop();
	var keyEnter = function () {
		$('#login').click();
	};
	Common.onKeyPress(keyEnter);
	function setMainHeight() {
		// $('.main').height($(window).height() - 104 - 80 +'px');
		$('.header').height(($(window).height() - 455)/2 + 'px');
		$('.footer').height(($(window).height() - 455)/2 + 'px')
	}
	function setSlideTop() {
		$('.main-slide').css({
			top: ($('.main').height()-450)/2 + 'px'
		});
		$('.login-user').css({
			top: ($('.main').height()-341)/2 + 'px'
		});
	}
	$(window).resize(function() {
		setMainHeight();
		setSlideTop();
	});
	
});
app.controller("loginController", ['$scope', '$http',"$cookieStore",'$localStorage', function($scope, $http,$cookieStore,$localStorage){
	$(function () {
		if(platform.isIE()){
			var IeVersion = platform.getIEInfo();
			switch (IeVersion){
				case 8 :
					$(".ie8-show-background").css({
						display :'block'
					});
					var queryBrowser = {
						url: Common.webRoot() + '/platform/component/dict/commondictcontroller/getDictByName',
						method: 'POST',
						data: JSON.stringify({"DICT_KEY":"bd.sys.platform.config.systemConfig.base.LLQXZ"}),
						success: function(result){
							var HTML = '';
							var obj = eval("("+result.data[0].PZZ+")")
							for(var i=0;i<obj.length;i++){
								HTML += "<p style='font-size: 14px;height: 30px;line-height: 30px;width: 50%;float: left;text-align: center'><a target='_blank' href='"+obj[i].url+"'>"+obj[i].name+"</a></p>"
							}
							layer.open({
								type: 1,
								skin: 'layui-layer-demo', //样式类名
								closeBtn: 0, //不显示关闭按钮
								area: ['420px', '200px'], //宽高
								shadeClose: false, //开启遮罩关闭
								content: '\
									<div>\
										<div>\
											<p  style="font-size: 14px;padding-left: 10px;padding-right: 10px;height: 30px;line-height: 30px;text-align: center">本系统不支持IE8以下浏览器，请升级</p>\
											<p  style="font-size: 14px;padding-left: 10px;padding-right: 10px;height: 30px;line-height: 30px;text-align: center">或请点击下列选项下载现代浏览器，以获取最优体验</p>\
										</div>\
										<div>\
											'+HTML+'\
										</div>\
									</div>'
							});
						},
						error: function(result){
						}
					}
					Common.send($scope, $http, queryBrowser);
					break;
				case 9 :
					$(".browserTips").removeClass('hide');
					break;
				case 10 :
					$(".browserTips").removeClass('hide')
					break;
				case 11 :
					break
			}
		}
	})

	//登录用户直接跳转到首页
	if(top.window.sessionID){
		window.location.href = Common.link("/index");
	}
	//$scope.Picture = JSON.parse($('.pictureSource').text())[0];
	$scope.AllPicture = [];
	for(var key in $scope.Picture){
		$scope.AllPicture.push({url:$scope.Picture[key]})
	}
	$scope.isImage = false;
	$scope.user = {};
	$scope.user.XTZH = '';
	$scope.user.YHPD = '';
  // $scope.user = $cookieStore.get("userInfo") ? $cookieStore.get("userInfo") : {};
  $scope.user = $localStorage.getObject("userInfo") ? $localStorage.getObject("userInfo") : {};

	//初始化验证码
	$scope.codeSrc = Common.webRoot() + "/code";
	
	//刷新验证码
	$scope.refreshCode=function(){
		var tmp = Math.random() * 100000;
		$scope.codeSrc = Common.webRoot()+"/code?temp=" + tmp;
	};
	/*初始化LOGO*/
	if($scope.showLOGO && $scope.showLOGO.isimage == 'true'){
		$scope.isImage = true
	}
  if (window.frames.length != parent.frames.length) {
		var SYSTEM_NAME = $(top.window.document.getElementById('PLATFORM_LOGIN_BODY')).text().toString().trim();
		Common.dict.getConfigAll($scope,$http,'bd.sys.platform.config.systemConfig.base.systemName',function (result) {
			var resText = result.data[0].PZZ ? result.data[0].PZZ.toString().trim() : '基础开发平台';
			if (resText == SYSTEM_NAME){
        top.location.href = Common.webRoot()+"/index.jsp"
			}
    });
		//
  }


	//登录
	$scope.login=function(){
		if($scope.user.XTZH == null || $scope.user.XTZH == ''){
			$scope.error='用户帐号不能为空';
			$("#showError").show();
			return;
		}
		
		if($scope.user.YHPD == null || $scope.user.YHPD == ''){
			$scope.error='密码不能为空';
			$("#showError").show();
			return;
		}
		
		if($scope.isShowCode){
			if($scope.user.validateCode == null || $scope.user.validateCode == ''){
				$scope.error='验证码不能为空';
				return;
			}
		}
		//加载提示
    	var index = Common.dialog({
    		type: "load",
    		style: 1
    	});
		Common.send($scope, $http, {
			method: "POST",
			inputobj:{id:'login'},
			url: Common.webRoot() + "/login",
			data: JSON.stringify({
                "XTZH": $scope.user.XTZH,
                "YHMM": $scope.user.YHPD,
                "validateCode": $scope.user.validateCode
            }),
            success: function(data){
            	if("登录成功！" == data.data.msg){
					if($scope.user.remember){
            // $cookieStore.put("userInfo", {'XTZH':$scope.user.XTZH,'remember':$scope.user.remember});
            $localStorage.setObject("userInfo", {'XTZH':$scope.user.XTZH,'remember':$scope.user.remember});
					}
					window.location.href = Common.link("/index");
				}else{
					$scope.error=data.data.msg;
					$("#showError").show();
					$scope.refreshCode();
				}
            	//关闭加载提示
            	Common.dialog({type: "close", index: index});
            },
            error: function(){
            	//关闭加载提示
            	Common.dialog({type: "close", index: index});
            }
		});
	};
}]);
app.factory('$localStorage',['$window',function($window){
  return{        //存储单个属性
    set :function(key,value){
      $window.localStorage[key]=value;
    },        //读取单个属性
    get:function(key,defaultValue){
      return  $window.localStorage[key] || defaultValue;
    },        //存储对象，以JSON格式存储
    setObject:function(key,value){
      $window.localStorage[key]=JSON.stringify(value);
    },        //读取对象
    getObject: function (key) {
      return JSON.parse($window.localStorage[key] || '{}');
    }
  }
}]);