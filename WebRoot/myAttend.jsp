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
	<title>我的订阅</title>
	<link href="css/myAttend.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/myAttend.js"></script>
</head>

<body>
	<jsp:include page="/actionBar.jsp"/>
	<div class="talk">
		<div class="talk_1">
    		我的订阅
    	</div>
    </div>
    <div align = "center" id = "withoutResult" style="position:fixd;">
    </div>
	<div class="main">
	    <div class="main_2">
	        <div class="main_21">
	            <ul id = "ul1" class="ul1">
	            </ul>
	        </div>
	    </div>
	</div>
	<jsp:include page="/bottom.jsp"/>
</body>
</html>
