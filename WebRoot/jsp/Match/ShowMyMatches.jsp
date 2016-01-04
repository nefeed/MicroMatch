<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>遍历用户创建的比赛</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style type="text/css">
		table tr td{ border-top:#00CCFF solid 1px; text-align:center; }
	</style>
  </head>
  
  <body>
    <div align="center">
    	<label>These are your Matches.</label>
    </div>
    <table align="center" id = "ShowMatchesTable">
    	<tr>
    		<td><label>MatchName</label></td><td><label>MatchContent</label></td><td><label>StartTime</label></td><td><label>EndTime</label></td><td><label>Register</label></td>
    	</tr>
    </table>
    <div align="center">
    	<button onclick="PublishMatch()">PublishMatch</button><button onclick="Back()">Back</button>
    </div>
    
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/Match/ShowMyMatches.js"></script>
  </body>
</html>
