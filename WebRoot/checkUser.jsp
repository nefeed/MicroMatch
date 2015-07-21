<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户审核</title>
<link href="css/checkUser.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/game.js" type="text/javascript"></script>
<script src="js/checkUser.js" type="text/javascript"></script>
</head>

<body>
<jsp:include page="actionBar.jsp"/>
<div class="subject">
	<div class="subject_left">
    	<em id="em1" class="subject_current">未审核</em><em id="em2">已审核</em>
    </div>
    <div class="subject_right">
    	<table id = "uncheck" width="100%" border="0" cellspacing="0" cellpadding="5" >
              <tr>
                <th scope="col">用户昵称 </th>
                <th scope="col" class = "regtime" >注册时间</th>
                <th scope="col">联系方式</th>
                <th scope="col">用户类型</th>
                <th scope="col">用户审核</th>
                <th class="right_border" scope="col">操作</th>
              </tr>
        </table>
		<table style="display: none" id = "checked" width="100%" border="0" cellspacing="0" cellpadding="5" >
			<tr>
                <th scope="col">用户昵称 </th>
                <th scope="col" class = "regtime" >注册时间</th>
                <th scope="col">联系方式</th>
                <th scope="col">用户类型</th>
                <th scope="col">用户审核</th>
                <th class="right_border" scope="col">操作</th>
            </tr>
		</table>
    </div>
</div>
<jsp:include page="bottom.jsp"/>
</body>
</html>
