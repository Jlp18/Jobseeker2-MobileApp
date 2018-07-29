
mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			showPanel:"before",
			userName:"",
			userEmail:"",
			code:"",
			password:"",
			rePassword:""
		},
		methods:{
			getCode:function(){
				var bool=checkUsername(this.userName);
				bool=checkEmail(this.userEmail)&&bool;
				if(bool){
					var ref=this;
					$.ajax({
						type:"post",
						url:nebula+"/email/sendEmail",
						data:{
							username:ref.userName,
							address:ref.userEmail
						},
						success:function(json){
							if(json.data==1&&json.result==true){
								mui.toast(json.msg);
								document.getElementById("sendButton").value="重新获取";
							}
							else{
								mui.toast(json.msg);
							}
						},
						error:function(error){
							mui.toast("执行错误");
							console.log(JSON.stringify(error));
						}
					});
				}
				else{
					mui.toast("输入信息有误");
					mui.openWindow("forgetPassword.html","forgetPassword.html",{});
				}
			},
			before:function(){
				var bool=checkUsername(this.userName);
				bool=checkEmail(this.userEmail)&&bool;
				bool=(this.code!="")&&bool;
				if(bool){
					var ref=this;
					$.ajax({
						url:nebula+"/email/checkCode",
						type:"post",
						data:{
							code:ref.code
						},
						success:function(json){
							if(json.data==1&&json.result==true){
								mui.toast(json.msg);
								document.getElementById("title").innerHTML="修改密码";
								ref.showPanel="after";
							}
							else{
								mui.toast(json.msg);
							}
						},
						error:function(error){
							mui.toast("执行错误");
							console.log(JSON.stringify(error));
						}
					});
				}
				else{
					mui.toast("输入信息有误");
					mui.openWindow("forgetPassword.html","forgetPassword.html",{});
				}
			},
			after:function(){
				var i=checkPassword(this.password);
				i=checkPassword(this.rePassword)&&i;
				if(i && (this.password==this.rePassword)){
					var ref=this;
					$.ajax({
						url:nebula+"/user/updatePassword",
						type:"post",
						data:{
							username:ref.userName,
							password:ref.password
						},
						success:function(json){
							if(json.result==true && json.data==1){
								mui.toast("密码更改成功");
								mui.openWindow("../login.html","login.html",{});
							}
							else{
								mui.toast("密码修改失败");
							}
						},
						error:function(error){
							mui.toast("执行错误");
							console.log(JSON.stringify(error));
						}
						});
				}
				else{
					mui.toast("输入信息有误");
					this.password="";
					this.rePassword="";
				}
			}
		}
	});
});


mui.plusReady(function(){
	
});

mui.init();