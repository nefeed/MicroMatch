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
    
    <title>查看我创建的课程</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src = "js/jquery.js"></script>
	<script type="text/javascript" src = "js/Course/ShowMyCourses.js"></script>
	<style type="text/css">
		table tr td{ border-top:#00CCFF solid 1px; text-align:center; }
	</style>


  </head>
  
  <body>
    <div align="center">
    	<label>These are your Courses.</label>
    </div>
    <table align="center" id = "ShowCoursesTable">
    	<tr>
    		<td><label>CourseName</label></td><td><label>CourseContent</label></td><td><label>SubjectName</label></td><td><label>AudienceNum</label></td><td><label>CreateTime</label></td>
    	</tr>
    </table>
    <div align="center">
    	<button onclick="CreateCourse()">CreateCourse</button><button onclick="Back()">Back</button>
    </div>
    <div align = "center">
    	<button onclick="Back()">Back</button>
    </div>
  </body>
</html>
