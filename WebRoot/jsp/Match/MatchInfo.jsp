<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%
	String MatchNum = request.getParameter("MatchNum");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<base href="<%=basePath%>" />
	
	<title>比赛详情</title>
	<link rel="stylesheet" type="text/css" href="css/style.css"/>
	<link rel="stylesheet" type="text/css" href="css/Oinformation.css"/>
	<link type="text/css" rel="stylesheet" href="css/aspxcs.css"/>
	<link type="text/css" rel="stylesheet" href="css/laypage.css" />
	<link type="text/css" rel="stylesheet" href="css/bootstrap/bootstrap.css" />
	<script type="text/javascript" src="js/jquery-2.1.4.min.js" ></script>
	<script type="text/javascript" src="js/Match/MatchInfo.js"></script>
	<script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript" src="js/laypage.js"></script>
	<script type="text/javascript" src="js/mypj.js"></script>
	<script type="text/javascript">
		var MatchNum = <%="'" + MatchNum + "'"%> ;
	</script>
</head>

<body>
	<jsp:include page="/actionBar.jsp" />
	<!-- 修改比赛Modal -->
	<div id="updateMatchModal" class="modal fade" tabindex="-1"
		role="dialog" data-backdrop="static" aria-labelledby="myModalLabel"
		aria-hidden="true" style="left:32%; width:600px; ">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button id="modalClose" type="button" class="close"
						data-dismiss="modal" aria-hidden="true">&times;</button>
					<h4 class="modal-title">修改比赛</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped" id="MatchInfoTable">
						<tr>
							<td>比赛名称:</td>
							<td><input id="newMatchName" class="form-control" type="text"></input></td>
						</tr>
						<tr>
							<td>比赛内容:</td>
							<td><textarea id="newMatchContent" class="form-control" rows="3"></textarea></td>
						</tr>
						<tr>
							<td>开始时间:</td>
							<td><input id="newStartTime" class="form-control"
								type="text" class="Wdate subject_text" onClick="WdatePicker()" ></input></td>
						</tr>
						<tr>
							<td>结束时间:</td>
							<td><input id="newEndTime" class="form-control" type="text" class="Wdate subject_text" onClick="WdatePicker()"></input></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="UpdateMatch()">确认修改</button>
				</div>
			</div>
			<!-- /.modal-content -->
		</div>
		<!-- /.modal-dialog -->
	</div>
	<!-- /.modal fade -->
	<!-- 确认报名Modal -->
	<div id="confirmRegistModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">报名参赛</h4>
				</div>
				<div class="modal-body">
					<h4>点击确认提交报名信息！</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="confirmRegist()" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal fade -->
	<!-- 结束比赛Modal -->
	<div id="finishMatchModal" class="modal fade" tabindex="-1"
		role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title" >结束当前阶段比赛</h4>
				</div>
				<div class="modal-body">
					<h4>点击确认结束当前阶段比赛！</h4>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary"
						onclick="finishMatchTemp()" data-dismiss="modal">确认</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal fade -->
	<!-- 新建奖项Modal -->
	<div id="newAwardModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">新建奖项</h4>
				</div>
				<div class="modal-body">
					<table class="table" id="MatchInfoTable">
						<tr>
							<td><label>奖项名称:</label></td>
							<td><input id="newAwardName" class="form-control" type="text" ></input></td>
						</tr>
						<tr>
							<td><label>奖项概述:</label></td>
							<td><textarea id="newAwardRemark" class="form-control" rows="3"></textarea></td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="newAward()"
						data-dismiss="modal">保存</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal fade -->
	<!-- 颁发奖项Modal -->
	<div id="giveAwardModal" class="modal fade" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>
					<h4 class="modal-title">颁发奖项</h4>
				</div>
				<div class="modal-body">
					<table class="table table-striped" id="MatchInfoTable">
						<tr>
							<td><label>奖项名称:</label></td>
							<td><input id="giveAwardName" class="form-control"
								type="text" readonly="readonly"></input></td>
						</tr>
						<tr>
							<td><label>奖项概述:</label></td>
							<td><textarea id="giveAwardRemark" class="form-control"
									readonly="readonly" rows="3"></textarea></td>
						</tr>
						<tr>
							<td><label>参赛作品:</label></td>
							<td>
								<select id="chooseRegist" class="form-control">
								</select>
							</td>
						</tr>
					</table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" onclick="giveAward()">颁奖</button>
				</div>
			</div>
		</div>
	</div>
	<!-- /.modal fade -->
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
		<div id = "content-head">
			<div class = "match-pic"><img id="matchPicture" src="Images/match-pic.png" style="width:592px;height:292px;" alt="比赛宣传图"/></div>
			<div class="match-txt">
		      <p id="matchName" style="color:#0b6abd;font-size:27px;">园林艺术参模大赛</p>
		      <p>赛事概述：<a id="matchContent" >本次比赛主要为了提升学生的动手操作能力，期间有相当一部分奖项，期间分为3个阶段赛事，分别是初赛，复赛和决赛。奖品级别也不一样......</a></p>
		      <p>比赛时间：<font color="red" id="matchStart" >2015-06-12</font><font color="red">——</font><font color="red" id="matchEnd" >2015-06-08</font></p>
		      <p>参赛课程数：<a id = "registNum">4节课程</a></p>
		      <p id="matchMaker" >创建者：李世华</p>
		      <p id="publishTime" >发布时间：2015年05月27日 11：45</p>
		    </div>
		    <div class="clear"></div>
		</div>
		<div class="btn-group collageoptionbtn" id="updateBody">
		</div>
		<div class="mainBody">
			<div class="starter">	
				<div id = "awardBody">
					<div class="tit_match"> <img src="Images/awtitle.jpg" alt="比赛奖项"/> </div>
					<table id="awardTable" class="table table-striped table-hover">
						<tr class="info">
							<td>奖项名称</td>
							<td>奖项概述</td>
							<td>获奖课程</td>
							<td class="otherOption">操作</td>
						</tr>
					</table>
				</div>
				<div class="match" id="registBody" >
				    <div style="float:left;" ><img src="Images/mchtitle.jpg" alt="参加比赛"/></div>
				    <div style="float:left;padding-top:2px;" ><font style="font-size:16px;color:#808080">(请选择课程,点击报名按钮参赛)</font></div>
				    <div style = "float:left; width:1000px ;margin-bottom: 10px;">
						<select id="courseSelect" class="select form-control">
						</select>
						<select id="chapterSelect" class="select form-control">
						</select>
						<button id="registBtn" type="button" class="btn btn-info" data-toggle="modal" data-target="#confirmRegistModal">报名</button>
				    </div>
				</div>
			</div>
		</div>
		<div style="margin-top:30px;"> <img src="Images/courtitle.jpg" alt="参赛课程"/> </div>
  		<div id = "finalMatch" class="match_nav">
		    <div class="recommend-final"> <font style="font-size:16px;color:#bc412c;">决赛推荐</font> </div>
		    	<div id = "finalBody" class="match_cnt">
		      
		      	</div>
		      	<div class="clear"></div>
	    </div>
	  	<div id = "quarterMatch" class="match_nav">
		    <div class="recommend-semi"><font style="font-size:16px;color:#3054a5;">复赛推荐</font></div>
		    	<div id = "quarterBody" class="match_cnt">
		      
		      	</div>
		      	<div class="clear"></div>
		</div>
	    <div id = "preliminaryMatch" class="match_nav">
		    <div class="recommend-first"> <font style="font-size:16px;color:#186e37;">初赛推荐</font> </div>
		    <div align = "center" id = "withoutResult" ></div>
		    <div id = "preliminaryBody" class="match_cnt">
	      			
	  		</div>
	  		<div class="clear"></div>
		</div>
		<div id="page" style="text-align:center;margin-top:10px;"></div>
	</div>
		<div class = "clear"></div>
		<jsp:include page="/bottom.jsp" />
</body>
</html>
