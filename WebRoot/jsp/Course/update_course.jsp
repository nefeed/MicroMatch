<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String CourseNum = "" ;
	try {
		CourseNum = request.getParameter( "CourseNum" ) ;
	}catch(Exception e) {
		e.printStackTrace() ;
		CourseNum = "" ;
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改课程信息</title>
	<link type="text/css" href="css/new_game.css" rel="stylesheet" />
	<script src="js/jquery-2.1.4.min.js" type="text/javascript"></script>
	<script src="js/Course/update_course.js" type="text/javascript"></script>
	<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
	<script src="js/bootstrap/bootstrap.js" type="text/javascript"></script>
	<script type="text/javascript">
		var CourseNum = <%="'"+CourseNum+"'"%> ;
	</script>
</head>
<body>
	<jsp:include page="../../actionBar.jsp"/>
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-lg">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			 上传课程图片
            		</h4>
            	</div>
				<div class = "modal-body" style = "height:560px ;">
					<div id="altContent">
						<h1>美图秀秀</h1>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<div class="subject">
		<label><em>课程名称：</em><input id = "newCourseName" class="subject_text" type="text" /></label>
	    <label>
	    	<em>学科分类：</em>
	    	<select id = "subSelect" class = "SelectL1" >
	    	</select>
	    	<select id = "childSubSelect" class = "SelectL2" >
	    	</select>
	    </label>
	    <label>
	    	<em>课程概述：</em>
			<div class="lable_right" >
	        	<div class="face">
	            	<input style = "display:none ;" class="label_button" type="button" />
	                <textarea id = "newCourseContent"></textarea>
	            </div>
	        </div>
	    </label>
	    <div class="pic">
	    	<img id = "newPic" class = "newPic" />
	    	<img data-toggle="modal" data-target="#myModal" id = "pic_upbutton" src = "img/new_game_4.png" type = "button" class="subject_upbutton" />
	    </div>
	    
	    <div class="pic_button">
	    	<input data-toggle="modal" data-target="#myModal" id="file_upload" class="subject_button" type="button" value="选择图片" />
	    	<input class="submitBTN" onclick="confirmUpdateCourse()" type="submit" value="" />
	    </div>
	    <div class = "clear"></div>
	</div>
	<jsp:include page="../../bottom.jsp"/>
</body>
</html>
