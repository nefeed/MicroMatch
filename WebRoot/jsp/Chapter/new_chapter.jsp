<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String CourseNum = request.getParameter("CourseNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>" />
	<title>新建章节</title>
	<link rel="stylesheet" type="text/css" href="css/new_game.css" />
	<link rel="stylesheet" type="text/css" href="js/uploadify/uploadify.css" />
	<link rel="stylesheet" type="text/css" href="css/modal.css" />
	<link rel="stylesheet" type="text/css" href="css/fileTitle.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/Chapter/new_chapter.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
    <script type="text/javascript">
      var CourseNum = <%="'"+CourseNum+"'"%> ;
    </script>

</head>

<body>
	<jsp:include page="/actionBar.jsp"/>
	<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>

	<div id="myModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true" style = "left:32%; width:600px; ">
		<div class="modal-dialog">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			 上传附件
            		</h4>
            	</div>
				<div class = "modal-body">
					<div align = "center" style = "margin-top:230px ;" >
						<div id="fileQueue"></div>  
				    	<input type="file" name="uploadify" id="uploadify" />  
				    	<div class="btn-group btn-group-sm" role="group"> 
    						<button type="button" class="btn btn-primary" onclick="$('#uploadify').uploadify('upload')">开始上传</button>
    						<button type="button" class="btn btn-danger" onclick="$('#uploadify').uplaodify('cancel','*')">取消上传</button>
   						</div>
					</div>
				</div>
				<div class="modal-footer">
        			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
      			</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<script type="text/javascript">
		$('#uploadify').uploadify({  
	        'auto'           : false,  
	        'swf'            : '/micromatch/js/uploadify/uploadify.swf',  
	        'uploader'       : '/micromatch/servlet/UploadVideoServlet',
	        'queueID'        : 'fileQueue',
	        'folder'         : 'CuSunPlayer/ChapterVideo',
	        'cancelImg'      : '/micromatch/js/uploadify/uploadify-cancel.png',
	        'queueSizeLimit' : 1,  
	        'fileTypeDesc'   : '只支持flv和mp4格式的视频',  
	        'fileTypeExts'   : '*.flv;*.mp4',
	        'multi'          : false,  
	        'buttonText'     : '本地文件',
			'onUploadSuccess' : function(file, data, response) {
				$('#videoName').val(file.name) ;
				$('#videoAddress').val('Chaptervideo/' + data) ;
				$('#myModal').modal('hide') ;
				$('#myModal').hide() ;
				$('#blockInit').hide() ;
				$('#blockInit').attr( 'style', 'display: none;' ) ;
				$('.vedio-name').html(file.name) ;
				$('.vedio-name').show() ;
			},
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	    		alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	   		},
		});
	</script>
	<div class="subject">
		<label><em>章节名称：</em><input id = "newChapterName" class="subject_text" type="text" /></label>
	    <label>
	    	<em>章节排序：</em>
	    	<select id = "listSelect" class = "SelectL" >
	    		<option id = "newListIndex" value=0 selected="selected">新的章节</option>
	    	</select>
	    </label>
	    <label>
	    	<em>章节概述：</em>
			<div class="lable_right">
	        	<div class="face">
	            	<input style = "display:none ;" class="label_button" type="button" />
	                <textarea id = "newChapterContent"></textarea>
	            </div>
	        </div>
	    </label>
	    <div align = "center" style = "display: none ;" >
	    	<input id = "videoName" type = "text" readonly="readonly" />
	    	<input id = "videoAddress" type = "text" readonly="readonly" />
	    </div>
	    <div class = "vedio-name" style = "display: none ;">视频1</div>
		<div class="subject_3">
			<input type="button" class = "btn btn-primary" value="上传视频" data-toggle="modal" data-target="#myModal" />
		</div>
		<div class="pic_button">
	    	<input type="button" value="确定" class="confirm_btn" onclick="confirmSaveChapter()" />
            <input type="button" value="取消" class="cancel_btn" onclick="backToHome()"/>
	    </div>
	    <div class = "clear"></div>
	</div>
	<jsp:include page="/bottom.jsp"/>
</body>
</html>
