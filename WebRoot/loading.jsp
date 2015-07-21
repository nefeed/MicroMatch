<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String LoginUserNum = "" ;
	String LoginNickName = "" ;
	String LoginUserType = "" ;
	try {
		LoginUserNum = session.getAttribute( "UserNum" ).toString() ;
		LoginNickName = session.getAttribute( "NickName" ).toString() ;
		LoginUserType = session.getAttribute("UserType").toString() ;
	}catch(Exception e) {
		e.printStackTrace() ;
		System.out.println("用户还没有登录！") ;
		LoginUserNum = null ;
		LoginNickName = null ;
		LoginUserType = null ;
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>loading</title>
<link href="css/loading.css" rel="stylesheet" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/loading.js" type="text/javascript"></script>
	<script type="text/javascript">
		var LoginUserNum = <%="'"+LoginUserNum+"'"%> ;
		var LoginNickName = <%="'"+LoginNickName+"'"%> ;
		var LoginUserType = <%="'"+LoginUserType+"'"%>;
	</script>
</head>

<body>
<div class="header">
  	<ul class="ul" style="margin-right:1%;">
    	<li class="li"><img id = "wkUserPicture" src="img/loading_2.png" /><a id = "wkNickName" >曾小贤</a></li><li class="li2"><a href="#"><img style="margin:4px 10px;" src="img/loading_7.png" /></a></li>
    </ul>
</div>
<div class="subject">
    <a style=" display:block; float:left;" href="checkCourse.jsp" id="aaa"><img src="img/loading_03.png" /><div>课程审核</div></a>
    <a style="display:block; float:left;" href="checkChapter.jsp" id="aaa2"><img src="img/loading_05.png" /><div>章节审核</div></a>
    <a style="display:block; float:left;" href="checkMatch.jsp" id="aaa3"><img src="img/loading_07.png" /><div>比赛审核</div></a>
    <a style="display:block; float:left;" href="checkUser.jsp" id="aaa4"><img src="img/loading_09.png" /><div>用户审核</div></a>
</div>
</body>
</html>
