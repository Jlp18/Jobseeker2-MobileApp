mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			username: localStorage.getItem("username"),
			exp:0,
			resumeCount:0,
			progress: null,
			recruit:[]
		},
		methods:{
			showrecruit_list:function(){
				mui.openWindow('recruit/recruit_list.html','recruit_list.html',{});
			},
			
			sendResume:function(s,e){
				var ref=this;
				var obj=$(e.target);   //触发事件的元素
				var name=obj.attr("name"); //元素的name属性
				var element=null;
				if(name=="recruit"){
					element=obj;
				}
				else{
					element=obj.parents("dt[name='recruit']");
				}
				var id=element.data("id");  //取出投递简历的招聘记录id
				
				mui.confirm("是否投递简历","通知消息",["取消","确认"],function(e){
					if(e.index==1){
						$.ajax({
							url:nebula+"/resume/sendMyResume",
							type:"post",
							data:{
								recruitId:id
							},
							beforeSend:function(request){
								request.setRequestHeader("Authorization",localStorage.getItem("token"));
							},
							success:function(json){
								if(json.data==-1){
									mui.toast("请先创建简历");
								}
								else if(json.data==0){
									mui.toast("已投递简历，不要重复投递");
								}
								else{
									for(one of ref.recruit){
										if(one.id==id){
											one.sended=1;
										}
									}
									ref.resumeCount+=1;
									//mui.toast("简历投递成功");
									$.ajax({
										url:nebula+"/userlevel/updateExp_1",
										type:"post",
										data:{},
										beforeSend:function(request){
											request.setRequestHeader("Authorization",localStorage.getItem("token"));
										},
										success:function(json){
											if(json.data==1){
												ref.exp+=10;
												ref.progress="width:"+ref.exp%1000/10+"%";
												mui.toast("经验值+10");
											}
										},
										error:function(error){
											console.log(JSON.stringify(error));
											mui.toast("执行错误");
										}
									});
								}
							},
							error:function(error){
								console.log(JSON.stringify(error));
								mui.toast("执行错误");
							}
						});
					}
				},"div");
				
			},
			positionInfo:function(e,s){
				var e = event.currentTarget;
				var obj=$(e);  //触发事件的元素
				var name=obj.attr("name"); //元素的name属性
				var element=null;
				if(name=="recruit"){
					element=obj;
				}
				else{
					element=obj.parents("dt[name='recruit']");
				}
				var id=element.data("id");  //取出投递简历的招聘记录id
				mui.openWindow({
					url:"position/position.html",
					id:"position.html",
					extras:{
						ID:id
					}
				});
			},
			resume:function(){
				mui.openWindow("resume/resume.html","resume.html;",{});
			}
		},
		filters:{
			welfareFilter:function(welfare){
				var temp=welfare.split(",");
				if(temp.length>0){
					return temp[0];
				}
				else{
					return null;
				}
			}
		},
		//VUE对象初始化完毕之后默认执行的回调方法
		mounted:function(){
			var ref=this;
			//发送Ajax请求
			$.ajax({
				url:nebula+"/user/searchSummary",
				type:"post",
				data:{},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					ref.exp=json.data.exp;
					ref.resumeCount=json.data.resumeCount;
					ref.progress="width:"+ref.exp%1000/10+"%";
					if(json.data.photoPath!=null){
						document.getElementById("photo").style.backgroundImage="url(\""+json.data.photoPath+"\")";
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			$.ajax({
				type:"get",
				url:nebula+"/recruit/searchCurrentRecruit",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.recruit=json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
		}
	})
});

mui.plusReady(function(){
	
});


mui.init({
	gestureConfig: {
		longtap: true, //默认为false
		release: true
	}
});