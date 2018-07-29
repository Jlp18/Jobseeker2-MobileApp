mui.plusReady(function(){
	var recruit_list_id=plus.webview.currentWebview().ID;
	new Vue({
		el: "#app",
		data: {
			jobname: "",
			salary: "",
			location: "", 
			experience : "一年以上",
			time: "",
			companyname: "",
			mainwork: "",
			type: "",
			scale: "",
			welfare: "",
			jobintroduce: "",
			require: "",
			c_id:"",
			recruit_id: recruit_list_id
		},
		methods:{
			showcompanyInfo:function(){
				mui.openWindow({
					url:"../company/companyInfo.html",
					id:"companyInfo.html",
					extras:{
						companyid:this.c_id
					}
				});
			},
			
			sendMyResume:function(){
				var ref=this;
				$.ajax({
				url:nebula+"/resume/sendMyResume",
				type:"post",
				data:{
					recruitId: ref.recruit_id
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						if(json.data==-1){
							mui.toast("请先创建简历");
						}
						else if(json.data==0){
							mui.toast("已投递简历，不要重复投递")
						}
						else{
							mui.toast("投递成功");
						}
					}
					else{
						mui.toast("投递简历失败");
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			}
		},
		mounted:function(){
			var ref=this;
			//发送Ajax请求
			$.ajax({
				url:nebula+"/recruitDetails/fromMysql",
				type:"post",
				data:{
					recruit_id: ref.recruit_id
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.jobname=json.data.job;
						ref.salary="￥"+json.data.salary + "/月薪";
						ref.location=json.data.city;
						ref.time=json.data.date;
						ref.companyname=json.data.name;
						ref.mainwork=json.data.mainwork;
						ref.type=json.data.type;
						ref.scale=json.data.scale;
						ref.welfare=json.data.welfare;
						ref.c_id=json.data.company_id;
					}
					else{
						mui.toast("数据加载失败");
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			$.ajax({
				url:nebula+"/recruitDetails/fromMongo",
				type:"post",
				data:{
					recruit_id: ref.recruit_id
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.jobintroduce=json.data.jobintroduce;
						ref.require=json.data.require;
						document.getElementById("jobintroduce").innerHTML=ref.jobintroduce;
						document.getElementById("require").innerHTML=ref.require;
					}
					else{
						mui.toast("数据加载失败");
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
		},
		filters:{
			mainworkFilter:function(mainwork){
				var temp=mainwork.split(",");
				if(temp.length>0){
					return temp[0];
				}
				else{
					return null;
				}
			},
			welfareFilter1:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[0];
				}
				else{
					return null;
				}
			},
			welfareFilter2:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[1];
				}
				else{
					return null;
				}
			},
			welfareFilter3:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[2];
				}
				else{
					return null;
				}
			},
			welfareFilter4:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[3];
				}
				else{
					return null;
				}
			},
			welfareFilter5:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[4];
				}
				else{
					return null;
				}
			},
			welfareFilter6:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[5];
				}
				else{
					return null;
				}
			},
			welfareFilter7:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[6];
				}
				else{
					return null;
				}
			},
			welfareFilter8:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[7];
				}
				else{
					return null;
				}
			}
		}
	});
	
	var old_back = mui.back;
	mui.back = function(){
		var wobj = plus.webview.getWebviewById("index.html");
		wobj.reload(true);
		old_back();
	};
	
});

mui.ready(function() {


});

mui.init();
