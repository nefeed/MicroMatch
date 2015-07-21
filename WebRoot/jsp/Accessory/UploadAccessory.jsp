<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%int ObjectType = Integer.parseInt( request.getParameter("ObjectType") ) ; %>
<%String ObjectNum = request.getParameter("ObjectNum"); %>
<%String UserNum = request.getParameter("UserNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Upload Accessory</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="css/uploadify.css" rel="stylesheet" type="text/css" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	<script type="text/javascript" src="js/jquery-2.1.3.min.js"></script>
	<script type="text/javascript" src="js/Accessory/UploadAccessory.js"></script>
	
    <script type="text/javascript">
      var ObjectType = <%="'"+ObjectType+"'"%> ;
      var ObjectNum = <%="'"+ObjectNum+"'"%> ;
      var UserNum = <%="'"+UserNum+"'"%> ;
    </script>
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.js"></script>
	<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
	<script type="text/javascript">
	$(function() {
      $("#file_upload").uploadify({
       'auto' : false,
       'method' : "get",
       	  'formData' : {'folder' : '/BoguAccessory','ObjectType' : ObjectType , 'ObjectNum' : ObjectNum , 'UserNum' : UserNum },
          'height' : 30,
          'swf' : '<%=basePath%>js/uploadify/uploadify.swf', 
          'uploader' : '<%=basePath%>servlet/UploadServlet', 
          'width' : 120,
          'fileTypeDesc' : 'doc',
          'fileTypeExts' : '*.doc',
          'fileSizeLimit' : '5MB',
          'buttonText' : '选择文件',
          'uploadLimit' : 5,
          'successTimeout' : 5,
          'requeueErrors' : false,
          'removeTimeout' : 10,
          'removeCompleted' : false,
          'queueSizeLimit' :10,
          'queueID'  : 'uploader_queue',
          'progressData' : 'speed',
          'onInit' : function (){},
       // 单个文件上传成功时的处理函数
          'onQueueComplete' : function(queueData) {
     $('#uploader_msg').html(queueData.uploadsSuccessful + ' files were successfully uploaded.');
    }     
      });
  });
	</script>
  </head>
  
  <body>
    <div align = "center" id = "UploadBody" style = "margin-top:230px;">
    	<div id="uploader_queue"></div>
    	<div id="uploader_msg"></div>
        <input type="file" name="file_upload" id="file_upload" />
        <p>
        <a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>&nbsp;
        <a href="javascript:$('#file_upload').uploadify('stop')">取消上传</a>
        </p>
	</div> 
	<div id = "result" align="center"></div>
  </body>
</html>
