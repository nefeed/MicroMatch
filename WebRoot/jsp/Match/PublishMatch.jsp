<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>比赛发布页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
  </head>
  
  <body>
    <div align="center">
    	<label>Please Input Your Course Infomation</label>
    </div>
    <table align="center">
    	<tr>
    		<td><label>Match Name:</label></td><td><input id = "MatchName" type = "text" ></input></td>
    	</tr>
    	<tr>
    		<td><label>Match Content:</label></td><td><input id = "MatchContent" type = "text" ></input></td>
    	</tr>
    	<tr>
    		<td><label>Start Time:</label></td><td><input id = "StartTime" type = "text" ></input></td>
    	</tr>
    	<tr>
    		<td><label>End Time:</label></td><td><input id = "EndTime" type = "text" ></input></td>
    	</tr>
    </table>
    <div align="center">
    	<input type = "button" onclick="PublishConfirm()" value ="Confirm"><input type = "button" onclick="Cancel()" value="Cancel">
    </div>
	<script type="text/javascript" src = "js/Match/PublishMatch.js"></script>
  </body>
</html>
