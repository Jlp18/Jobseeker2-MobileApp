# 目录结构

    ├──── layout                        // 编辑器样式目录

    ├── webuploader.min.js                // WebUploader上传插件，当使用图片上传时需引入。

    ├── jquery.min.js                     // Eleditor依赖jQuery或者Zepto，必须引入才能工作

    ├── Eleditor.js                       // 开发版本
    ├── Eleditor.min.js                   // 压缩版本

    └── upload.json                       // 上传示例文件，可删除

# 调用示例

    <div id="articleEditor"></div>

    <script>
    var Edr = new Eleditor({
                el: '#articleEditor', //容器
                upload:{ //上传配置
                   server: '/upload.json', //上传路径
                   compress: true, //上传前是否压缩图片
                   fileSizeLimit: 2 //限制图片上传大小，单位M
                },
        });

    //请记住下面常用方法---------------------------------------->
    //Edr.append( str ); 往编辑器追加内容
    //Edr.getContent(); 获取编辑器内容
    //Edr.getContentText(); 获取编辑器纯文本
    //Edr.destory(); 移除编辑器
    </script>

# 扩展示例
    <div id="articleEditor"></div>

    <script>
    var Ed = new Eleditor({
      el: '#contentEditor',
            upload:{
              server: '/upload.json',
              compress: false,
              fileSizeLimit: 2
            },
            toolbars: [
              'insertText',
              'editText',
              'insertImage',
              'insertLink',
              'deleteBefore',
              'deleteAfter',
              'insertHr',
              'deleteThis',
              'undo',
              //自定义一个按钮
              {
                  id: 'changeIndent',
                  tag: 'P', //指定P标签操作，可不填
                  name: '缩进',
                  handle: function(select, controll){
						...
				  }
              },

              //自定义按钮，该按钮只有在编辑IMG标签时才会显示
              {
                  id: 'rotateImage',
                  tag: 'IMG', //指定IMG标签操作，可不填
                  name: '反转图片',
                  handle: function(select, controll){
                    var _$ele = $(select),
                      _$controll = $(controll);
                    if( _$ele.attr('transform-rotate') != '180deg' ){
                      _$controll.html('还原图片');
                        _$ele.attr('transform-rotate', '180deg').css('transform', 'rotate(180deg)');
                    }else{
                      _$controll.html('反转图片');
                      _$ele.removeAttr('transform-rotate').css('transform', 'rotate(0)');
                    }
                  }
              },

              'cancel',
            ]
    });
    </script>