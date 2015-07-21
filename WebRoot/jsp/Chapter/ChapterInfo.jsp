<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String ChapterNum = request.getParameter("ChapterNum"); %>
<%String UserNum = request.getParameter("UserNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>章节详情界面</title>
    
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
	<script type="text/javascript" src="js/Chapter/ChapterInfo.js"></script>

    <script type="text/javascript">
      var ChapterNum = <%="'"+ChapterNum+"'"%> ;
      var UserNum = <%="'"+UserNum+"'"%> ;
    </script>
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.min.js"></script>
	<script type="text/javascript" src="js/uploadify/swfobject.js"></script>
    <script type="text/javascript">
		  $(function() {
      $("#file_upload").uploadify({
       'auto' : false,
       'method' : "get",
       	  'formData' : {'folder' : '/BoguChapter','ChapterNum' : ChapterNum },
          'height' : 30,
          'swf' : '<%=basePath%>js/uploadify/uploadify.swf', 
          'uploader' : '<%=basePath%>servlet/UploadVideoServlet', 
          'width' : 120,
          'fileTypeDesc' : 'mp4或flv文件' ,
          'fileTypeExts' : '*.mp4,*.flv',
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
    <style type="text/css">
		table tr td{ border-top:#00CCFF solid 1px; text-align:center; }
	</style>
  </head>
  
  <body>
    <div align="center" >
    	<label>Chapter Information</label>
    </div>
    <table align="center" id = "ChapterInfoTable">
    	<tr>
    		<td><label>ChapterName:</label></td><td><input id="ChapterName" type="text" readonly="readonly" ></td>
    	</tr>
    	<tr>
    		<td><label>ChapterContent:</label></td><td><textarea id="ChapterContent" readonly="readonly" rows="3" ></textarea></td>
    	</tr>
    	<tr>
    		<td><label>ChapterVideo:</label></td><td><input id="ChapterVideo" type="text" readonly="readonly" ></td>
    	</tr>
    	<tr>
    		<td><label>CreateTime:</label></td><td><input id="Createtime" type="text" readonly="readonly" ></td>
    	</tr>
    </table>
    <div align = "center">
	    <button onclick="ShowVideo()">Show me Video</button>
    </div>
    <div align = "center" id = "UploadBody">
    	<div id="uploader_queue"></div>
    	<div id="uploader_msg"></div>
        <input type="file" name="file_upload" id="file_upload" />
        <p>
        <a href="javascript:$('#file_upload').uploadify('upload','*')">上传</a>&nbsp;
        <a href="javascript:$('#file_upload').uploadify('stop')">取消上传</a>
        </p>
	</div> 
	<div align = "center" id = "DownloadAccessoryBody">
	</div>
	<div align = "center" id = "AccessoryBody">
		<button onclick = "ChangeUpload()">Upload Accessory</button>
	</div>
	<div align = "center">
		<button onclick = "Back()">Back</button>
	</div>
  </body>
</html>
