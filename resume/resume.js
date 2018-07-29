
function clickItem(json) {
	let type = json.type;
	let callback;
	if(type == "picker") {
		callback = function() {
			let obj = $(this);
			let picker = new mui.PopPicker();
			let value = obj.find(".value").data("value"); //控件原有值
			picker.setData(json.data);
			picker.pickers[0].setSelectedValue(value);
			picker.show(function(items) {
				let element = obj.find(".value");
				element.html(items[0].value);
				element.data("value", items[0].value)
				picker.dispose();
			});
		}
	} else if(type == "prompt") {
		callback = function() {
			let obj = $(this);
			mui.prompt(json.message, json.placeholder, json.title, json.btn, function(result) {
				if(result.index == 1) {
					let element = obj.find(".value");
					let regExp = new RegExp(json.pattern);
					if(regExp.test(result.value)) {
						element.html(result.value + json.unit);
					} else {
						mui.toast("填写错误");
					}
				}

			}, 'div');
		}

	} else if(type == "datePicker") {
		callback = function() {
			let obj = $(this);
			let dtPicker = new mui.DtPicker({
				type: "date",
				beginDate: new Date(1900,1,1),
				endDate: new Date(2019,1,1),
			});
			dtPicker.show(function(items) {
				let element = obj.find(".value");
				
				let year = items.y.value;
				let month = items.m.value;
				let date = items.d.value;
				let temp = year + "-" + month + "-" + date;
				element.html(temp);
				dtPicker.dispose();
			});
		}
	}
	$(json.el).on("tap", callback);

	if(type == "editor") {
		var editor = new Eleditor({
			el: json.el,
			toolbars: [
				"editText",
				"deleteThis",
				"cancel"
			]
		});
		
	$(json.el).data("editor",editor);
	}
};

	//拍照
			function getImages(){
				var mobileCamera=plus.camera.getCamera();
				mobileCamera.captureImage(function(e){
					plus.io.resolveLocalFileSystemURL(e,function(entry){
						var path=entry.toLocalURL()+'?version='+ new Date().getTime();
						uploadHeadImg(path);
						console.log(path);
					},function(e){
						console.log("读取拍照文件错误");
					});
				},function(e){
					console.log("er",e);
				},function(){
					filename:'_doc/head.png';
				});
			};
			//从本地相册选择
			function galleryImages(){
				console.log("你选择了从相册选择");
				plus.gallery.pick(function(a){
					plus.io.resolveLocalFileSystemURL(a,function(entry){
						plus.io.resolveLocalFileSystemURL('_doc/',function(root){
							root.getFile('head.png',{},function(file){
								//文件已经存在
								file.remove(function(){
									console.log("文件移除成功");
									entry.copyTo(root,'head.png',function(e){
										var path=e.fullPath+'?version='+new Date().getTime();
										uploadHeadImg(path);
									},function(err){
										console.log("copy image fail:",err);
									});
								},function(err){
									console.log("删除图片失败:("+JSON.stringify(err)+")");
								});
							},function(err){
								//打开文件失败
								entry.copyTo(root,'head.png',function(e){
									var path=e.fullPath+'?version='+new Date().getTime();
									uploadHeadImg(path);
								},function(err){
									console.log("上传图片失败:("+JSON.stringify(err)+")");
								});
							});
						},function(e){
							console.log("读取文件夹失败:("+JSON.stringify(err)+")");
						});
					});
				},function(err){
					console.log("读取拍照文件失败：",err);
				},{
					filter:'image'
				});
			};
			//上传图片
			function uploadHeadImg(imgPath){
				//选中图片之后，头像当前的照片变为选择的照片
				var mainImg=document.getElementById("Img");
				mainImg.src=imgPath;
				
				$.ajax({
					url:nebula+"/resume/updatePhoto",
					type:"post",
					data:{
						photo_path:imgPath
					},
					beforeSend:function(request){
						request.setRequestHeader("Authorization",localStorage.getItem("token"));
					},
					success:function(json){
						if(json.code!=200||json.result!=true){
							mui.toast("头像上传失败");
						}
					},
					error:function(error){
						mui.toast("执行错误");
						console.log(JSON.stringify(error));
					}
				});
				
			}


