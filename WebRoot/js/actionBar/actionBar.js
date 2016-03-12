var verifycode = '' ;
var verifypath = '' ;
$(function(){
	$.ajaxSettings.async = false ;
	if( 'undefind'==LoginUserNum || LoginUserNum == '' ) {
		$("#UnLoginType").show() ;
		$("#HaveLoginType").hide() ; 
	} else {
		$("#HaveLoginType").show() ; 
		$("#UnLoginType").hide() ; 
		$('#loginNick').attr('href','person.jsp?UserNum='+LoginUserNum ) ; 
		var str = LoginNickName ;
		if( str.length > 12 ) {
			str = LoginNickName.substring(0,8) ;
			str += "..." ;
		}
		$("#loginNick").html(str) ;
		var lootemp = '' ;
		var $loo = $('#loginOtherOption') ;
		$loo.html('') ;
		switch( LoginUserType ) {
			case 0 : 
				// 管理员用户
				// 个人中心，新建课程，新建比赛，退出
				lootemp = '<li role="presentation" class="personalli" ><a role="menuitem" tabindex="-1" onclick="myInfomation()" href="javascript:;" style = "font-size:14px ;border-top:1px solid #e0e0e0;"><img src="Images/personal.png" />&nbsp;&nbsp;个人中心</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="courseli"><a role="menuitem" tabindex="-1" href="./jsp/Course/new_course.jsp" style = "font-size:14px ;"><img src="Images/course.png"/>&nbsp;&nbsp;新建课程</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="matchli"><a role="menuitem" tabindex="-1" href="./jsp/Match/new_match.jsp" style = "font-size:14px ;"><img src="Images/match.png"/>&nbsp;&nbsp;新建比赛</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="retirli"><a role="menuitem" tabindex="-1" id="logOutBtn" onclick="logOut()" href="javascript:;" style = "font-size:14px ;padding-left:25px;"><img src="Images/retire.png"/>&nbsp;&nbsp;退出</a></li>' ;
				break ;
			case 1 :
				// 学院用户
				// 个人中心，我的订阅，新建比赛，退出
				lootemp = '<li role="presentation" class="personalli" ><a role="menuitem" tabindex="-1" onclick="myInfomation()" href="javascript:;" style = "font-size:14px ;border-top:1px solid #e0e0e0;"><img src="Images/personal.png" />&nbsp;&nbsp;个人中心</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="courseli"><a role="menuitem" tabindex="-1" href="./myAttend.jsp" style = "font-size:14px ;"><img src="Images/myAttend.png"/>&nbsp;&nbsp;我的订阅</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="matchli"><a role="menuitem" tabindex="-1" href="./jsp/Match/new_match.jsp" style = "font-size:14px ;"><img src="Images/match.png"/>&nbsp;&nbsp;新建比赛</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="retirli"><a role="menuitem" tabindex="-1" id="logOutBtn" onclick="logOut()" href="javascript:;" style = "font-size:14px ;padding-left:25px;"><img src="Images/retire.png"/>&nbsp;&nbsp;退出</a></li>' ;
				break ;
			case 2 :
				// 教师用户
				// 个人中心，我的订阅，新建课程，退出
				lootemp = '<li role="presentation" class="personalli" ><a role="menuitem" tabindex="-1" onclick="myInfomation()" href="javascript:;" style = "font-size:14px ;border-top:1px solid #e0e0e0;"><img src="Images/personal.png" />&nbsp;&nbsp;个人中心</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="courseli"><a role="menuitem" tabindex="-1" href="./myAttend.jsp" style = "font-size:14px ;"><img src="Images/myAttend.png"/>&nbsp;&nbsp;我的订阅</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="courseli"><a role="menuitem" tabindex="-1" href="./jsp/Course/new_course.jsp" style = "font-size:14px ;"><img src="Images/course.png"/>&nbsp;&nbsp;新建课程</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="retirli"><a role="menuitem" tabindex="-1" id="logOutBtn" onclick="logOut()" href="javascript:;" style = "font-size:14px ;padding-left:25px;"><img src="Images/retire.png"/>&nbsp;&nbsp;退出</a></li>' ;
				break ;
			case 3 :
				// 学生用户
				// 个人中心，我的订阅，退出
				lootemp = '<li role="presentation" class="personalli" ><a role="menuitem" tabindex="-1" onclick="myInfomation()" href="javascript:;" style = "font-size:14px ;border-top:1px solid #e0e0e0;"><img src="Images/personal.png" />&nbsp;&nbsp;个人中心</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="courseli"><a role="menuitem" tabindex="-1" href="myAttend.jsp" style = "font-size:14px ;"><img src="Images/myAttend.png"/>&nbsp;&nbsp;我的订阅</a></li>'
					+ '<li role="presentation" class="divider" style="margin:0px 0px;height:1px;width:126px;"></li><li role="presentation" class="retirli"><a role="menuitem" tabindex="-1" id="logOutBtn" onclick="logOut()" href="javascript:;" style = "font-size:14px ;padding-left:25px;"><img src="Images/retire.png"/>&nbsp;&nbsp;退出</a></li>' ;
				break ;
		}
		$("#logOutBtn").attr( 'href' , './matchIndex.jsp' ) ;
		$("#loginBtn").attr( 'href' , './login.jsp') ;
		$loo.html( lootemp ) ;
	}
	
	$('#loginTab').click(function(){
		$('#loginTab').attr( 'class', 'modal_nav1') ;
		$('#free_loginTab').attr( 'class', 'modal_nav2' ) ;
		$('#reginTab').attr( 'class', 'modal_nav2' ) ;
		$('#login_ul').show() ;
		$('#free_regin_ul').hide() ;
		$('#regin_ul').hide() ;
	});
	$('#free_loginTab').click(function(){
		$('#loginTab').attr( 'class', 'modal_nav2') ;
		$('#free_loginTab').attr( 'class', 'modal_nav1' ) ;
		$('#reginTab').attr( 'class', 'modal_nav2' ) ;
		$('#login_ul').hide() ;
		$('#free_regin_ul').show() ;
		$('#regin_ul').hide() ;
	});
	$('#reginTab').click(function(){
		$('#loginTab').attr( 'class', 'modal_nav2') ;
		$('#free_loginTab').attr( 'class', 'modal_nav2' ) ;
		$('#reginTab').attr( 'class', 'modal_nav1' ) ;
		$('#login_ul').hide() ;
		$('#free_regin_ul').hide() ;
		$('#regin_ul').show() ;
	});
});


