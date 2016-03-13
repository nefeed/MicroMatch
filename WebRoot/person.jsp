<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String TUserNum = "" ;
	try {
		TUserNum = request.getParameter( "UserNum" ) ;
	}catch(Exception e) {
		TUserNum = "" ;
	}
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>" />
	<title>个人中心</title>
	<link type="text/css" rel="stylesheet" href="css/person.css" />
	<link type="text/css" rel="stylesheet" href="css/laypage.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript" src="js/person.js" ></script>
	<script type="text/javascript" src="js/laypage.js"></script>
	<script type="text/javascript" src="js/mypj.js"></script>
	<script type="text/javascript">
		var TUserNum = <%="'"+TUserNum+"'"%> ;
	</script>
	<script type="text/javascript" src="http://open.web.meitu.com/sources/xiuxiu.js" ></script>
	<script type="text/javascript">
		window.onload=function() {
		    /*第1个参数是加载编辑器div容器id，第2个参数是编辑器类型，第3个参数是div容器宽，第4个参数是div容器高*/
			xiuxiu.embedSWF("altContent",5,"93%","100%");
		    //修改为您自己的图片上传接口
			xiuxiu.setUploadURL("/micromatch/servlet/uploadUserPicServlet");
			xiuxiu.setUploadType(2);
			xiuxiu.setUploadDataFieldName("userpic_file");
			xiuxiu.onInit = function () {
				xiuxiu.loadPhoto("");
			};
			xiuxiu.onUploadResponse = function (data) {
				userPic = decodeURI(data) ;
				userPic = userPic.replace(/ /, '').replace( /\r/,'' ).replace( /\n/,'' ).replace( /\t/, '') ;
				$('#fileAddress').val(userPic) ;
				updateUserPic( userPic ) ;
			};
		};
	</script>
</head>

<body>
	<jsp:include page="/actionBar.jsp"/>
<div class="subject">
	<div class="subject_left">
    	<div class="subject_1">
        	<img id = "emUserPicture" src="img/course_29.png" />
            <em><a id = "emNickName" >NickName</a><br /><input style = "display: none ;" type="button" /></em>
        </div>
        <ul class="subject_2">
        	<li id="geren" class="subject_current"><a href="javascript:">个人中心</a></li><li id="picSettings"><a href="javascript:" >头像设置</a></li><li id="person_Logout" onclick = "logOut()" ><a  href="javascript:">一键注销</a></li>
        </ul>	
    </div>
    <div id = "mainBody" class="subject_right right1">
    	<div class="subject_r_1">
        	<em id="em1" class="em_current">我的订阅</em><em id="em3" style="display:none;">我的比赛</em><em id="em2" style="display:none;" >我的课程</em>
        </div>
        <!-- 我的订阅列表 -->
    	<ul id="ul1">
        </ul>
        <!-- 我的比赛列表 -->
        <ul id="ul3" style="display:none;">
        </ul>
        <!-- 我的课程列表 -->
        <ul id="ul2" style="display:none;">
        </ul>
        <div id="page" style="text-align:center;margin-top:15px;"></div>
    </div>
    <div id = "upPicBody" class = "subject_right right2">
    	<div id="altContent">
			<h1>美图秀秀</h1>
		</div>
    </div>
    <div style="display: none;">
    	<a>头像地址：</a><input id = "fileAddress" type = "text" readonly="readonly" />
    </div>
</div>
<jsp:include page="/bottom.jsp"/>
</body>
</html>