function myFunction(){
		if(mui.os.plus){
					var a=[{
						title:'拍照'
					},{
						title:'从手机相册选择'
					}];
					plus.nativeUI.actionSheet({
						title:'修改头像',
						cancel:'取消',
						buttons:a
					},function(b){
						switch(b.index){
							case 0:
								break;
							case 1:
								//拍照
								getImages();
								break;
							case 2:
								//打开相册
								galleryImages();
								break;
							default:
								break;
						}
					},false);
				}
};

mui.ready(function(){
	
	

			
			
		/*new Vue({
		el:"#sex",
		data:{
			dataList:[
				{
					value:'男',
					text:'男'
				},{
					value:'女',
					text:'女'
				}
			]
		},
		methods:{
			test:function(){
				let obj = $(this);
				let picker = new mui.PopPicker();
				let value = obj.find(".value").data("value"); //控件原有值
				picker.setData(this.dataList);
				picker.pickers[0].setSelectedValue(value);
				picker.show(function(items) {
				let element = obj.find(".value");
				element.html(items[0].value + "<i class='iconfont icon-enter'></i>");
				element.data("value", items[0].value);
				picker.dispose();
			});
			}
		}
	});
	*/
	var array =[{
			el: "#name",
			message: "输入您的姓名",
			placeholder: "",
			title: "姓名",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[\u4e00-\u9fa5]{2,4}$",
			type: "prompt"
		},{
			el: "#approve",
			type: "picker",
			data: [{
				value: '未认证',
				text: '未认证'
			}, {
				value: '已认证',
				text: '已认证'
			}]
		},{
			el: "#sex",
			type: "picker",
			data: [{
				value: '男',
				text: '男'
			}, {
				value: '女',
				text: '女'
			}]
		}, {
			el: "#birthday",
			type: "datePicker"
		},
		{
			el: "#height",
			message: "请输入您的身高",
			placeholder: "",
			title: "身高",
			btn: ["取消", "确认"],
			unit: "cm",
			pattern: "^[12][0-9]{2}$",
			type: "prompt"
		},
		{
			el: "#weight",
			message: "请输入您的体重",
			placeholder: "",
			title: "体重",
			btn: ["取消", "确认"],
			unit: "kg",
			pattern: "^[0-9][0-9]{1,2}$",
			type: "prompt"
		},

		{
			el: "#marriage",
			data: [{
				value: '未婚',
				text: '未婚'
			}, {
				value: '已婚',
				text: '已婚'
			}],
			type: "picker"
		},
		{
			el: "#ancestral_home",
			message: "输入您的籍贯",
			placeholder: "",
			title: "籍贯",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[\u4e00-\u9fa5]{2,4}$",
			type: "prompt"
		},
		{
			el: "#info [name='education']",
			data: [{
				value: '无',
				text: '无'
			}, {
				value: '中专',
				text: '中专'
			}, {
				value: '大专',
				text: '大专'
			}, {
				value: '本科',
				text: '本科'
			}, {
				value: '研究生',
				text: '研究生'
			}, {
				value: '博士',
				text: '博士'
			}],
			type: "picker"
		},
		{
			el: "#tel",
			message: "输入您的电话",
			placeholder: "",
			title: "电话",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[1][0-9]{10}$",
			type: "prompt"
		},
		{
			el: "#email",
			message: "输入您的邮箱",
			placeholder: "",
			title: "邮箱",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$",
			type: "prompt"
		},
		{
			el: "#educationExp [name='school']",
			message: "输入您的毕业学校",
			placeholder: "",
			title: "毕业学校",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[\u4e00-\u9fa5]{2,10}$",
			type: "prompt"
		},
		{
			el: "#educationExp [name='major']",
			message: "输入您所学的专业",
			placeholder: "",
			title: "所学专业",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[\u4e00-\u9fa5]{2,20}$",
			type: "prompt"
		},
		{
			el: "#educationExp [name='education']",
			data: [{
				value: '无',
				text: '无'
			}, {
				value: '中专',
				text: '中专'
			}, {
				value: '大专',
				text: '大专'
			}, {
				value: '本科',
				text: '本科'
			}, {
				value: '研究生',
				text: '研究生'
			}, {
				value: '博士',
				text: '博士'
			}],
			type: "picker"
		},
		{
			el: "#educationExp [name='year']",
			message: "输入您的毕业年份",
			placeholder: "",
			title: "毕业年份",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[0-9]{4}$",
			type: "prompt"
		},
		{
			el: "#work [name='company']",
			message: "输入您工作过的企业名称",
			placeholder: "",
			title: "企业",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[a-zA-Z0-9\u4e00-\u9fa5]{2,40}$",
			type: "prompt"
		},
		{
			el: "#work [name='hiredate']",
			type: "datePicker"
		}, {
			el: "#work [name='leavedate']",
			type: "datePicker"
		}, 
		{
			el: "#job [name='work_place']",
			message: "输入工作地点",
			placeholder: "",
			title: "工作地点",
			btn: ["取消", "确认"],
			unit:"",
			pattern: "^[a-zA-Z0-9\u4e00-\u9fa5]{2,40}$",
			type: "prompt"
		},
		{
			el: "#job [name='salary']",
			message: "输入薪资要求",
			placeholder: "",
			title: "薪资要求",
			btn: ["取消", "确认"],
			unit:"元/月",
			pattern: "^[0-9]{4,20}$",
			type: "prompt"
		},
		{
			el: "#job [name='position']",
			message: "输入您的求职岗位",
			placeholder: "",
			title: "求职岗位",
			btn: ["取消", "确认"],
			unit: "",
			pattern: "^[\u4e00-\u9fa5]{2,20}$",
			type: "prompt"
		},
		{
			el: "#job [name='job_time']",
			data: [{
				value: '立即入职',
				text: '立即入职'
			}, {
				value: '一个月以内',
				text: '一个月以内'
			}, {
				value: '三个月以内',
				text: '三个月以内'
			}, {
				value: '半年以内',
				text: '半年以内'
			}],
			type: "picker"
		}
	
		
		
		
	]
	

	$(".operate").on("tap", function() {
		if($(this).text() == "修改") {
			$(this).text("保存");
			
			document.getElementById("userImg").addEventListener('tap',myFunction); //为头像添加点击事件
			for(let one of array) {
				clickItem(one);
			}
			let dynamic = $("[name='dynamic']")
			dynamic.html(dynamic.text()+"&nbsp;&nbsp;（长按删除）");
			
			$("#education .btn[name='add']").on("tap", function() {
				//解除已有控件的点击事件
				for(let one of array) {
					if(one.type == "editor") {
						$(one.el).removeAttr("Eleditor-Inited");
					} else {
						$(one.el).unbind("tap");
					}
				}

				let temp = `
					<dt name="dynamic">教育经历&nbsp;&nbsp;(长按删除)</dt>
					<dd>
						<a name="school">
							<span class="label">毕业学校</span>
							<span class="value"><i class="iconfont icon-enter"></i></span>
						</a>
					</dd>
					<dd>
						<a name="major">
							<span class="label">所学专业</span>
							<span class="value"><i class="iconfont icon-enter"></i></span>
						</a>
					</dd>
					<dd>
						<a name="education">
							<span class="label">学历</span>
							<span class="value"><i class="iconfont icon-enter"></i></span>
						</a>
					</dd>
					<dd>
						<a name="year">
							<span class="label">毕业年份</span>
							<span class="value"><i class="iconfont icon-enter"></i></span>
						</a>
					</dd>
				`;
				$("#education dl").append(temp);
				//为所有控件重新绑定点击事件
				for(let one of array) {
					clickItem(one);
				}
				//为所有动态的内容解除长按事件
				$("[name='dynamic']").unbind("longtap");
				//为所有的动态内容绑定长按事件
				$("[name='dynamic']").on("longtap", function() {
					//TODO 震动
					plus.device.vibrate(100);
					let obj = $(this);
					mui.confirm("是否删除选中的内容", "提示信息", ["否", "是"], function(result) {
						if(result.index == 1) {
							obj.nextUntil("dt").remove();
							obj.remove();
						}
					}, "div");
				});
			});

		} else {
			$(this).text("修改");
			document.getElementById("userImg").removeEventListener('tap',myFunction);
			var name=document.getElementById("user_name").innerHTML;
			var auth=document.getElementById("auth").innerHTML;
			var sex=document.getElementById("sex_1").innerHTML;
			var birthday=document.getElementById("birthday_1").innerHTML;
			var height=document.getElementById("height_1").innerHTML;
			var temp_height=height.split("c");
			var weight=document.getElementById("weight_1").innerHTML;
			var temp_weight=weight.split("k");
			var married=document.getElementById("married").innerHTML;
			var city=document.getElementById("city").innerHTML;
			var education=document.getElementById("education_1").innerHTML;
			var tel=document.getElementById("tel_1").innerHTML;
			var email=document.getElementById("email_1").innerHTML;
			var job_city=document.getElementById("work_place1").innerHTML;
			var job_salary=document.getElementById("salary_1").innerHTML;
			var job_name=document.getElementById("position_1").innerHTML;
			var job_hiredate=document.getElementById("job_time1").innerHTML;
		    var school=document.getElementById("school_1").innerHTML;
		    var major=document.getElementById("major_1").innerHTML;
		    var year=document.getElementById("year_1").innerHTML;
		    var education_1=document.getElementById("education_2").innerHTML;
		    var company_name=document.getElementById("company").innerHTML;
		    var hiredate=document.getElementById("hiredate").innerHTML;
		    var leavedate=document.getElementById("leavedate").innerHTML;
		    var works=document.getElementById("works").innerHTML;
			
			
			$.ajax({
				url:nebula+"/resume/insertResumeinfo",
				type:"post",
				data:{
					name:name,
					auth:auth,
					sex:sex,
					birthday:birthday,
					height:temp_height[0],
					weight:temp_weight[0],
					married:married,
					city:city,
					education:education,
					tel:tel,
					email:email,
					job_city:job_city,
					job_salary:job_salary,
					job_name:job_name,
					job_hiredate:job_hiredate,
					school:school,
					major:major,
					year:year,
					education_1:education_1,
					company_name:company_name,
					hiredate:hiredate,
					leavedate:leavedate,
					works:works
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						
					}else{
						mui.toast("简历修改失败");
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			
			$.ajax({
				url:nebula+"/resume/updateResumeinfo",
				type:"post",
				data:{
					name:name,
					auth:auth,
					sex:sex,
					birthday:birthday,
					height:temp_height[0],
					weight:temp_weight[0],
					married:married,
					city:city,
					education:education,
					tel:tel,
					email:email,
					job_city:job_city,
					job_salary:job_salary,
					job_name:job_name,
					job_hiredate:job_hiredate,
					school:school,
					major:major,
					year:year,
					education_1:education_1,
					company_name:company_name,
					hiredate:hiredate,
					leavedate:leavedate,
					works:works
				},
				beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
				success:function(json){
					if(json.code==200&&json.result==true){
						mui.toast(json.msg);
					}else{
						mui.toast("简历修改失败");
					}
				},
				error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
			});
			
			for(let one of array) {
				if(one.type == "editor") {
					let editor=$(one.el).data("editor");
					editor.destory();
					$(one.el).removeData("editor");
				} else {
					$(one.el).unbind("tap");
				}
			}
			$(".btn[name='add']").unbind("tap");
			let dynamic = $("[name='dynamic']")
			dynamic.text(dynamic.text().substring(0,4))
	}
		
		
		
		
			
			/*for(let one of array) {
				if(one.type == "editor") {
					let editor=$(one.el).data("editor");
					editor.destory();
					$(one.el).removeData("editor");
				} else {
					$(one.el).unbind("tap");
				}
			}
			$(".btn[name='add']").unbind("tap");
			let dynamic = $("[name='dynamic']")
			dynamic.text(dynamic.text().substring(0,4))*/
		});
		
		$.ajax({
		url:nebula+"/resume/selectResumeinfo",
		type:"get",
		data:{},
			 beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
			 success:function(json){
					if(json.code==200&&json.result==true){
				document.getElementById("user_name").innerHTML=json.data.name;
			    document.getElementById("auth").innerHTML=json.data.auth;
			    document.getElementById("sex_1").innerHTML=json.data.sex;
			    document.getElementById("birthday_1").innerHTML=json.data.birthday;
			    document.getElementById("height_1").innerHTML=json.data.height+"cm";
			    document.getElementById("weight_1").innerHTML=json.data.weight+"kg";
			    document.getElementById("married").innerHTML=json.data.married;
			    document.getElementById("city").innerHTML=json.data.city;
			    document.getElementById("education_1").innerHTML=json.data.education;
			    document.getElementById("tel_1").innerHTML=json.data.tel;
			    document.getElementById("email_1").innerHTML=json.data.email;
			    document.getElementById("work_place1").innerHTML=json.data.job_city;
			    document.getElementById("salary_1").innerHTML=json.data.job_salary;
			    document.getElementById("position_1").innerHTML=json.data.job_name;
			    document.getElementById("job_time1").innerHTML=json.data.job_hiredate;
			    document.getElementById("school_1").innerHTML=json.data.school;
			    document.getElementById("major_1").innerHTML=json.data.major;
			    document.getElementById("year_1").innerHTML=json.data.year;
			    document.getElementById("education_2").innerHTML=json.data.education_1;
			    document.getElementById("company").innerHTML=json.data.company_name;
			    document.getElementById("hiredate").innerHTML=json.data.hiredate;
			    document.getElementById("leavedate").innerHTML=json.data.leavedate;
			    document.getElementById("works").innerHTML=json.data.works;
			    if(json.data.photo_path!=null){
			    	document.getElementById("Img").src=json.data.photo_path;
			    }
				}
				else{
						mui.toast("简历查询失败");
					}
				},
			 error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
	});
});
	
	
	
	
	
	
	
	
	
	
	






mui.plusReady(function() {
				plus.navigator.setStatusBarBackground("#007AFF"); //OS顶部状态栏背景色
				plus.screen.lockOrientation("portrait-primary"); //禁止横屏切换
			});

mui.init({
			    gestureConfig: {
					tap: true, //默认为true
					doubletap: true, //默认为false
					longtap: true, //默认为false
					swipe: true, //默认为true
					drag: true, //默认为true
					hold: false, //默认为false，不监听
					release: false //默认为false，不监听
				}	
			});
			






/*$.ajax({
		url:nebula+"/resume/insertResumeinfo",
		type:"post",
		data:{
			name:temp_name[0],
			auth:temp_auth[0],
			sex:temp_sex[0],
		    birthday:temp_birthday[0],
			height:temp_height[0],
			weight:temp_weight[0],
			married:temp_married[0],
			city:temp_city[0],
			education:temp_education[0],
			tel:temp_tel[0],
			email:temp_email[0]
		},
		beforeSend:function(request){
					request.setRequestHeader("Authorization",localStorage.getItem("token"));
				},
		success:function(json){
					if(json.code==200&&json.result==true){
						mui.toast(json.msg);
					}else{
						mui.toast("简历插入失败");
					}
					
				},
		error:function(error){
					console.log(JSON.stringify(error));
					mui.toast("执行错误");
				}
	});*/