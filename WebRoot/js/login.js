// JavaScript Document
$(function() {
	$.ajaxSettings.async = false ;
	$("#form1").show() ;
	$("#form2").hide() ;
	if( LoginUserName != null || LoginUserName != '' || LoginUserName != 'undefined' ) {
		$("#userName").val( LoginUserName ) ;
	}
});
function login(){
	$('#li1').addClass('current');
	$('#li2').removeClass('current');
	$("#form1").show() ;
	$("#form2").hide() ;
}
function signup(){
	$('#li2').addClass('current');
	$('#li1').removeClass('current');
	$("#form1").hide() ;
	$("#form2").show() ;
}

function confirmLogin() {
	var userName = $("#userName").val() ;
	var userPassword = $("#userPassword").val() ;
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
}

function confirmSignUp() {
	var userName = $("#signUserName").val() ;
	var userPassword = $("#signPassword").val() ;
	var nickName = $("#signNickName").val() ;
	var userType = $("#signUserType").val() ;
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
				alert( "注册成功\n" +
						json.NickName+"欢迎来到博古微课云平台！" ) ;
				$("#userName").val( userName ) ;
				$("#userPassword").val( userPassword ) ;
				confirmLogin() ;
			}else if ( json.result == 1 ) {
				alert( "此账号已经存在！\n请修改账号继续尝试！" ) ;
			}
		});
	}
}