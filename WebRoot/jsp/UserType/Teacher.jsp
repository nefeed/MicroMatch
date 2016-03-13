<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>" />
    
    <title>教师个人界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/UserType/Teacher.js"></script>

	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  
  	<div align = "center">
  		<label>Welcome To Teacher Page!</label>
  	</div>
  	<table align = "center">
  		<tr>
  			<td><button onclick="CreateCourse()">Create My Course</button></td>
  			<td><button onclick="ShowMyCourses()">Show My Courses</button></td>
  		</tr>
  	</table>
  	<div align = "center">
  		<button onclick="Home()">Home</button>  	
  	</div>
  	<div align = "center" id = "TBody">
  	</div>
  </body>
</html>
