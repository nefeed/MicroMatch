function adminLogin(){
	var wkid = $("#wkid").val() ;
	var wkkey = $("#wkkey").val() ;
	if( wkid.length == 0 ){
		alert("微课ID不能为空！") ;
	}else if( wkkey.length == 0 ) {
		alert("密码不能为空！") ;
	} else {
		$.getJSON("./servlet/LoginServlet",{
			UserName:wkid,
			UserPassword:wkkey,
		},function(json){
			if( json.result == 0 ){
				if( json.UserType != 0 ) {
					alert("您非管理员用户，请勿继续尝试登录！");
				} else {
					alert( json.NickName+"欢迎回来！" ) ;
					location.href="loading.jsp" ;
				}			
			} else if( json.result == 1 ) {
				alert("密码错误！") ;
			} else if( json.result == 2 ) {
				alert("无此账号！") ;
			}
		});		
	}
}