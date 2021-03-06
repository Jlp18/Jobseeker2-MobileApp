var I;
var a=0;
var b=0;
var c=0;
var d0=0;

mui.ready(function(){
    I = new Vue({
		el:"#app",
		data:{
			showPanel:"default",
			recruitname:"recruit1",
			condition:"",
			recruit1:[],//（默认）默认排序
			add1:[],
			recruit2:[],//（默认）最低月薪
			add2:[],
			recruit3:[],//（默认）最高月薪
			add3:[],
			recruit4:[],//（默认）最新排序
			add4:[],
		    recruit5:[],//（搜索）默认排序
		    add5:[],
		    recruit6:[],//（搜索）最低月薪
		    add6:[],
		    recruit7:[],//（搜索）最高月薪
		    add7:[],
		    recruit8:[],//（搜索）最新排序
		    add8:[],
		    recruit_list_id:""
		},
		methods:{
			getrecruitid:function(e,s){
				var ref=this;	
				var e = event.currentTarget;
				var obj=$(e);   //触发事件的元素
				var name=obj.attr("name"); //元素的name属性
				var element=null;			
				if(name==ref.recruitname){
					element=obj;
				}
				else{
					element=obj.parents("dt[name='+ref.recruitname+']");
				}
				var id=element.data("id");
				recruit_list_id=id;
				mui.openWindow({
					url:"../position/position.html",
					id:"extras",
					extras:{
						ID:recruit_list_id
					}
				});
				
			},
		
			showDefaultPanel:function(){			
				this.showPanel="default";
				mui("#refreshContainer").pullRefresh().scrollTo(0,0,10);
				var ref=this;
				if(ref.condition == ""){
					ref.recruitname="recruit1";
				}
				else{
					ref.recruitname="recruit5";
				}
			},
			
			showSalaryMinPanel:function(){
				this.showPanel="salarymin";
				mui("#refreshContainer").pullRefresh().scrollTo(0,0,10);
				var ref=this;
				if(ref.condition == ""){
					ref.recruitname="recruit2";
				}
				else{
					ref.recruitname="recruit6";
				}			
			},
			
			showSalaryMaxPanel:function(){
				this.showPanel="salarymax";
				mui("#refreshContainer").pullRefresh().scrollTo(0,0,10);
				var ref = this;
				if(ref.condition == ""){
					ref.recruitname="recruit3";
				}
				else{
					ref.recruitname="recruit7";
				}	
				
			},
			
			showNewPanel:function(){
				this.showPanel="new";
				mui("#refreshContainer").pullRefresh().scrollTo(0,0,10);//滚动回顶部
				var ref = this;
				if(ref.condition == ""){
					ref.recruitname="recruit4";
				}
				else{
					ref.recruitname="recruit8";
				}	
			},
			
			inputFunc:function(){
				this.recruit5=[];	
				this.recruit6=[];
				this.recruit7=[];
				this.recruit8=[];
				this.recruitname="recruit1";
			},
			
			searchList:function(){	
				var ref=this;
				this.showPanel="default";
				ref.recruitname="recruit5";
				mui("#refreshContainer").pullRefresh().scrollTo(0,0,10);
				$.ajax({
					type:"post",
					data:{
						content:ref.condition
					},
					url:nebula+"/search/searchListDefault",
					beforeSend:function(request){
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
					success:function(json){
						if(json.code==200&&json.result==true){
							ref.recruit5=json.data;
							
						}
						if(json.data=="")
						{
							mui.toast("没有搜索结果");
						}
					},
					error:function(error){
						console.log(JSON.stringify(error));
						mui.toast("执行错误");
					}
				});
				
				$.ajax({
					type:"post",
					data:{
						content:ref.condition
					},
					url:nebula+"/search/searchListSalarymin",
					beforeSend:function(request){
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
					success:function(json){
						if(json.code==200&&json.result==true){
							ref.recruit6=json.data;
							
						}
						if(json.data=="")
						{
							mui.toast("没有搜索结果");
						}
					},
					error:function(error){
						console.log(JSON.stringify(error));
						mui.toast("执行错误");
					}
				});
				
				$.ajax({
					type:"post",
					data:{
						content:ref.condition
					},
					url:nebula+"/search/searchListSalarymax",
					beforeSend:function(request){
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
					success:function(json){
						if(json.code==200&&json.result==true){
							ref.recruit7=json.data;
							
						}
						if(json.data=="")
						{
							mui.toast("没有搜索结果");
						}
					},
					error:function(error){
						console.log(JSON.stringify(error));
						mui.toast("执行错误");
					}
				});
				
				$.ajax({
					type:"post",
					data:{
						content:ref.condition
					},
					url:nebula+"/search/searchListNew",
					beforeSend:function(request){
						request.setRequestHeader("Authorization", localStorage.getItem("token"));
					},
					success:function(json){
						if(json.code==200&&json.result==true){
							ref.recruit8=json.data;
						}
						/*if(json.data=="")
						{
							mui.toast("没有搜索结果");
						}*/
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
			$.ajax({
				type:"post",
				data:{
					s:a
				},
				url:nebula+"/recruit/searchRecruitListDefault",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.recruit1=json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			$.ajax({
				type:"post",
				data:{
					smin:b
				},
				url:nebula+"/recruit/searchRecruitListSalarymin",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.recruit2=json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});	
			
			$.ajax({
				type:"post",
				data:{
					smax:c
				},
				url:nebula+"/recruit/searchRecruitListSalarymax",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.recruit3=json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});	
			
			$.ajax({
				type:"post",
				data:{
					d:d0
				},
				url:nebula+"/recruit/searchRecruitListNew",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						ref.recruit4=json.data;
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});		
			
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
	});
})

mui.plusReady(function(){
	plus.navigator.setStatusBarBackground("#007AFF");  //OS顶部状态栏背景色
	plus.navigator.setStatusBarStyle("light");  //OS顶部文字白色
	plus.screen.lockOrientation("portrait-primary"); //禁止横屏切换

});

mui.init({	
    pullRefresh : {
        container:"#refreshContainer",//待刷新区域标识，querySelector能定位的css选择器均可，比如：id、.class等
        up : {
            height:30,// 可选.默认50.触发上拉加载拖动距离
            auto:false,// 可选,默认false.自动上拉加载一次
            callback : function() {
                var self = this; // 这里的this == mui('#refreshContainer').pullRefresh()
                // 加载更多的内容
                this.refresh(true);
                loadMore(this);
            } //必选，刷新函数，根据具体业务来编写，比如通过ajax从服务器获取新数据；
        }
    }
});

var loadMore = function(pullRefresh){		
		if(I.showPanel=="default")
		{
			//pullRefresh.endPullupToRefresh(false);
			a=a+5;		
			$.ajax({
				type:"post",
				data:{
					s:a
				},
				url:nebula+"/recruit/searchRecruitListDefault",
				beforeSend:function(request){
					request.setRequestHeader("Authorization", localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						I.add1=json.data;
						for(var i = 0,len = I.add1.length;i < len;i++) {
							I.recruit1.push(I.add1[i]);
						}
						pullRefresh.endPullupToRefresh(false);
					}
					if(json.data==""){
						mui.toast("没有更多数据了");
    					//pullRefresh.endPullupToRefresh(true);
   					}
    				
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
		}
		if(I.showPanel=="new"){
			//pullRefresh.endPullupToRefresh(false);			
			d0=d0+5;
			$.ajax({
			type:"post",
			data:{
				d:d0
			},
			url:nebula+"/recruit/searchRecruitListNew",
			beforeSend:function(request){
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(json){
				if(json.code==200&&json.result==true){
					I.add4=json.data;
					for(var i = 0,len = I.add4.length;i < len;i++) {
						I.recruit4.push(I.add4[i]);
					}
					pullRefresh.endPullupToRefresh(false);
				}
				if(json.data==""){
					mui.toast("没有更多数据了");
    				//pullRefresh.endPullupToRefresh(true);
   				}
    				
			},
			error:function(error){
				console.log(JSON.stringify(error));
				mui.toast("执行错误");
			}
		});
		}
		
		if(I.showPanel=="salarymin")
		{		
			b=b+5;
			$.ajax({
			type:"post",
			data:{
				smin:b
			},
			url:nebula+"/recruit/searchRecruitListSalarymin",
			beforeSend:function(request){
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(json){
				if(json.code==200&&json.result==true){
					I.add2=json.data;
					for(var i = 0,len = I.add2.length;i < len;i++) {
						I.recruit2.push(I.add2[i]);
					}
					pullRefresh.endPullupToRefresh(false);
				}
				if(json.data==""){
					mui.toast("没有更多数据了");
    				//pullRefresh.endPullupToRefresh(true);
   				}
    				
			},
			error:function(error){
				console.log(JSON.stringify(error));
				mui.toast("执行错误");
			}
			});
		}	
		
		if(I.showPanel=="salarymax")
		{		
			c=c+5;
			$.ajax({
			type:"post",
			data:{
				smax:c
			},
			url:nebula+"/recruit/searchRecruitListSalarymax",
			beforeSend:function(request){
				request.setRequestHeader("Authorization", localStorage.getItem("token"));
			},
			success:function(json){
				if(json.code==200&&json.result==true){
					I.add3=json.data;
					for(var i = 0,len = I.add3.length;i < len;i++) {
						I.recruit3.push(I.add3[i]);
					}
					pullRefresh.endPullupToRefresh(false);
				}
				if(json.data==""){
					mui.toast("没有更多数据了");
    				//pullRefresh.endPullupToRefresh(true);
   				}			
			},
			error:function(error){
				console.log(JSON.stringify(error));
				mui.toast("执行错误");
			}
			});
		}
		
		
};

