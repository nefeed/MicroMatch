function Confirm() {
	$.ajaxSettings.async = false ;
	var UserName = $("#UserName").val() ;
	var UserPassword = $("#UserPassword").val() ;
	var UserPassword2 = $("#UserPassword2").val() ;
	var NickName = $("#NickName").val() ;
	var UserType = $("#UserType").val();
	
	if( UserName.length == 0 ){
		alert("账号不能为空！") ;
	} else if( UserPassword.length == 0 ) {
		alert("密码不能为空！") ;
	} else if( UserPassword2.length == 0){
		alert("重复输入密码不能为空！") ;
	} else if( UserPassword != UserPassword2 ){
		alert("两次密码输入不一致，请重现输入");
	} else if( UserType.length == 0){
		alert("用户类型不能为空！") ;
	} else {		
		$.getJSON("./servlet/SignUpServlet",{
			UserName:UserName,
			UserPassword:UserPassword,
			NickName:NickName,
			UserType:UserType,
		},function(json) {
			if ( json.result == 0 ){
				alert( "注册成功\n" +
						json.NickName+"欢迎来到博古云平台！" ) ;
			}else if ( json.result == 1 ) {
				alert( "此账号已经存在！\n请修改账号继续尝试！" ) ;
			}
		});
	} 
}
function Cancle() {
	location.href = "Login.jsp" ;
}
