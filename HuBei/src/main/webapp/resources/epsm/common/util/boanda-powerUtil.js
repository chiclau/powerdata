/**
 * 一些通用工具
 */
var powerUtil = {
	//初始化自定义服务
	initService:function(appName){
		if(appName){
			appName.service("commUtileService",function(){
				return new commUtileService();
			});
		}
	},
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
	 * 数组删除
	 */
	removeArray:function(data,from, to) {
		  var rest = data.slice((to || from) + 1 || data.length);
		  data.length = from < 0 ? data.length + from : from;
		  return data.push.apply(data, rest);
	},
	/**
	 * 获取url参数
	 */
	getQueryParam:function(name) {  
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");  
        var r = window.location.search.substr(1).match(reg);  
        if (r != null) return unescape(r[2]);  
        return null;  
    },
    isObject:function(obj){
		if(typeof obj === 'object'){
			return true;
		}
		return false;
	},
	isArray:function(obj){
		if(typeof obj === "object" && Array == obj.constructor){
			return true;
		}
		return false;
	}
    
};

			



