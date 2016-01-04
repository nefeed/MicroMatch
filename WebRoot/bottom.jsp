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

	<title>微课大赛底栏</title>
	<link type="text/css" href="css/bottom.css" rel="stylesheet" />
</head>

<body>
	<div class="bottom">
		<span>技术支持: 章华隽 GavinChangCN</span>
		<span>电话：15757856563</span>
		<span>邮箱：gavinchangcn@163.com</span>
		<span>网址：https://github.com/gavinchangcn</span>
	</div>
</body>
</html>