function logOut() {
	$.ajaxSettings.async = false ;
	$.getJSON('./servlet/logOutServlet',{
	},function(json){
		
	});
	$.ajaxSettings.async = true ;
	location.href = './matchIndex.jsp' ;
}
function myInfomation() {
	location.href = './person.jsp?UserNum='+LoginUserNum ;
}

function confirmSearch() {
	var searchInput = $("#searchInput").val() ;
	location.href = "./searchCourse.jsp?unsureCourseName=" + searchInput ;
}

function backToHome() {
	location.href = "./matchIndex.jsp" ;
}

function listenSearch() {
	if( event.keyCode == 13 ){
		confirmSearch() ;
	}
}
/*登录、注册*/
function loginchg(){
	$('#login_ul').show() ;
	$('#free_regin_ul').hide() ;
	$('#regin_ul').hide() ;
	$('#loginTab').attr('class', 'modal_nav1') ;
	$('#free_loginTab').attr('class', 'modal_nav2') ;
	$('#reginTab').attr('class', 'modal_nav2') ;
	getVerifyCode() ;
}
function reginchg(){
	$('#login_ul').hide() ;
	$('#free_regin_ul').hide() ;
	$('#regin_ul').show() ;
	$('#loginTab').attr('class', 'modal_nav2') ;
	$('#free_loginTab').attr('class', 'modal_nav2') ;
	$('#reginTab').attr('class', 'modal_nav1') ;
	getVerifyCode() ;
}

/**
 * 登录功能
 */
