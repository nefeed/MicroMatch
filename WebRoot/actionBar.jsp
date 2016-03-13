<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
	     		LoginUserType = Integer.parseInt( cookieTemp.getValue().toString() );
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
	<base href="<%=basePath%>" />
	<title>导航栏主页</title>
	<link type="text/css" rel="stylesheet" href="css/bootstrap/bootstrap.min.css" />
	<link type="text/css" rel="stylesheet" href="css/actionBar.css" />
	<link type="text/css" rel="stylesheet"  href="css/blue.css"/>
	
	<script type="text/javascript" src="js/jquery-2.1.4.min.js" ></script>
	<script type="text/javascript" src="js/actionBar/actionBar.js"></script>
	<script type="text/javascript" src="js/icheck.min.js"></script>
	<script type="text/javascript" src="js/myicheck.js"></script>
	<script type="text/javascript" src="js/bootstrap/bootstrap.js"></script>
	<script type="text/javascript">
		var LoginUserNum = <%="'" + LoginUserNum + "'"%> ;
		var LoginNickName = <%="'" + LoginNickName + "'"%> ;
		var LoginUserType = <%=LoginUserType%> ;
	</script>
</head>

<body>
	<div id="signModal" class="modal fade" tabindex="-1" role="dialog" data-backdrop="static" aria-labelledby="myModalLabel" aria-hidden="true">
		<div class="modal-dialog add_mdal">
      		<div class="login_modal">
				<div class="modal_header">
                    	<a id="loginTab" class="modal_nav1" onclick="" >会员登入</a>
                        <a id="free_loginTab" class="modal_nav2" style="display: none;" onclick="" >免注册登入</a>
                        <a id="reginTab" class="modal_nav2" onclick="" >用户注册</a>
                        <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">×</span><span class="sr-only">Close</span></button>
                </div>
				<div class="modal_content">
					<div align = "center"  >
						<ul id="login_ul" style="display:block;">
                        	<li class="modal_li">
                            	<span class="sp_txt">账号</span>
                                <input id = "signinAccount" type="text" class="ipt_txt1" placeholder="用户名/手机号/邮箱" />
                            </li>
                            <li class="modal_li">
                            	<span class="sp_txt">密码</span>
                                <input id = "signinPassword" type="password" class="ipt_txt1"  placeholder="密码" />                              
                            </li>
                            <li>
                            	<span class="sp_txt">验证码</span>
                                <input id = "signinvalidate" type="text" class="ipt_txt2" placeholder="验证码" />
                                <img id="signindate" alt="" src=""/>  
        						<a href="javascript:" onclick="getVerifyCode()">换一张</a>
                            </li>
                            <li class="modal_li1">
                                <input type="checkbox" id="ckb_freelog" checked />
                                <a for="ckb_freelog" class="auto_login" >7天内免登入</a>
                            </li>
                            <li>
                            	<input type="submit" value="登入" class="btn1" onclick="signIn()" />
                            </li>
                        </ul>
                        <ul id="free_regin_ul" style = "display: display;">
                        	<li>
                            	<span class="sp_txt">手机号</span>
                                <input id="signPhone" type="text" class="ipt_txt1" placeholder="手机号码" />
                            </li>
                            <li>
                            	<span class="sp_txt">验证码</span>
                                <input id="signValidate" type="text" class="ipt_txt2" placeholder="验证码"/>
                                <img id="signdate" alt="" src=""/>  
        						<a href="javascript:" onclick="getVerifyCode()">换一张</a>
                            </li>
                             <li>
                            	<span class="sp_txt">动态码</span>
                                <input id="signMsg" type="text" class="ipt_txt2" />
                                <input type="button" value="免费获取动态码" class="btn2"/>
                                </a>
                            </li>
                            <li>
                            	<input type="submit" value="登入" class="btn1"/>
                            </li>
                        </ul>
                        <ul id="regin_ul" style="display:block;">
                        	<li class="modal_li">
                            	<span class="sp_txt">用户名</span>
                                <input id = "signupAccount" type="text" class="ipt_txt1" placeholder="用户名"/>
                            </li>
                            <li >
                            	<span class="sp_txt">设置密码</span>
                                <input id = "signupPassword" type="password" class="ipt_txt1" placeholder="密码 (6~15位)" />
                            </li>
                            <li>
                            	<span class="sp_txt">用户昵称</span>
                                <input id = "signupNickName" type="text" class="ipt_txt1" placeholder="昵称" />
                            </li>
                            <li class="modal_li">
                            	<span class="sp_txt">验证码</span>
                                <input id = "signupvalidate" type="text" class="ipt_txt2" placeholder="验证码"/>
                                <img id="signupdate" alt="" src=""/>  
       							<a href="javascript:" onclick="getVerifyCode()">换一张</a>
                            </li>
                            <li>
                            	<input type="submit" value="注册" class="btn1" onclick = "signUp()"/>
                            </li>
                        </ul>
                        <div class="clear"></div>
					</div>
				</div>
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal fade -->
	<div id = "header">
		<div id = "top">
			<div id = "logo">
				<a href = "/micromatch/matchIndex.jsp"><img src="Images/logo1.png" alt="徽标" onclick="backToHome()"/></a>
				<a href = "/micromatch/matchIndex.jsp"><img src="Images/logo2.png" alt="公司名称" onclick="backToHome()"/></a>
			</div>
			<div class="navbg" ><a class = "navbg-link" href="/micromatch/matchIndex.jsp">首页</a></div>
			<div class="navbg" ><a class = "navbg-link" href="/micromatch/jsp/Match/matchList.jsp">微赛</a></div>
			<div class="search">
		        <input id="searchInput" class="txtsearch" type="text" onkeydown="listenSearch()" placeholder="搜索课程名"/>
		        <input type="submit" class="subsearch" value="" onclick="confirmSearch()" />
	    	</div>
	    	<div id="HaveLoginType" class="login" style="display: none"> 
				<div id="fat-menu" class="dropdown" style="">
				<a class="caretl"></a>
		            <a id="loginNick" onclick="myInfomation()" href="javascript:" role="button" class="dropdown-toggle" data-toggle="dropdown" style="font-size:18px;color: #8d8791;">Eternal</a>
		            <ul id = "loginOtherOption" class="dropdown-menu dropdownul" role="menu" aria-labelledby="loginNick">
		                <li role="presentation" class="personalli" >
		                    <a role="menuitem" tabindex="-1" onclick="myInfomation()" href="javascript:" style = "font-size:14px ;border-top:1px solid #e0e0e0;">
		                    	<img src="Images/personal.png" />
		                    	&nbsp;&nbsp;个人中心
		                    </a>
						</li>
						<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;">
						</li>
						<li role="presentation" class="courseli">
							<a role="menuitem" tabindex="-1" href="/micromatch/jsp/Course/new_course.jsp" style = "font-size:14px ;">
								<img src="Images/course.png"/>
								&nbsp;&nbsp;新建课程
							</a>
						</li>
         				<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;">
         				</li>
         				<li role="presentation" class="matchli">
         					<a role="menuitem" tabindex="-1" href="/micromatch/jsp/Match/new_match.jsp" style = "font-size:14px ;">
         						<img src="Images/match.png"/>
         						&nbsp;&nbsp;新建比赛
         					</a>
         				</li>
		                <li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;">
						</li>
		                <li role="presentation" class="retirli">
		                	<a role="menuitem" tabindex="-1" id="logOutBtn" onclick="logOut()" href="javascript:" style = "font-size:14px ;padding-left:25px;">
		                		<img src="Images/retire.png"/>
		                		&nbsp;&nbsp;退出
		                	</a>
						</li>
		            </ul>
		        </div>
	    	</div>
	    	<div id="UnLoginType" class="login"> 
	      		<ul class="loginul">
			      	<li class="logindo">
			        	<a id="loginBtn" data-toggle="modal" data-target="#signModal" onclick="loginchg()">登入</a>&nbsp;|
			        	<a id="registBtn" data-toggle="modal" data-target="#signModal" onclick="reginchg()">注册</a>
			        </li>
			    </ul>
	    	</div>
    	</div>
	</div>
</body>
</html>
