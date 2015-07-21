<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String CourseNum = request.getParameter("CourseNum"); %>
<%String ChapterId = request.getParameter("ChpterId"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>上传章节界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script> 
	<script type="text/javascript" src="js/Chapter/UploadChapterFile.js"></script>
	<script type="text/javascript">
      var CourseNum = <%="'"+CourseNum+"'"%> ;
      var ChapterId = <%="'"+ChapterId+"'"%> ;
    </script>
  </head>
  
  <body>
    <div align="center" >
    	<label>Add Your Video</label>
    </div>
    <table align="center" id = "CourseInfoTable">
    	<tr>
			<td><label>VideoFile:</label></td><td><input id = "VideoFile" type = "file"></input></td>
    	</tr>
    </table>
    <div align="center">
    	<button onclick="Confirm()">Confirm</button><button onclick="Cancel()">Cancel</button>
    </div>
  </body>
</html>
