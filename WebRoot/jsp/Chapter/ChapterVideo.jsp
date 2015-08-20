<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%String ChapterNum = request.getParameter("ChapterNum"); %>
<%String UserNum = request.getParameter("UserNum"); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>Chapter Video Page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="Images/common.css">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
      var ChapterNum = <%="'"+ChapterNum+"'"%> ;
      var UserNum = <%="'"+UserNum+"'"%> ;
    </script>
	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript" src="js/Chapter/ChapterVideo.js"></script>
  	<SCRIPT LANGUAGE=JavaScript>
		<!--
		function getLight(pars)
		{	
			//alert("播放器模式参数值："+string+"");
			if(pars == "open")
			{
				close_light(this);
			}
			else
			{
				close_light(this);
			}
		}
		
		//兼容性
		function thisMovie(movieName) {
		    if (navigator.appName.indexOf("Microsoft") != -1) {
		        return window[movieName]
		    }
		    else {
		        return document[movieName]
		    }
		}
		//-->
	</SCRIPT> 
  </head>
  
  <body>
    <div align = "center">
    	<h1>Chapter Video</h1>
    </div>
    <DIV class=close_light_bg id=close_light_bg></DIV>
		<!--myplayer/begin-->
		<div id="myplayer">
		  <!--酷播迷你 CuPlayerMiniV3.0 代码开始-->
		  <script type="text/javascript" src="Images/swfobject.js"></script>
		  <div class="video" id="CuPlayer"> <strong>酷播迷你(CuPlayerMiniV3.0) 提示：您的Flash Player版本过低，请<a href="http://www.CuPlayer.com/" >点此进行播放器升级</a>！</strong> </div>
		  <div class="list">
		    <ul class="mylist" style="height:150px;width:600px;overflow-x:hidden;overflow-y:auto" >
		      <li><a href="#"  onclick="changeStream(0);">1 Hello,Man.</a></li>
		      <li><a href="#"  onclick="changeStream(1);">2 How are you?</a></li>
			  <li><a href="#"  onclick="changeStream(2);">3 I`m fine,Thanks.And you?</a></li>
			  <li><a href="#"  onclick="changeStream(3);">4 Me too.</a></li>
			  <li><a href="#"  onclick="changeStream(4);">5 Have a good day,Guy.</a></li>
		    </ul>
		  </div>
		  
		<script type=text/javascript>
		<!--
		//酷播迷你：官方连播代码示例//
		var CuPlayerList ="<%=basePath%>/BoguChapter/baozou.mp4|<%=basePath%>/BoguChapter/baozou.mp4|<%=basePath%>/BoguChapter/baozou.mp4|<%=basePath%>/BoguChapter/baozou.mp4|<%=basePath%>/BoguChapter/baozou.mp4";
		var sp =CuPlayerList.split("|");
		var num = sp.length;
		var video_index = 0;
		function getNext(pars)
		{	
		  if(video_index < num-1)
			{
				video_index++;
				so.addVariable("JcScpVideoPath",sp[video_index]);
				so.write("CuPlayer");	
			}
			else
			{
			video_index = 0;
			so.addVariable("JcScpVideoPath",sp[video_index]);
			so.write("CuPlayer");	
			}
		}
		function changeStream(CuPlayerFile){
		so.addVariable("JcScpVideoPath",sp[CuPlayerFile]);
		so.write("CuPlayer");	
		}
		
		CuPlayerFile =sp[video_index];
		var so = new SWFObject("CuSunPlayer/player.swf","ply","600","410","9","#000000");
		so.addParam("allowfullscreen","true");
		so.addParam("allowscriptaccess","always");
		so.addParam("wmode","opaque");
		so.addParam("quality","high");
		so.addParam("salign","lt");
		//播放器设置文件-----------------------------
		so.addVariable("JcScpFile","CuSunPlayer/CuSunV2set.xml");
		//视频文件及略缩图--------------------------
		so.addVariable("JcScpVideoPath",CuPlayerFile);
		so.addVariable("JcScpImg","Images/flashChangfa2.jpg"); 
		so.addVariable("JcScpSharetitle","标题信息"); 
		so.write("CuPlayer");
		
		//-->
		</script>
		  <!--酷播迷你 CuPlayerMiniV3.0 代码结束-->
		</div>
		<!--myplayer/end-->
	
		<SCRIPT language=javascript src="Images/action.js" type=text/javascript></SCRIPT>
		<!--极酷播放器/代码结束-->
		
		<div id="title">
		<h2>列表自动连播[显示列表]</h2>
		</div>
		<div align = "center">
			<button onclick = "Back()">Back</button>
		</div>
		<!--footer/begin-->
		<div id="footer" class="en">
			<p>BoguSoft Footer Bar</p>
		</div>
		<!--footer/end-->
  </body>
</html>
