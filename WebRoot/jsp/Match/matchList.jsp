<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>比赛列表</title>
    
	<link type="text/css" rel="stylesheet" href="css/index.css" />
	<link type="text/css" rel="stylesheet" href="css/laypage.css" />
	<link type="text/css" rel="stylesheet" href="css/matchList.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js"></script> 
	<script type="text/javascript" src="js/Match/matchList.js"></script>
	<script type="text/javascript" src="js/laypage.js"></script>
	<script type="text/javascript" src="js/mypj.js"></script>

  </head>
  
  <body>
    <jsp:include page="../../actionBar.jsp"/>
    <!--  <div class="talk">
		<div class="talk_1" id = "subjectItself">
    		微赛列表
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
    	<div class = "clear"></div>
    </div>-->
    <div id = "matchList" class="warp">
	  <div class="witkey_cnt">
	    <div class="witkey_bannar">
	    	<a class="witkey_top">园林工程大赛</a>
	    	<img src="Images/banner.png"/>
	        <a class="witkey_bottom">火热进行中!</a>
	    </div>
	    <div class="witkey_txt">
	    	<ul>
	        	<li class="witkey_txt1">
	            	<p class="top_p">活动时间：</p>
	                <p class="">2015.03.08~2015.04.09</p>
	            </li>
	            <li class="witkey_txt2">
	            	<p class="top_p">活动介绍：</p>
	                <p class="">本课程为2015年最新园林教育最新课程，其中包含有园林教育，园林测dcdcdc你最新园林教育最新课程，其中包含有园林教育最新园林教育最新课程，新园林教育最...<a href="#" class="class-dpt">[详情]</a></p>
	            </li>
	        </ul>
	    </div>
	    <div class="clear"></div>
	  </div>
	  <div class="witkey_cnt">
	    <div class="witkey_bannar">
	    	<a class="witkey_top">园林工程大赛</a>
	    	<img src="Images/match-pic.jpg"/>
	        <a class="witkey_bottom">火热进行中!</a>
	    </div>
	    <div class="witkey_txt">
	    	<ul>
	        	<li class="witkey_txt1">
	            	<p class="top_p">活动时间：</p>
	                <p class="">2015.03.08~2015.04.09</p>
	            </li>
	            <li class="witkey_txt2">
	            	<p class="top_p">活动介绍：</p>
	                <p class="">本课程为2015年最新园林教育最新课程，其中包含有园林教育，园林测dcdcdc你最新园林教育最新课程，其中包含有园林教育最新园林教育最新课程，新园林教育最...<a href="#" class="class-dpt">[详情]</a></p>
	            </li>
	        </ul>
	    </div>
	    <div class="clear"></div>
	  </div>
	  <div class="witkey_cnt">
	    <div class="witkey_bannar">
	    	<a class="witkey_top">园林工程大赛</a>
	    	<img src="Images/banner.png"/>
	        <a class="witkey_bottom">火热进行中!</a>
	    </div>
	    <div class="witkey_txt">
	    	<ul>
	        	<li class="witkey_txt1">
	            	<p class="top_p">活动时间：</p>
	                <p class="">2015.03.08~2015.04.09</p>
	            </li>
	            <li class="witkey_txt2">
	            	<p class="top_p">活动介绍：</p>
	                <p class="">本课程为2015年最新园林教育最新课程，其中包含有园林教育，园林测dcdcdc你最新园林教育最新课程，其中包含有园林教育最新园林教育最新课程，新园林教育最...<a href="#" class="class-dpt">[详情]</a></p>
	            </li>
	        </ul>
	    </div>
	    <div class="clear"></div>
	  </div>
	  <div class="witkey_cnt">
	    <div class="witkey_bannar">
	    	<a class="witkey_top">园林工程大赛</a>
	    	<img src="Images/banner.png"/>
	        <a class="witkey_bottom">火热进行中!</a>
	    </div>
	    <div class="witkey_txt">
	    	<ul>
	        	<li class="witkey_txt1">
	            	<p class="top_p">活动时间：</p>
	                <p class="">2015.03.08~2015.04.09</p>
	            </li>
	            <li class="witkey_txt2">
	            	<p class="top_p">活动介绍：</p>
	                <p class="">本课程为2015年最新园林教育最新课程，其中包含有园林教育，园林测dcdcdc你最新园林教育最新课程，其中包含有园林教育最新园林教育最新课程，新园林教育最...<a href="#" class="class-dpt">[详情]</a></p>
	            </li>
	        </ul>
	    </div>
	    <div class="clear"></div>
	  </div>
	</div>
	<div id="page" style="text-align:center;margin-top:15px;"></div>
	<jsp:include page="../../bottom.jsp"/>
  </body>
</html>
