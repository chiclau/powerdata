/**
 * artTemplate 模板引起动态渲染工具操作
 * @author 王武
 * @date 2017-03-17
 */
var ArtRender = {
		/**
		 * 动态渲染dom
		 * @param tplId 模板ID
		 * @param renerId 渲染的Id
		 * @param data 数据
		 * @isAppend 是否追加 用上拉加载时候
		 */
		render:function (tplId,renerId,data,isAppend){
			try{
				var htmlStr = template(tplId, data);
				if(isAppend){
					$("#"+renerId).append(htmlStr);
				}else{
					$("#"+renerId).html("");
					$("#"+renerId).html(htmlStr);
				}
			}catch (e) {
				console.log(e);
			}
		},
		
		/**
		 * 请求数据
		 */
		send :function (url,param,callback,erroCallback){
			jQuery.ajax({
			    headers: { 
			        'Accept': 'application/json',
			        'Content-Type': 'application/json' 
			    },
			    type: 'POST',
			    url: url,
			    data: JSON.stringify(param),
			    dataType: 'json',
			    success: function(res){
			    	callback(res);
			    },
			    error:function(req,errormsg,e){
			    	if(typeof erroCallback =='function'){
			    		erroCallback();
			    	}else{
			    		console.log("erroCallback is not a function");
			    	}
			    	console.log(e);
			    }
		  });
		},
		
		/**
		 * 跨域请求
		 */
		getDatap :function (){
			
		}
}