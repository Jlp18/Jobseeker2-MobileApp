
mui.ready(function(){
	new Vue({
		el:"#app",
		data:{
			showPanel:"login", //通过赋值 login显示登录页面，register显示注册页面
			login:{
				username:"",
				password:""
			},
			register:{
				username:"",
				password:"",
				repassword:"",
				userEmail:"",
				chooseRole:"求职者",
				roleNumber:1
			}
			
		},
		methods:{
			showLoginPanel:function(){
				this.showPanel="login";
			},
			showRegisterPanel:function(){
				this.showPanel="register";
			},
			loginSystem:function(){
				var bool_l=checkUsername(this.login.username);
				bool_l=checkPassword(this.login.password) && bool_l;
				if(bool_l){
					var ref=this;
					$.ajax({
						url: nebula + "/user/login",
						type:"post",
						data:{
							username:ref.login.username,
							password:ref.login.password
						},
						success:function(json){
							if(json.code == 200 && json.result == true){
								mui.toast(json.msg);
								localStorage.setItem("token", json.data.token);  //本地保存令牌
								localStorage.setItem("username",ref.login.username);  //本地保存用户名
								mui.openWindow('index.html','index.html',{});
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
				}
			},
			registerSystem:function(){
				var bool_r=checkUsername(this.register.username);
				bool_r=checkPassword(this.register.password) && checkPassword(this.register.repassword) && bool_r;
				bool_r=checkEmail(this.register.userEmail)&&bool_r;
				if(bool_r && (this.register.password==this.register.repassword)){
					if(this.register.chooseRole=="求职者"){
						this.register.roleNumber=1;
					}
					if(this.register.chooseRole=="企业用户"){
						this.register.roleNumber=2;
					}
					if(this.register.chooseRole=="管理员"){
						this.register.roleNumber=3;
					}
					if(this.register.chooseRole=="超级管理员"){
						this.register.roleNumber=4;
					}
					var ref=this;
					$.ajax({
						url:nebula+"/user/register",
						type:"post",
						data:{
							username:ref.register.username,
							password:ref.register.password,
							email:ref.register.userEmail,
							roleId:ref.register.roleNumber
						},
						success:function(json){
							if(json.data==0){
								mui.toast("用户已存在");
							}
							else{
								mui.toast(json.msg);
								mui.openWindow("login.html","login.html",{});
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
				}
			}
		}
	});
});

mui.plusReady(function(){
	plus.navigator.setStatusBarBackground("#007AFF");  //OS顶部状态栏背景色
	plus.navigator.setStatusBarStyle("light");  //OS顶部文字白色
	plus.screen.lockOrientation("portrait-primary"); //禁止横屏切换
	//隐藏滚动条
	plus.webview.currentWebview().setStyle({
		scrollIndicator:'none'
	})
});

mui.init();