<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String ObjectNum = request.getParameter("ObjectNum"); %>
<%String ObjectType = request.getParameter("ObjectType"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>" />
    
    <title>Comment Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="js/jquery.js"></script> 
	<script type="text/javascript" src="js/Comment/ShowComment.js"></script>
	<script type="text/javascript">
      var ObjectNum = <%="'"+ObjectNum+"'"%> ;
      var ObjectType = <%="'"+ObjectType+"'"%> ;
    </script>
    <style type="text/css">
		table tr td{ border-top:#f2a59d solid 1px; text-align:center; }
	</style>
  </head>
  
  <body>
  	<div align = "center" >
  		<label>Comment Lists</label>
  	</div>
    <table align="center" id = "CommentTable" border="1">
    	<tr>
    		<td><label>CommentTime</label></td><td>NickName</td><td>CommentContent</td>
    	</tr>
    </table>
    <div align="center" style="margin:20px">
    	<textarea rows = "3" id = "MyCommentContent" ></textarea>
    </div>
    <div align = "center" >
    	<button onclick="PublishComment()" >Publish Comment</button>
    </div>
  </body>
</html>
