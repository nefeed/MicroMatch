<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Show Unchecked Chapters</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/Chapter/ShowUncheckedChapters.js"></script>

  </head>
  
  <body>
    <div align="center">
    	<label>These are the Chapters Of Selected Course.</label>
    </div>
    <table align="center" id = "ShowChaptersTable">
    	<tr>
    		<td><label>ChapterName</label></td><td><label>ChapterContent</label></td><td><label>Operation</label></td>
    	</tr>
    </table>
    <div align="center">
    	<button onclick="AddChapter()">Add Chapter</button><button onclick="Back()">Back</button>
    </div>
  </body>
</html>
