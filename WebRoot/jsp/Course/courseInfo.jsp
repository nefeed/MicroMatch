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
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<base href="<%=basePath%>">
	<title>课程</title>
	<link type="text/css" rel="stylesheet" href="css/courseInfo.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap/bootstrap.css" />
	<link type="text/css" rel="stylesheet" href="js/uploadify/uploadify.css" />

	<script type="text/javascript">
		var CourseNum = <%="'"+CourseNum+"'"%> ;
	</script>
</head>

<body>
	<jsp:include page="../../actionBar.jsp"/>
	<script type="text/javascript" src="js/uploadify/swfobject.js" ></script>  
	<script type="text/javascript" src="js/uploadify/jquery.uploadify.js"></script>
	<div id="myModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			 上传附件
            		</h4>
            	</div>
				<div class = "modal-body">
					<div align = "center" style = "margin:230px auto;" >
						<div id="fileQueue"></div>  
    					<input type="file" name="uploadify" id="uploadify" />  
    					<div class="btn-group btn-group-sm" role="group" style = "width:140px ;"> 
    						<button type="button" class="btn btn-primary" onclick="$('#uploadify').uploadify('upload')">开始上传</button>
    						<button type="button" class="btn btn-danger" onclick="$('#uploadify').uplaodify('cancel','*')">取消上传</button>
   						</div>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<script type="text/javascript" >
		$('#uploadify').uploadify({  
	        'auto'           : false,  
	        'swf'            : '../../js/uploadify/uploadify.swf',  
	        'uploader'       : '../../servlet/UploadServlet', 
	        'queueID'        : 'fileQueue',  
	        'folder'         : 'Accessory',
	        'removeCompleted': false ,
	        'queueSizeLimit' : 1,  
	        'cancelImg'      : '../../js/uploadify/uploadify-cancel.png',
	        'fileTypeDesc'   : 'doc.ppt.pdf.xls',  
	        'fileTypeExts'   : '*.doc;*.ppt;*.pdf;*.xls',  
	        'multi'          : false,  
	        'buttonText'     : '本地文件',
			'onUploadSuccess': function(file, data, response) {
				$('#fileName').val(file.name) ;
				$('#fileAddress').val( 'Accessory/' + data ) ;
				saveAccessory( fileName , fileAddress ) ;
			},
	        'onUploadError' : function(file, errorCode, errorMsg, errorString) {
	    		alert('The file ' + file.name + ' could not be uploaded: ' + errorString);
	   		},
		});
	</script>
	<div id="MatchListBody" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true" style = "left:32%; width:600px; ">
		<div class="modal-dialog">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			曾获荣誉
            		</h4>
            	</div>
				<div class = "modal-body">
					<table class="table table-striped" id="MatchListTable">
						<tr class="info">
							<td>比赛名称</td>
							<td>比赛概述</td>
							<td>开始时间</td>
							<td>结束时间</td>
							<td>操作</td>
						</tr>
						
					</table>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<div class="content">
		<div class="content_cnt">	
		<div class="content_left">
	    	<img id = "coverPic" src="img/select_1.png" />
	    </div>
	    <div class="content_right">
	    	<h1 id = "courseName">2015最新园林工程技术精选课程</h1>
	        <div class="content_r_1">
	            <div class="content_text">
					<div>学时：<span class="content_span1" id = "period">14章</span></div>
					<div>发布：<span class="content_span1" id = "teacherNick">北京园林学院</span></div>				
					<div>学科分类：<span class="content_span1" id = "subject">园林工程</span></div>
					<div>观看人数：<span class="content_span2" id = "audienceNum">50</span></div>
					<div>发布时间：<span class="content_span1" id = "createtime">2015-10-17</span></div>
	            </div>
	            <div class="content_button">
	            	<input class="content_button1" onclick="openCourse()" type="button" value="进入课程" />
	                <input class="content_button2" id = "attendBTN" onclick="attendConfirm()" type="button" value = "订阅课程" />
	            </div>
	        </div>
	    </div>
		<div style="clear:both;"></div>
		</div>
	</div>
	<div class="main">
		<div class="main_left">
	    	<div class="main_title">
	        	<em>课程概述</em>
	        </div>
	        <p id = "courseContent">本课程为2015年最新园林工程技术精选课程！</p>
	        <div id = "optionBtnGroup" class="btn-group" role="group">
	        </div>
	        <div class="main_title main_content">
	        	<em>章节列表</em>
	        </div>
	        <div class="main_chapter">
	            <table id="chapterList" class = "chapterlTable" >
	            	
	            </table>
	        </div>
	        
	        <div class="talk">
				<div class="main_title main_content">
					<em>讨论区</em>
				</div>
				<div id = "commentBody" class="talk_2">
				</div>
				<div>
					<textarea id = "publishCommentContent" onfocus="if(value=='请输入您要评论的内容。'){value=''; style.color='#000000';}"
					 onblur="if (value ==''){style.color='#979797';value='请输入您要评论的内容。'}">请输入您要评论的内容。</textarea>
				</div>
				<button class = "commentBTN" onclick = "publishComment()">发表评论</button>
			</div>
	    </div>
	</div>
	<div style = "display: none ;">
    	<a>附件名称：</a><input id = "fileName" type = "text" readonly="readonly" />
    	<a>附件地址：</a><input id = "fileAddress" type = "text" readonly="readonly" />
    </div>
	<jsp:include page="../../bottom.jsp"/> 
	<script type="text/javascript" src="js/Course/courseInfo.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
</body>
</html>
