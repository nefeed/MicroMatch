<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String LoginUserName = "" ;
	Cookie[] cookies = request.getCookies() ;
	Cookie cookieTemp = null ;
	if( cookies != null ) {
		for ( int i = 0 ; i < cookies.length ; i ++ ) {
			cookieTemp = cookies[i] ;
			if( cookieTemp.getName().equals("userName") ) {
     			LoginUserName = cookieTemp.getValue();
    		}
		}
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登陆</title>
	<link type="text/css" rel="stylesheet" href="css/login.css"/>
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script> 
	<script type="text/javascript" src="js/login.js"></script>
	<script type="text/javascript">
		var LoginUserName = <%="'"+LoginUserName+"'"%> ;
	</script>
</head>

<body>
	<div class="subject" style = "margin-top:160px ;">
		<div class="subject_left">
	    	<ul class="subject_l_1" id="denglu_3">
	        	<li id="li1" onclick = "login()" class="current">会员登录</li><li id="li2" onclick = "signup()">注册账号</li>
	        </ul>
	        <div id="denglu" >
	            <div id="form1">
	                <div class="form"><em>用户名</em><input id = "userName" type="text" placeholder="用户名" /></div>
	                <div class="form"><em>密码</em><input id = "userPassword" type="password" placeholder="密码" /><a href="#" style="display:none;" >忘记密码</a></div>
	                <div style="display:none;" >
	                	<div class="form"><em>验证码</em><input class="yanzheng" type="text" placeholder="验证码" /><img style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
	                	<div class="form" style="font-size:12px;"><em style="height:20px;"></em><input class="checkbox" style="display:none;" type="checkbox"/>7天内免登录</div>
	                </div>
	                <div class="form" style="font-size:12px;"><em></em><input class="button" type="button" value="登录" onclick="confirmLogin()" /></div>
	            </div>
	            <div id="form2" class = "formdiv">
	                <div class="form"><em>用户名</em><input id = "signUserName" type="text" placeholder="用户名" /></div>
	                <div class="form"><em>设置密码</em><input id = "signPassword" type="password" placeholder="6~15位" /></div>
	                <div class="form"><em>用户昵称</em><input id = "signNickName" type="text" placeholder="昵称" /></div>
	                <div class="form"><em>用户类型</em>
	                	<select id = "signUserType"  >
	                		<option value = "3" >学生用户</option>
	                		<option value = "2" style="display:none;" >教师用户</option>
	                		<option value = "1" style="display:none;" >学校用户</option>
	                		<option value = "0" style="display:none;" >管理用户</option>
	                	</select>
	                </div>
	                <div style="display:none;" class="form"><em>验证码</em><input style="display:none;" class="yanzheng" type="text" placeholder="验证码" /><img style="display:none;" style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="display:none;" style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
	    
	                <div class="form" style="font-size:12px;"><em></em><button onclick = "confirmSignUp()" >注册</button></div>
	            </div>
	        </div>
	    </div>
	</div>
	<jsp:include page="bottom.jsp"/>
</body>
</html>