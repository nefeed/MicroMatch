<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
	String LoginUserNum = "" ;
	String LoginNickName = "" ;
	Integer LoginUserType = 3 ;
	Cookie[] cookies = request.getCookies() ;
	Cookie cookieTemp = null ;
	if( cookies != null ) {
		for ( int i = 0 ; i < cookies.length ; i ++ ) {
			cookieTemp = cookies[i] ;
			if( cookieTemp.getName().equals("un") ) {
		     	LoginUserNum = cookieTemp.getValue();
		    } else if ( cookieTemp.getName().equals("nn") ) {
		   		LoginNickName = cookieTemp.getValue();
		   	} else if( cookieTemp.getName().equals("ut") ) {
		   		LoginUserType = Integer.parseInt( cookieTemp.getValue() ) ;
		   	} 	
		}
	}
		try {
			LoginUserNum = session.getAttribute( "UserNum" ).toString() ;
			LoginNickName = session.getAttribute( "NickName" ).toString() ;
			LoginUserType = Integer.parseInt( session.getAttribute("UserType").toString() ) ;
		}catch(Exception e) {
		}
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>微课首页</title>
    <link type="text/css" rel="stylesheet" href="css/matchIndex.css" />
    <link type="text/css" rel="stylesheet" href="css/aspxcs.css"/>
    <link type="text/css" rel="stylesheet" href="css/bootstrap/bootstrap.css" />
    <link type="text/css" rel="stylesheet" href="css/imageWheel/imageWheel.css" />
    
    <script type="text/javascript" src="js/jquery-2.1.4.min.js"></script>
	<script type="text/javascript" src="js/matchIndex.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript" src="js/imageWheel/imageWheel.js"></script>
	<script type="text/javascript">
		var LoginUserNum = <%="'"+LoginUserNum+"'"%> ;
		var LoginNickName = <%="'"+LoginNickName+"'"%> ;
		var LoginUserType = <%=LoginUserType%> ;
	</script>

  </head>
  
  <body>
  	<jsp:include page="actionBar.jsp"/>
  	<!-- 评价课程Modal -->
	<div id="periodModal" class="modal fade" tabindex="-1" role="dialog" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">课程评分</h4>
				</div>
				<div class="modal-body">
					<h4>请选择你给予的评分，按确认提交！</h4>
					<div id="xzw_starSys">
						<div id="xzw_starBox">
							<ul class="star" id="star">
								<li><a href="javascript:void(0)" title="1" class="one-star">1</a></li>
								<li><a href="javascript:void(0)" title="2" class="two-stars">2</a></li>
								<li><a href="javascript:void(0)" title="3" class="three-stars">3</a></li>
								<li><a href="javascript:void(0)" title="4" class="four-stars">4</a></li>
								<li><a href="javascript:void(0)" title="5" class="five-stars">5</a></li>
							</ul>
							<div class="current-rating" id="showb"></div>
						</div>
						<span>(请评分)</span>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="givePeriod()" >确认</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal fade -->
	<div class="warp">
		<div id="index-head">
			<div id="zSlider">
				<div id="picshow">
					<div id="picshow_img">
						<ul id = "newestMatchBigImg">
						  <li style="display: none;"><a href="#" target="_blank"><img src="Images/1.jpg"></a></li>
						  <li style="display: none;"><a href="#" target="_blank"><img src="Images/2.jpg"></a></li>
						  <li style="display: list-item;"><a href="#" target="_blank"><img src="Images/3.jpg"></a></li>
						  <li style="display: none;"><a href="#" target="_blank"><img src="Images/4.jpg"></a></li>
						</ul>
					</div>
					<div id="picshow_tx">
						<ul id = "newestMatchBigImgAlt">
						  <li style="display: none;">
							  <x3><a href="#" target="_blank">中国死飞店铺推介：上海死飞店FACTORY FIVE</a></x3>
							  <p>上海第一家死飞精品店，由三个外国人与一中国人联合创办，主要经营客订个性单速车，帮助他们得到自己梦想中的车架。</p>
						  </li>
						  <li style="display: none;">
							  <x3><a href="#" target="_blank">骑看世界：纯美的世界恬静的心冰岛骑游之旅</a></x3>
							  <p>冰岛有“火山岛”、“雾岛”、“冰封的土地”、“冰与火之岛”之称。有想过在这里骑游吗？下面看看Ovegur的冰岛骑游之旅吧。</p>
						  </li>
						  <li style="display: list-item;">
							  <x3><a href="#" target="_blank">空气糟糕透了！推荐几款实用的骑行防毒口罩</a></x3>
							  <p>这几天，全国各地的空气糟糕透顶！北京空气污染指数又爆表了！！！经过资深车友的推荐及亲身体验，整理出几款超强防毒的骑行口罩。</p>
						  </li>
						  <li style="display: none;">
							  <x3><a href="#" target="_blank">[组图]1200万像素带Wi-Fi 骑行记录仪Gopro Hero3评测</a></x3>
							  <p>近年来户外骑行等运动录像盛行，Gopro这品牌可说功不可没，新版的Gopro Hero3具有1200万像素带Wi-Fi功能……</p>
						  </li>
						</ul>
					</div>
				</div>
				<div id="select_btn">
			    	<a id="sbr_title">
			                	最新比赛
			            		</a>
					<ul id = "newestRightMatchesUl">
					  <li class="" style="border: none;"><a href="http://www.lanrentuku.com/" target="_blank"><img src="Images/01.jpg"><span class="select_text">上海死飞店</span><span class="select_date"><x>College</x>&nbsp&nbsp<x>08.01</x>-<x>08.30</x></span></a></li>
					  <li class=""><a href="http://www.lanrentuku.com/" target="_blank"><img src="Images/02.jpg"><span class="select_text">骑看世界：北欧冰岛</span><span class="select_date"><x>College</x>&nbsp&nbsp<x>08.01</x>-<x>08.30</x></span></a></li>
					  <li class="current"><a href="http://www.lanrentuku.com/" target="_blank"><img src="Images/03.jpg"><span class="select_text">推荐几款实用的骑行</span><span class="select_date"><x>College</x>&nbsp&nbsp<x>08.01</x>-<x>08.30</x></span></a></li>
					  <li class=""><a href="http://www.lanrentuku.com/" target="_blank"><img src="Images/04.jpg"><span class="select_text">骑行记录仪</span><span class="select_date"><x>College</x>&nbsp&nbsp<x>08.01</x>-<x>08.30</x></span></a></li>
					</ul>
				</div>	
			</div>
		</div>
		<!-- 首页比赛的课程显示 -->
		<div class="dividing-house">
			<div class="dividing-bg" id="newestMatchDiv1">
				<span class="dividing-text-left"></span> <span
					class="dividing-text-center">园艺工程大赛</span> <span
					class="dividing-text-right"></span> <a class="dividing-more"
					href="#">更多>></a>
			</div>
			<div id="newestRegisterBody" style="height:230px;margin-top: 35px;">
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="top-right-new">
							<img src="Images/new-mark.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="top-right-new">
							<img src="Images/new-mark.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="top-right-new">
							<img src="Images/new-mark.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="top-right-new">
							<img src="Images/new-mark.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- 上期获奖微课显示，3条显示，冠军显示详情 -->
		<div class="dividing-house">
			<div class="dividing-bg">
				<span class="dividing-text-left"></span> <span id = "secondMatchTitle" class="dividing-text-center">上期获奖微课</span> <span class="dividing-text-right"></span>
			</div>
			<div style="height:230px; margin-top: 20px; display: none;">
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="content-top-left">
							<img src="Images/gold.png" alt="展示图片" />
						</div>
						<div class="content-top-right">
							<img src="Images/match-gold.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
				<div class="show-details">
					<ul class="witkey-details">
						<li>主讲：北京园林学院</li>
						<li>发布：<font color="#185e92">北京园林学院</font></li>
						<li>课程有效期：<font color="red">长期</font></li>
						<li>分类：<font color="#185e92">园林工程</font></li>
						<li>学习人数：<font color="red">50</font></li>
						<li><img src="Images/collection.jpg"
							style="margin-bottom:5px;" />&nbsp;&nbsp;&nbsp;<font
							color="#185e92">收藏课程</font></li>
						<li>课程介绍<br /> 本课程为2015年最新园林教育最新课程，其中包含有园林教育，园林......<a
							href="#" class="class-dpt">[详情]</a>
						</li>
					</ul>
				</div>
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="content-top-left">
							<img src="Images/silver.png" alt="展示图片" />
						</div>
						<div class="content-top-right">
							<img src="Images/match-silver.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
				<div class="show-content">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
						<div class="content-top-left">
							<img src="Images/bronze.png" alt="展示图片" />
						</div>
						<div class="content-top-right">
							<img src="Images/match-bronze.png" alt="展示图片" />
						</div>
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
						<div class="landscape-right">
							<input type="button" class="votebtn" value="评分" />
						</div>
					</div>
					<div class="interactive">
						<div class="score">
							<div class="score-pic">
								<img src="Images/show-score.jpg" alt="得分" />
							</div>
							<div>
								<a style="color:#959595;">得分:</a> <a style="color:#959595;">0</a>
							</div>
						</div>
						<div class="vote">
							<div class="vote-pic">
								<img src="Images/show-vote.jpg" alt="投票" />
							</div>
							<a style="color:#959595;">投票:</a> <a style="color:#959595;">0</a>
						</div>
					</div>
				</div>
			</div>
			<!-- 其它优秀获奖课程，表单形式显示 -->
			<div style="display:none ;">
				<img src="Images/honors.png" alt="比赛奖项"
					style="padding-bottom:3px;padding-right:16px;" /><a
					style="color:#c62626;font-size:18px;">其他优秀获奖</a>
			</div>
			<div style="margin-top:10px;">
				<table id="awardTable" class="table table-striped table-hover">
					<tr class="info">
						<td>奖项名称</td>
						<td>奖项概述</td>
						<td>获奖课程</td>
					</tr>
				</table>
			</div>
		</div>

		<div class="dividing-house">
			<!-- 最新微课底下左边显示 -->
			<div style="width:485px;height:502px;float:left"
				id="newestCoursesBody">
				<div class="dividing-short">
					<span class="dividing-text-left"></span> <span
						class="dividing-text-center">最新微课</span> <span
						class="dividing-text-right"></span>
					<a class="dividing-more" href="index.jsp">更多>></a>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
			</div>

			<!-- 热门微课，底下右边显示 -->
			<div style="width:485px;height:502px;float:right" id="hotCoursesBody">
				<div class="dividing-short">
					<span class="dividing-text-left"></span> <span
						class="dividing-text-center">热门微课</span> <span
						class="dividing-text-right"></span>
					<a class="dividing-more" href="index.jsp">更多>></a>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
				<div class="show-content-little">
					<div class="content-top">
						<img src="Images/recommend.png" alt="展示图片" />
					</div>
					<div class="landscape">
						<div class="landscape-left">
							<font>园林测量运算</font>
						</div>
					</div>
					<div class="interactive">
						<div class="class-left">
							<div class="pic-font">
								<img src="Images/clock.png" />
							</div>
							<a style="color:#959595;">4课时 50分钟</a>
						</div>
						<div class="class-right">
							<div class="pic-font">
								<img src="Images/triangle.png" />
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="dividing-clear"></div>
	</div>
	<jsp:include page="bottom.jsp"/>
  </body>
</html>
