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
	<title>新建比赛</title>
	<link type="text/css" rel="stylesheet" href="css/new_game.css" />
</head>

<body>
	<jsp:include page="/actionBar.jsp"/>
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
				<div class = "modal-body"  style = "height:560px ;">
					<div id="altContent">
						<h1>美图秀秀</h1>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<div class="subject">
		<label><em>比赛名称：</em><input id = "matchName" class="subject_text" type="text" /></label>
	    <label>
	    	<em>开始时间：</em>
			<input id = "startInput" class="Wdate subject_text" onClick="WdatePicker()" />
	    </label>
	    <label>
	    	<em>截止时间：</em>
	    	<input id = "endInput" class="Wdate subject_text" onClick="WdatePicker()" />
	    </label>
	    <label>
	    	<em>比赛概述：</em>
	        <div class="lable_right" >
	        	<div class="face">
	            	<input class="label_button" type="button" style = "display: none ;"/>
	                <textarea id = "matchContent" ></textarea>
	            </div>
	        </div>
	    </label>
	    <div class="select_pic">
	    	<span class="sl-custom-file">
	    		<input type="button" value="选择图片" class="select_pic_btn" />
    		<input type="file" id="file_upload" name = "file" class="ui-input-file" />
	    	</span>
            <a class="select_pic_note">仅支持JPG、GIF、PNG常见图片类型,且文件小于5M</a>
        </div>
		<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	    <div class="pic">
	    	<img id = "newPic" class = "newPic"/>
	    	<span class="sl-custom-file">
	    		<input type="button" class="subject_upbutton" />
    			<input type="file" id = "pic_upbutton" name = "file" class="ui-input-file" />
	    	</span>
	    </div>
	    <div class="pic_button">
	    	<input type="button" value="确定" class="confirm_btn" onclick="saveMatch()" />
            <input type="button" value="取消" class="cancel_btn" onclick="backToHome()"/>
	    </div>
	    <div class = "clear"></div>
	</div>
	<jsp:include page="/bottom.jsp"/>
	<script type="text/javascript" src="js/Match/new_match.js" ></script>
	<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script src="http://open.web.meitu.com/sources/xiuxiu.js" type="text/javascript"></script>
</body>
</html>
