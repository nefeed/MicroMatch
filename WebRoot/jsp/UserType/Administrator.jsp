<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Administra MainPage</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/UserType/Administrator.js"></script>
	<style type="text/css">
		table tr td{ border-top:#FF0000 solid 1px; text-align:center; }
	</style>
  </head>
  
  <body>
    <div align = "center">
  	 	<label>Welcome To Administrator Main Page!</label>
  	</div>
  	<div align = "center">
  		<button onclick = "ShowUncheckedMatches()">Show Unchecked Matches</button>
  		<button onclick = "ShowUncheckedCourses()">Show Unchecked Courses</button>
  		<button onclick = "ShowUncheckedChapters()">Show Unchecked Chapters</button>
  		<button onclick = "ShowAllUser()">Show All User</button>
  	</div> 
  	<div align = "center" id ="ABody" >
	  	<div align = "center">
	  		<button onclick = "PublishMatch()">Publish Match</button>
	  	</div>
    </div>
  	<div align = "center">
  			<button onclick="Home()">Home</button>  	
  	</div>
  </body>
</html>
