
mui.ready(function(){
	
});

mui.plusReady(function(){
	document.getElementById("userImg").addEventListener('tap',function(){
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
	});
	
	
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
								console.log(path);
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
		mainImg.src="imgPath";
	}
});

mui.init();