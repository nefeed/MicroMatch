<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String unsureCourseName = "" ;
	try {
		unsureCourseName = request.getParameter( "unsureCourseName" ) ;
	}catch(Exception e) {
		e.printStackTrace() ;
		System.out.println("用户还没有登录！") ;
		unsureCourseName = null ;
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>" />
    
    <title>搜索课程结果</title>
    
	<link type="text/css" rel="stylesheet" href="css/index.css" />
	<link type="text/css" rel="stylesheet" href="css/laypage.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/Course/searchCourse.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript" src="js/laypage.js"></script>
	<script type="text/javascript" src="js/mypj.js"></script>
	<script type="text/javascript">
		var unsureCourseName = <%="'"+unsureCourseName+"'"%> ;
	</script>
  </head>
  
  <body>
  	<jsp:include page="/actionBar.jsp"/>
    <div class="talk">
		<div class="talk_1">
    		搜索结果
    	</div>
    </div>
    <div align = "center" id = "withoutResult" style="position:fixd;">
    </div>
    <div class="main" >
       	<ul class="ul1" id = "ul1" >
		</ul>
	   	<div class = "clear" ></div>
    </div>
   	<div id="page" style="text-align:center;margin-top:15px;"></div>
	<jsp:include page="/bottom.jsp"/>
  </body>
</html>
