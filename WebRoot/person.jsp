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
    	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	    <div class="select_pic">
            <span class="sl-custom-file">
	    		<input type="button" value="选择图片" class="select_pic_btn" />
    			<input type="file" id="file_upload" name = "file" class="ui-input-file" />
	    	</span>
            <a class="select_pic_note">仅支持JPG、GIF、PNG常见图片类型,且文件小于5M</a>
        </div>
	    <div class="pic">
	    	<img id = "newPic" class = "newPic"/>
	    </div>
	   	<div class="pic_button">
	    	<input class="submitBTN" onclick="updateUserPic()" type="submit" value="" />
	    </div>
    </div>
    <div style="display: none;">
    	<a>头像地址：</a><input id = "fileAddress" type = "text" readonly="readonly" />
    </div>
</div>
<jsp:include page="/bottom.jsp"/>
</body>
</html>
