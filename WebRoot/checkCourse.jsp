<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>课程审核</title>
	<link href="css/checkUser.css" rel="stylesheet" type="text/css" />
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/game.js"></script>
	<script type="text/javascript" src="js/checkCourse.js"></script>
</head>

<body>
<jsp:include page="/actionBar.jsp"/>
<div class="subject">
	<div class="subject_left">
    	<em id="em1" class="subject_current" >未审核</em><em id="em2" >已审核</em>
    </div>
    <div class="subject_right">
    	<table id = "uncheck" width="100%" border="0" cellspacing="0" cellpadding="5" >
              <tr>
                <th scope="col">课程名称 </th>
                <th scope="col" class = "regtime" >创建时间</th>
                <th scope="col">课改概述</th>
                <th scope="col">学科分类</th>
                <th scope="col">教师姓名</th>
                <th class="right_border" scope="col">操作</th>
              </tr>
        </table>
		<table style = "display: none " id = "checked" width="100%" border="0" cellspacing="0" cellpadding="5" >
			<tr>
                <th scope="col">课程名称 </th>
                <th scope="col" class = "regtime" >创建时间</th>
                <th scope="col">课改概述</th>
                <th scope="col">学科分类</th>
                <th scope="col">教师姓名</th>
                <th class="right_border" scope="col">操作</th>
              </tr>
		</table>
    </div>
</div>
<jsp:include page="/bottom.jsp"/>
</body>
</html>
