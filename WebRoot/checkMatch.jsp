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
	<title>比赛审核</title>
	<link type="text/css" href="css/game.css" rel="stylesheet" />
	<script src="js/jquery.js" type="text/javascript" ></script>
	<script src="js/game.js" type="text/javascript" ></script>
</head>

<body>
<jsp:include page="/actionBar.jsp"/>
<div class="subject">
	<div class="subject_left">
    	<em id="em1" class="subject_current">未审核</em><em id="em2">已审核</em>
    </div>
    <div class="subject_right">
    	<table id = "uncheck" width="100%" border="0" cellspacing="0" cellpadding="5" >
              <tr>
                <th scope="col">举办人</th>
                <th scope="col">比赛名称</th>
                <th scope="col">比赛内容</th>
                <th class="right_border" scope="col">比赛日期</th>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
           </table>
           
			<table id = "checked" style = "display: none" width="100%" border="0" cellspacing="0" cellpadding="5" >
              <tr>
                <th scope="col">举办人</th>
                <th scope="col">比赛名称</th>
                <th scope="col">比赛内容</th>
                <th class="right_border" scope="col">比赛日期</th>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
              <tr>
                <td>李国荣</td>
                <td>青春校园杯大赛</td>
                <td>各位同学根据自己能力做出一套园林设计方案。</td>
                <td class="right_border">2015.06.09  </td>
              </tr>
           </table>
    </div>
</div>
<jsp:include page="/bottom.jsp"/>
</body>
</html>
