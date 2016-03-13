<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>" />
	<title>后台登陆页</title>
	<link href="css/login2.css" rel="stylesheet" type="text/css" />
	<script src="js/jquery.js" type="text/javascript"></script>
	<script src="js/login2.js" type="text/javascript"></script>
</head>

<body>
	<div class="bg">
	    <div class="header">
	        <img src="img/denglu_logo.png" />
	        <h1>登录博古微课</h1>
	    </div>
	    <div class="form" style=" overflow:hidden; " >
			<input id = "wkid" type="text" class="text"  value="微课ID" onblur="if(!this.value.length){this.style.color='#979797';this.value='微课ID';}" onclick="if(this.value=='微课ID'){this.style.color='#979797';this.value='';}" />
	        <div style="overflow:hidden;">
	          	<input id = "wkkey" type="text" class="password" value="密码" onfocus="if(this.value==defaultValue) {this.value='';this.type='password'}" onblur="if(!value) {value=defaultValue; this.type='text';}" style="color:#a6aca9; " />
	          	<input class="button" type="button" onclick = "adminLogin()"></input>
	        </div>
	        <label style="margin:0 auto; display:block; width:170px; padding:20px 0; color:#fefefe;" for="remember-me">
	        <input class="checkbox" type="checkbox" />保持我的登录状态</label>
	    </div>
	</div>
</body>
</html>
