<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>学校用户界面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/UserType/College.js"></script>

  </head>
  
  <body>
    <div align="center" >
    	<label>Welcome to College Page</label>
    </div>
    <table align = "center">
    	<tr>
    		<td><button onclick = "PublishMatch()">Publish Match</button></td><td><button onclick = "ShowMyMatches()">Show My Matches</button></td>
    	</tr>
    </table>
    <div align = "center">
    	<button onclick = "Home()">Home</button>
    </div>
    <div align = "center" id = "CBody">
  	</div>
  </body>
</html>
