<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String MatchNum = request.getParameter("MatchNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>比赛添加内容</title>
    
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/Match/afterNewMatch.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
    <script type="text/javascript">
      var MatchNum = <%="'"+MatchNum+"'"%> ;
    </script>

  </head>
  
  <body>
    <jsp:include page="../../actionBar.jsp"/>
    <div id="registrationBody" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true" style = "left:32%; width:600px; ">
		<div class="modal-dialog">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			参赛课程
            		</h4>
            	</div>
				<div class = "modal-body">

				</div>
        		<div class="modal-footer">
          			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          			<button type="button" class="btn btn-primary">保存</button>
        		</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
  	<div id="newAwardBody" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true" style = "left:32%; width:600px; ">
		<div class="modal-dialog">
      		<div class="modal-content">
				<div class="modal-header">
            		<button id = "modalClose" type="button" class="close" 
              			 data-dismiss="modal" aria-hidden="true">
                  		&times;
            		</button>
            		<h4 class="modal-title" id="myModalLabel">
              			新增奖项
            		</h4>
            	</div>
				<div class = "modal-body">
					<div align="center" style="margin-top:220px" > 
						奖项名称：<input id = "aName" type = "text" />
						奖项介绍：<textarea id = "aRemark" ></textarea>
					</div>
				</div>
        		<div class="modal-footer">
          			<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
          			<button type="button" class="btn btn-primary">保存</button>
        		</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
    <div id = "moreOption" class="btn-group  btn-group-lg" role="group" align="center" style = "margin: 20% auto ;  " >
			<button style = "width: 200px ; font-family: '微软雅黑' ;" type="button" class = "btn btn-lg btn-info"  data-toggle="modal" data-target="#registrationBody">参赛课程</button>
 			<button style = "width: 200px ; font-family: '微软雅黑' ;" type = "button" class = "btn btn-lg btn-primary" id = "newAwardBtn" data-toggle="modal" data-target="#newAwardBody" >新增奖项</button>
	</div>
	<div style = "display: none ;">
    	<a>附件名称：</a><input id = "fileName" type = "text" readonly="readonly" />
    	<a>附件地址：</a><input id = "fileAddress" type = "text" readonly="readonly" />
    </div>
    <jsp:include page="../../bottom.jsp"/>
  </body>
</html>
