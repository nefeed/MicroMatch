<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String CourseNum = request.getParameter("CourseNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>" />
    
    <title>Show all Matchs</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

    <script type="text/javascript">
      var CourseNum = <%="'"+CourseNum+"'"%> ;
    </script>
    <style type="text/css">
		table tr td{ border-top:#00CCFF solid 1px; text-align:center; }
	</style>

  </head>
  
  <body>
  	<div align = "center" id = "AllMatchBody">
    	<table align="center" id = "ShowMatchesTable">
    		<tr>
    			<td><label>MatchName</label></td><td><label>MatchContent</label></td><td><label>StartTime</label></td><td><label>EndTime</label></td><td><label>Register</label></td><td><label>Operation</label></td>
  	  		</tr>
    	</table>
  	</div>
	<script type="text/javascript" src="js/Match/ShowAllMatches.js"></script>
  </body>
</html>
