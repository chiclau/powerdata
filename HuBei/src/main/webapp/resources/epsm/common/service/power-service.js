/**
 * 通用工具类service
 */
var commUtileService = function(){};
commUtileService.prototype = {
		/**
	     * 验证JS对象为空
	     * @param obj  需要验证的对象
	     * @returns 验证结果
	     */
		isEmpty:function(obj){
			if((obj === undefined || obj === "" || obj === null)) return true;
			if($.type(obj) == 'object') return $.isEmptyObject(obj);
			obj = $.trim(obj); 
			return (obj == undefined || obj == "" || obj == null);
		},
		
		/**
		 * 验证JS对象不为空
		 * @param obj  需要验证的对象
		 * @returns 验证结果
		 */
		isNotEmpty:function(obj){
			return !this.isEmpty(obj);
		},
		
		/**
		 * @private
		 * 生成随机数
		 * @returns 随机数
		 */
		random4:function() {
			return (((1 + Math.random()) * 0x10000) | 0).toString(16).substring(1);
		},
		
		/**
		 * 生成唯一的UUID
		 * @returns UUID
		 */
		UUID:function() {
			return (this.random4() + this.random4() + this.random4() + this.random4() + this.random4() +  this.random4() + this.random4() + this.random4());
		},
		
		/**
		 * 得到当前毫秒数
		 * @returns 毫秒数
		 */
		getTime:function(){
			return new Date().getTime();
		},
		
		/**
		 * 表格自动计算高度
		 */
		gridAutoHeight:function(gridClass, padding){
			//计算表格高度 注意使用必选是boostrap布局
			function autoHeight(gridClass, padding){
				var row = $("."+gridClass).parents('.row:eq(0)');
	        	if(row.length > 0){
                    var top = row.offset().top;
                    var wh = $(window).height();
                    if(padding){
                    	wh -= padding;
                    }
                    var height = 450;
                    if(top > 0 ){
                    	height = wh - top - 35;
                    }else{
                		var col = $("."+gridClass).parents('.power-wrapper:eq(0)');
                		if(col.offset()){
                			top = col.offset().top;
                		}
                    	height = wh - top - 35;
                    }
                    angular.element($("."+gridClass)).css('height', height+"px");
	            } 
			}
			//页面加载完成之后
			setTimeout(function(){
				autoHeight(gridClass,padding);
			},300);
			//浏览器窗口大小更改
			$(window).resize(function(){
				autoHeight(gridClass,padding);
			});
		},
		/**
		 * 数据反转
		 */
		dataReverse:function(origData){
			var newData = [];
			for(var i=(origData.length-1);i>=0;i--){
				newData.push(origData[i]);
			}
			return newData;
		},
		/**
		 * url String 地址
		 * param Object 参数对象
		 */
		addUrlParams : function(url,param){
			var isHasParam = false;
			if(url.indexOf("?")!=-1){
				isHasParam = true;
			}
			var i = 0;
			for(var name in param){
				if(isHasParam){
					url+="&"+name+"="+param[name];
				}else{
					if(i==0){
						url+="?"+name+"="+param[name];
					}else{
						url+="&"+name+"="+param[name];
					}
				}
				i++;
			}
			return url;
		},
		/**
		 * 获取url参数
		 */
		getQueryParam : function(name) {  
	        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
	        var r = window.location.search.substr(1).match(reg);  
	        if (r != null) return unescape(r[2]);  
	        return null;  
	    },
	    //回退刷新
	    goBack : function(){
	    	window.history.go(-1);//返回上一页不刷新  
			window.location.href = document.referrer;//返回上一页并刷新  
	    },
	    /**
		 * 通用的打印方法
		 */
	    print:function(scope, http, window, dyly){
	    	// 获取自身对象，用与调用自身的方法
	    	var me = this;
	    	// 根据打印的类型获取打印配置
			Common.send(scope, http, {
				method: "POST",
				url: Common.webRoot() + "/config/printconfigcontroller/getprintconfig",
				data:JSON.stringify({DYLX:dyly}),
				showTips : false,
				success: function(data){
					var resData = data.data;
					if(resData.code==0){
						if(resData.result){
							var dycs = resData.result.TCCS;
							var dycssm = resData.result.TCCSSM;
							var url = '';
							// 如果打印配置了参数，没有配置项，则默认取businessId中的值为过滤条件
							if(dycs != null && dycs != '' && (dycssm == null || dycssm == '')){
								url = Common.webRoot()+"/config/printconfigcontroller/printfiletest?DYLX=" + dyly + "&" + dycs + '=' + me.getQueryParam("businessId");
							}else if(dycs != null && dycs != '' && dycssm != null && dycssm != ''){
								var param = '';
								var dycss = dycs.split(',');
								var dycssms = dycssm.split(',');
								// 对参数个数和配置项个数进行校验
								if(dycss.length > 0 && dycss.length != dycssms.length){
									Common.RBTips('打印配置的参数和配置项个数不一致，不能进行打印',2);
									return;
								}
								// 对指定参数和配置项的，取参数进行打印url拼接
								for(var i = 0; i < dycss.length; i ++){
									param += '&' + dycss[i] + '=' + me.getQueryParam(dycssms[i]);
								}
								url = Common.webRoot()+"/config/printconfigcontroller/printfiletest?DYLX=" + dyly + param;
							}else{
								Common.RBTips('打印配置的参数异常，不能进行打印',2);
								return;
							}
							// 校验打印功能，如果通过则直接打印
							me.printtest(scope, http, window, url);
						}else{
							Common.RBTips('根据传入的打印类型，未获取获取打印数据',2);
						}
					}else{
						Common.RBTips('获取打印数据失败',2);
					}
				}
			});
			
		},
		 /**
		 * 校验打印是否异常
		 */
		printtest:function(scope, http, window, url){
			Common.send(scope, http, {
				method: "POST",
				url: url,
				data:JSON.stringify({}),
				showTips : false,
				success: function(data){
					var resData = data.data;
					// 如果校验通过，则进行打印，否则爆出错误信息
					if(resData.code==0){
						if(resData.result){
							console.log(resData.result);
							window.location.href = Common.webRoot()+"/config/printconfigcontroller/templateprint?fileName=" + resData.result;
						}else{
							Common.RBTips('返回数据异常，请刷新重试或联系管理员',2);
						}
					}else{
//						Common.msg(resData.msg,2);
						top.layer.alert(resData.msg,{icon: 2,title:"提示"});
					}
				}
			});
		}
	    
};
	