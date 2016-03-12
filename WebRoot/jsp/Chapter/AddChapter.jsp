<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String CourseNum = request.getParameter("CourseNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>章节上传界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script> 
	<script type="text/javascript" src="js/Chapter/AddChapter.js"></script>
	<script type="text/javascript">
      var CourseNum = <%="'"+CourseNum+"'"%> ;
    </script>
  </head>
  
  <body>
  	<div align = "center" id = "ChapterUseBody">
    <div align="center" >
    	<label>Add Your Chapter</label>
    </div>
	
    <table align="center" id = "CourseInfoTable">
        <tr>
			<td><label>ChapterName:</label></td><td><input id = "ChapterName" name="ChapterName" type = "text"></input></td>
    	</tr>
    	<tr>
    		<td><label>ChapterContent:</label></td><td><textarea id = "ChapterContent" name="ChapterContent" rows="3" ></textarea></td>
    	</tr>
    	<tr>
    		<td>ChapterIndex:</td><td><input id = "ListID" type = "text"></input></td>
    	</tr>
	</table>
    <div align="center">
    	<input type = "button" value="Confirm" onclick="Confirm()" /><button onclick="Cancle()">Cancle</button>
    </div>
    </div>
  </body>
</html>
