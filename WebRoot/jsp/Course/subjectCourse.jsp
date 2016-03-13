<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String SubName = "" ;
	try {
		SubName = request.getParameter( "SubName" ) ;
	}catch(Exception e) {
		e.printStackTrace() ;
		SubName = null ;
	}
 %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <base href="<%=basePath%>" />
    <title>本学科所有课程</title>
	<link type="text/css" rel="stylesheet" href="css/index.css"/>
	<link type="text/css" rel="stylesheet" href="css/laypage.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script> 
	<script type="text/javascript" src="js/Course/subjectCourse.js" ></script>
	<script type="text/javascript" src="js/laypage.js"></script>
	<script type="text/javascript" src="js/mypj.js"></script>
	<script type="text/javascript">
		var SubName = <%="'"+SubName+"'"%> ;
	</script>
  </head>
  
  <body>
  	<jsp:include page="/actionBar.jsp"/>
    <div class="talk">
		<div class="talk_1" id = "subjectItself">
    		本学科所有课程
    	</div>
    </div>
    <div align = "center" id = "withoutResult" style="position:fixd;">
    </div>
    <div class="main">
    	<div class="main_2">
        	<div class="main_21">
            	<ul class="ul1" id = "ul1" >
            	</ul>
        	</div>
    	</div>
    	<div class = "clear" ></div>
    </div>
    <div id="page" style="text-align:center;"></div>
	<jsp:include page="/bottom.jsp"/>
  </body>
</html>