function signIn() {
	var userName = $( '#signinAccount' ).val() ;
	var userPassword = $( '#signinPassword' ).val() ;
	var validate = $( '#signinvalidate' ).val() ;
	validate = validate.toUpperCase() ;
	if( validate == verifycode ) {
		if( userName.length == 0 ){
			alert("账号不能为空！") ;
		} else if( userPassword.length == 0 ) {
			alert("密码不能为空！") ;
		} else {		
			$.getJSON("./servlet/LoginServlet",{
				UserName:userName,
				UserPassword:userPassword,
			},function(json) {
				if ( json.result == 0 ){
					location.href="./matchIndex.jsp" ;
				}else if ( json.result == 1 ) {
					alert( "密码错误！" ) ;
				}else if ( json.result == 2 ) {
					alert( "无此账号！" ) ;
				}
			});
		} 	
	} else {
		alert( '您输入的验证码有误，请重新输入！' ) ;
	}
}

/**
 * 普通注册功能
 */
function signUp() {
	var userName = $('#signupAccount').val() ;
	var userPassword = $('#signupPassword').val() ;
	var nickName = $('#signupNickName').val() ;
	var validate = $('#signupvalidate').val() ;
	var userType = 3 ;
	validate = validate.toUpperCase() ;
	if( validate == verifycode ) {
		if( userName.length == 0 || userName.length > 15 ){
			alert( "账号长度不能为空！" ) ;
		} else if ( userName.length < 5 ) {
			alert( "账号长度过短，请重新设置！" ) ;
		} else if ( userName.length > 22 ) {
			alert( "账号长度过长，请重新设置！" ) ;
		} else if( userPassword.length == 0 ) {
			alert( "密码长度不能为空！") ;
		} else if( userPassword.length < 5 ) {
			alert( "密码长度过短,请重新设置！" ) ;
		} else if( userPassword.length > 15 ) {
			alert( "密码长度过长,请重新设置！" ) ;
		} else if( nickName.length == 0 ) {
			alert( "用户昵称长度不符合要求！" ) ;
		} else if( nickName.lentgh < 5 ) {
			alert( "用户昵称长度过短，请重新设置！") ;
		} else if( nickName.lentgh > 15 ) {
			alert( "用户昵称长度过长，请重新设置！") ;
		} else if( userType.length == 0 ) {
			alert( "用户类型不能为空！" ) ;
		} else {
			$.getJSON( "./servlet/SignUpServlet",{
				UserName:userName,
				UserPassword:userPassword,
				NickName:nickName,
				UserType:userType,
			},function(json){
				if ( json.result == 0 ){
					alert( "注册成功！\n" +
							json.NickName+"欢迎来到博古微课云平台！" ) ;
					$('#signinAccount').val( userName ) ;
					$('#signinPassword').val( userPassword ) ;
					$('#signinvalidate').val( verifycode ) ;
					signIn() ;
				}else if ( json.result == 1 ) {
					alert( "此账号已经存在！\n请修改账号继续尝试！" ) ;
				}
			});
		}	
	} else {
		alert( '您输入的验证码有误，请重新输入！' ) ;
	}
}

/**
 * 免注册登录
 */
function signInWithoutUp() {
	var signPhone = $('#signPhone').val() ;
	var validate = $('#signValidate').val() ;
	var msg = $('#signMsg').val() ;
}

/**
 * 获取验证码
 */
function getVerifyCode() {
	$('#signupvalidate').val("");
	$.ajax({
        url: './servlet/VerifyCodeServlet',
        type: 'post' ,
        dataType: 'json',
        success:function(data){
        	if( data ) {
        		verifycode = data.data ;
        		verifypath = data.filepath ;
        		$('#signindate').attr( 'src', verifypath ) ;
        		$('#signdate').attr( 'src', verifypath ) ;
        		$('#signupdate').attr( 'src', verifypath ) ;    		
        	} else {
        		alert( 'data的值为空!' ) ;
        	}
        },
        error:function(){
        	alert('失败了！') ;
        }
    });
}