
mui.ready(function(){
	var app=new Vue({
		el:"#app",
		data:{
			address:"<b>大连市甘井子区金虹东路生态科技城D1楼1层滕泰科技</b>",
			username:null,
			city: null,
			role:"企业用户",
			age:null,
			student:[
				{name:"李强",sex:"男",age:15},
				{name:"赵娜",sex:"女",age:27},
				{name:"陈浩",sex:"男",age:13},
				{name:"王强",sex:"男",age:19}
			],
			level:"警告消息"
		},
		methods:{
			sayHello:function(){
				alert("Hello World");
			}
		},
		filters:{
			hideName:function(name){
				var firstName=name.charAt(0);
				var temp=firstName;
				for(var i=0;i<name.length-1;i++){
					temp+="*"
				}
				return temp;
			}
		}
	});
});


mui.plusReady(function(){
	
});

mui.init();
