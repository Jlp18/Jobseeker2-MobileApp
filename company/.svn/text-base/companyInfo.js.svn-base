mui.plusReady(function(){
	var companyid=plus.webview.currentWebview().companyid;
	new Vue({
		el:"#app",
		data:{
			id:companyid,
			mysqlResume:"",
			mongoResume:""
		},
		methods:{
			backpositionInfo:function(){
				mui.openWindow('../position/position.html','../position/position.html',{});
			}
		},
		mounted:function(){
			var ref=this;
			
			/*发送Ajax请求 Mysql*/
			$.ajax({
				type:"post",
				url:nebula+"/company/selectFromMysql",
				data:{
					company_id:ref.id
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.mysqlResume=json.data;
						document.getElementById("companyName").innerHTML=ref.mysqlResume.name;
						document.getElementById("type").innerHTML=ref.mysqlResume.type;
						document.getElementById("scale").innerHTML=ref.mysqlResume.scale;
						document.getElementById("mainwork").innerHTML=ref.mysqlResume.mainwork;
						document.getElementById("address").innerHTML=ref.mysqlResume.address;
					}
					else{
						mui.toast("数据加载失败");
					}
				},
				error:function(error){
					mui.toast("执行错误");
					console.log(JSON.stringify(error));
				}
			});
			
			
			
			/*发送Ajax请求  MongoDB*/
			$.ajax({
				url:nebula+"/company/selectFromMongo",
				type:"post",
				data:{
					company_id:ref.id    //这里接参数
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.mongoResume=json.data;
						document.getElementById("introduce").innerHTML=ref.mongoResume.resume;
					}
					else{
						mui.toast("数据加载失败");
					}
				},
				error:function(error){
					mui.toast("执行错误");
					console.log(JSON.stringify(error));
				}
			});
		}
	});
});


mui.plusReady(function(){

});


mui.init();