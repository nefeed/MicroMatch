<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>登陆</title>
	<link href="css/login.css" type="text/css" rel="stylesheet" />
	<script type="text/javascript" src="js/jquery.js"></script> 
	<script src="js/login.js" type="text/javascript"></script>
</head>

<body>
	<div class="subject">
	<div class="subject_left">
    	<ul class="subject_l_1" id="denglu_3">
        	<li id="li1" class="current">会员登录</li><li id="li2">免注册登录</li>
        </ul>
        <ul class="subject_l_1" style="display:none;" id="zhuce_3">
        	<li id="li3" class="current"><em class="em1">手机号注册</em></li><li id="li4"><em class="em2">邮箱注册</em></li>
        </ul>
        <ul class="subject_l_1" style="display:none;" id="zhuce_33">
        	<li id="li5"><em class="em1">手机号注册</em></li><li class="current" id="li6"><em class="em2">邮箱注册</em></li>
        </ul>
        <div id="denglu">
            <div id="form1">
                <div class="form"><em>账号</em><input id = "userName" type="text" placeholder="邮箱/手机/用户名" /></div>
                <div class="form"><em>密码</em><input id = "userPassword" type="password" placeholder="密码" /><a href="#">忘记密码</a></div>
                <div class="form"><em>验证码</em><input class="yanzheng" type="text" placeholder="验证码" /><img style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
                <div class="form" style="font-size:12px;"><em style="height:20px;"></em><input class="checkbox" type="checkbox"/>7天内免登录</div>
                <div class="form" style="font-size:12px;"><em></em><input class="button" type="button" value="登录" onclick="confirmLogin()" /></div>
            </div>
            <div id="form2" style="display:none;">
                <div class="form"><em>手机号</em><input type="text" placeholder="手机号" /></div>
                <div class="form"><em>验证码</em><input class="yanzheng" type="text" placeholder="验证码" /><img style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
                <div class="form"><em>动态码</em><input class="yanzheng" type="text" /><input class="button1" type="button" value="免费获取动态码" style="height:37px;" /></div>
    
                <div class="form" style="font-size:12px;"><em style="height:20px;"></em><input class="checkbox" type="checkbox"/>记住手机号</div>
                <div class="form" style="font-size:12px;"><em></em><input onclick="confirmLogin()" class="button" type="button" value="登录"/></div>
            </div>
        </div>
            <div id="form3" style="display:none;">
                <div class="form"><em>手机号</em><input type="text" placeholder="手机号" /></div>
                <div class="form"><em>验证码</em><input class="yanzheng" type="text" placeholder="验证码" /><img style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
                <div class="form"><em>动态码</em><input class="yanzheng" type="text" /><input class="button1" type="button" value="免费获取动态码" style="height:37px;" /></div>
                <div class="form"><em>设置密码</em><input type="password" placeholder="6~12位" /></div>
    
                <div class="form" style="font-size:12px;"><em></em><input class="button" type="button" value="注册"/></div>
            </div>
            <div class = "formdiv" id="form4" style="display:none;">
                <div class="form"><em>邮箱</em><input id = "signUserName" type="text" placeholder="邮箱" /></div>
                <div class="form"><em>设置密码</em><input id = "signPassword" type="password" placeholder="6~12位" /></div>
                <div class="form"><em>用户昵称</em><input id = "signNickName" type="text" placeholder="昵称" /></div>
                <div class="form"><em>用户类型</em>
                	<select id = "signUserType"  >
                		<option value = "3" >学生用户</option>
                		<option value = "2" >教师用户</option>
                		<option value = "1" >学校用户</option>
                		<option value = "0" >管理用户</option>
                	</select>
                </div>
                <div class="form"><em>验证码</em><input class="yanzheng" type="text" placeholder="验证码" /><img style="float:left; margin-right:10px;" src="img/denglu_3.png" /><a style="color:#b2b2b2;" href="#">看不清楚？换一张</a></div>
    
                <div class="form" style="font-size:12px;"><em></em><button onclick = "confirmSignUp()" >注册</button></div>
            </div>
    </div>
    <div class="subject_right">
    	<!--<div class="subject_r_1" id="div1" style="display:none;">直接用博古账号登陆</div>-->
        <div id="denglu_2">
            <div class="subject_r_1">没有博古微课账号？</div>
            <div><input class="button1" type="button" onclick="phone()" value="手机号快速注册" /><input class="button2" value="邮箱注册" type="button" onclick="email()" /></div>
        </div>
        <div id="zhuce_2" style="display:none;">
            <div class="subject_r_1" id="div1">已有博古帐号？</div>
            <div><input class="button2" value="立即登录" onclick="denglu()" type="button" /></div>
        </div>
        <div class="subject_r_2">用第三方账号<a href="#">直接登录</a>，无需注册</div>
        <div class="subject_r_3"><a href="#"><img src="img/denglu_4.png" /></a></div>
        <div class="subject_r_3"><a href="#"><img src="img/denglu_5.png" /></a></div>
        <div class="subject_r_3"><a href="#"><img src="img/denglu_6.png" /></a></div>
    </div>
</div>
<div class="last">
	<span>技术支持: 杭州博古科技有限公司</span>      <span>电话：0571-89716094</span>      <span>邮箱：bogusoft@163.com</span>      <span>网址：http://www.boguedu.com</span>
</div>
</body>
</html>