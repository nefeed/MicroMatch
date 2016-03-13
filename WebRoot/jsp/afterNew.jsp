<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String ObjectNum = request.getParameter("ObjectNum");
%>
<%
	Integer ObjectType = Integer.parseInt(request
			.getParameter("ObjectType"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<base href="<%=basePath%>" />
<title>请选择其余功能</title>

<link rel="stylesheet" type="text/css" href="js/uploadify/uploadify.css" />
<link rel="stylesheet" type="text/css" href="css/fileTitle.css" />
<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
<script type="text/javascript" src="js/afterNew.js"></script>
<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
<script type="text/javascript">
	var ObjectNum =
<%="'" + ObjectNum + "'"%>
	;
	var ObjectType =
<%=ObjectType%>
	;
</script>
</head>

<body>
	<jsp:include page="/actionBar.jsp" />
	<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog"
		data-backdrop="static" aria-labelledby="myModalLabel"
		aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="modalClose" type="button" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title" id="myModalLabel">上传附件</h4>
				</div>
				<div class="modal-body">
					<div align="center" style="margin:230px auto;">
						<div id="fileQueue"></div>
						<input type="file" name="uploadify" id="uploadify" />
						<div class="btn-group btn-group-sm" role="group"
							style="width:140px ;">
							<button type="button" class="btn btn-primary"
								onclick="$('#uploadify').uploadify('upload')">开始上传</button>
							<button type="button" class="btn btn-danger"
								onclick="$('#uploadify').uplaodify('cancel','*')">取消上传</button>
						</div>
					</div>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal fade -->
	<script type="text/javascript">
		$('#uploadify').uploadify({  
	        'auto'           : false,  
	        'swf'            : '/micromatch/js/uploadify/uploadify.swf',  
	        'uploader'       : '/micromatch/servlet/UploadServlet',
	        'queueID'        : 'fileQueue',
	        'folder'         : 'Accessory',
	        'cancelImg'      : '/micromatch/js/uploadify/uploadify-cancel.png',
	        'queueSizeLimit' : 1,  
	        'fileTypeDesc' : 'doc.ppt.pdf.xls',
			'fileTypeExts' : '*.doc;*.ppt;*.pdf;*.xls',
			'multi' : false,
			'buttonText' : '本地文件',
			'onUploadSuccess' : function(file, data, response) {
				$('#fileName').val(file.name);
				$('#fileAddress').val('Accessory/' + data);
				$('.vedio-name').html('您上传上一份的附件为：' + file.name);
				$('.vedio-name').show();
			    saveAccessory(fileName, fileAddress);
			},
			'onUploadError' : function(file, errorCode,
					errorMsg, errorString) {
				alert('The file ' + file.name + ' could not be uploaded: '
					+ errorString);
			},
		});
	</script>
	<div class="vedio-name" style="display: none ;">视频1</div>
	<div id="moreOption" class="btn-group  btn-group-lg" role="group"
		align="center" style="margin: 20% 37% ;width: 400px ;  ">
		<button style="width: 200px ; font-family: '微软雅黑' ;" type="button"
			class="btn btn-lg btn-info" id="newChapterBtn"
			onclick="loadnewChapter()">新建章节</button>
		<button style="width: 200px ; font-family: '微软雅黑' ;" type="button"
			class="btn btn-lg btn-primary" data-toggle="modal"
			data-target="#myModal">上传附件</button>
	</div>
	<div style="display: none ;">
		<a>附件名称：</a><input id="fileName" type="text" readonly="readonly" /> <a>附件地址：</a><input
			id="fileAddress" type="text" readonly="readonly" />
	</div>
	<jsp:include page="/bottom.jsp" />
</body>
</html>
