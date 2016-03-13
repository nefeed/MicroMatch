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
    
    <title>Sign Up</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<link rel="stylesheet" type="text/css" href="css/modal.css" />
	<script type="text/javascript" src="js/jquery.js" ></script>
	<script type="text/javascript" src="js/SignUp.js" ></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>

  </head>
  
	<body>
	<div class="btn-group btn-group-sm" role="group"> 
    	<button type="button" class="btn btn-primary" onclick="$('#uploadify').uploadify('upload')">开始上传</button>
    	<button type="button" class="btn btn-danger" onclick="$('#uploadify').uplaodify('cancel','*')">取消上传</button>
   	</div>
  	<div align = "center">
    <div>
    	<label>Welcome to BoguSoft!</label>
    </div>
    <table align = "center">
    	<tr>
    		<td><label>Account:</label></td><td><input id = "UserName" type = "text" /></td>
    	</tr>    
    	<tr>
    		<td><label>Password:</label></td><td><input id = "UserPassword" type = "password" /></td>
    	</tr>
    	<tr>
    		<td><label>Password Confirm:</label></td><td><input id = "UserPassword2" type = "password" /></td>
    	</tr>
    	<tr>
    		<td><label>NickName:</label></td><td><input id = "NickName" type = "text" /></td>
    	</tr>
    	<tr>
    		<td><label>UserType:</label></td><td><input id = "UserType" type = "text" /></td>
    	</tr>
    	
  	</table>
  	<div align = "center">
  		<a>0-admin;1-college;2-teacher;3-student</a>
  	</div>
  	<div align = "center" >
    	<button onclick = "Confirm()">Confirm</button><button onclick = "Cancle()" >Cancle</button>
    </div>
    </div>
  </body>
</html>
