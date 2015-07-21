<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<base href="<%=basePath%>">

	<title>博古科技底栏</title>
	<link type="text/css" href="css/bottom.css" rel="stylesheet" />
</head>

<body>
	<div class="bottom">
		<span>技术支持: 杭州博古科技有限公司</span>
		<span>电话：0571-89716094</span>
		<span>邮箱：bogusoft@163.com</span>
		<span>网址：http://www.boguedu.com.cn</span>
	</div>
</body>
</html>
